package nodebook.controller;

import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nodebook.persistence.Page;

import java.util.List;

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

//    public static TreeItem<Page> createTreeItem(String name, int treeLevel) {
//        ImageView imageView = new ImageView(
//                new Image(ControllerUtil.class.getResourceAsStream(getIconName(treeLevel)))
//        );
//
//        Page page = new Page();
//        page.setTitle(name);
//        page.setExpanded(true);
//
//        return new TreeItem<>(page, imageView);
//    }

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

        for (Page subPage : page.getChildren()) {
            item.getChildren().add(createTreeItem(subPage, treeLevel + 1));
        }

        return item;
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
}
