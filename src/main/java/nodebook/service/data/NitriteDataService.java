package nodebook.service.data;

import nodebook.persistence.entities.Content;
import nodebook.persistence.entities.Page;
import nodebook.ui.richtext.RichtextUtil;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fxmisc.richtext.model.StyledDocument;
import org.reactfx.util.Either;

import javax.annotation.PreDestroy;

//@Service
public class NitriteDataService extends AbstractDataService {
    private Nitrite db = Nitrite.builder()
            .compressed()
            .filePath("/home/roman/work/nodebook/test.db")
            .openOrCreate("user", "password");
    private ObjectRepository<Page> pageRepository = db.getRepository(Page.class);
    private ObjectRepository<Content> contentRepository = db.getRepository(Content.class);

    @PreDestroy
    public void shutDown() {
        if (!db.isClosed()) {
            db.commit();
            db.compact();
            db.close();
        }
    }

    @Override
    public Page getRootPage() {
        Cursor<Page> cursor = pageRepository.find(ObjectFilters.eq("id", rootPageId));
        return cursor.firstOrDefault();
    }

    @Override
    public void saveRootPage(Page page) {
        pageRepository.update(page, true);
    }

    @Override
    public StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getContent(String pageId) {
        Cursor<Content> cursor = contentRepository.find(ObjectFilters.eq("id", pageId));
        Content content = cursor.firstOrDefault();
        if (content != null) {
            return RichtextUtil.mapToStyledDocument(content);
        }

        return RichtextUtil.getEmptyDocument();
    }

    @Override
    public void saveContent(String pageId, StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> content) {
        contentRepository.update(RichtextUtil.mapToContent(content, pageId), true);
    }
}
