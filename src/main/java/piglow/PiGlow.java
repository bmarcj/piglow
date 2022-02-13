package piglow;

import com.pi4j.io.i2c.I2C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * The {@link PiGlow} class depends upon the pi4j library, particulary the {@link I2C} circuit board
 * interface abstraction.
 */
public final class PiGlow implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(PiGlow.class);
    //From datasheet:
    private static final int ENABLE_OUTPUT_ADDR = 0x0;
    private static final int ENABLE_TOP_ARM_ADDR = 0x13;
    private static final int ENABLE_LEFT_ARM_ADDR = 0x14;
    private static final int ENABLE_RIGHT_ARM_ADDR = 0x15;
    private static final int COMMIT_ADDR = 0x16;

    private static final byte COMMIT_CMD = 0x0;
    private static final byte ENABLE_OUTPUT_CMD = 0x1;
    private static final byte ENABLE_ARM_CMD = (byte) 0x3f;

    private final I2C i2C;

    public PiGlow(final I2C i2C) {
        this.i2C = i2C;
    }

    synchronized public void start() {

        log.info("Starting up PiGlow");

        log.info("Enabling output");
        i2C.register(ENABLE_OUTPUT_ADDR).write(ENABLE_OUTPUT_CMD);

        log.info("Enabling arms");
        i2C.register(ENABLE_TOP_ARM_ADDR).write(ENABLE_ARM_CMD);
        i2C.register(ENABLE_LEFT_ARM_ADDR).write(ENABLE_ARM_CMD);
        i2C.register(ENABLE_RIGHT_ARM_ADDR).write(ENABLE_ARM_CMD);

        log.info("Piglow startup complete");
    }

    private void commit() {
        i2C.register(COMMIT_ADDR).write(COMMIT_CMD);
    }

    private void validate(int intensity){
        if (intensity < 0 || intensity > 255){
            throw new IllegalArgumentException("Intensity must be between 0 and 255; value is " + intensity);
        }
    }

    synchronized public void on(Collection<PiGlowLED> leds, int intensity) {
        validate(intensity);
        leds.forEach(led -> i2C.register(led.getAddress()).write(intensity));
        commit();
    }

    synchronized public void off(Collection<PiGlowLED> leds, int intensity) {
        validate(intensity);
        leds.forEach(led -> i2C.register(led.getAddress()).write(intensity));
        commit();
    }

    synchronized public void on(PiGlowArm piGlowArm, int intensity) {
        validate(intensity);
        on(piGlowArm.getLeds(), intensity);
        commit();
    }

    synchronized public void off(PiGlowArm piGlowArm, int intensity) {
        validate(intensity);
        off(piGlowArm.getLeds(), intensity);
        commit();
    }

    synchronized public void allOff() {
        for (PiGlowLED led : PiGlowLED.values()) {
            i2C.register(led.getAddress()).write(0);
        }
        commit();
    }

    @Override
    synchronized public void close() {

        log.info("Shutting down piglow");

        allOff();
        i2C.close();

        log.info("Shutdown compete");
    }
}
