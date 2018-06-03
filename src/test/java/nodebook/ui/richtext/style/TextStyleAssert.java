package nodebook.ui.richtext.style;

import org.assertj.core.api.AbstractAssert;

public class TextStyleAssert extends AbstractAssert<TextStyleAssert, TextStyle> {

    public TextStyleAssert(TextStyle actual) {
        super(actual, TextStyleAssert.class);
    }

    public static TextStyleAssert assertThat(TextStyle actual) {
        return new TextStyleAssert(actual);
    }

    public TextStyleAssert isNotNull() {
        return super.isNotNull();
    }

    public TextStyleAssert isBold() {
        isNotNull();
        if (!actual.isBold()) {
            failWithMessage("Expected style to be bold");
        }
        return this;
    }

    public TextStyleAssert isNotBold() {
        isNotNull();
        if (actual.isBold()) {
            failWithMessage("Expected style to be not bold");
        }
        return this;
    }

    public TextStyleAssert isItalic() {
        isNotNull();
        if (!actual.isItalic()) {
            failWithMessage("Expected style to be italic");
        }
        return this;
    }

    public TextStyleAssert isNotItalic() {
        isNotNull();
        if (actual.isItalic()) {
            failWithMessage("Expected style to be not italic");
        }
        return this;
    }

    public TextStyleAssert isUnderline() {
        isNotNull();
        if (!actual.isUnderline()) {
            failWithMessage("Expected style to be underline");
        }
        return this;
    }

    public TextStyleAssert isNotUnderline() {
        isNotNull();
        if (actual.isUnderline()) {
            failWithMessage("Expected style to be not underline");
        }
        return this;
    }

    public TextStyleAssert isStrikethrough() {
        isNotNull();
        if (!actual.isStrikethrough()) {
            failWithMessage("Expected style to be strikethrough");
        }
        return this;
    }

    public TextStyleAssert isNotStrikethrough() {
        isNotNull();
        if (actual.isStrikethrough()) {
            failWithMessage("Expected style to be not strikethrough");
        }
        return this;
    }

    public TextStyleAssert hasHeader(TextHeader header) {
        isNotNull();
        if (!actual.getHeader().equals(header)) {
            failWithMessage("Expected style to have header %s but was %s",
                    header.name(), actual.getHeader().name());
        }
        return this;
    }

    public TextStyleAssert hasFontColor(ColorStyle color) {
        isNotNull();
        if (!actual.getFontColor().equals(color)) {
            failWithMessage("Expected style to have font color %s but was %s",
                    color.name(), actual.getFontColor().name());
        }
        return this;
    }

    public TextStyleAssert hasBackgroundColor(ColorStyle color) {
        isNotNull();
        if (!actual.getBackgroundColor().equals(color)) {
            failWithMessage("Expected style to have background color %s but was %s",
                    color.name(), actual.getBackgroundColor().name());
        }
        return this;
    }
}
