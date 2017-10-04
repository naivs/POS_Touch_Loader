package sprites;

import gui.BrickGameController;
import javafx.geometry.VPos;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class GameField extends FlowPane {

    private final Brick[] field;

    public GameField(BrickGameController controller) {
        setHgap(1.0);
        setVgap(1.0);
        setLayoutX(6.0);
        setLayoutY(6.0);
        setPrefHeight(341.0);
        setPrefWidth(171.0);
        setRowValignment(VPos.TOP);
        field = new Brick[200];

        for (int i = 0; i < 200; i++) {
            field[i] = new Brick();
            super.getChildren().add(field[i]);
        }
    }

    public void brickOn(int brick) {
        field[brick - 1].on(Brick.DEFAULT_ON);
    }

    public void brickOn(int brick, Color color) {
        field[brick - 1].on(color);
    }

    public void brickOff(int brick) {
        field[brick - 1].off();
    }

    public Color getBrickColor(int brick) {
        return field[brick - 1].getColor();
    }

    public boolean brickIsOff(int brick) {
        return field[brick - 1].getColor().equals(Brick.OFF);
    }

    public void clear() {
        for (Brick brick : field) {
            brick.off();
        }
    }
}
