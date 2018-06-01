package nodebook.service;

import nodebook.persistence.Content;
import nodebook.persistence.Page;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {
    private final String rootPageId = "4 8 15 16 23 42";
    private Nitrite db = Nitrite.builder()
            .compressed()
            .filePath("/tmp/test.db")
            .openOrCreate("user", "password");
    private ObjectRepository<Page> pageRepository = db.getRepository(Page.class);

    public Page createEmptyRootPage() {
        Page rootPage = new Page();
        rootPage.setId(rootPageId);
        rootPage.setTitle("root-page");
        rootPage.setExpanded(true);
        return rootPage;
    }

    public Page getRootPage() {
        Cursor<Page> cursor = pageRepository.find(ObjectFilters.eq("id", rootPageId));
        return cursor.firstOrDefault();
    }

    public Page saveRootPage(Page page) {
        pageRepository.update(page, true);
        return page;
    }

    public Optional<Content> getContent(String pageId, Content content) {
        return Optional.of(content);
    }

    public Content saveContent(String pageId, Content content) {
        return content;
    }
}
