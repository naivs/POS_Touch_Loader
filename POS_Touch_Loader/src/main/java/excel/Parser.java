package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ivan
 */
public class Parser {

    private Workbook wb;

    public Parser(File file) throws FileNotFoundException, IOException {

        wb = new XSSFWorkbook(new FileInputStream(file));

//        for (String out : getProducts(5, 1)) {
//
//            System.out.println(out);
//        }
    }

    public String[] getGroupsNames() {

        String[] groupNames = new String[8];

        Sheet mySheet = wb.getSheetAt(0);

        int k = 1;
        for (int i = 0; i < groupNames.length; i++) {

            groupNames[i] = mySheet.getRow(0).getCell(k).getStringCellValue();
            k += 7;
        }

        return groupNames;
    }

    public String[] getProducts(int day, int group) {

        List<String> products = new ArrayList<String>();

        Sheet mySheet = wb.getSheetAt(day);

        int k = 2;
        String plu;

        while (!(plu = String.valueOf(mySheet.getRow(k).getCell(group * 7 + 1 + 5).getNumericCellValue())).equals("0.0")) {

            String buf;
            buf = plu.substring(0, plu.length() - 2) + "::" + mySheet.getRow(k).getCell(group * 7 + 1 + 6).getStringCellValue();
            products.add(buf);
            k++;
        }

        String[] prod = new String[products.size()];

        for (int i = 0; i < prod.length; i++) {
            prod[i] = products.get(i);
        }

        return prod;
    }

}
