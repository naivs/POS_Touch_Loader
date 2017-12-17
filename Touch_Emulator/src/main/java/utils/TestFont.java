/*
 * Copyright (C) 2017 ivan
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

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author ivan
 */
public class TestFont extends JFrame {
    
    public TestFont() {
        super("String size test");
        String str = "Никаких грубых слов, только любовь...";
        Font font = new Font(Font.DIALOG, Font.BOLD, 32);
        DrawPanel dp = new DrawPanel(str, 2, 60, font);
        getContentPane().add(dp, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
 
    public static void main(String[] args) {
        new TestFont().setVisible(true);
    }
 
 
    private class DrawPanel extends JPanel {
 
        private String text;
        private int x;
        private int y;
        private Font font;
 
 
        DrawPanel(String text, int x, int y, Font font) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.font = font;
        }
 
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.red);
            g2d.drawString("String length: " + String.valueOf(Math.round(test1(text, font).getWidth())) + "px", 2, 10);
            g2d.translate(x,y);
            g2d.draw(test1(text, font));
            g2d.setColor(Color.blue);
            g2d.draw(test2(text, font));
            g2d.setFont(font);
            g2d.setColor(Color.black);
            g2d.drawString(text, 0, 0);
        }
 
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 300);
        }
    }
 
    private static Rectangle2D test1(String text, Font font) {
        TextLayout tl = new TextLayout(text, font, new FontRenderContext(null, true, true));
        return tl.getBounds();
    }
 
    private static Rectangle2D test2(String text, Font font) {
        return font.getStringBounds(text, new FontRenderContext(null, true, true));
    }
}
