package nodebook.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
    @JsonProperty("i")
    private String id;
    @JsonProperty("t")
    private String title;
    @JsonProperty("x")
    private Boolean expanded;
    @JsonProperty("c")
    private List<Page> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public List<Page> getChildren() {
        return children;
    }

    public void setChildren(List<Page> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (!id.equals(page.id)) return false;
        if (!title.equals(page.title)) return false;
        if (!expanded.equals(page.expanded)) return false;
        return children.equals(page.children);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + expanded.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }
}
