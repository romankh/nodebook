package nodebook.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToolBarComponent {
    private final String[] buttons = {
            "add-node",
            "add-subnode",
            "save",
            "bold",
            "italic",
            "underline",
            "strikethrough",
            "h1",
            "h2",
            "h3",
            "bullet-list",
            "numbered-list",
    };

    @Autowired
    private RichTextComponent richTextComponent;

    public void addButtons(ToolBar toolBar) {
        for (String button : buttons) {
            toolBar.getItems().add(createButton(button, getAction(button), button));
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
            case "header-1":
                return richTextComponent::toggleHeader1;
            case "header-2":
                return richTextComponent::toggleHeader2;
            case "header-3":
                return richTextComponent::toggleHeader3;
            default:
                return richTextComponent::toggleBold;
        }
    }
}
