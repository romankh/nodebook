package nodebook.richtext.style;

import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.fxmisc.richtext.model.Codec;

import java.util.Objects;
import java.util.Optional;

import static javafx.scene.text.TextAlignment.*;

/**
 * Holds information about the style of a paragraph.
 */
public class ParStyle {

    public static final ParStyle EMPTY = new ParStyle();
    public static final Codec<ParStyle> CODEC = new ParCodec();
    public final Optional<TextAlignment> alignment;
    public final Optional<Color> backgroundColor;

    public ParStyle() {
        this(Optional.empty(), Optional.empty());
    }

    public ParStyle(Optional<TextAlignment> alignment, Optional<Color> backgroundColor) {
        this.alignment = alignment;
        this.backgroundColor = backgroundColor;
    }

    public static ParStyle alignLeft() {
        return EMPTY.updateAlignment(LEFT);
    }

    public static ParStyle alignCenter() {
        return EMPTY.updateAlignment(CENTER);
    }

    public static ParStyle alignRight() {
        return EMPTY.updateAlignment(RIGHT);
    }

    public static ParStyle alignJustify() {
        return EMPTY.updateAlignment(JUSTIFY);
    }

    public static ParStyle backgroundColor(Color color) {
        return EMPTY.updateBackgroundColor(color);
    }

    public String toCss() {
        StringBuilder sb = new StringBuilder();

        alignment.ifPresent(al -> {
            String cssAlignment;
            switch (al) {
                case LEFT:
                    cssAlignment = "left";
                    break;
                case CENTER:
                    cssAlignment = "center";
                    break;
                case RIGHT:
                    cssAlignment = "right";
                    break;
                case JUSTIFY:
                    cssAlignment = "justify";
                    break;
                default:
                    throw new AssertionError("unreachable code");
            }
            sb.append("-fx-text-alignment: " + cssAlignment + ";");
        });

        backgroundColor.ifPresent(color -> {
            sb.append("-fx-background-color: " + TextStyle.cssColor(color) + ";");
        });

        return sb.toString();
    }

    public ParStyle updateWith(ParStyle mixin) {
        return new ParStyle(
                mixin.alignment.isPresent() ? mixin.alignment : alignment,
                mixin.backgroundColor.isPresent() ? mixin.backgroundColor : backgroundColor);
    }

    public ParStyle updateAlignment(TextAlignment alignment) {
        return new ParStyle(Optional.of(alignment), backgroundColor);
    }

    public ParStyle updateBackgroundColor(Color backgroundColor) {
        return new ParStyle(alignment, Optional.of(backgroundColor));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ParStyle) {
            ParStyle that = (ParStyle) other;
            return Objects.equals(this.alignment, that.alignment) &&
                    Objects.equals(this.backgroundColor, that.backgroundColor);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(alignment, backgroundColor);
    }

    @Override
    public String toString() {
        return toCss();
    }
}
