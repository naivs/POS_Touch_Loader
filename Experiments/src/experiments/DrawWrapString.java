package experiments;

import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Ivan
 */
public class DrawWrapString extends JPanel {

    private JFrame jFrame;

    public DrawWrapString() {

        jFrame = new JFrame("Wrap and alignment text");
        jFrame.setSize(300, 150);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        jFrame.getContentPane().add(this);
    }

    public void drawString(Graphics g, String s, int x, int y, int width) {
        // FontMetrics gives us information about the width,
        // height, etc. of the current Graphics object's Font.
        FontMetrics fm = g.getFontMetrics();

        int lineHeight = fm.getHeight();

        int curX = x;
        int curY = y;

        String[] words = s.split(" ");

        String line = "";

        for (String word : words) {
            // Find out thw width of the word.
            if (fm.stringWidth(line + word + " ") <= width) {
                line += word + " ";
            } else {
                curX = x + (width - fm.stringWidth(line)) / 2;
                g.drawString(line, curX, curY);

                // If text exceeds the width, then move to next line.
                curY += lineHeight;
                line = "";
                line += word + " ";
            }
        }

        if (!line.isEmpty()) {
            curX = x + (width - fm.stringWidth(line)) / 2;
            g.drawString(line, curX, curY);
        }

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawRect(50, 30, 84, 70);
        drawString(g, "Hello my dear beautifull ass friend!!!", 50, 40, 84);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawWrapString();
            }
        });
    }
}
