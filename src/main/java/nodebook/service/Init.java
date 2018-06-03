package nodebook.service;

import nodebook.persistence.entities.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Init implements ApplicationListener<ApplicationReadyEvent> {
    private final DataService dataService;

    @Autowired
    public Init(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        boolean doIt = false;
//        doIt = true;


        if (doIt) {
            Page rootPage = dataService.createEmptyRootPage();

            for (int i = 0; i < 3; i++) {
                Page page = createPage("page-" + String.valueOf(i));

                for (int j = 0; j < 3; j++) {
                    Page subPage = createPage("sub-page-lvl1-" + String.valueOf(j));

                    for (int k = 0; k < 2; k++) {
                        Page subsubPage = createPage("sub-page-lvl2-" + String.valueOf(k));
                        subPage.getChildren().add(subsubPage);
                    }
                    page.getChildren().add(subPage);
                }
                rootPage.getChildren().add(page);
            }

            dataService.saveRootPage(rootPage);

        }
    }

    private Page createPage(String title) {
        Page page = new Page();
        page.setId(UUID.randomUUID().toString());
        page.setExpanded(true);
        page.setTitle(title);
        return page;
    }
}
