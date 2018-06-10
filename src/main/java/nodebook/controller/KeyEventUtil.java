package nodebook.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import nodebook.NodebookApplication;

public final class KeyEventUtil {
    private KeyEventUtil() {
        // no instantiation, static methods only.
    }

    public static void initKeyHandlers(NodebookController controller) {
        Stage stage = NodebookApplication.getStage();
        addEventFilter(stage, new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), controller::saveDocument);
        addEventFilter(stage, new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN), controller::addNode);
        addEventFilter(stage, new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
                controller::addSubNode);
    }

    private static void addEventFilter(Stage stage, KeyCombination keyCombination, Runnable action) {
        stage.addEventFilter(
                KeyEvent.KEY_PRESSED,
                getKeyEventHandler(keyCombination, action)
        );
    }

    private static EventHandler<KeyEvent> getKeyEventHandler(KeyCombination keyCombination, Runnable action) {
        return event -> {
            if (keyCombination.match(event)) {
                action.run();
                event.consume();
            }
        };
    }
}
