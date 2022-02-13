package piglow;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A full enumeration of all arms on the piglow board, defined by their address and colour.
 * (Each address on the board is unique.)
 */
public enum PiGlowLED {

    LED_1(1, PiGlowColour.RED),
    LED_2(2, PiGlowColour.ORANGE),
    LED_3(3, PiGlowColour.YELLOW),
    LED_4(4, PiGlowColour.GREEN),
    LED_5(5, PiGlowColour.BLUE),
    LED_6(6, PiGlowColour.GREEN),
    LED_7(7, PiGlowColour.RED),
    LED_8(8, PiGlowColour.ORANGE),
    LED_9(9, PiGlowColour.YELLOW),
    LED_10(10, PiGlowColour.WHITE),
    LED_11(11, PiGlowColour.WHITE),
    LED_12(12, PiGlowColour.BLUE),
    LED_13(13, PiGlowColour.WHITE),
    LED_14(14, PiGlowColour.GREEN),
    LED_15(15, PiGlowColour.BLUE),
    LED_16(16, PiGlowColour.YELLOW),
    LED_17(17, PiGlowColour.ORANGE),
    LED_18(18, PiGlowColour.RED);

    private final PiGlowColour colour;
    private final int address;

    PiGlowLED(int address, PiGlowColour colour) {
        this.colour = colour;
        this.address = address;
    }

    /**
     * Returns the led based on the int address of the led.
     */
    public static PiGlowLED ofAddress(int address) {

        for (PiGlowLED piGlowLED : PiGlowLED.values()) {
            if (piGlowLED.getAddress() == address) {
                return piGlowLED;
            }
        }
        throw new IllegalArgumentException("No such led:" + address);
    }

    /**
     * Return all leds matching the given colour.
     */
    public static Collection<PiGlowLED> ofColour(PiGlowColour colour) {

        return Arrays.stream(PiGlowLED.values())
                .filter(led -> led.colour.equals(colour))
                .collect(Collectors.toList());
    }

    public PiGlowColour getColour() {
        return colour;
    }

    public int getAddress() {
        return address;
    }
}
