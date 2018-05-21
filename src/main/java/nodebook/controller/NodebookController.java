package nodebook.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import nodebook.service.NodeTreeService;
import nodebook.service.RichTextService;
import nodebook.service.ToolBarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class NodebookController implements Initializable {
    private final NodeTreeService nodeTreeService;
    private final ToolBarService toolBarService;
    private final RichTextService richTextService;
    @FXML
    private TreeView<String> nodeTreeView;
    @FXML
    private ToolBar toolBar;
    @FXML
    private AnchorPane richtextPane;

    @Autowired
    public NodebookController(NodeTreeService nodeTreeService,
                              ToolBarService toolBarService,
                              RichTextService richTextService) {
        this.nodeTreeService = nodeTreeService;
        this.toolBarService = toolBarService;
        this.richTextService = richTextService;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeTreeService.setTreeNodes(nodeTreeView);
        toolBarService.addButtons(toolBar);
        richTextService.initalize(richtextPane);
    }
}
