package nodebook.persistence.codec;

import nodebook.ui.richtext.style.TextHeader;
import nodebook.ui.richtext.style.TextStyle;

public class TextStyleCodec implements Codec<TextStyle> {
    private final int fontSize;
    private final String fontFamily;
    /*
        Encoding Schema:
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |     7    |     6    |     5    |     4    |     3    |     2    |     1    |     0    |
       | reserved | reserved | reserved | reserved |  strike  |   under  |  italic  |    bold  |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    15    |    14    |    13    |    12    |    11    |    10    |     9    |     8    |
       | reserved | reserved |        header       | reserved | reserved | reserved | reserved |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    23    |    22    |    21    |    20    |    19    |    18    |    17    |    16    |
       |                                        fontColor                                      |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    31    |    30    |    29    |    28    |    27    |    26    |    25    |    24    |
       |                                     backgroundColor                                   |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
    */

    public TextStyleCodec(int fontSize, String fontFamily) {
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
    }

    @Override
    public int encode(TextStyle style) {
        int value = 0;
        if (style.isBold()) {
            value = 1;
        }
        if (style.isItalic()) {
            value |= CodecUtil.leftShift(1, 1);
        }
        if (style.isUnderline()) {
            value |= CodecUtil.leftShift(1, 2);
        }
        if (style.isStrikethrough()) {
            value |= CodecUtil.leftShift(1, 3);
        }

        if (style.getHeader().equals(TextHeader.H1)) {
            value |= CodecUtil.leftShift(1, 12);
        } else if (style.getHeader().equals(TextHeader.H2)) {
            value |= CodecUtil.leftShift(2, 12);
        } else if (style.getHeader().equals(TextHeader.H3)) {
            value |= CodecUtil.leftShift(3, 12);
        }

        if (style.getFontColor() != null) {
            value |= CodecUtil.leftShift(style.getFontColor().getOrdinal(), 16);
        }
        if (style.getBackgroundColor() != null) {
            value |= CodecUtil.leftShift(style.getBackgroundColor().getOrdinal(), 24);
        }

        return value;
    }

    @Override
    public TextStyle decode(Integer style) {
        // Todo: assert number >= 0
        return TextStyle.builder(fontSize, fontFamily)
                .bold(CodecUtil.evaluateBooleanBit(style, 0))
                .italic(CodecUtil.evaluateBooleanBit(style, 1))
                .underline(CodecUtil.evaluateBooleanBit(style, 2))
                .strikethrough(CodecUtil.evaluateBooleanBit(style, 3))
                .header(CodecUtil.evaluateHeaderBits(style, 12))
                .fontColor(CodecUtil.evaluateColorStyleBits(style, 16))
                .backgroundColor(CodecUtil.evaluateColorStyleBits(style, 24))
                .build();
    }
}
