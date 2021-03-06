package nodebook.ui.richtext.style;

import javafx.scene.paint.Color;

public class StyleUtil {
    private StyleUtil() {
        // no instantiation
    }

    public static String cssColor(ColorStyle colorStyle) {
        Color color = new Color(colorStyle.getRed(), colorStyle.getGreen(),
                colorStyle.getBlue(), colorStyle.getOpacity());
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        return "rgb(" + red + ", " + green + ", " + blue + ")";
    }
}
