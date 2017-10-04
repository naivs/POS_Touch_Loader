package games;

import javafx.scene.input.KeyEvent;
import sprites.GameField;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Created by ivannaux on 05.04.2016.
 */
public class Snake implements GameController {

    private final GameField gameField;
    private int[] mapLevel;
    private ArrayList<Integer> snake; // worm.get(0) - head
    private int direction;
    private boolean canMove;
    private boolean engineLock;
    //private String status;
    private final String controlls = "CONTROLLS\nUP, DOWN, LEFT, RIGHT - direction;\nENTER - boost;\nP - pause."; // [0] - level, [1] - scores, [2] - status, [3] - controlls
    private String gameStatus = STOP;
    private int level = 0;
    private int scores = 0;

    // Move directions
    private static final int UP = 0;
    private static final int DOWN = 3;
    private static final int LEFT = 2;
    private static final int RIGHT = 1;

    // Status constants
    private static final String PLAY = "PLAY";
    private static final String STOP = "STOP";
    private static final String PAUSE = "PAUSE";
    private static final String GAME_OVER = "GAME OVER";
    private static final String WIN = "WIN!!!";

    public Snake(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void start() {
        snake = new ArrayList<>();
        snake.add(65);
        snake.add(75);
        snake.add(85);
        generateLevel();
        direction = UP;
        canMove = true;
        show();
        generateFood();
        gameStatus = PLAY;
        engineLock = false;
    }

    private boolean brickIsFood(int brick) {
        return gameField.getBrickColor(brick).equals(Color.LIGHTGREEN);
    }

    public void move() {
        try {
            canMove = false; // change direction is Locked

            if ((scores / 100) / (level + 1) == 20) { // to next level
                engineLock = true;

                if (level < 4) {
                    level++;
                    restart();
                    return;
                } else { // победа, если 4 уровня пройдены
                    gameStatus = WIN;
                    return;
                }
            }

            switch (direction) {
                case 0:
                    // up
                    if (gameField.brickIsOff(snake.get(0) - 10)) {
                        this.erase();
                        snake.add(0, snake.get(0) - 10);
                        snake.remove(snake.size() - 1);
                    } else if (brickIsFood(snake.get(0) - 10)) {
                        this.generateFood();
                        this.erase();
                        snake.add(0, snake.get(0) - 10);
                    } else {
                        gameOver();//stop(1); //врезались в стену или в себя
                    }

                    this.show();
                    break;

                case 1:
                    // right
                    if (gameField.brickIsOff(snake.get(0) + 1) && (snake.get(0) + 1) % 10 != 1) {
                        this.erase();
                        snake.add(0, snake.get(0) + 1);
                        snake.remove(snake.size() - 1);
                    } else if (brickIsFood(snake.get(0) + 1)) {
                        this.generateFood();
                        this.erase();

//                        if ((snake.get(0) + 1) % 10 == 0) {
//                            gameOver();//stop(1);
//                        }
                        snake.add(0, snake.get(0) + 1);
                    } else {
                        gameOver();//stop(1);
                    }

                    this.show();
                    break;

                case 2:
                    // left
                    if (gameField.brickIsOff(snake.get(0) - 1) && (snake.get(0) - 1) % 10 != 0) {
                        this.erase();
                        snake.add(0, snake.get(0) - 1);
                        snake.remove(snake.size() - 1);
                    } else if (brickIsFood(snake.get(0) - 1)) {
                        this.generateFood();
                        this.erase();
                        snake.add(0, snake.get(0) - 1);
                    } else {
                        gameOver();//stop(1);
                    }

                    this.show();
                    break;

                case 3:
                    // down
                    if (gameField.brickIsOff(snake.get(0) + 10)) {
                        this.erase();
                        snake.add(0, snake.get(0) + 10);
                        snake.remove(snake.size() - 1);
                    } else if (brickIsFood(snake.get(0) + 10)) {
                        this.generateFood();
                        this.erase();
                        snake.add(0, snake.get(0) + 10);

                    } else {
                        gameOver();//stop(1);
                    }

                    this.show();
                    break;
            }

            canMove = true; // change direction is Unlocked
        } catch (ArrayIndexOutOfBoundsException e) {
            gameOver();//stop(1);// game Over
        }
    }

    @Override
    public void stop() {
        gameStatus = STOP;
        gameField.clear();
    }

    private void restart() {
        gameField.clear();
        
        if (level > 1) {
            for (int i = 0; i < mapLevel.length; i++) {
                mapLevel[i] = 0;
            }
        }

        scores = 0;
        start();
    }

    private void gameOver() {
        engineLock = true;
        gameStatus = GAME_OVER;
    }

    private void generateFood() {
        Random food = new Random();
        int brickFood;

        do {
            brickFood = food.nextInt(200);
        } while (!gameField.brickIsOff(brickFood));

        scores += 100 * (level + 1); // прибавка очков
        gameField.brickOn(brickFood, Color.LIGHTGREEN); // создание кубика еды
    }

    private void show() {
        snake.stream().forEach((brick) -> {
            gameField.brickOn(brick);
        });
    }

    private void erase() {
        snake.stream().forEach((brick) -> {
            gameField.brickOff(brick);
        });
    }

    private boolean checkDirection(int direction) {
        switch (direction) {
            case 0:
                return this.direction != DOWN;

            case 1:
                return this.direction != LEFT;

            case 2:
                return this.direction != RIGHT;

            case 3:
                return this.direction != UP;

            default:
                return false;
        }
    }

    private void generateLevel() {
//        if (!engineLock) {
//            snakeMoving.stop();
//        }

        switch (level) {
            case 0:
                break;
            case 1:
                mapLevel = new int[]{63, 64, 67, 68,
                    73, 74, 77, 78,
                    83, 84, 87, 88,
                    93, 94, 97, 98,
                    103, 104, 107, 108,
                    113, 114, 117, 118,
                    123, 124, 127, 128,
                    133, 134, 137, 138};
                break;

            case 2:
                mapLevel = new int[]{33, 34, 43, 44, 53, 54, 63, 64, 73, 74, 83, 84, 93, 94,
                    37, 38, 47, 48, 57, 58, 67, 68, 77, 78, 87, 88, 97, 98,
                    95, 96, 105, 106, 115, 116, 125, 126, 135, 136, 145, 146, 155, 156,
                    163, 164, 165, 166, 167, 168, 173, 174, 175, 176, 177, 178};
                break;

            case 3:
                mapLevel = new int[]{22, 23, 24, 32, 33, 34, 42, 43, 44, 27, 28, 29, 37, 38, 39, 47, 48, 49,
                    83, 84, 93, 94, 102, 103, 104, 105, 106, 107, 108, 109, 87, 88, 97, 98, 113, 114, 117, 118, 123, 124, 127, 128,
                    163, 164, 167, 168, 173, 174, 177, 178, 183, 184, 187, 188};
                break;

            case 4:
                mapLevel = new int[]{12, 13, 14, 15, 16, 17, 18, 19,
                    22, 23, 24, 25, 26, 27, 28, 29,
                    38, 39, 48, 49, 52, 53, 54, 62, 63, 64,
                    78, 79, 88, 89,
                    92, 93, 94, 95, 96, 97, 98, 99,
                    102, 103, 104, 105, 106, 107, 108, 109,
                    112, 113, 122, 123,
                    136, 137, 138,
                    146, 147, 148,
                    152, 153, 162, 163,
                    172, 173, 174, 175, 176, 177, 178, 179,
                    182, 183, 184, 185, 186, 187, 188, 189};
                break;
        }

        if (level != 0) {
            for (Integer brick : mapLevel) {
                //gameField.brickOn(brick);
                makeWall(brick);
            }
        }
    }

    private void makeWall(int brick) {
        gameField.brickOn(brick, Color.RED);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DOWN)) {
            if (checkDirection(DOWN) && canMove) {
                direction = DOWN;
                canMove = false;
            }
        }

        if (event.getCode().equals(KeyCode.UP)) {
            if (checkDirection(UP) && canMove) {
                direction = UP;
                canMove = false;
            }
        }

        if (event.getCode().equals(KeyCode.RIGHT)) {
            if (checkDirection(RIGHT) && canMove) {
                direction = RIGHT;
                canMove = false;
            }
        }

        if (event.getCode().equals(KeyCode.LEFT)) {
            if (checkDirection(LEFT) && canMove) {
                direction = LEFT;
                canMove = false;
            }
        }

        if (event.getCode().equals(KeyCode.P)) {
            if (engineLock) {
                gameStatus = PLAY;
                engineLock = false;
            } else {
                gameStatus = PAUSE;
                engineLock = true;
            }
        }

        if (event.getCode().equals(KeyCode.ENTER)) {
            // turbo boost
            move();
        }

        //System.out.println(event.getCode() + " was pressed...");
    }

    @Override
    public String pulse() {
        if (!engineLock) {
            move();
            canMove = true;
        }

        return "Level " + level + "\n"
                + "HI-SCORES: " + scores + "\n\n"
                + gameStatus + "\n\n"
                + controlls;
    }
}
