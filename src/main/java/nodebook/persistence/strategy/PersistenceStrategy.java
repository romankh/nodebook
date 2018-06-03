package nodebook.persistence.strategy;

import nodebook.persistence.entities.Content;
import nodebook.persistence.entities.Page;

import java.util.Optional;

public interface PersistenceStrategy {

    Page getPageTree();

    void savePageTree(Page rootPage);

    Optional<Content> getContent(String pageId);

    void saveContent(Content content);
}
