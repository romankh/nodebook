package nodebook.persistence;

import org.dizitart.no2.objects.Id;

import java.util.List;

public class Content {
    @Id
    private String id;
    private List<ContentParagraph> paragraphs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ContentParagraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ContentParagraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (!id.equals(content.id)) return false;
        return paragraphs.equals(content.paragraphs);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + paragraphs.hashCode();
        return result;
    }
}
