//package gui;
//
//import javafx.event.EventHandler;
//import javafx.scene.control.Label;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import sprites.GameField;
//import games.Invaders;
//import games.Snake;
//
//public class GamesList {
//    private static VBox menu;
//    private static Label[] games;
//    private static int currentMenuPos;
//
//    private static String[] menuStrUnselected = {" 1. Snake", " 2. Space Invaders", " 3. Pocket Tanks", " 4. Arkanoid"};
//    private static String[] menuStrSelected = {"> 1. Snake", "> 2. Space Invaders", "> 3. Pocket Tanks", "> 4. Arkanoid"};
//
//    public static void initialize()
//    {
//        menu = new VBox();
//        games = new Label[4];
//        currentMenuPos = 0;
//
//        games[0] = new Label(menuStrSelected[0]);
//        games[1] = new Label(menuStrUnselected[1]);
//        games[2] = new Label(menuStrUnselected[2]);
//        games[3] = new Label(menuStrUnselected[3]);
//
//        for(Label label : games) {
//            label.setFont(Font.font(24));
//            label.setTextFill(Color.CYAN);
//        }
//
//        menu.getChildren().addAll(games);
//
//        menu.setOnKeyPressed(new EventHandler<KeyEvent>() {         // main Menu
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode().equals(KeyCode.DOWN) && menu.isFocused()) {
//                    GamesList.changeGame(0);
//                }
//
//                if (event.getCode().equals(KeyCode.UP) && menu.isFocused()) {
//                    GamesList.changeGame(1);
//                }
//
//                if (event.getCode().equals(KeyCode.ENTER) && menu.isFocused()) {
//                    //root.getChildren().remove(0);
//                    BrickGame.getRoot().getChildren().set(0, GameField.getGamePole());
//                    GamesList.changeGame(2);
//                }
//            }
//        });
//    }
//
//    public static VBox getMenu()
//    {
//        return menu;
//    }
//
//    private static void changeGame(int mode)
//    {
//        switch (mode){
//            case 0:
//                if(currentMenuPos != 3) {
//                    games[currentMenuPos].setText(menuStrUnselected[currentMenuPos]);
//                    currentMenuPos++;
//                    games[currentMenuPos].setText(menuStrSelected[currentMenuPos]);
//                }
//                else
//                {
//                    games[currentMenuPos].setText(menuStrUnselected[currentMenuPos]);
//                    currentMenuPos = 0;
//                    games[currentMenuPos].setText(menuStrSelected[currentMenuPos]);
//                }
//                System.out.println("Select");
//                break;
//
//            case 1:
//                if(currentMenuPos != 0) {
//                    games[currentMenuPos].setText(menuStrUnselected[currentMenuPos]);
//                    currentMenuPos--;
//                    games[currentMenuPos].setText(menuStrSelected[currentMenuPos]);
//                }
//                else
//                {
//                    games[currentMenuPos].setText(menuStrUnselected[currentMenuPos]);
//                    currentMenuPos = 3;
//                    games[currentMenuPos].setText(menuStrSelected[currentMenuPos]);
//                }
//                System.out.println("Select");
//                break;
//
//            case 2:
//                System.out.println("Enter");
//
//                switch (currentMenuPos)
//                {
//                    case 0:
//                        Snake snake = new Snake();
//                        snake.start();
//                        break;
//
//                    case 1:
//                        // Space Invaders
//                        Invaders invaders = new Invaders();
//                        invaders.start();
//                        break;
//
//                    case 2:
//                        // Pocket Tank
//                        break;
//
//                    case 3:
//                        // Arkanoid
//                        break;
//                }
//                break;
//        }
//    }
//}
