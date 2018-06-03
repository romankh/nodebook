package nodebook.ui.richtext.style;

public class TextStyle {
    private final int fontSize;
    private final String fontFamily;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final boolean strikethrough;
    private final ColorStyle fontColor;
    private final ColorStyle backgroundColor;
    private final TextHeader header;

    public TextStyle(StyleBuilder styleBuilder) {
        this.fontSize = styleBuilder.fontSize;
        this.fontFamily = styleBuilder.fontFamily;
        this.bold = styleBuilder.bold;
        this.italic = styleBuilder.italic;
        this.underline = styleBuilder.underline;
        this.strikethrough = styleBuilder.strikethrough;
        this.fontColor = styleBuilder.fontColor;
        this.backgroundColor = styleBuilder.backgroundColor;
        this.header = styleBuilder.header;
    }

    public static StyleBuilder builder(int fontSize, String fontFamily) {
        return new StyleBuilder(fontSize, fontFamily);
    }

    public static StyleBuilder builder(TextStyle textStyle) {
        return new StyleBuilder(textStyle);
    }

    public int getFontSize() {
        int modifier = 0;
        switch (this.header) {
            case H1:
                modifier = 10;
                break;
            case H2:
                modifier = 6;
                break;
            case H3:
                modifier = 2;
                break;
            default:
                break;
        }
        return fontSize + modifier;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public ColorStyle getFontColor() {
        return fontColor;
    }

    public ColorStyle getBackgroundColor() {
        return backgroundColor;
    }

    public TextHeader getHeader() {
        return header;
    }

    public String toCss() {
        StringBuilder sb = new StringBuilder();
        if (bold || !header.equals(TextHeader.NONE)) {
            sb.append("-fx-font-weight: bold;");
        } else {
            sb.append("-fx-font-weight: normal;");
        }

        if (italic) {
            sb.append("-fx-font-style: italic;");
        } else {
            sb.append("-fx-font-style: normal;");
        }

        if (underline) {
            sb.append("-fx-underline: true;");
        } else {
            sb.append("-fx-underline: false;");
        }

        if (strikethrough) {
            sb.append("-fx-strikethrough: true;");
        } else {
            sb.append("-fx-strikethrough: false;");
        }

        sb.append("-fx-font-size: ").append(getFontSize()).append("pt;");
        sb.append("-fx-font-family: ").append(getFontFamily()).append(";");

        if (fontColor != ColorStyle.NONE) {
            sb.append("-fx-fill: ").append(StyleUtil.cssColor(getFontColor())).append(";");
        }

        if (backgroundColor != ColorStyle.NONE) {
            sb.append("-rtfx-background-color: ").append(StyleUtil.cssColor(getBackgroundColor())).append(";");
        }

        return sb.toString();
    }

    public static class StyleBuilder {
        private final int fontSize;
        private final String fontFamily;
        private boolean bold;
        private boolean italic;
        private boolean underline;
        private boolean strikethrough;
        private ColorStyle fontColor;
        private ColorStyle backgroundColor;
        private TextHeader header;

        public StyleBuilder(int fontSize, String fontFamily) {
            this.fontSize = fontSize;
            this.fontFamily = fontFamily;
            this.header = TextHeader.NONE;
            this.fontColor = ColorStyle.NONE;
            this.backgroundColor = ColorStyle.NONE;
        }

        public StyleBuilder(TextStyle textStyle) {
            this.fontSize = textStyle.fontSize;
            this.fontFamily = textStyle.fontFamily;
            this.bold = textStyle.bold;
            this.italic = textStyle.italic;
            this.underline = textStyle.underline;
            this.strikethrough = textStyle.strikethrough;
            this.fontColor = textStyle.fontColor;
            this.backgroundColor = textStyle.backgroundColor;
            this.header = textStyle.header;
        }

        public StyleBuilder bold(boolean bold) {
            this.bold = bold;
            return this;
        }

        public StyleBuilder italic(boolean italic) {
            this.italic = italic;
            return this;
        }

        public StyleBuilder underline(boolean underline) {
            this.underline = underline;
            return this;
        }

        public StyleBuilder strikethrough(boolean strikethrough) {
            this.strikethrough = strikethrough;
            return this;
        }

        public StyleBuilder fontColor(ColorStyle fontColor) {
            this.fontColor = fontColor;
            return this;
        }

        public StyleBuilder backgroundColor(ColorStyle backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public StyleBuilder header(TextHeader header) {
            this.header = header;
            return this;
        }

        public TextStyle build() {
            return new TextStyle(this);
        }
    }
}
