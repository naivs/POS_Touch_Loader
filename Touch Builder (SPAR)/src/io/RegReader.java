package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegReader {

    //FUNCTIONS
    public String[] readCategoryNames() { //получаю названия DYNA кнопок

        String[] DYNAButtonsNames = new String[8];
        String buf;
        //DYNAButtonsNames[0] = "КАТЕГОРИЯ";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(data.Primary.PAR_PATH), "Cp866"));

            int i = 0;

            while ((buf = reader.readLine()) != null) {

                if (buf.substring(0, 4).equals("PRES")) {

                    DYNAButtonsNames[i] = i+1 + ". " + removeEndSpaces(buf.substring(11, 25)) + " " + removeEndSpaces(buf.substring(28, 46));
                    i++;
                }
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e1) {
            System.out.println(e1);
        }

        return DYNAButtonsNames;
    }

    public String[] readSubCategoryIndexes() { //получаю номера обычных кнопок

        String[] PDButtonsNumbers = new String[64];
        String buf;

        try {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(data.Primary.PAR_PATH), "Cp866"));

            int i = 0;

            while ((buf = reader.readLine()) != null) {

                if (buf.substring(0, 2).equals("PD")) {

                    PDButtonsNumbers[i] = buf.substring(24, 27);
                    i++;
                }

                if (i > 64) {
                    break;
                }
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e1) {
            System.out.println(e1);
        }

        return PDButtonsNumbers;
    }

    public String[] readSubCategoryNames() { //получаю названия обычных кнопок

        String[] PDButtonsNames = new String[64];
        String buf;

        try {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(data.Primary.PAR_PATH), "Cp866"));

            int i = 0;

            while ((buf = reader.readLine()) != null) {

                if (buf.substring(0, 2).equals("PD")) {

                    PDButtonsNames[i] = removeEndSpaces(buf.substring(11, 23)) + " ";
                    PDButtonsNames[i] += removeEndSpaces(buf.substring(28, 46));
                    i++;
                }

                if (i > 64) {
                    break;
                }
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e1) {
            System.out.println(e1);
        }

        return PDButtonsNames;
    }

    private String removeEndSpaces(String line) {

        int index = line.length() - 1;

        while (line.charAt(index) == ' ' && index > 0) {

            index--;
        }

        return line.substring(0, index + 1);
    }
}
