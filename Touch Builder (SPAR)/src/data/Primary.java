package data;

import io.PluReader;
import io.PluWriter;
import io.RegReader;
import java.util.HashMap;

public class Primary {

// FIELDS
    //public static final String IP = "192.168.59.2";
    public static final String PAR_PATH = "D:/TOUCH_SERVER/P_REGPAR.DAT";
    public static final String REF_PATH = "D:/TOUCH_SERVER/S_PLUREF.DAT";
    public static final String BUTTONS_PATH = "D:/TOUCH_SERVER/items.dat";

    private final HashMap<String, Button[]> plurefData; // данные из файла PLUREF.DAT
    private final String[] categoryNames;
    private final String[] subCategoryNames;
    private final String[] subCategoryIndexes;

    private final PluWriter pluWriter;

// CONSTRUCTOR
    public Primary() {

        plurefData = new PluReader().read();

        RegReader rr = new RegReader();
        categoryNames = rr.readCategoryNames();
        subCategoryNames = rr.readSubCategoryNames();
        subCategoryIndexes = rr.readSubCategoryIndexes();

        pluWriter = new PluWriter();
    }

// FUNCTIONS
    public String[] getDynaButtonsNames() {
        return categoryNames;
    }

    public String[] getPdButtonsNames() {
        return subCategoryNames;
    }

    public String[] getPdButtonsNumbers() {
        return subCategoryIndexes;
    }

    public Button[] getPdButton(String key) {
        return plurefData.get(key);
    }

    public HashMap<String, Button[]> getPdButtons() {
        return plurefData;
    }

    public int[] getFreePlu(String[] keys) {

        int[] free = new int[8];

        for (int i = 0; i < 8; i++) {

            Button[] buttons = plurefData.get(keys[i]);

            for (int j = 0; j < 20; j++) {

                if (buttons[j].getPlu().trim().equals("")) {
                    free[i]++;
                }
            }
        }

        return free;
    }

    public void writeData() {
        for (String index : subCategoryIndexes) {
            sortButtonsNames(index);
        }

        pluWriter.writeData(plurefData);
    }

    public void sortButtonsNames(String buttonIndexes) {

        // беру массив кнопок по индексу для сортировки
        Button[] buttons = plurefData.get(buttonIndexes);

        // считаю количество пустых кнопок, добавляю их в конец массива
        int endIndex = buttons.length - 1;

        for (int j = 0; j <= endIndex; j++) {

            if (buttons[j].getText().trim().equals("")) {
                
                while (buttons[endIndex].getText().trim().equals("") && endIndex > j) {
                    endIndex--;
                }

                Button buf = buttons[j];
                buttons[j] = buttons[endIndex];
                buttons[endIndex] = buf;
                
                endIndex--;
            }
        }
        
        // пузырьковая сортировка (без учета регистра)
        for (int i = endIndex; i >= 0; i--) {
            for (int j = 0; j < i; j++) {

                Button buf;

                if (buttons[i].getText().compareToIgnoreCase(buttons[j].getText()) < 0) {

                    buf = buttons[j];
                    buttons[j] = buttons[i];
                    buttons[i] = buf;
                }
            }
        }

        

        // присваиваю правильные порядковые номера кнопкам
        for (int i = 0; i < buttons.length; i++) {

            if (i < 9) {
                buttons[i].setNumber("0" + (i + 1));
            } else {
                buttons[i].setNumber(String.valueOf((i + 1)));
            }
        }
    }
}
