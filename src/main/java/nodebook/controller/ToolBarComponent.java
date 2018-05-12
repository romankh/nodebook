package nodebook.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
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
            "strike-through",
            "header-1",
            "header-2",
            "header-3",
    };

    public void addButtons(ToolBar toolBar) {
        for (String button : buttons) {
            toolBar.getItems().add(createButton(button, null, ""));
        }
    }


    private Button createButton(String styleClass, Runnable action, String toolTip) {
        Button button = new Button();

        button.setPrefWidth(25);
        button.setPrefHeight(25);

        button.getStyleClass().add(styleClass);
//        button.setOnAction(evt -> {
//            action.run();
//            area.requestFocus();
//        });

        if (toolTip != null) {
            button.setTooltip(new Tooltip(toolTip));
        }

        return button;
    }
}
