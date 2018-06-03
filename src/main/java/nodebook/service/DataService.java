package nodebook.service;

import nodebook.persistence.entities.Page;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.fxmisc.richtext.model.StyledDocument;
import org.reactfx.util.Either;

public interface DataService {
    Page createEmptyRootPage();

    Page getRootPage();

    void saveRootPage(Page page);

    StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getContent(String pageId);

    void saveContent(String pageId, StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> content);
}
