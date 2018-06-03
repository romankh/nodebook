package nodebook.persistence.codec;

import javafx.scene.text.TextAlignment;
import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.ParStyleAssert;
import org.junit.Test;

public class ParStyleCodecDecodingTest {
    private ParStyleCodec codec = new ParStyleCodec();

    @Test
    public void decode_noStyles_correctDecoding() throws Exception {
        int style = 0;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.LEFT)
                .isNotBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_alignmentCenter_correctDecoding() throws Exception {
        int style = 1;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.CENTER)
                .isNotBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_alignmentRight_correctDecoding() throws Exception {
        int style = 2;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.RIGHT)
                .isNotBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_alignmentJustify_correctDecoding() throws Exception {
        int style = 3;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.JUSTIFY)
                .isNotBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_bulletList_correctDecoding() throws Exception {
        int style = 4;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.LEFT)
                .isBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_numberedList_correctDecoding() throws Exception {
        int style = 8;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.LEFT)
                .isNotBulletList()
                .isNumberedList()
                .hasBackgroundColor(ColorStyle.NONE);
    }

    @Test
    public void decode_backgroundColorYellow_correctDecoding() throws Exception {
        int style = 1114112;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.LEFT)
                .isNotBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.YELLOW);
    }

    @Test
    public void decode_alignJustifyBulletListBackgroundRed1_correctDecoding() throws Exception {
        int style = 786439;

        ParStyle result = codec.decode(style);
        ParStyleAssert.assertThat(result)
                .isNotNull()
                .hasAlignment(TextAlignment.JUSTIFY)
                .isBulletList()
                .isNotNumberedList()
                .hasBackgroundColor(ColorStyle.RED1);
    }
}