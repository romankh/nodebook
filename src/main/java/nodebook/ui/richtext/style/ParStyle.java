package nodebook.ui.richtext.style;

import javafx.scene.text.TextAlignment;

/**
 * Holds information about the style of a paragraph.
 */
public class ParStyle {
    private final TextAlignment alignment;
    private final ColorStyle backgroundColor;
    private final boolean bulletList;
    private final boolean numberedList;

    public ParStyle(StyleBuilder styleBuilder) {
        this.alignment = styleBuilder.alignment;
        this.backgroundColor = styleBuilder.backgroundColor;
        this.bulletList = styleBuilder.bulletList;
        this.numberedList = styleBuilder.numberedList;
    }

    public static ParStyle.StyleBuilder builder() {
        return new ParStyle.StyleBuilder();
    }

    public static ParStyle.StyleBuilder builder(ParStyle parStyle) {
        return new ParStyle.StyleBuilder(parStyle);
    }

    public TextAlignment getAlignment() {
        return alignment;
    }

    public ColorStyle getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isBulletList() {
        return bulletList;
    }

    public boolean isNumberedList() {
        return numberedList;
    }


    public String toCss() {
        StringBuilder sb = new StringBuilder();

        String cssAlignment;
        switch (getAlignment()) {
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
                cssAlignment = "left";
                break;
        }
        sb.append("-fx-text-alignment: ").append(cssAlignment).append(";");

        if (backgroundColor != null) {
            sb.append("-fx-background-color: ").append(StyleUtil.cssColor(getBackgroundColor())).append(";");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParStyle parStyle = (ParStyle) o;

        if (bulletList != parStyle.bulletList) return false;
        if (numberedList != parStyle.numberedList) return false;
        if (alignment != parStyle.alignment) return false;
        return backgroundColor == parStyle.backgroundColor;
    }

    @Override
    public int hashCode() {
        int result = alignment.hashCode();
        result = 31 * result + backgroundColor.hashCode();
        result = 31 * result + (bulletList ? 1 : 0);
        result = 31 * result + (numberedList ? 1 : 0);
        return result;
    }

    public static class StyleBuilder {
        private TextAlignment alignment;
        private ColorStyle backgroundColor;
        private boolean bulletList;
        private boolean numberedList;

        public StyleBuilder() {
            this.alignment = TextAlignment.LEFT;
        }

        public StyleBuilder(ParStyle parStyle) {
            this.alignment = parStyle.alignment;
            this.backgroundColor = parStyle.backgroundColor;
            this.bulletList = parStyle.bulletList;
            this.numberedList = parStyle.numberedList;
        }

        public ParStyle.StyleBuilder alignment(TextAlignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public ParStyle.StyleBuilder backgroundColor(ColorStyle backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public ParStyle.StyleBuilder bulletList(boolean bulletList) {
            this.bulletList = bulletList;
            return this;
        }

        public ParStyle.StyleBuilder numberedList(boolean numberedList) {
            this.numberedList = numberedList;
            return this;
        }

        public ParStyle build() {
            return new ParStyle(this);
        }
    }
}
