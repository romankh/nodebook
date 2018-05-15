package nodebook.richtext.style;

import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.fxmisc.richtext.model.Codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

public class ParCodec implements Codec<ParStyle> {
    private final Codec<Optional<TextAlignment>> OPT_ALIGNMENT_CODEC = Codec.optionalCodec(
            Codec.enumCodec(TextAlignment.class)
    );
    private final Codec<Optional<Color>> OPT_COLOR_CODEC = Codec.optionalCodec(Codec.COLOR_CODEC);

    @Override
    public String getName() {
        return "par-style";
    }

    @Override
    public void encode(DataOutputStream os, ParStyle parStyle) throws IOException {
        OPT_ALIGNMENT_CODEC.encode(os, parStyle.alignment);
        OPT_COLOR_CODEC.encode(os, parStyle.backgroundColor);
    }

    @Override
    public ParStyle decode(DataInputStream is) throws IOException {
        return new ParStyle(
                OPT_ALIGNMENT_CODEC.decode(is),
                OPT_COLOR_CODEC.decode(is));
    }
}
