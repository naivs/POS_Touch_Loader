package games;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sprites.GameField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Invaders implements GameController {

    private final GameField gameField;
    private ArrayList<Unit> units = new ArrayList<>(); // units.get(0) - hero
    EnemyShip enemy;
    private boolean engineLock;
    private boolean bulletLock;

    private Bullet bullet;
    

    public Invaders(GameField gameField) {
        this.gameField = gameField;
    }

    public void pause() {
        engineLock = !engineLock;
    }

    @Override
    public void start() {
        units.add(new Canon(gameField));
        enemy = new EnemyShip(gameField);
        //System.out.println("Units array: " + units);
        //units.add(new EnemyShip(gameField));
        //units.get(0).show();
        engineLock = false;
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getCode() == KeyCode.RIGHT) {
            units.get(0).move(Unit.RIGHT);
        }

        if (evt.getCode() == KeyCode.LEFT) {
            units.get(0).move(Unit.LEFT);
        }

        if (evt.getCode() == KeyCode.ALT) {
            //enemy.enemyShip.remove(units.get(0).getUnit().get(0) - 180);
            bullet = new Bullet(units.get(0).getUnit().get(0) - 10, gameField, enemy);
            bullet.fire();
        }
    }

    @Override
    public String pulse() {
        if (!engineLock) {
            enemy.move(Unit.DOWN);
        }
        
        return null;
    }
}

interface Unit {

    /**
     * This interface for all units in game
     *
     */
    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;

    void move(int direction);

    void show();

    void erase();

    List<Integer> getUnit();
}

final class Canon implements Unit { // player's cannon

    private final GameField gameField;
    private final List<Integer> canon = Arrays.asList(185, 194, 195, 196);

    public Canon(GameField gameField) {
        this.gameField = gameField;
        show();
    }

    @Override
    public void move(int direction) {
        erase();

        switch (direction) {
            case Unit.LEFT:
                if (canon.get(1) - 1 != 190) {
                    if (canon.get(0) == 190) {
                        canon.set(0, canon.get(0) - 1);
                        break;
                    }

                    for (int i = 0; i < canon.size(); i++) {
                        canon.set(i, canon.get(i) - 1);
                    }
                } else if (canon.get(0) != 181) {
                    canon.set(0, canon.get(0) - 1);
                }
                break;

            case Unit.RIGHT:
                if (canon.get(3) + 1 != 201) {
                    if (canon.get(0) == 181) {
                        canon.set(0, canon.get(0) + 1);
                        break;
                    }

                    for (int i = 0; i < canon.size(); i++) {
                        canon.set(i, canon.get(i) + 1);
                    }
                } else if (canon.get(0) != 190) {
                    canon.set(0, canon.get(0) + 1);
                }
                break;
        }
        show();
    }

    @Override
    public void show() {
        for (int i = 0; i < canon.size(); i++) {
            gameField.brickOn(canon.get(i));
        }
    }

    @Override
    public void erase() {
        for (int i = 0; i < canon.size(); i++) {
            gameField.brickOff(canon.get(i));
        }
    }

    @Override
    public List<Integer> getUnit() {
        return canon;
    }
}

class EnemyShip implements Unit {

    private final GameField gameField;
    ArrayList<Integer> enemyShip = new ArrayList<>();
    
//4, 8, 15, 17, 24, 25, 26, 27, 28,
            //33, 34, 36, 38, 39, 42, 43, 44, 45, 46, 47, 48, 49, 50,
            //52, 54, 58, 60, 65, 67);
    

    public void remove(int i) {
        enemyShip.remove(i);
    }
    
    public EnemyShip(GameField gameField) {
        this.gameField = gameField;
        
        enemyShip.add(4); enemyShip.add(8); enemyShip.add(15); enemyShip.add(17); enemyShip.add(24); enemyShip.add(25); enemyShip.add(26); enemyShip.add(27); 
        enemyShip.add(28); enemyShip.add(33); enemyShip.add(34); enemyShip.add(36); enemyShip.add(38); enemyShip.add(39); enemyShip.add(42); enemyShip.add(43);
        enemyShip.add(44); enemyShip.add(45); enemyShip.add(46); enemyShip.add(47); enemyShip.add(48); enemyShip.add(49); enemyShip.add(50); enemyShip.add(52); enemyShip.add(54);
        enemyShip.add(58); enemyShip.add(60); enemyShip.add(65); enemyShip.add(67);
        //Random rnd = new Random();

//        enemyShip.add(rnd.nextInt(2)); // 0, 1
//        enemyShip.add(rnd.nextInt(2) + 2); // 2, 3
//        enemyShip.add(rnd.nextInt(2) + 4); // 4, 5
//        enemyShip.add(rnd.nextInt(2) + 6); // 6, 7
//        enemyShip.add(rnd.nextInt(3) + 8); // 8, 9, 10
        //show();
    }

    @Override
    public void move(int direction) {
        //if(invaders.checkImpact())
        gameField.clear();
        erase();

//        for (int i = 0; i < enemyShip.size(); i++) {
//            enemyShip.set(i, enemyShip.get(i) + 10);
//        }

        show();
    }

    @Override
    public void show() {
        for (int i = 0; i < enemyShip.size(); i++) {
            gameField.brickOn(enemyShip.get(i), Color.DARKORCHID);
        }
    }

    @Override
    public void erase() {
        for (int i = 0; i < enemyShip.size(); i++) {
            gameField.brickOff(enemyShip.get(i));
        }
    }

    @Override
    public List<Integer> getUnit() {
        return enemyShip;
    }
}

final class Bullet implements Unit {

    private final GameField gameField;
    private Unit enemy;
    private int bullet;
    private final Timeline bulletFlight = new Timeline(
            new KeyFrame(Duration.millis(10), ae -> {
                move(Unit.UP);
            })
    );

    public Bullet(int bullet, GameField gameField, EnemyShip enemy) {
        this.gameField = gameField;
        this.enemy = enemy;
        this.bullet = bullet;
        
        bulletFlight.setCycleCount(18); // Ограничим число повторений
    }

    private boolean checkImpact() {
//        if (!GameField.getPole()[bullet.get(0) - 11].isOff()) {
//            //Invaders enemyShip = new Invaders();
//            Invaders.invaders.get(Invaders.invaders.size() - 1).getTransport().remove(bullet.get(0) - 11);
//            return true;
//        }

        return false;
    }

    public int fire() {
        bulletFlight.play();
        return 0;
    }
    
    private void terminate() {
        gameField.brickOff(bullet);
    }

    @Override
    public void move(int direction) {
        erase();

//        if(bullet - 10 < 0) {// если вылет за экран
//            terminate();
//            return;
//        }
        //if (!gameField.brickIsOff(bullet - 10))
            if(enemy.getUnit().contains(bullet - 10)) { // если попал во врага
            for(int i = 0; i < enemy.getUnit().size(); i++) {
                if(enemy.getUnit().get(i).equals(bullet - 10))
                    enemy.getUnit().remove(i);
            }
            //enemy.getUnit().remove(bullet - 10);
        } else {
            bullet -= 10;
            show();
        }
    }

    @Override
    public void show() {
        gameField.brickOn(bullet, Color.RED);
    }

    @Override
    public void erase() {
        gameField.brickOff(bullet);
    }

    @Override
    public List<Integer> getUnit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
