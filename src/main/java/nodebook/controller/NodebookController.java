package nodebook.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class NodebookController implements Initializable {
    @FXML
    private TreeView<String> nodeTreeView;
    @FXML
    private ToolBar toolBar;
    private final NodeTreeComponent nodeTreeComponent;
    private final ToolBarComponent toolBarComponent;

    @Autowired
    public NodebookController(NodeTreeComponent nodeTreeComponent, ToolBarComponent toolBarComponent) {
        this.nodeTreeComponent = nodeTreeComponent;
        this.toolBarComponent = toolBarComponent;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeTreeComponent.setTreeNodes(nodeTreeView);
        toolBarComponent.addButtons(toolBar);
    }
}
