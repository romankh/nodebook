package nodebook;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NodebookApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(NodebookApplication.class, NodebookView.class, args);
    }
}
