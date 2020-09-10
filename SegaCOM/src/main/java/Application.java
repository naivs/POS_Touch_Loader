import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * @author ivan
 */
public class Application {

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() ->
                new GUI().setVisible(true));
    }
}
