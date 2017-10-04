package io;

import data.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.HashMap;

/**
 *
 * @author Ivan
 */
public class PluWriter {

    private BufferedWriter plurefBufferedWriter;
    private ObjectOutputStream buttonsOOS;

// FUNCTIONS
    public void writeData(HashMap<String, Button[]> data) {

        try {
            plurefBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Primary.REF_PATH), "Cp866"));
            buttonsOOS = new ObjectOutputStream(new FileOutputStream(Primary.BUTTONS_PATH));

            for (int i = 1; i <= 64; i++) {

                Formatter fmt = new Formatter();
                fmt.format("%03d", i);

                Button[] button = data.get(fmt.toString());

                for (Button button1 : button) {
                    plurefBufferedWriter.append(unparseButton(fmt.toString(), button1) + "\r\n");
                }

                plurefBufferedWriter.flush();
            }

            plurefBufferedWriter.close();
            
            buttonsOOS.writeObject(data);
            buttonsOOS.flush();
            buttonsOOS.close();

        } catch (UnsupportedEncodingException e1) {
            System.out.println(e1.getMessage());
        } catch (FileNotFoundException e2) {
            System.out.println(e2.getMessage());
        } catch (IOException e3) {
            System.out.println(e3.getMessage());
        }
    }

    private String unparseButton(String index, Button button) {

        String out = "";
        String plu; //must be len = 16

        plu = button.getPlu();

        while (plu.length() < 16) {
            plu = " " + plu;
        }

        out += "*" + index + ":  " + button.getNumber() + ":" + plu + ":0000:                    ";

        return out;
    }
}
