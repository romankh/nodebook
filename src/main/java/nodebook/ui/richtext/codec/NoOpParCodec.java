package nodebook.ui.richtext.codec;

import nodebook.ui.richtext.style.ParStyle;
import org.fxmisc.richtext.model.Codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NoOpParCodec implements Codec<ParStyle> {
    @Override
    public String getName() {
        return "par-style";
    }

    @Override
    public void encode(DataOutputStream os, ParStyle parStyle) throws IOException {
        // nothing to do...
    }

    @Override
    public ParStyle decode(DataInputStream is) throws IOException {
        return ParStyle.builder().build();
    }
}
