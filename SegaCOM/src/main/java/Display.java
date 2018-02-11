import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ivan
 */
public class Display extends JPanel {
    private static final String GROUND = "img/back.gif";
    private static final String CAGE = "img/cage.gif";
    private static final String L_GAMEPAD = "img/gamepad_l.gif";
    private static final String R_GAMEPAD = "img/gamepad_r.gif";
    private static final String[][] BUTTONS = {{
        "img/l_a.gif",
        "img/l_b.gif",
        "img/l_c.gif",
        "img/l_x.gif",
        "img/l_y.gif",
        "img/l_z.gif",
        "img/l_up.gif",
        "img/l_down.gif",
        "img/l_left.gif",
        "img/l_right.gif",
        "img/l_start.gif",
        "img/l_mode.gif"
    }, {
        "img/r_a.gif",
        "img/r_b.gif",
        "img/r_c.gif",
        "img/r_x.gif",
        "img/r_y.gif",
        "img/r_z.gif",
        "img/r_up.gif",
        "img/r_down.gif",
        "img/r_left.gif",
        "img/r_right.gif",
        "img/r_start.gif",
        "img/r_mode.gif"
    }};
    private Graphics graphic;
    private final boolean[][] keyStates = new boolean[2][12];
    
    public void press(int pad, int key) {
        keyStates[pad][key] = true;
    }
    
    public void release(int pad, int key) {
        keyStates[pad][key] = false;
    }
    
    public boolean isPressed(int pad, int key) {
        return keyStates[pad][key];
    }
    
    public void log(String message) {
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        graphic = g;
        super.paintComponent(graphic);

        graphic.drawImage(new ImageIcon(GROUND).getImage(), 0, 0, this);
        graphic.drawImage(new ImageIcon(L_GAMEPAD).getImage(), 0, 0, this);
        graphic.drawImage(new ImageIcon(R_GAMEPAD).getImage(), 0, 0, this);
        graphic.drawImage(new ImageIcon(CAGE).getImage(), 0, 0, this);
        
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 12; j++) {
                if(keyStates[i][j])
                    graphic.drawImage(new ImageIcon(BUTTONS[i][j]).getImage(), 0, 0, this);
            }
        }
    }
}
