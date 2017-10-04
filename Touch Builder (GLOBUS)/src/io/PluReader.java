package io;

import data.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Formatter;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PluReader {

    // FIELDS
    private ObjectInputStream buttonsOIS;

    private HashMap<String, Button[]> data; // данные из файла PLUREF.DAT

    // CONSTRUCTOR
    public PluReader() {

        try {
            buttonsOIS = new ObjectInputStream(new FileInputStream(Primary.BUTTONS_PATH));

            data = (HashMap<String, Button[]>) buttonsOIS.readObject();
            buttonsOIS.close();

        } catch (IOException e) {
            System.out.println("pluref or items read failed... " + e.getMessage());
            generateEmptyData();
        } catch (ClassNotFoundException e) {
            System.out.println("HashMap data read failed... " + e.getMessage());
        }
    }

    // FUNCTIONS
    private void generateEmptyData() {

        data = new HashMap();

        try {
        ImageIcon emptyImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/empty.gif")));

        for (int i = 1; i <= 64; i++) {

            Formatter fmt = new Formatter();
            fmt.format("%03d", i);

            data.put(fmt.toString(), new Button[20]);

            for (int j = 0; j < 20; j++) {
                data.get(fmt.toString())[j] = new Button(String.valueOf(j), "", "", emptyImage);
            }
        }
        } catch (IOException e) {
            System.out.println("empty image read failed..." + e.getMessage());
        }
    }

    public HashMap<String, Button[]> read() {
        return data;
    }
}
