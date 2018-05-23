package nodebook.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.PopOver;

public class ColorSelectionPopOver extends PopOver {
    private final Color[] colors = {
            Color.TRANSPARENT, Color.BLACK, Color.DARKGRAY, Color.GRAY, Color.LIGHTGRAY, Color.WHITE,
            Color.DARKBLUE, Color.BLUE, Color.LIGHTBLUE,
            Color.DARKGREEN, Color.GREEN, Color.LIGHTGREEN,
            Color.DARKRED, Color.RED, Color.valueOf("ff7b7b"),
            Color.ORANGERED, Color.ORANGE, Color.YELLOW
    };
    private final int NUMBER_OF_COLUMNS = 6;
    private final ReadOnlyObjectWrapper<Color> selectedColor = new ReadOnlyObjectWrapper<>();
    private final String title;

    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public ColorSelectionPopOver(String title) {
        this.title = title;
        initialize();
    }

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return onAction;
    }

    public final EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }

    public final void setOnAction(EventHandler<ActionEvent> value) {
        onActionProperty().set(value);
    }

    public Color getSelectedColor() {
        return selectedColor.get();
    }

    private void initialize() {
        this.setDetachable(false);
        this.setDetached(false);
        this.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        this.setAnimated(true);
        this.setTitle(title);
        this.setHeaderAlwaysVisible(true);
        this.setCloseButtonEnabled(false);
        this.getStyleClass().add("color-selector");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        int row = 0;
        for (int i = 0; i < colors.length; i++) {
            final Button button = new Button();
            button.setPrefSize(25, 25);

            final Color color = colors[i];

            final Tooltip tooltip = new Tooltip(colors[i].toString());
            final Rectangle rectangle = new Rectangle(25, 25, color);
            rectangle.setStroke(Color.BLACK);
            button.setTooltip(tooltip);
            button.setGraphic(rectangle);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedColor.set(color);
                    hide();
                }
            });

            gridPane.add(button, i % NUMBER_OF_COLUMNS, row);

            if (i % NUMBER_OF_COLUMNS == NUMBER_OF_COLUMNS - 1) {
                row++;
            }
        }
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(gridPane);
        anchorPane.setPrefSize(250d, 130d);
        anchorPane.setMinSize(250d, 130d);

        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);

        this.setContentNode(anchorPane);
    }
}
