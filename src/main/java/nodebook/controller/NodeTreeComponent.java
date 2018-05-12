package nodebook.controller;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.stereotype.Component;

@Component
public class NodeTreeComponent {
    public void setTreeNodes(TreeView<String> nodeTreeView) {
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.getChildren().add(new TreeItem<>("Test"));
        rootItem.getChildren().add(new TreeItem<>("Test 2"));
        rootItem.getChildren().add(new TreeItem<>("Test 3"));
        nodeTreeView.setRoot(rootItem);
    }
}
