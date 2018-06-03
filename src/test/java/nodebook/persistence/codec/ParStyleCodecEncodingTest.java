package nodebook.persistence.codec;

import javafx.scene.text.TextAlignment;
import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.ParStyle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParStyleCodecEncodingTest {
    private ParStyleCodec codec = new ParStyleCodec();

    @Test
    public void encode_noStyles_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder().build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_alignLeft_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .alignment(TextAlignment.LEFT)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void encode_alignCenter_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .alignment(TextAlignment.CENTER)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void encode_alignRight_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .alignment(TextAlignment.RIGHT)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void encode_alignJustify_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .alignment(TextAlignment.JUSTIFY)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void encode_bulletList_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .bulletList(true)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void encode_numberedList_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .numberedList(true)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(8);
    }

    @Test
    public void encode_backgroundColorBlack_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .backgroundColor(ColorStyle.BLACK)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(65536);
    }

    @Test
    public void encode_backgroundColorOrange1_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .backgroundColor(ColorStyle.ORANGE1)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(983040);
    }

    @Test
    public void encode_backgroundColorOrange2_correctEncoding() throws Exception {
        ParStyle style = ParStyle.builder()
                .alignment(TextAlignment.CENTER)
                .numberedList(true)
                .backgroundColor(ColorStyle.ORANGE1)
                .build();
        int result = codec.encode(style);
        assertThat(result).isEqualTo(983049);
    }
}