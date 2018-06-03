package nodebook.service.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import nodebook.persistence.entities.Page;
import nodebook.persistence.strategy.GZIPFileStrategy;
import nodebook.persistence.strategy.PersistenceStrategy;
import nodebook.ui.richtext.RichtextUtil;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.fxmisc.richtext.model.StyledDocument;
import org.reactfx.util.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyDataService extends AbstractDataService {
    private final String rootPath = "/home/roman/work/nodebook-data/";
    private final ObjectMapper objectMapper;
    private PersistenceStrategy strategy;

    @Autowired
    public StrategyDataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.strategy = new GZIPFileStrategy(rootPath, "test", objectMapper);
    }

    @Override
    public Page getRootPage() {
        return strategy.getPageTree();
    }

    @Override
    public void saveRootPage(Page page) {
        strategy.savePageTree(page);
    }

    @Override
    public StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getContent(String pageId) {
        return strategy.getContent(pageId)
                .map(RichtextUtil::mapToStyledDocument).orElseGet(RichtextUtil::getEmptyDocument);
    }

    @Override
    public void saveContent(String pageId, StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> content) {
        strategy.saveContent(RichtextUtil.mapToContent(content, pageId));
    }
}
