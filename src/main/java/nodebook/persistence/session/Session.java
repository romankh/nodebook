package nodebook.persistence.session;

import nodebook.persistence.entities.Page;
import nodebook.persistence.strategy.GZIPFileStrategy;
import nodebook.persistence.strategy.NoOpStrategy;
import nodebook.persistence.strategy.PersistenceStrategy;
import nodebook.ui.richtext.RichtextUtil;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.fxmisc.richtext.model.StyledDocument;
import org.reactfx.util.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private static final Logger LOGGER = LoggerFactory.getLogger(Session.class);
    private String id;
    private String dbRootPath;
    private String dbName;
    private boolean dataChanged;
    private Page rootPage;
    private Map<String, SessionCacheEntry> contentCache = new ConcurrentHashMap<>();
    private String currentPage;
    private PersistenceStrategy strategy;

    public Session() {
        this.id = UUID.randomUUID().toString();
        this.strategy = new NoOpStrategy();
    }

    public Session(String dbRootPath, String dbName) {
        this.id = UUID.randomUUID().toString();
        this.dbRootPath = dbRootPath;
        this.dbName = dbName;
        this.strategy = new GZIPFileStrategy(dbRootPath, dbName);
        this.rootPage = this.strategy.getPageTree();
    }

    public String getId() {
        return id;
    }

    public Optional<String> getDbRootPath() {
        return Optional.ofNullable(dbRootPath);
    }

    public Optional<String> getDbName() {
        return Optional.ofNullable(dbName);
    }

    public void setDb(String dbRootPath, String dbName) {
        this.dbRootPath = dbRootPath;
        this.dbName = dbName;
        if (this.strategy == null || this.strategy instanceof NoOpStrategy) {
            this.strategy = new GZIPFileStrategy(dbRootPath, dbName);
        }
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public void dataChanged() {
        this.dataChanged = true;
    }

    public Page getRootPage() {
        return rootPage;
    }

    public void setRootPage(Page rootPage) {
        if (!this.rootPage.equals(rootPage)) {
            this.rootPage = rootPage;
            this.dataChanged();
        }
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getContent(String pageId) {
        LOGGER.debug("get content {}", pageId);
        this.currentPage = pageId;
        if (!contentCache.containsKey(pageId)) {
            LOGGER.debug("Update cache from file system");
            contentCache.put(pageId, new SessionCacheEntry(strategy.getContent(pageId).orElse(null)));
        }

        return contentCache.get(pageId).getContent()
                .map(RichtextUtil::mapToStyledDocument).orElseGet(RichtextUtil::getEmptyDocument);
    }

    public void updateContent(String pageId,
                              StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> content) {
        LOGGER.debug("Updating changed content for pageId {}", pageId);
        this.contentCache.get(pageId).setChanged(true);
        this.contentCache.get(pageId).setContent(RichtextUtil.mapToContent(content, pageId));
        this.dataChanged();
    }

    public void save() {
        strategy.savePageTree(rootPage);
        for (Map.Entry<String, SessionCacheEntry> entry : contentCache.entrySet()) {
            SessionCacheEntry cacheEntry = entry.getValue();
            if (cacheEntry.isChanged() && cacheEntry.getContent().isPresent()) {
                this.strategy.saveContent(cacheEntry.getContent().get());
                cacheEntry.setChanged(false);
            }
        }
        dataChanged = false;
    }
}
