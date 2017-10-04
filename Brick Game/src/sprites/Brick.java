package sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Pane {

    protected static final Color DEFAULT_ON = Color.CYAN;
    protected static final Color OFF = Color.gray(0.05);
    private final Rectangle rectOutter;
    private final Rectangle rectInner;

    protected Brick() {
        rectOutter = new Rectangle(0, 0, 14, 14);
        rectOutter.setStroke(OFF);
        rectOutter.setStrokeWidth(2);

        rectInner = new Rectangle(3.5, 3.5, 7, 7);
        rectInner.setFill(OFF);

        super.getChildren().add(rectOutter);
        super.getChildren().add(rectInner);
    }

    protected void on(Color color) {
        rectOutter.setStroke(color);
        rectInner.setFill(color);
    }

    protected void off() {
        rectOutter.setStroke(OFF);
        rectInner.setFill(OFF);
    }

    protected Color getColor() {
        return (Color) rectInner.getFill();
    }
}
