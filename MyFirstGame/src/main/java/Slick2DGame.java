/*
 * Copyright (C) 2017 Ivan Naumov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Ivan
 */
public class Slick2DGame extends BasicGame {

    private int x;
    private int y;
    private int speed;

    private Image background;

    private Image creature;
    private Image creature_normal;
    private Image creature_right;
    private Image creature_left;

    private HashSet<Enemy> enemies;
    //private int enemiesOnScreen;
    //private int maxEnemiesOnScreen;

    private Random rnd;
    private HashSet<Circle> stars;

    private HashSet<Rectangle> laser;
    private boolean isShoot;
    private int missShoot;

    public Slick2DGame(String title) {
        super(title);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AppGameContainer agc = new AppGameContainer(new Slick2DGame("My first Slick2D game"));
            agc.setDisplayMode(600, 600, false);
            //agc.setAlwaysRender(true);

            agc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        x = gc.getWidth() / 2;
        y = gc.getHeight() / 2;
        speed = 5;

        rnd = new Random();
        stars = new HashSet<>();

        laser = new HashSet<>();
        missShoot = 10;

        background = new Image("resources/background.png");

        creature_normal = new Image("resources/creature_normal.png");
        creature_right = new Image("resources/creature_right.png");
        creature_left = new Image("resources/creature_left.png");

        creature = creature_normal;

        enemies = new HashSet<>();
        //enemiesOnScreen = 8;
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_MIDDLE));
        enemies.add(new Enemy(Enemy.SKIN_MIDDLE));
        enemies.add(new Enemy(Enemy.SKIN_MIDDLE));
        enemies.add(new Enemy(Enemy.SKIN_MIDDLE));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
        enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));

        gc.setTargetFrameRate(60);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        // stars anims
        if (rnd.nextInt() < 50) {
            stars.add(new Circle(rnd.nextInt(600), -2, 1));
        }

        Iterator<Circle> iter = stars.iterator();

        while (iter.hasNext()) {
            Circle star = iter.next();
            if (star.getCenterY() > 600) {
                iter.remove();
            } else {
                star.setCenterY(star.getCenterY() + 5);
            }
        }

        // enemy anims
//        for(int j = 0; j < enemiesOnScreen; j++) {
//            enemies.add(new Enemy(Enemy.SKIN_ELEMENTARY));
//        }
        Iterator<Enemy> iter3 = enemies.iterator();

        while (iter3.hasNext()) {
            Enemy enemy = iter3.next();

            if (enemy.getDeleteTime() < 0) {
                iter3.remove();
            } else {
                enemy.move();
            }
        }

        // shoot anims
        if (isShoot && missShoot <= 0) {
            laser.add(new Rectangle(x + 16, y, 3, 20));
            missShoot = 10;
        } else {
            missShoot--;
        }

        Iterator<Rectangle> iter1 = laser.iterator();

        while (iter1.hasNext()) {
            Rectangle bullet = iter1.next();
            if (bullet.getY() < 0) {
                iter1.remove();
            } else {
                // check collision
                Iterator<Enemy> iter2 = enemies.iterator();

                while (iter2.hasNext()) {
                    Enemy enemy = iter2.next();

                    if(enemy.getShape().intersects(bullet))
                        enemy.hit();
                }

                // move bullet
                bullet.setY(bullet.getY() - 10);
            }
        }

        // controlls
        Input input = gc.getInput();

        if (y > 300) {
            if (input.isKeyDown(Input.KEY_UP)) {
                y -= 1 * speed;
            }
        }

        if (y < 600 - creature.getHeight()) {
            if (input.isKeyDown(Input.KEY_DOWN)) {
                y += 1 * speed;
            }
        }

        if (x < 600 - creature.getWidth()) {
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                creature = creature_right;
                x += 1 * speed;
            }
        }

        if (x > 0) {
            if (input.isKeyDown(Input.KEY_LEFT)) {
                creature = creature_left;
                x -= 1 * speed;
            }
        }

        isShoot = input.isKeyDown(Input.KEY_SPACE);

        if (!input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_LEFT)) {
            creature = creature_normal;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {

        grphcs.drawImage(background, 0, 0);

        grphcs.setColor(Color.white);
        for (Circle star : stars) {
            grphcs.fill(star);
        }

        grphcs.setColor(Color.red);
        for (Rectangle shoot : laser) {
            grphcs.fill(shoot);
        }

        for (Enemy en : enemies) {
            en.draw(grphcs);
        }

        grphcs.drawImage(creature, x, y);

        grphcs.drawString("Enemies: " + enemies.size(), 10, 580);
    }
}

class Enemy {

    public static final String SKIN_ELEMENTARY = "resources/enemy_1.png";
    public static final String SKIN_MIDDLE = "resources/enemy_2.png";
    public static final String SKIN_ADVANCED = "resources/enemy_3.png";
    public static final String SKIN_MASTER = "resources/enemy_4.png";

    private int x;
    private int y;
    private int speed;
    private Image skin;
    
    private Shape shape;

    private boolean isHit;
    private int deleteTime;

    private SpriteSheet exp;
    private Animation explosion;

    private Random rnd;

    private boolean isArrivedX;
    private boolean isArrivedY;
    private int destinationX;
    private int destinationY;

    Enemy(String skin) throws SlickException {
        this.skin = new Image(skin);

        shape = new Circle(x, y, (this.skin.getWidth() + this.skin.getHeight()) / 4);
        
        exp = new SpriteSheet("resources/exp.png", 64, 64);
        explosion = new Animation(exp, 200);

        deleteTime = 192;

        rnd = new Random();
        x = rnd.nextInt(600);
        y = this.skin.getHeight() * -1;

        speed = 3;

        isArrivedX = true;
        isArrivedY = true;
    }

    int getPositionX() {
        return x;
    }

    int getPositionY() {
        return y;
    }

    int getSpeed() {
        return speed;
    }

    int getWidth() {
        return skin.getWidth();
    }

    int getHeight() {
        return skin.getHeight();
    }

    Shape getShape() {
        return shape;
    }
    
    void move() {
        if (!isHit) {
            if (isArrivedX && isArrivedY) {
                destinationX = rnd.nextInt(600);
                destinationY = rnd.nextInt(300);

                isArrivedX = false;
                isArrivedY = false;
            } else {

                // X
                if (destinationX > x) {
                    x += speed;
                    shape.setX(x);
                    if (x >= destinationX) {
                        isArrivedX = true;
                    }
                } else if (destinationX < x) {
                    x -= speed;
                    shape.setX(x);
                    if (x <= destinationX) {
                        isArrivedX = true;
                    }
                } else {
                    isArrivedX = true;
                }

                // Y
                if (destinationY > y) {
                    y += speed;
                    shape.setY(y);
                    if (y >= destinationY) {
                        isArrivedY = true;
                    }
                } else if (destinationY < y) {
                    y -= speed;
                    shape.setY(y);
                    if (y <= destinationY) {
                        isArrivedY = true;
                    }
                } else {
                    isArrivedY = true;
                }
            }
        }
    }

    Image getSkin() {
        return skin;
    }
    
    void hit() {
        isHit = true;
    }

    void draw(Graphics g) {
        if (isHit) {
            g.drawAnimation(explosion, x, y);
            deleteTime--;
        } else {
            g.drawImage(skin, x, y);
            g.setColor(Color.green);
            //g.draw(shape);
        }
    }

    int getDeleteTime() {
        return deleteTime;
    }
}
