package nodebook.service.codec;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import nodebook.ui.richtext.style.TextStyle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextStyleCodecRandomCodingDecodingTest {
    private final int fontSize = 12;
    private final String fontFamily = "Sans";
    private Randomizer<Integer> fontSizeRandomizer = () -> fontSize;
    private Randomizer<String> fontFamilyRandomizer = (Randomizer<String>) () -> fontFamily;
    private TextStyleCodec codec = new TextStyleCodec(fontSize, fontFamily);
    private EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .randomize(FieldDefinitionBuilder.field().named("fontSize").ofType(int.class).get(), fontSizeRandomizer)
            .randomize(FieldDefinitionBuilder.field().named("fontFamily").ofType(String.class).get(), fontFamilyRandomizer)
            .build();

    @Test
    public void encodeDecode_random_shouldResultEqualStyles() throws Exception {
        TextStyle input;
        TextStyle output;

        for (int i = 0; i < 500; i++) {
            input = enhancedRandom.nextObject(TextStyle.class);
            output = codec.decode(codec.encode(input));
            assertThat(output).isEqualTo(input);
        }
    }
}
