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

    public void createDays(String[] dayNames) {
        for (String day : dayNames) {
            workbook.createSheet(day);
        }
    }

    public void createGroups(int day, String[] groupNames) {
        Sheet sheet = workbook.getSheetAt(day);
        Row row = sheet.createRow(yGroup);
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
        
    }

    public void write() throws IOException {
        workbook.write(fos);
    }
    
    public void close() throws IOException {
        workbook.close();
        fos.close();
    }
}
