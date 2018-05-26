package nodebook;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import nodebook.ui.AppSplashScreen;
import nodebook.view.NodebookView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NodebookApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(NodebookApplication.class, NodebookView.class, new AppSplashScreen(), args);
    }
}
