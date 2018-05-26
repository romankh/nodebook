package nodebook.richtext.codec;

import nodebook.richtext.style.TextStyle;
import org.fxmisc.richtext.model.Codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TextCodec implements Codec<TextStyle> {
    @Override
    public String getName() {
        return "text-style";
    }

    @Override
    public void encode(DataOutputStream os, TextStyle s) throws IOException {
        // nothing to do...
    }

    @Override
    public TextStyle decode(DataInputStream is) throws IOException {
        return TextStyle.builder(12, "Sans").build();
    }
}
