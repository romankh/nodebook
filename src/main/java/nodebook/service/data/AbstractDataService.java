package nodebook.service.data;

import nodebook.persistence.entities.Page;
import nodebook.service.DataService;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.fxmisc.richtext.model.StyledDocument;
import org.reactfx.util.Either;

public abstract class AbstractDataService implements DataService {
    final String rootPageId = "4 8 15 16 23 42";

    @Override
    public Page createEmptyRootPage() {
        Page rootPage = new Page();
        rootPage.setId(rootPageId);
        rootPage.setTitle("root-page");
        rootPage.setExpanded(true);
        return rootPage;
    }

    public abstract Page getRootPage();

    public abstract void saveRootPage(Page page);

    public abstract StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getContent(String pageId);

    public abstract void saveContent(String pageId, StyledDocument<ParStyle, Either<String, LinkedImage>,
            TextStyle> content);
}
