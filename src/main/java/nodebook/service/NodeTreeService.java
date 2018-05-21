package nodebook.service;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Service;

@Service
public class NodeTreeService {
    public void setTreeNodes(TreeView<String> nodeTreeView) {
        TreeItem<String> rootItem = createTreeItem("Root", 0);
        rootItem.getChildren().add(createTreeItem("Test", 1));
        rootItem.getChildren().add(createTreeItem("Test 2", 1));
        rootItem.getChildren().add(createTreeItem("Test 3", 1));
        nodeTreeView.setRoot(rootItem);
    }

    private TreeItem<String> createTreeItem(String name, int treeLevel) {
        ImageView imageView = new ImageView(
                new Image(getClass().getResourceAsStream(getIconName(treeLevel)))
        );

        return new TreeItem<>(name, imageView);
    }

    private String getIconName(int treeLevel) {
        String color = "";
        switch (treeLevel) {
            case 0:
                color = "red";
                break;
            case 1:
                color = "blue";
                break;
            default:
                color = "green";
                break;
        }

        return "/images/dot-" + color + ".png";
    }
}
