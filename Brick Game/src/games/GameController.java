package games;

import javafx.scene.input.KeyEvent;

public interface GameController {
    void start();

    void stop();
    
    /** KEY CONTROLLER:
    * arrows - up, down, left, right
    * space - fire, turboBoost
    * p - pause
    * 
     * @param evt
    */
    void keyPressed(KeyEvent evt);
    
    // it fire by Timer. Main frequency
    String pulse();
}
