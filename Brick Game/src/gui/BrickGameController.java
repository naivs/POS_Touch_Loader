package gui;

import games.GameController;
import games.Invaders;
import games.Snake;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sprites.GameField;

/**
 * FXML Controller class
 *
 * @author ivannaux
 */
public class BrickGameController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private AnchorPane mainScene;
    @FXML
    private ChoiceBox gamesChoiseBox;
    @FXML
    private Label scores;
    @FXML
    private Label level;
    @FXML
    private Label controlls;

    private GameField gameField;
    private GameController game;
    private Timeline engine;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameField = new GameField(this);
        mainScene.getChildren().add(gameField);
        engine = new Timeline(
                new KeyFrame(Duration.millis(200), (ActionEvent ae) -> {
                    scores.setText(game.pulse());
                    //System.out.println("tick");
                })
        );
        engine.setCycleCount(Integer.MAX_VALUE);
        gamesChoiseBox.setItems(FXCollections.observableArrayList("-select game-", "Snake", "Invaders", "Tanks"));
        gamesChoiseBox.setValue("-select game-");

        startButton.setOnAction((ActionEvent evt) -> {
            if (gamesChoiseBox.getValue().equals("-select game-") || gamesChoiseBox.getValue().equals("Tanks")) {
                System.out.println("please select a game...");
            } else {
                game.start();
                engine.play();
                System.out.println("service starting...");
            }
        });

        stopButton.setOnAction((ActionEvent evt) -> {
            game.stop();
            engine.stop();
            gamesChoiseBox.setValue("-select game-");
            System.out.println("service stopping...");
        });

        mainScene.setOnKeyPressed((KeyEvent evt) -> {
            game.keyPressed(evt);
            //System.out.println("key " + evt.getCode() + " pressed...");
        });

        gamesChoiseBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                if (gamesChoiseBox.getValue().equals("Snake")) {
                    game = new Snake(gameField);
                    System.out.println("snake chosen...");
                } else if (gamesChoiseBox.getValue().equals("Invaders")) {
                    game = new Invaders(gameField);
                    System.out.println("invaders chosen...");
                } else if (gamesChoiseBox.getValue().equals("Tanks")) {
                    //game = new Tanks(gameField);
                    System.out.println("tanks chosen...");
                }
            }
        });
    }
}
