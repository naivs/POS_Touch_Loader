/*
 * Copyright (C) 2018 Ivan Naumov
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ivan Naumov
 */
public class POISerializer {

    private final Workbook workbook;
    private final FileOutputStream fos;

    private final int xGroup = 1;
    private final int yGroup = 0;
    private final int xSubgroup = 0;
    private final int ySubgroup = 2;
    private final int xProdPlu = 2;
    private final int yProdPlu = 2;
    private final int xProdName = 3;
    private final int yProdName = 2;

    public POISerializer(File file) throws FileNotFoundException, IOException {
        fos = new FileOutputStream(file);
        workbook = new XSSFWorkbook();
    }

    public void applyPattern() {
        int dx = 4;
        int dy = 10;

        for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
            for (int y = 2; y < 162;) {
                for (int x = 0; x < 29;) {
                    workbook.getSheetAt(s).addMergedRegion(new CellRangeAddress(y, y + 9, x, x));
                    x += dx;
                }
                y += dy;
            }
        }
    }

    public void createDays(String[] dayNames) {
        for (String day : dayNames) {
            Sheet sheet = workbook.createSheet(day);
            for (int i = 0; i < 162; i++) {
                sheet.createRow(i);
            }
        }
    }

    public void createGroups(int day, String[] groupNames) {
        Sheet sheet = workbook.getSheetAt(day);
        Row row = sheet.getRow(yGroup);
        int dx = 4;

        for (int i = 0; i < groupNames.length; i++) {
            String[] name = groupNames[i].split("::");
            if (name.length > 0) {
                if (name.length == 2) {
                    row.createCell(xGroup + i * dx + 1, CellType.STRING).setCellValue(name[1].trim());
                }
                row.createCell(xGroup + i * dx, CellType.STRING).setCellValue(name[0].trim());
            }
        }
    }

    public void createSubgroups(int day, int group, String[] subgroupNames) {
        Sheet sheet = workbook.getSheetAt(day);
        int dx = 4;
        int dy = 20;

        for (int i = 0; i < subgroupNames.length; i++) {
            String[] name = subgroupNames[i].split("::");
            if (name.length > 0) {
                if (name.length == 2) {
                    sheet.getRow(ySubgroup + i * dy + 10).createCell(xSubgroup + group * dx).setCellValue(name[1].trim());
                }
                sheet.getRow(ySubgroup + i * dy).createCell(xSubgroup + group * dx).setCellValue(name[0].trim());
            }
        }
    }
    
    public void createProductNames(int day, int group, int subgroup, String[] productNames) {
        
    }
    
    public void createProductPlus(int day, int group, int subgroup, String[] productPlu) {
        
    }

    public void write() throws IOException {
        workbook.write(fos);
    }

    public void close() throws IOException {
        workbook.close();
        fos.close();
    }
}
