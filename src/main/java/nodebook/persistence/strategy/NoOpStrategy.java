package nodebook.persistence.strategy;

import nodebook.persistence.entities.Content;
import nodebook.persistence.entities.Page;

import java.util.Optional;

public class NoOpStrategy implements PersistenceStrategy {
    @Override
    public Page getPageTree() {
        throw new UnsupportedOperationException("No-Op-Strategy, data is not persisted yet");
    }

    @Override
    public void savePageTree(Page rootPage) {
        // no op
    }

    @Override
    public Optional<Content> getContent(String pageId) {
        return Optional.empty();
    }

    @Override
    public void saveContent(Content content) {
        // no op
    }
}
