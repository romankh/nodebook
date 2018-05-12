package nodebook.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class NodebookController implements Initializable {
    @FXML
    private TreeView<String> nodeTreeView;
    @FXML
    private Button dodelButton;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.getChildren().add(new TreeItem<>("Test"));
        rootItem.getChildren().add(new TreeItem<>("Test 2"));
        rootItem.getChildren().add(new TreeItem<>("Test 3"));

        nodeTreeView.setRoot(rootItem);
        dodelButton.setText("Dodel...");
    }
}
