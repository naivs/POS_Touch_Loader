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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
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

    private final int cellNameSizeX = 109;
    private final int cellNameSizeY = 76;
    private final Dimension cellTextPadding = new Dimension(0, 0);
    private final Dimension cellPLUPosition = new Dimension(10, 92);
    private final Dimension cellNumberPosition = new Dimension(4, 92);

    private final int stepX = 111;
    private final int stepY = 96;

    private Product[] products;

    public PictureDrawer() {
        //ClassLoader classLoader = getClass().getClassLoader();
        GROUND = new File("resources/ground.gif");
    }

    public BufferedImage draw(Product[] products) {
        this.products = products;
        SCREEN = new BufferedImage(555, 384, BufferedImage.TYPE_INT_RGB);
        //graphic settings
        Graphics2D graphics = SCREEN.createGraphics();

        try {
            SCREEN.createGraphics().drawImage(ImageIO.read(GROUND), 0, 0, null);
        } catch (IOException ex) {
            System.err.println("Can't read \"ground.gif\"." + ex.getMessage());
        }

        for (int i = 0; i < products.length; i++) {
            if (!products[i].getName().trim().equals("")) {
                //drawing product text
                graphics.setFont(TEXT_FONT);
                graphics.setColor(Color.black);
                drawText(i, graphics);

                //drawing product plu
                graphics.setFont(PLU_FONT);
                graphics.setColor(Color.white);
                drawPlu(i, graphics);

                //drawing cell number
                graphics.setColor(Color.black);
                drawNumber(i, graphics);
            }
        }

        graphics.dispose();
        return SCREEN;
    }

    private void drawText(int productNum, Graphics2D graphics) {
        int interval = 4; //in pixels
        float startX = (productNum % 4) * stepX + (float) cellTextPadding.getWidth();
        float startY = (productNum / 4) * stepY + (float) cellTextPadding.getHeight();

        String[] lines = WordUtils.wrap(products[productNum].getName(), 14, "\n", true).split("\n");

        int ySize = (lines.length - 1) * interval;
        for (String out : lines) {
            ySize += Math.round(test1(out).getHeight());
        }
        startY += (cellNameSizeY - ySize) / 2 + Math.round(test1(lines[0]).getHeight());

        //center and print strings
        for (String out : lines) {
            float x = startX + (cellNameSizeX - Math.round(test1(out).getWidth())) / 2;
            graphics.drawString(out, x, startY);
            startY += interval + Math.round(test1(out).getHeight());
        }
    }
    
    private static Rectangle2D test1(String text) {
        TextLayout tl = new TextLayout(text, TEXT_FONT, new FontRenderContext(null, true, true));
        return tl.getBounds();
    }

    private void drawPlu(int productNum, Graphics2D graphics) {
        String plu = products[productNum].getPlu();

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

        int x = (productNum % 4) * stepX + (int) cellPLUPosition.getWidth();
        int y = (productNum / 4) * stepY + (int) cellPLUPosition.getHeight();

        graphics.drawString(plu, (13 - plu.length()) * 8 + x, y);
    }

    private void drawNumber(int productNum, Graphics2D graphics) {
        int x = (productNum % 4) * stepX + (int) cellNumberPosition.getWidth();
        int y = (productNum / 4) * stepY + (int) cellNumberPosition.getHeight();

        graphics.drawString(String.valueOf(productNum + 1), x, y);
    }
}
