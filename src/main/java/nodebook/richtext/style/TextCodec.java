package nodebook.richtext.style;

import javafx.scene.paint.Color;
import org.fxmisc.richtext.model.Codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Optional;

public class TextCodec implements Codec<TextStyle> {
    private final Codec<Optional<String>> OPT_STRING_CODEC = Codec.optionalCodec(Codec.STRING_CODEC);
    private final Codec<Optional<Color>> OPT_COLOR_CODEC = Codec.optionalCodec(Codec.COLOR_CODEC);

    @Override
    public String getName() {
        return "text-style";
    }

    @Override
    public void encode(DataOutputStream os, TextStyle s)
            throws IOException {
        os.writeByte(encodeBoldItalicUnderlineStrikethrough(s));
        os.writeInt(encodeOptionalUint(s.fontSize));
        OPT_STRING_CODEC.encode(os, s.fontFamily);
        OPT_COLOR_CODEC.encode(os, s.fontColor);
        OPT_COLOR_CODEC.encode(os, s.backgroundColor);
    }

    @Override
    public TextStyle decode(DataInputStream is) throws IOException {
        byte bius = is.readByte();
        Optional<Integer> fontSize = decodeOptionalUint(is.readInt());
        Optional<String> fontFamily = OPT_STRING_CODEC.decode(is);
        Optional<Color> textColor = OPT_COLOR_CODEC.decode(is);
        Optional<Color> bgrColor = OPT_COLOR_CODEC.decode(is);
        return new TextStyle(
                bold(bius), italic(bius), underline(bius), strikethrough(bius),
                fontSize, fontFamily, textColor, bgrColor);
    }

    private int encodeBoldItalicUnderlineStrikethrough(TextStyle s) {
        return encodeOptionalBoolean(s.bold) << 6 |
                encodeOptionalBoolean(s.italic) << 4 |
                encodeOptionalBoolean(s.underline) << 2 |
                encodeOptionalBoolean(s.strikethrough);
    }

    private Optional<Boolean> bold(byte bius) throws IOException {
        return decodeOptionalBoolean((bius >> 6) & 3);
    }

    private Optional<Boolean> italic(byte bius) throws IOException {
        return decodeOptionalBoolean((bius >> 4) & 3);
    }

    private Optional<Boolean> underline(byte bius) throws IOException {
        return decodeOptionalBoolean((bius >> 2) & 3);
    }

    private Optional<Boolean> strikethrough(byte bius) throws IOException {
        return decodeOptionalBoolean((bius >> 0) & 3);
    }

    private int encodeOptionalBoolean(Optional<Boolean> ob) {
        return ob.map(b -> 2 + (b ? 1 : 0)).orElse(0);
    }

    private Optional<Boolean> decodeOptionalBoolean(int i) throws IOException {
        switch (i) {
            case 0:
                return Optional.empty();
            case 2:
                return Optional.of(false);
            case 3:
                return Optional.of(true);
        }
        throw new MalformedInputException(0);
    }

    private int encodeOptionalUint(Optional<Integer> oi) {
        return oi.orElse(-1);
    }

    private Optional<Integer> decodeOptionalUint(int i) {
        return (i < 0) ? Optional.empty() : Optional.of(i);
    }
}
