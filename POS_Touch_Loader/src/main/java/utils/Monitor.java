package utils;

import data.Product;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author Ivan
 */
public class Monitor extends javax.swing.JPanel {

// FIELDS
    private Image ground;

    private Graphics graphic;
    private JLabel[] labels = new JLabel[20];
    private Font textFont = new Font("Franklin Gothic Medium Cond Regular", Font.BOLD, 14);
    private Font pluFont = new Font("Franklin Gothic Medium Cond Regular", Font.BOLD, 14);

    private String[] buttonsText = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] buttonsPlu = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] buttonsPicPath = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

// CONSTRUCTOR
    public Monitor() {

        super();

        try {
            ground = ImageIO.read(new File("resources/ground.gif"));
        } catch (IOException e) {
            System.out.println("ground image or cell image read failed... " + e.getMessage());
        }

        addLabels();
    }

// FUNCTIONS
    private void addLabels() {
        int w = 109;
        int h = 80;

        int plu_dx = 111;
        int plu_dy = 96;
        int index = 0;

        for (int i = 1; i < 384; i += plu_dy) {
            for (int j = 1; j < 555; j += plu_dx) {
                labels[index] = new JLabel();
                labels[index].setHorizontalTextPosition(JLabel.CENTER);
                labels[index].setVerticalTextPosition(JLabel.CENTER);

                super.add(labels[index]);

                labels[index].setBounds(j, i, w, h);
                labels[index].setFont(textFont);
                labels[index].setForeground(Color.BLACK);
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
        int product = 0;
        for (int i = 1; i < 384; i += cell_dy) {
            for (int j = 1; j < 555; j += cell_dx) {

                if (!buttonsPicPath[product].equals("")) {
                    //прописать подгрузку картинки по пути -> 
                    try {
                        graphic.drawImage(ImageIO.read(new File(buttonsPicPath[product])), j, i, 109, 61, this);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }

                //graphic.drawImage(cell, j, i, this);
                product++;
            }
        }

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

        int plu_dx = 111;
        int plu_dy = 96;
        int index = 0;

        graphic.setFont(pluFont);

        for (int i = 91; i < 384; i += plu_dy) {
            for (int j = 4; j < 555; j += plu_dx) {
                graphic.setColor(Color.BLACK);
                graphic.drawString(String.valueOf(index + 1), j, i);
                graphic.setColor(Color.WHITE);
                switch (buttonsPlu[index].length()) {
                    case 8:
                        graphic.drawString(buttonsPlu[index], j + 45, i);
                        break;
                    case 7:
                        graphic.drawString(buttonsPlu[index], j + 48, i);
                        break;
                    case 5:
                        graphic.drawString(buttonsPlu[index], j + 59, i);
                        break;
                    case 3:
                        graphic.drawString(buttonsPlu[index], j + 76, i);
                        break;
                    case 2:
                        graphic.drawString(buttonsPlu[index], j + 84, i);
                        break;
                    case 1:
                        graphic.drawString(buttonsPlu[index], j + 94, i);
                        break;
                }
                //graphic.setColor(Color.BLACK);
                //graphic.drawString(String.valueOf(index + 1), j + 88, i);
                index++;
            }
        }
    }

    public void display(Product[] products) {

        for (int i = 0; i < products.length; i++) {

            //this.products = products;
            if (products[i] != null) {
                buttonsText[i] = products[i].toString();

                // разделение plu строки на разряды для удобного чтения
                if (products[i].getPlu().length() > 2 && (products[i].getPlu().length() & 1) == 0) {
                    String plu = "";

                    for (int j = 0; j < products[i].getPlu().length(); j++) {
                        if ((j & 1) == 0 && j > 0) {
                            plu += " " + products[i].getPlu().charAt(j);
                        } else {
                            plu += products[i].getPlu().charAt(j);
                        }
                    }

                    buttonsPlu[i] = plu;
                } else {
                    buttonsPlu[i] = products[i].getPlu();
                }

                if (products[i].getPicturePath() != null) {
                    buttonsPicPath[i] = products[i].getPicturePath();
                } else {
                    buttonsPicPath[i] = "";
                }
            } else {
                buttonsText[i] = "";
                buttonsPlu[i] = "";
                buttonsPicPath[i] = "";
            }
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
