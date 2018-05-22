package nodebook.service;

import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolBarService {
    private final List<List<String>> buttons = Lists.newArrayList(
            Lists.newArrayList(
                    "add-node",
                    "add-subnode"
            ),
            Lists.newArrayList(
                    "save"
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

    @Autowired
    private RichTextService richTextService;

    public void addButtons(ToolBar toolBar) {
        for (List<String> buttonGroup : buttons) {
            for (String button : buttonGroup) {
                toolBar.getItems().add(createButton(button, getAction(button), button));
            }
            toolBar.getItems().add(new Separator());
        }
    }

    private Button createButton(String styleClass, Runnable action, String toolTip) {
        Button button = new Button();
        button.setPrefWidth(24);
        button.setPrefHeight(24);
        button.getStyleClass().add(styleClass);
        button.setOnAction(evt -> {
            action.run();
//            area.requestFocus();
        });

        if (toolTip != null) {
            button.setTooltip(new Tooltip(toolTip));
        }

        return button;
    }

    private Runnable getAction(String button) {
        switch (button) {
            case "bold":
                return richTextService::toggleBold;
            case "italic":
                return richTextService::toggleItalic;
            case "underline":
                return richTextService::toggleUnderline;
            case "strikethrough":
                return richTextService::toggleStrikethrough;
            case "h1":
                return richTextService::toggleHeader1;
            case "h2":
                return richTextService::toggleHeader2;
            case "h3":
                return richTextService::toggleHeader3;
            case "date":
                return richTextService::addDate;
            case "datetime":
                return richTextService::addDateTime;
            case "separator":
                return richTextService::addSeparator;
            case "bullet-list":
                return richTextService::toggleBulletList;
            case "numbered-list":
                return richTextService::toggleNumberedList;
            case "clear-format":
                return richTextService::clearFormat;
            default:
                return richTextService::toggleBold;
        }
    }
}
