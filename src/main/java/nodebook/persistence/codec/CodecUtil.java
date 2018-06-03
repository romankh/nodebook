package nodebook.persistence.codec;

import javafx.scene.text.TextAlignment;
import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.TextHeader;

public final class CodecUtil {
    private CodecUtil() {
        // no instantiation, use static methods.
    }

    public static int leftShift(int number, int offset) {
        return number << offset;
    }

    public static int rightShift(int number, int offset) {
        return number >>> offset;
    }

    public static boolean evaluateBooleanBit(int style, int offset) {
        int shifted = rightShift(style, offset);
        return (shifted & 1) == 1;
    }

    public static TextHeader evaluateHeaderBits(int style, int offset) {
        int shifted = CodecUtil.rightShift(style, offset);
        int ordinal = (shifted & 3);
        switch (ordinal) {
            case 1:
                return TextHeader.H1;
            case 2:
                return TextHeader.H2;
            case 3:
                return TextHeader.H3;
            default:
                return TextHeader.NONE;
        }
    }

    public static ColorStyle evaluateColorStyleBits(int style, int offset) {
        int shifted = CodecUtil.rightShift(style, offset);
        int ordinal = (shifted & 255);

        return ColorStyle.valueOf(ordinal);
    }

    public static TextAlignment evaluateAlignmentBits(int style, int offset) {
        int shifted = CodecUtil.rightShift(style, offset);
        int ordinal = (shifted & 3);
        switch (ordinal) {
            case 1:
                return TextAlignment.CENTER;
            case 2:
                return TextAlignment.RIGHT;
            case 3:
                return TextAlignment.JUSTIFY;
            case 0:
            default:
                return TextAlignment.LEFT;
        }
    }
}
