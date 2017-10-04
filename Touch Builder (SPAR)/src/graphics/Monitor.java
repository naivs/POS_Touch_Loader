package graphics;

import data.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ivan
 */
public class Monitor extends JPanel {

// FIELDS
    private Image ground;
    private Image cell;

    private int displayMode;

    private Graphics graphic;
    private JLabel[] labels = new JLabel[20];
    private Font textFont = new Font("Verdana Pro", Font.PLAIN, 14);
    private Font pluFont = new Font("Verdana Pro", Font.BOLD, 17);

    private Button[] buttons;
    private String[] buttonsText = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] buttonsPlu = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

// CONSTRUCTOR
    public Monitor() {

        super();

        try {
            ground = ImageIO.read(getClass().getResource("/resources/ground.gif"));
            cell = ImageIO.read(getClass().getResource("/resources/cell.gif"));
        } catch (IOException e) {
            System.out.println("ground image or cell image read failed... " + e.getMessage());
        }

        addLabels();
    }

// FUNCTIONS
    private void addLabels() {

        int w = 109;
        int h = 34;

        int plu_dx = 111;
        int plu_dy = 96;
        int index = 0;

        for (int i = 60; i < 384; i += plu_dy) {
            for (int j = 1; j < 555; j += plu_dx) {

                labels[index] = new JLabel();
                labels[index].setHorizontalTextPosition(JLabel.CENTER);
                labels[index].setVerticalTextPosition(JLabel.CENTER);

                super.add(labels[index]);

                labels[index].setBounds(j, i, w, h);
                labels[index].setFont(textFont);
                labels[index].setForeground(Color.WHITE);
                index++;

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        graphic = g;
        super.paintComponent(graphic);

        graphic.drawImage(ground, 0, 0, this);

        int cell_dx = 111;
        int cell_dy = 96;
        int button = 0;

        for (int i = 1; i < 384; i += cell_dy) {
            for (int j = 1; j < 555; j += cell_dx) {
                if (buttons[button].getImage() != null && displayMode != 0) {
                    graphic.drawImage(buttons[button].getImage().getImage(), j + 19, i, 69, 61, this);
                }
                graphic.drawImage(cell, j, i, this);
                button++;
            }
        }

        if (displayMode != 1) {
            for (int i = 0; i < 20; i++) {

                labels[i].setText("<html>\n"
                        + "<head>\n"
                        + "<style>\n"
                        + "P {\n"
                        + "width: 84px;\n"
                        + "word-wrap: break-word;\n"
                        + "text-align: center;\n"
                        + "}\n"
                        + "</style>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<P>" + buttonsText[i] + "</P>\n"
                        + "</body>\n"
                        + "</html>");
            }
        } else {
            for (int i = 0; i < 20; i++) {

                labels[i].setText("<html>\n"
                        + "<head>\n"
                        + "<style>\n"
                        + "P {\n"
                        + "width: 84px;\n"
                        + "word-wrap: break-word;\n"
                        //+ "text-align: justify;\n"
                        + "}\n"
                        + "</style>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<P>" + "" + "</P>\n"
                        + "</body>\n"
                        + "</html>");
            }
        }

        int plu_dx = 111;
        int plu_dy = 96;
        int index = 0;

        graphic.setFont(pluFont);

        for (int i = 16; i < 384; i += plu_dy) {
            for (int j = 2; j < 555; j += plu_dx) {
                graphic.setColor(new Color(7, 123, 54));
                graphic.drawString(buttonsPlu[index], j, i);
                //graphic.setColor(Color.BLACK);
                //graphic.drawString(String.valueOf(index + 1), j + 88, i);
                index++;
            }
        }
    }

    public void display(Button[] buttons, int cellPattern, int displayMode) {

        for (int i = 0; i < buttons.length; i++) {
            
            this.displayMode = displayMode;
            this.buttons = buttons;
            buttonsText[i] = buttons[i].getText();
            buttonsPlu[i] = buttons[i].getPlu();
        }

        try {
            // шаблон ячейки в зависимости от переданного параметра
            switch (cellPattern) {
                case 1:
                    cell = ImageIO.read(getClass().getResource("/resources/cell.gif"));
                    break;

                case 2:
                    cell = ImageIO.read(getClass().getResource("/resources/cell1.gif"));
                    break;

                case 3:
                    cell = ImageIO.read(getClass().getResource("/resources/cell2.gif"));
                    break;
            }
        } catch (IOException e) {
            System.out.println("ground image or cell image read failed... " + e.getMessage());
        }

        repaint();
    }

    public void clear() {

        for (int i = 0; i < 20; i++) {
            buttonsText[i] = "";
            buttonsPlu[i] = "";
        }

        repaint();
    }
}
