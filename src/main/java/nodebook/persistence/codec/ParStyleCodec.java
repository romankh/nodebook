package nodebook.persistence.codec;

import nodebook.ui.richtext.style.ParStyle;

public class ParStyleCodec implements Codec<ParStyle> {
    /*
        Encoding Schema:
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |     7    |     6    |     5    |     4    |     3    |     2    |     1    |     0    |
       | reserved | reserved | reserved | reserved | numbered |  bullet  |      alignment      |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    15    |    14    |    13    |    12    |    11    |    10    |     9    |     8    |
       | reserved | reserved | reserved | reserved | reserved | reserved | reserved | reserved |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    23    |    22    |    21    |    20    |    19    |    18    |    17    |    16    |
       |                                     backgroundColor                                   |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
       |    31    |    30    |    29    |    28    |    27    |    26    |    25    |    24    |
       | reserved | reserved | reserved | reserved | reserved | reserved | reserved | reserved |
       –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
    */
    @Override
    public int encode(ParStyle style) {
        int value = 0;
        switch (style.getAlignment()) {
            case CENTER:
                value = 1;
                break;
            case RIGHT:
                value = 2;
                break;
            case JUSTIFY:
                value = 3;
                break;
            case LEFT:
            default:
                break;
        }

        if (style.isBulletList()) {
            value |= CodecUtil.leftShift(1, 2);
        }
        if (style.isNumberedList()) {
            value |= CodecUtil.leftShift(1, 3);
        }

        if (style.getBackgroundColor() != null) {
            value |= CodecUtil.leftShift(style.getBackgroundColor().getOrdinal(), 16);
        }

        return value;
    }

    @Override
    public ParStyle decode(Integer style) {
        return ParStyle.builder()
                .alignment(CodecUtil.evaluateAlignmentBits(style, 0))
                .bulletList(CodecUtil.evaluateBooleanBit(style, 2))
                .numberedList(CodecUtil.evaluateBooleanBit(style, 3))
                .backgroundColor(CodecUtil.evaluateColorStyleBits(style, 16))
                .build();
    }
}
