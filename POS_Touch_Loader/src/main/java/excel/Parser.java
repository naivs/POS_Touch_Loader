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
        int k = 2;
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = mySheet.getRow(0).getCell(k).getStringCellValue() + "::" + mySheet.getRow(0).getCell(k + 5).getStringCellValue();
            k += 7;
        }

        return groupNames;
    }
    
    public String[] getSubgroupNames(int day, int group) {
        String[] subgroupNames = new String[8];
        Sheet mySheet = wb.getSheetAt(day);
        int k = 2;
        for(int i = 0; i < subgroupNames.length; i++) {
            String buf = mySheet.getRow(k).getCell(group * 7 + 1).getStringCellValue();
            subgroupNames[i] =  buf.isEmpty() ? (i+1) + ":: " : buf + "::" + 
                    mySheet.getRow(k + 10).getCell(group * 7 + 1).getStringCellValue();
            k += 20;
        }
        return subgroupNames;
    }

    public String[] getProducts(int day, int group) {
        List<String> products = new ArrayList<>();
        Sheet mySheet = wb.getSheetAt(day);
        //int k = 2;
        String plu;
        
        for(int i = 2; i < 160; i++) {
            plu = String.valueOf(mySheet.getRow(i).getCell(group * 7 + 1 + 5).getNumericCellValue());
            String buf = plu.equals("0.0") ? " :: " : plu.substring(0, plu.length() - 2) + "::" + mySheet.getRow(i).getCell(group * 7 + 1 + 6).getStringCellValue();
            products.add(buf);
        }
        
//        while (!(plu = String.valueOf(mySheet.getRow(k).getCell(group * 7 + 1 + 5).getNumericCellValue())).equals("0.0")) {
//            String buf;
//            buf = plu.substring(0, plu.length() - 2) + "::" + mySheet.getRow(k).getCell(group * 7 + 1 + 6).getStringCellValue();
//            products.add(buf);
//            k++;
//        }

        String[] prod = new String[products.size()];
        for (int i = 0; i < prod.length; i++) {
            prod[i] = products.get(i);
        }

        return prod;
    }
}
