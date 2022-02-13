package piglow;

public enum PiGlowColour {
    RED, ORANGE, YELLOW, GREEN, BLUE, WHITE;

    /**
     * Return a {@link PiGlowColour} from a string value (allowing a bit more flexibility than
     * the enum name).
     */
    public static PiGlowColour ofLabel(String label) {
        for (PiGlowColour piGlowColour : PiGlowColour.values()) {
            if (label.equalsIgnoreCase(piGlowColour.name())) {
                return piGlowColour;
            }
        }
        throw new IllegalArgumentException("No such value for " + label);
    }
}