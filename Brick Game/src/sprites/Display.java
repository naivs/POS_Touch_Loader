//package sprites;
//
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//public class Display {
//    static VBox screen;
//    private static Label scores;
//    private static Label level;
//    private static Label status;
//    private static Label guide;
//
//    public static void initialize()
//    {
//        screen = new VBox();
//
//        scores = new Label("");
//        scores.setTextFill(Color.YELLOW);
//        scores.setFont(Font.font(20));
//
//        level = new Label("");
//        level.setTextFill(Color.CYAN);
//        level.setFont(Font.font(20));
//
//        status = new Label("");
//        status.setTextFill(Color.RED);
//        status.setFont(Font.font(25));
//
//        guide = new Label("");
//        guide.setTextFill(Color.CYAN);
//
//        screen.getChildren().add(scores);
//        screen.getChildren().add(level);
//        screen.getChildren().add(status);
//        screen.getChildren().add(guide);
//    }
//
//    public static void clear()
//    {
//        scores.setText("");
//        level.setText("");
//        status.setText("");
//        guide.setText("");
//
//        for(Brick brick : GameField.getPole())
//            brick.brickOff();
//    }
//
//    public static VBox getDisplay()
//    {
//        return screen;
//    }
//
//    public static void setScores(int scores)
//    {
//        Display.scores.setText("Scores: " + String.valueOf(scores));
//    }
//
//    public static void setLevel(int level)
//    {
//        Display.level.setText(String.valueOf("Level\n  " + level));
//
//        Display.guide.setText("   CONTROLS\nDirection: arrows\nTurbo Boost: enter\nPause: P\nQuit: Q");
//    }
//
//    public static void setStatus(String status)
//    {
//        Display.status.setText(status);
//    }
//}
