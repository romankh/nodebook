package nodebook.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContentSegment implements Serializable {
    @JsonProperty("y")
    private Integer style;
    @JsonProperty("t")
    private String text;

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentSegment that = (ContentSegment) o;

        if (!style.equals(that.style)) return false;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = style.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
