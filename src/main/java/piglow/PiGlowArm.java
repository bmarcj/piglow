package piglow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static piglow.PiGlowLED.*;

/**
 * The piglow has three 'arms', each with six leds (in an order). This enumerates the
 * possible arms.
 */
public enum PiGlowArm {

    TOP(List.of(LED_10, LED_5, LED_6, LED_9, LED_8, LED_7)),
    RIGHT(List.of(LED_11, LED_12, LED_14, LED_16, LED_17, LED_18)),
    LEFT(List.of(LED_13, LED_15, LED_4, LED_3, LED_2, LED_1));

    private final List<PiGlowLED> leds;

    PiGlowArm(List<PiGlowLED> leds) {
        this.leds = leds;
    }

    /**
     * Return a {@link PiGlowArm} from a string value (allowing a bit more flexibility than
     * the enum name).
     */
    public static PiGlowArm ofLabel(String label) {
        for (PiGlowArm piGlowArm : PiGlowArm.values()) {
            if (label.equalsIgnoreCase(piGlowArm.name())) {
                return piGlowArm;
            }
        }
        throw new IllegalArgumentException("No such value for " + label);
    }

    /**
     * Return all leds held in an arm.
     */
    public List<PiGlowLED> getLeds() {
        return leds;
    }

    /**
     * Returns all the leds at a given position within an arm.
     */
    public static Collection<PiGlowLED> getPosition(int position) {

        ArrayList<PiGlowLED> leds = new ArrayList<>();
        for (PiGlowArm arm : PiGlowArm.values()) {
            leds.add(arm.getLeds().get(position));
        }
        return leds;
    }
}
