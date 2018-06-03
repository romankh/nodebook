package nodebook.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ContentParagraph implements Serializable {
    @JsonProperty("y")
    private Integer style;
    @JsonProperty("s")
    private List<ContentSegment> segments;

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public List<ContentSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<ContentSegment> segments) {
        this.segments = segments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentParagraph that = (ContentParagraph) o;

        if (!style.equals(that.style)) return false;
        return segments.equals(that.segments);
    }

    @Override
    public int hashCode() {
        int result = style.hashCode();
        result = 31 * result + segments.hashCode();
        return result;
    }
}
