package nodebook.persistence.session;

import nodebook.persistence.entities.Content;

import java.util.Optional;

public class SessionCacheEntry {
    private boolean changed;
    private Content content;

    public SessionCacheEntry(Content content) {
        this.content = content;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Optional<Content> getContent() {
        return Optional.ofNullable(content);
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionCacheEntry)) return false;

        SessionCacheEntry that = (SessionCacheEntry) o;

        if (changed != that.changed) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = (changed ? 1 : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
