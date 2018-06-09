package nodebook.controller;

import com.google.common.collect.Lists;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import nodebook.NodebookApplication;
import nodebook.persistence.entities.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class ControllerUtil {

    public static final List<List<String>> toolBarButtons = Lists.newArrayList(
            Lists.newArrayList(
                    "add-node",
                    "add-subnode"
            ),
            Lists.newArrayList(
                    "save"
            ),
            Lists.newArrayList(
                    "font-color",
                    "font-highlight"
            ),
            Lists.newArrayList(
                    "bold",
                    "italic",
                    "underline",
                    "strikethrough"
            ),
            Lists.newArrayList(
                    "h1",
                    "h2",
                    "h3"
            ),
            Lists.newArrayList(
                    "bullet-list",
                    "numbered-list"
            ),
            Lists.newArrayList(
                    "date",
                    "datetime",
                    "separator"
            ),
            Lists.newArrayList(
                    "clear-format"
            )
    );

    private ControllerUtil() {
    }

    public static Runnable getAction(String button, NodebookController controller) {
        switch (button) {
            case "add-node":
                return controller::addNode;
            case "add-subnode":
                return controller::addSubNode;
            case "save":
                return controller::saveDocument;
            case "bold":
                return controller::toggleBold;
            case "italic":
                return controller::toggleItalic;
            case "underline":
                return controller::toggleUnderline;
            case "strikethrough":
                return controller::toggleStrikethrough;
            case "h1":
                return controller::toggleHeader1;
            case "h2":
                return controller::toggleHeader2;
            case "h3":
                return controller::toggleHeader3;
            case "date":
                return controller::addDate;
            case "datetime":
                return controller::addDateTime;
            case "separator":
                return controller::addSeparator;
            case "bullet-list":
                return controller::toggleBulletList;
            case "numbered-list":
                return controller::toggleNumberedList;
            case "clear-format":
                return controller::clearFormat;
            case "font-color":
                return controller::showFontColorPopOver;
            case "font-highlight":
                return controller::showFontBackgroundPopOver;
            default:
                return controller::toggleBold;
        }
    }

    public static Button createButton(String styleClass, Runnable action, String toolTip) {
        Button button = new Button();
        button.setPrefWidth(24);
        button.setPrefHeight(24);
        button.getStyleClass().add(styleClass);
        button.setOnAction(evt -> {
            action.run();
        });

        if (toolTip != null) {
            button.setTooltip(new Tooltip(toolTip));
        }

        return button;
    }

    public static TreeItem<Page> createRootTreeItem(Page rootPage) {
        TreeItem<Page> rootItem = new TreeItem<>(rootPage);

        for (Page subPage : rootPage.getChildren()) {
            rootItem.getChildren().add(createTreeItem(subPage, 0));
        }

        return rootItem;
    }

    public static TreeItem<Page> createTreeItem(Page page, int treeLevel) {
        ImageView icon = new ImageView(
                new Image(ControllerUtil.class.getResourceAsStream(getIconName(treeLevel)))
        );

        TreeItem<Page> item = new TreeItem<>(page, icon);
        item.setExpanded(page.isExpanded());
        // update expanded property of page
        item.expandedProperty().addListener((observable, oldValue, newValue) -> item.getValue().setExpanded(newValue));

        for (Page subPage : page.getChildren()) {
            item.getChildren().add(createTreeItem(subPage, treeLevel + 1));
        }

        return item;
    }

    public static int getNodeLevel(TreeItem<Page> page) {
        if (page == null || page.getValue().getId().equals("4 8 15 16 23 42")) {
            return -1;
        }
        int result = 0;
        TreeItem<Page> parent = page.getParent();
        while (parent != null && !parent.getValue().getId().equals("4 8 15 16 23 42")) {
            result++;
            parent = page.getParent();
        }
        return result;
    }

    public static TextInputDialog getAddTreeNodeDialog() {
        return getTreeNodeDialog("Add node", "Add node on same level as selected", "Name: ", null);
    }

    public static TextInputDialog getChangeTreeNodeDialog(String currentValue) {
        return getTreeNodeDialog("Edit node", "Edit node name", "Name: ", currentValue);
    }

    public static void addTreeNode(TreeItem<Page> parent) {
        TextInputDialog dialog = ControllerUtil.getAddTreeNodeDialog();
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().length() > 0) {
            Page page = new Page();
            page.setId(UUID.randomUUID().toString());
            page.setTitle(result.get());
            page.setExpanded(false);

            Page parentPage = parent.getValue();
            parentPage.getChildren().add(page);

            TreeItem<Page> treeItem = ControllerUtil.createTreeItem(page, ControllerUtil.getNodeLevel(parent) + 1);
            parent.getChildren().add(treeItem);


            TreeItem.TreeModificationEvent<Page> event = new TreeItem.TreeModificationEvent<>(
                    TreeItem.valueChangedEvent(), parent);
            Event.fireEvent(parent, event);
        }
    }

    private static String getIconName(int treeLevel) {
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

    private static TextInputDialog getTreeNodeDialog(String title, String headerText, String contentText,
                                                     String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.initOwner(NodebookApplication.getStage());
        dialog.initStyle(StageStyle.UNIFIED);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle(title);
        dialog.getDialogPane().setGraphic(null);
        dialog.getDialogPane().setHeaderText(headerText);
        dialog.getDialogPane().setContentText(contentText);
        return dialog;
    }
}
