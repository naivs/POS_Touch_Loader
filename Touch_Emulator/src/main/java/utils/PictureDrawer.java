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
package utils;

import data.Product;
import data.Subgroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.text.WordUtils;

/**
 *
 * @author Ivan Naumov
 */
public class PictureDrawer {

    private final File GROUND;
    private BufferedImage SCREEN;
    private static final Font TEXT_FONT = new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16);
    private static final Font PLU_FONT = new Font("Franklin Gothic Medium Cond", Font.PLAIN, 15);

    public PictureDrawer() {
        //ClassLoader classLoader = getClass().getClassLoader();
        GROUND = new File("resources/ground.gif");
    }

    public BufferedImage draw(Subgroup subgroup) {
        SCREEN = new BufferedImage(555, 384, BufferedImage.TYPE_INT_RGB);
        try {
            SCREEN.createGraphics().drawImage(ImageIO.read(GROUND), 0, 0, null);
        } catch (IOException ex) {
            System.err.println("Can't read \"ground.gif\"." + ex.getMessage());
        }
        Product[] products = subgroup.getProducts();

        int dx = 111;
        int dy = 96;

        int i = 0;

        int nameY = 15;
        int pluY = 92;
        int numY = 92;
        for (int y = 0; y < 4; y++) {
            int nameX = 5;
            int pluX = 10;
            int numX = 2;
            for (int x = 0; x < 5; x++) {
                if (products[i] != null) {
                    drawName(products[i].getName(), nameX, nameY);
                    drawPlu(products[i].getPlu(), pluX, pluY);
                    drawNumber(String.valueOf(i + 1), numX, numY);

                    i++;
                }
                nameX += dx;
                pluX += dx;
                numX += dx;
            }
            nameY += dy;
            pluY += dy;
            numY += dy;
        }

        return SCREEN;
    }

    private void drawName(String text, int x, int y) {
        Graphics2D graphics = SCREEN.createGraphics();
        graphics.setFont(TEXT_FONT);
        graphics.setColor(Color.black);

        for (String out : WordUtils.wrap(text, 14, "\n", true).split("\n")) {
            graphics.drawString(out, (14 - out.length()) * 3 + x, y);
            y += 20;
        }
    }
    
//    private Dimension centerText() {
//        
//    }

    private void drawPlu(String plu, int x, int y) {
        Graphics2D graphics = SCREEN.createGraphics();
        graphics.setFont(PLU_FONT);
        graphics.setColor(Color.white);

        if (plu.length() > 6) {
            plu = plu.substring(0, 6);
        }
        // разделение plu строки на разряды для удобного чтения
        if (plu.length() > 2 && (plu.length() & 1) == 0) {
            String buf_plu = "";
            for (int j = 0; j < plu.length(); j++) {
                if ((j & 1) == 0 && j > 0) {
                    buf_plu += " " + plu.charAt(j);
                } else {
                    buf_plu += plu.charAt(j);
                }
            }

            plu = buf_plu;
        }
        graphics.drawString(plu, (13 - plu.length()) * 8 + x, y);
    }

    private void drawNumber(String number, int x, int y) {
        Graphics2D graphics = SCREEN.createGraphics();
        graphics.setFont(PLU_FONT);
        graphics.setColor(Color.black);
        graphics.drawString(number, x, y);
    }
}
