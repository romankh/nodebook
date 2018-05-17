package nodebook.richtext.style;

import javafx.scene.paint.Color;
import org.fxmisc.richtext.model.Codec;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Holds information about the style of a text fragment.
 */
public class TextStyle {
    public static final TextStyle EMPTY = new TextStyle();
    public static final Codec<TextStyle> CODEC = new TextCodec();

    public final Optional<Boolean> bold;
    public final Optional<Boolean> italic;
    public final Optional<Boolean> underline;
    public final Optional<Boolean> strikethrough;
    public final Optional<Integer> fontSize;
    public final Optional<String> fontFamily;
    public final Optional<Color> textColor;
    public final Optional<Color> backgroundColor;

    public Optional<Boolean> header1 = Optional.of(false);
    public Optional<Boolean> header2 = Optional.of(false);
    public Optional<Boolean> header3 = Optional.of(false);

    public TextStyle() {
        this(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );
    }

    public TextStyle(
            Optional<Boolean> bold,
            Optional<Boolean> italic,
            Optional<Boolean> underline,
            Optional<Boolean> strikethrough,
            Optional<Integer> fontSize,
            Optional<String> fontFamily,
            Optional<Color> textColor,
            Optional<Color> backgroundColor) {
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public static TextStyle bold(boolean bold) {
        return EMPTY.updateBold(bold);
    }

    public static TextStyle italic(boolean italic) {
        return EMPTY.updateItalic(italic);
    }

    public static TextStyle underline(boolean underline) {
        return EMPTY.updateUnderline(underline);
    }

    public static TextStyle strikethrough(boolean strikethrough) {
        return EMPTY.updateStrikethrough(strikethrough);
    }

    public static TextStyle fontSize(int fontSize) {
        return EMPTY.updateFontSize(fontSize);
    }

    public static TextStyle fontFamily(String family) {
        return EMPTY.updateFontFamily(family);
    }

    public static TextStyle textColor(Color color) {
        return EMPTY.updateTextColor(color);
    }

    public static TextStyle backgroundColor(Color color) {
        return EMPTY.updateBackgroundColor(color);
    }

    public static TextStyle header1(boolean header1) {
        return EMPTY.updateHeader1(header1);
    }

    public static TextStyle header2(boolean header2) {
        return EMPTY.updateHeader2(header2);
    }

    public static TextStyle header3(boolean header3) {
        return EMPTY.updateHeader3(header3);
    }

    static String cssColor(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        return "rgb(" + red + ", " + green + ", " + blue + ")";
    }

    public TextStyle updateWith(TextStyle mixin) {
        TextStyle newStyle = new TextStyle(
                mixin.bold.isPresent() ? mixin.bold : bold,
                mixin.italic.isPresent() ? mixin.italic : italic,
                mixin.underline.isPresent() ? mixin.underline : underline,
                mixin.strikethrough.isPresent() ? mixin.strikethrough : strikethrough,
                mixin.fontSize.isPresent() ? mixin.fontSize : fontSize,
                mixin.fontFamily.isPresent() ? mixin.fontFamily : fontFamily,
                mixin.textColor.isPresent() ? mixin.textColor : textColor,
                mixin.backgroundColor.isPresent() ? mixin.backgroundColor : backgroundColor);
        newStyle.header1 = mixin.header1;
        newStyle.header2 = mixin.header2;
        newStyle.header3 = mixin.header3;
        return newStyle;
    }

    public TextStyle updateBold(boolean bold) {
        return new TextStyle(Optional.of(bold), italic, underline, strikethrough, fontSize, fontFamily, textColor, backgroundColor);
    }

    public TextStyle updateItalic(boolean italic) {
        return new TextStyle(bold, Optional.of(italic), underline, strikethrough, fontSize, fontFamily, textColor, backgroundColor);
    }

    public TextStyle updateUnderline(boolean underline) {
        return new TextStyle(bold, italic, Optional.of(underline), strikethrough, fontSize, fontFamily, textColor, backgroundColor);
    }

    public TextStyle updateStrikethrough(boolean strikethrough) {
        return new TextStyle(bold, italic, underline, Optional.of(strikethrough), fontSize, fontFamily, textColor, backgroundColor);
    }

    public TextStyle updateFontSize(int fontSize) {
        return new TextStyle(bold, italic, underline, strikethrough, Optional.of(fontSize), fontFamily, textColor, backgroundColor);
    }

    public TextStyle updateFontFamily(String fontFamily) {
        return new TextStyle(bold, italic, underline, strikethrough, fontSize, Optional.of(fontFamily), textColor, backgroundColor);
    }

    public TextStyle updateTextColor(Color textColor) {
        return new TextStyle(bold, italic, underline, strikethrough, fontSize, fontFamily, Optional.of(textColor), backgroundColor);
    }

    public TextStyle updateBackgroundColor(Color backgroundColor) {
        return new TextStyle(bold, italic, underline, strikethrough, fontSize, fontFamily, textColor, Optional.of(backgroundColor));
    }

    public TextStyle updateHeader1(boolean header1) {
        Optional<Integer> newFontSize = header1 ? Optional.of(22) : Optional.of(12);
        TextStyle newStyle = new TextStyle(Optional.of(header1), italic, underline, strikethrough, newFontSize, fontFamily, textColor, backgroundColor);
        newStyle.header1 = Optional.of(header1);
        newStyle.header2 = Optional.of(!header1);
        newStyle.header3 = Optional.of(!header1);
        return newStyle;
    }

    public TextStyle updateHeader2(boolean header2) {
        Optional<Integer> newFontSize = header2 ? Optional.of(18) : Optional.of(12);
        TextStyle newStyle = new TextStyle(Optional.of(header2), italic, underline, strikethrough, newFontSize, fontFamily, textColor, backgroundColor);
        newStyle.header1 = Optional.of(!header2);
        newStyle.header2 = Optional.of(header2);
        newStyle.header3 = Optional.of(!header2);
        return newStyle;
    }

    public TextStyle updateHeader3(boolean header3) {
        Optional<Integer> newFontSize = header3 ? Optional.of(14) : Optional.of(12);
        TextStyle newStyle = new TextStyle(Optional.of(header3), italic, underline, strikethrough, newFontSize, fontFamily, textColor, backgroundColor);
        newStyle.header1 = Optional.of(!header3);
        newStyle.header2 = Optional.of(!header3);
        newStyle.header3 = Optional.of(header3);
        return newStyle;
    }

    public String toCss() {
        StringBuilder sb = new StringBuilder();

        if (bold.isPresent()) {
            if (bold.get()) {
                sb.append("-fx-font-weight: bold;");
            } else {
                sb.append("-fx-font-weight: normal;");
            }
        }

        if (italic.isPresent()) {
            if (italic.get()) {
                sb.append("-fx-font-style: italic;");
            } else {
                sb.append("-fx-font-style: normal;");
            }
        }

        if (underline.isPresent()) {
            if (underline.get()) {
                sb.append("-fx-underline: true;");
            } else {
                sb.append("-fx-underline: false;");
            }
        }

        if (strikethrough.isPresent()) {
            if (strikethrough.get()) {
                sb.append("-fx-strikethrough: true;");
            } else {
                sb.append("-fx-strikethrough: false;");
            }
        }

        fontSize.ifPresent(integer -> sb.append("-fx-font-size: ").append(integer).append("pt;"));
        fontFamily.ifPresent(s -> sb.append("-fx-font-family: ").append(s).append(";"));

        if (textColor.isPresent()) {
            Color color = textColor.get();
            sb.append("-fx-fill: ").append(cssColor(color)).append(";");
        }

        if (backgroundColor.isPresent()) {
            Color color = backgroundColor.get();
            sb.append("-rtfx-background-color: ").append(cssColor(color)).append(";");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TextStyle) {
            TextStyle that = (TextStyle) other;
            return Objects.equals(this.bold, that.bold) &&
                    Objects.equals(this.italic, that.italic) &&
                    Objects.equals(this.underline, that.underline) &&
                    Objects.equals(this.strikethrough, that.strikethrough) &&
                    Objects.equals(this.fontSize, that.fontSize) &&
                    Objects.equals(this.fontFamily, that.fontFamily) &&
                    Objects.equals(this.textColor, that.textColor) &&
                    Objects.equals(this.backgroundColor, that.backgroundColor);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                bold, italic, underline, strikethrough,
                fontSize, fontFamily, textColor, backgroundColor);
    }

    @Override
    public String toString() {
        List<String> styles = new ArrayList<>();

        bold.ifPresent(b -> styles.add(b.toString()));
        italic.ifPresent(i -> styles.add(i.toString()));
        underline.ifPresent(u -> styles.add(u.toString()));
        strikethrough.ifPresent(s -> styles.add(s.toString()));
        fontSize.ifPresent(s -> styles.add(s.toString()));
        fontFamily.ifPresent(f -> styles.add(f));
        textColor.ifPresent(c -> styles.add(c.toString()));
        backgroundColor.ifPresent(b -> styles.add(b.toString()));

        return String.join(",", styles);
    }
}