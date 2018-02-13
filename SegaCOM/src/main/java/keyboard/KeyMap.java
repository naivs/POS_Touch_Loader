package keyboard;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 *
 * @author ivan
 */
public class KeyMap implements Serializable {

    public static final int CUSTOM = 0;
    public static final int SEGA = 1;
    public static final int DOS = 2;
    public static final int PC = 3;

    private String name;
    private int[][] keys;

    public KeyMap() {
        keys = new int[12][2];
    }

    public KeyMap(int TYPE) {
        this();

        switch (TYPE) {
            case CUSTOM:
                name = "Custom";

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
                break;

            case SEGA:
                name = "Sega";

                keys[0][0] = KeyEvent.VK_NUMPAD1;
                keys[1][0] = KeyEvent.VK_NUMPAD2;
                keys[2][0] = KeyEvent.VK_NUMPAD3;
                keys[3][0] = KeyEvent.VK_NUMPAD4;
                keys[4][0] = KeyEvent.VK_NUMPAD5;
                keys[5][0] = KeyEvent.VK_NUMPAD6;
                keys[6][0] = KeyEvent.VK_W;
                keys[7][0] = KeyEvent.VK_S;
                keys[8][0] = KeyEvent.VK_A;
                keys[9][0] = KeyEvent.VK_D;
                keys[10][0] = KeyEvent.VK_F;
                keys[11][0] = KeyEvent.VK_NUMPAD0;

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
                break;

            case DOS:
                name = "DOS";

                keys[0][0] = KeyEvent.VK_NUMPAD1;
                keys[1][0] = KeyEvent.VK_NUMPAD2;
                keys[2][0] = KeyEvent.VK_NUMPAD3;
                keys[3][0] = KeyEvent.VK_NUMPAD4;
                keys[4][0] = KeyEvent.VK_NUMPAD5;
                keys[5][0] = KeyEvent.VK_NUMPAD6;
                keys[6][0] = KeyEvent.VK_UP;
                keys[7][0] = KeyEvent.VK_DOWN;
                keys[8][0] = KeyEvent.VK_LEFT;
                keys[9][0] = KeyEvent.VK_RIGHT;
                keys[10][0] = KeyEvent.VK_ENTER;
                keys[11][0] = KeyEvent.VK_NUMPAD0;

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
                break;

            case PC:
                name = "PC";

                keys[0][0] = KeyEvent.VK_NUMPAD1;
                keys[1][0] = KeyEvent.VK_NUMPAD2;
                keys[2][0] = KeyEvent.VK_NUMPAD3;
                keys[3][0] = KeyEvent.VK_NUMPAD4;
                keys[4][0] = KeyEvent.VK_NUMPAD5;
                keys[5][0] = KeyEvent.VK_NUMPAD6;
                keys[6][0] = KeyEvent.VK_UP;
                keys[7][0] = KeyEvent.VK_DOWN;
                keys[8][0] = KeyEvent.VK_LEFT;
                keys[9][0] = KeyEvent.VK_RIGHT;
                keys[10][0] = KeyEvent.VK_ENTER;
                keys[11][0] = KeyEvent.VK_NUMPAD0;

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
                break;
        }
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getKey(int key, int controller) {
        return keys[key][controller];
    }

    public void setKey(int key, int controller, int KEY_CODE) {
        keys[key][controller] = KEY_CODE;
    }
}
