package nodebook.ui.richtext.style;

import java.util.HashMap;
import java.util.Map;

public enum ColorStyle {
    NONE(0, "None", 0.0, 0.0, 0.0, 0.0),
    BLACK(1, "Black", 0.0, 0.0, 0.0, 1.0),
    GRAY1(2, "Dark Gray", 0.6627451181411743, 0.6627451181411743, 0.6627451181411743, 1.0),
    GRAY2(3, "Gray", 0.501960813999176, 0.501960813999176, 0.501960813999176, 1.0),
    GRAY3(4, "Light Gray", 0.8274509906768799, 0.8274509906768799, 0.8274509906768799, 1.0),
    WHITE(5, "White", 1.0, 1.0, 1.0, 1.0),
    BLUE1(6, "Dark Blue", 0.0, 0.0, 0.545098066329956, 1.0),
    BLUE2(7, "Blue", 0.0, 0.0, 1.0, 1.0),
    BLUE3(8, "Light Blue", 0.6784313917160034, 0.8470588326454163, 0.9019607901573181, 1.0),
    GREEN1(9, "Dark Green", 0.0, 0.3921568691730499, 0.0, 1.0),
    GREEN2(10, "Green", 0.0, 0.501960813999176, 0.0, 1.0),
    GREEN3(11, "Light Green", 0.5647059082984924, 0.9333333373069763, 0.5647059082984924, 1.0),
    RED1(12, "Dark Red", 0.545098066329956, 0.0, 0.0, 1.0),
    RED2(13, "Red", 1.0, 0.0, 0.0, 1.0),
    RED3(14, "Light Red", 1.0, 0.48235294222831726, 0.48235294222831726, 1.0),
    ORANGE1(15, "Dark Orange", 1.0, 0.2705882489681244, 0.0, 1.0),
    ORANGE2(16, "Orange", 1.0, 0.6470588445663452, 0.0, 1.0),
    YELLOW(17, "Yellow", 1.0, 1.0, 0.0, 1.0);

    private static final Map<Integer, ColorStyle> valueMap = new HashMap<>();

    static {
        for (ColorStyle style : ColorStyle.values()) {
            valueMap.put(style.ordinal, style);
        }
    }

    private final int ordinal;
    private final String text;
    private final double red;
    private final double green;
    private final double blue;
    private final double opacity;

    ColorStyle(final int ordinal, final String text,
               final double red, final double green, final double blue, final double opacity) {
        this.ordinal = ordinal;
        this.text = text;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public static ColorStyle valueOf(int ordinal) {
        return valueMap.get(ordinal);
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public String getText() {
        return text;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }

    @Override
    public String toString() {
        return text;
    }
}
