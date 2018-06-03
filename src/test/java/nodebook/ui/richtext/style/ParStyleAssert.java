package nodebook.ui.richtext.style;

import javafx.scene.text.TextAlignment;
import org.assertj.core.api.AbstractAssert;

public class ParStyleAssert extends AbstractAssert<ParStyleAssert, ParStyle> {

    public ParStyleAssert(ParStyle actual) {
        super(actual, ParStyleAssert.class);
    }

    public static ParStyleAssert assertThat(ParStyle actual) {
        return new ParStyleAssert(actual);
    }

    public ParStyleAssert isNotNull() {
        return super.isNotNull();
    }

    public ParStyleAssert hasAlignment(TextAlignment alignment) {
        isNotNull();
        if (!actual.getAlignment().equals(alignment)) {
            failWithMessage("Expected style to have alignment %s but was %s",
                    alignment.name(), actual.getAlignment().name());
        }
        return this;
    }

    public ParStyleAssert isBulletList() {
        isNotNull();
        if (!actual.isBulletList()) {
            failWithMessage("Expected style to be bulletList");
        }
        return this;
    }

    public ParStyleAssert isNotBulletList() {
        isNotNull();
        if (actual.isBulletList()) {
            failWithMessage("Expected style to be not bulletList");
        }
        return this;
    }

    public ParStyleAssert isNumberedList() {
        isNotNull();
        if (!actual.isNumberedList()) {
            failWithMessage("Expected style to be numberedList");
        }
        return this;
    }

    public ParStyleAssert isNotNumberedList() {
        isNotNull();
        if (actual.isNumberedList()) {
            failWithMessage("Expected style to be not numberedList");
        }
        return this;
    }

    public ParStyleAssert hasBackgroundColor(ColorStyle color) {
        isNotNull();
        if (!actual.getBackgroundColor().equals(color)) {
            failWithMessage("Expected style to have background color %s but was %s",
                    color.name(), actual.getBackgroundColor().name());
        }
        return this;
    }
}
