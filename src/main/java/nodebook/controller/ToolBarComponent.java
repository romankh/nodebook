package nodebook.controller;

import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToolBarComponent {
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
            )
    );

    @Autowired
    private RichTextComponent richTextComponent;

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
                return richTextComponent::toggleBold;
            case "italic":
                return richTextComponent::toggleItalic;
            case "underline":
                return richTextComponent::toggleUnderline;
            case "strikethrough":
                return richTextComponent::toggleStrikethrough;
            case "h1":
                return richTextComponent::toggleHeader1;
            case "h2":
                return richTextComponent::toggleHeader2;
            case "h3":
                return richTextComponent::toggleHeader3;
            default:
                return richTextComponent::toggleBold;
        }
    }
}
