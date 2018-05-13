package nodebook.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class NodebookController implements Initializable {
    private final NodeTreeComponent nodeTreeComponent;
    private final ToolBarComponent toolBarComponent;
    private final RichTextComponent richTextComponent;
    @FXML
    private TreeView<String> nodeTreeView;
    @FXML
    private ToolBar toolBar;
    @FXML
    private AnchorPane richtextPane;

    @Autowired
    public NodebookController(NodeTreeComponent nodeTreeComponent,
                              ToolBarComponent toolBarComponent,
                              RichTextComponent richTextComponent) {
        this.nodeTreeComponent = nodeTreeComponent;
        this.toolBarComponent = toolBarComponent;
        this.richTextComponent = richTextComponent;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeTreeComponent.setTreeNodes(nodeTreeView);
        toolBarComponent.addButtons(toolBar);
        richTextComponent.initalize(richtextPane);
    }
}
