package keyboard;


import java.awt.event.KeyEvent;

/**
 *
 * @author ivan
 */
public class KeyMap {
    
    public static final int DEFAULT = 1;
    private int[][] keys;
    
    public KeyMap() {
        keys = new int[12][2];
    }
    
    public KeyMap(int TYPE) {
        this();
        
        if (TYPE == DEFAULT) {
            keys[0][0] = KeyEvent.VK_Q;
            keys[1][0] = KeyEvent.VK_A;
            keys[2][0] = KeyEvent.VK_Z;
            keys[3][0] = KeyEvent.VK_W;
            keys[4][0] = KeyEvent.VK_S;
            keys[5][0] = KeyEvent.VK_X;
            keys[6][0] = KeyEvent.VK_E;
            keys[7][0] = KeyEvent.VK_D;
            keys[8][0] = KeyEvent.VK_C;
            keys[9][0] = KeyEvent.VK_R;
            keys[10][0] = KeyEvent.VK_F;
            keys[11][0] = KeyEvent.VK_V;
            
            keys[0][1] = KeyEvent.VK_T;
            keys[1][1] = KeyEvent.VK_G;
            keys[2][1] = KeyEvent.VK_B;
            keys[3][1] = KeyEvent.VK_Y;
            keys[4][1] = KeyEvent.VK_H;
            keys[5][1] = KeyEvent.VK_N;
            keys[6][1] = KeyEvent.VK_U;
            keys[7][1] = KeyEvent.VK_J;
            keys[8][1] = KeyEvent.VK_M;
            keys[9][1] = KeyEvent.VK_I;
            keys[10][1] = KeyEvent.VK_K;
            keys[11][1] = KeyEvent.VK_O;
        }
    }
    
    public int[][] getKeyMap() {
        return keys;
    }
    
    public void setKey(int key, int controller, int KEY_CODE) {
        keys[key][controller] = KEY_CODE;
    }
}
