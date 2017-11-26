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

    private final Workbook wb;

    public Parser(File file) throws FileNotFoundException, IOException {
        wb = new XSSFWorkbook(new FileInputStream(file));
    }

    public String[] getGroupsNames() {
        String[] groupNames = new String[8];
        Sheet mySheet = wb.getSheetAt(0);
        int k = 1;
        for (int i = 0; i < groupNames.length; i++) {
            String buf = mySheet.getRow(0).getCell(k).getStringCellValue().replace("\n", "").trim();
            buf = buf.length() > 14 ? buf.substring(0, 14) : buf;
            String buf2 = mySheet.getRow(0).getCell(k + 1).getStringCellValue().replace("\n", "").trim();
            buf2 = buf2.length() > 18 ? buf2.substring(0, 18) : buf2;
            groupNames[i] = buf + "::" + buf2;
            k += 4;
        }
        return groupNames;
    }

    public String[] getSubgroupNames(int day, int group) {
        String[] subgroupNames = new String[8];
        Sheet mySheet = wb.getSheetAt(day);
        int k = 2;
        for (int i = 0; i < subgroupNames.length; i++) {
            String buf = mySheet.getRow(k).getCell(group * 4).getStringCellValue().replace("\n", "").trim();
            buf = buf.length() > 12 ? buf.substring(0, 12) : buf;
            String buf2 = mySheet.getRow(k + 10).getCell(group * 4).getStringCellValue();
            buf2 = buf2.length() > 18 ? buf2.substring(0, 18) : buf2;
            subgroupNames[i] = buf.isEmpty() ? (i + 1) + ":: " : buf + "::" + buf2;
            k += 20;
        }
        return subgroupNames;
    }

    public String[] getProducts(int day, int group) {
        List<String> products = new ArrayList<>();
        Sheet mySheet = wb.getSheetAt(day);
        long plu;
        String buf;
        
        for (int i = 2; i < 162; i++) {
            try {
                plu = new Double(mySheet.getRow(i).getCell(group * 4 + 2).getNumericCellValue()).longValue();
                buf = " :: ";
                if(plu != 0) {
                    buf = String.valueOf(plu) + "::" + mySheet.getRow(i).getCell(group * 4 + 2 + 1).getStringCellValue().replace("\n", "").trim();
                }
                products.add(buf);
            } catch (IllegalStateException ex) {
                System.err.println(String.format("Day: %d; Group: %d; Product: %d", day+1, group+1, i-2));
                throw ex;
            }
        }

        String[] prod = new String[products.size()];
        for (int i = 0; i < prod.length; i++) {
            prod[i] = products.get(i);
        }
        return prod;
    }
}
