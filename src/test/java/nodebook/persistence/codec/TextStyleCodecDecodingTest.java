package nodebook.persistence.codec;

import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.TextHeader;
import nodebook.ui.richtext.style.TextStyle;
import nodebook.ui.richtext.style.TextStyleAssert;
import org.junit.Test;

public class TextStyleCodecDecodingTest {

    private TextStyleCodec codec = new TextStyleCodec(12, "Sans");

    @Test
    public void decode_noStyles_correctDecoding() throws Exception {
        int style = 0;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_bold_correctDecoding() throws Exception {
        int style = 1;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_italic_correctDecoding() throws Exception {
        int style = 2;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_underline_correctDecoding() throws Exception {
        int style = 4;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_strikethrough_correctDecoding() throws Exception {
        int style = 8;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_boldItalic_correctDecoding() throws Exception {
        int style = 3;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isBold()
                .isItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_boldUnderline_correctDecoding() throws Exception {
        int style = 5;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isBold()
                .isNotItalic()
                .isUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_boldItalicUnderlineStrikethrough_correctDecoding() throws Exception {
        int style = 15;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isBold()
                .isItalic()
                .isUnderline()
                .isStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_headerNone_correctDecoding() throws Exception {
        int style = 0;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_header1_correctDecoding() throws Exception {
        int style = 4096;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.H1)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_header2_correctDecoding() throws Exception {
        int style = 8192;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.H2)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_header3_correctDecoding() throws Exception {
        int style = 12288;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.H3)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_boldHeader2_correctDecoding() throws Exception {
        int style = 8193;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.H2)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_underlineHeader1_correctDecoding() throws Exception {
        int style = 4100;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.H1)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_fontColorBlack_correctDecoding() throws Exception {
        int style = 65536;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.BLACK)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_fontColorGray1_correctDecoding() throws Exception {
        int style = 196608;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.GRAY2)
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_backgroundColorRed3_correctDecoding() throws Exception {
        int style = 234881024;

        TextStyle result = codec.decode(style);
        TextStyleAssert.assertThat(result)
                .isNotNull()
                .isNotBold()
                .isNotItalic()
                .isNotUnderline()
                .isNotStrikethrough()
                .hasHeader(TextHeader.NONE)
                .hasFontColor(ColorStyle.NONE)
                .hasBackgroundColor(ColorStyle.RED3);
    }

}