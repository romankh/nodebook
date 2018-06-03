package nodebook.service.codec;

import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.TextHeader;
import nodebook.ui.richtext.style.TextStyle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextStyleCodecEncodingTest {
    private final int fontSize = 12;
    private final String fontFamily = "Sans";
    private TextStyleCodec codec = new TextStyleCodec(fontSize, fontFamily);


    @Test
    public void encode_noStyles_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily).build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_bold_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .bold(true).build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void encode_italic_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .italic(true).build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void encode_underline_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .underline(true).build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void encode_strikethrough_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .strikethrough(true).build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(8);
    }

    @Test
    public void encode_boldStrikethrough_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .bold(true)
                .strikethrough(true)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(9);
    }

    @Test
    public void encode_italicUnderline_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .italic(true)
                .underline(true)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void encode_boldItalicUnderlineStrikethrough_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .bold(true)
                .italic(true)
                .underline(true)
                .strikethrough(true)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(15);
    }

    @Test
    public void encode_headerNone_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .header(TextHeader.NONE)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_header1_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .header(TextHeader.H1)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(4096);
    }

    @Test
    public void encode_header2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .header(TextHeader.H2)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(8192);
    }

    @Test
    public void encode_header3_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .header(TextHeader.H3)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(12288);
    }

    @Test
    public void encode_fontColorNone_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_fontColorBlack_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.BLACK)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(65536);
    }

    @Test
    public void encode_fontColorGray1_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.GRAY1)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(131072);
    }

    @Test
    public void encode_fontColorGray3_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.GRAY3)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(262144);
    }

    @Test
    public void encode_fontColorBlue2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.BLUE2)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(458752);
    }

    @Test
    public void encode_fontColorGreen3_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.GREEN3)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(720896);
    }

    @Test
    public void encode_fontColorRed2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.RED2)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(851968);
    }

    @Test
    public void encode_fontColorYellow_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .fontColor(ColorStyle.YELLOW)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(1114112);
    }

    @Test
    public void encode_backgroundColorNone_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_backgroundColorGray2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .backgroundColor(ColorStyle.GRAY2)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(50331648);
    }

    @Test
    public void encode_backgroundColorGreen2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .backgroundColor(ColorStyle.GREEN2)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(167772160);
    }

    @Test
    public void encode_multipleSettings1_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .bold(true)
                .underline(true)
                .header(TextHeader.H1)
                .backgroundColor(ColorStyle.YELLOW)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(285216773);
    }

    @Test
    public void encode_multipleSettings2_correctEncoding() throws Exception {
        TextStyle style = TextStyle.builder(fontSize, fontFamily)
                .bold(true)
                .strikethrough(true)
                .header(TextHeader.H3)
                .fontColor(ColorStyle.RED1)
                .backgroundColor(ColorStyle.WHITE)
                .build();

        int result = codec.encode(style);
        assertThat(result).isEqualTo(84684809);
    }
}