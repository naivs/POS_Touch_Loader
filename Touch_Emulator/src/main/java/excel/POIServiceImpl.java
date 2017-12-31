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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ivan Naumov
 */
public class POIServiceImpl implements XLSXService {

    private final int xGroup = 2;
    private final int yGroup = 1;
    private final int xSubgroup = 1;
    private final int ySubgroup = 3;
    private final int xProdPlu = 3;
    private final int yProdPlu = 3;
    private final int xProdName = 4;
    private final int yProdName = 3;
    
    private final int dx = 4;
    private final int dy = 21;
    
    private final Workbook workbook;
    
    public POIServiceImpl(File file) throws FileNotFoundException, IOException {
        workbook = new XSSFWorkbook(new FileInputStream(file));
    }

    @Override
    public boolean isStatic() {
        int daysCount = workbook.getNumberOfSheets();
        return daysCount > 1;
    }

    @Override
    public boolean isDayEmpty(int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGroupEmpty(int day, int group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSubgroupEmpty(int day, int group, int subgroup) {
        boolean isEmpty = false;
        for(int i = 0; i < 20; i++) {
            if(isProductEmpty(day, group, subgroup, i)) {
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    @Override
    public boolean isProductEmpty(int day, int group, int subgroup, int product) {
        return (readProductName(day, group, subgroup, product).equals("") &&
                readProductPlu(day, group, subgroup, product) == 0);
    }

    @Override
    public String[] getGroupNames(int day, int group) {
        String[] names = new String[8];
        for(int i = 0; i < names.length; i++) {
            Cell cellFirst = workbook.getSheetAt(day).getRow(xGroup + group * dx).getCell(yGroup);
            Cell cellSecond = workbook.getSheetAt(day).getRow(xGroup + group * dx + 1).getCell(yGroup);
            
            if(cellFirst.getCellTypeEnum() == CellType.STRING ||
                    cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] = cellFirst.getStringCellValue().trim();
            }
            
            if(cellSecond.getCellTypeEnum() == CellType.STRING ||
                    cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] += "::" + cellSecond.getStringCellValue().trim();
            }
        }
        return names;
    }

    @Override
    public String[] getSubgroupNames(int day, int group, int subgroup) {
        String[] names = new String[8];
        for(int i = 0; i < names.length; i++) {
            Cell cellFirst = workbook.getSheetAt(day).getRow(xSubgroup + subgroup * dx).getCell(ySubgroup);
            Cell cellSecond = workbook.getSheetAt(day).getRow(xSubgroup + subgroup * dx).getCell(ySubgroup + 1);
            
            if(cellFirst.getCellTypeEnum() == CellType.STRING ||
                    cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] = cellFirst.getStringCellValue().trim();
            }
            
            if(cellSecond.getCellTypeEnum() == CellType.STRING ||
                    cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] += "::" + cellSecond.getStringCellValue().trim();
            }
        }
        return names;
    }

    @Override
    public String[] getProductNames(int day, int group, int subgroup) {
        String[] names = new String[20];
        for(int i = 0; i < names.length; i++) {
            names[0] = readProductName(day, group, subgroup, i);
        }
        return names;
    }

    @Override
    public int[] getProductPlu(int day, int group, int subgroup) {
        int[] plus = new int[20];
        for(int i = 0; i < plus.length; i++) {
            plus[0] = readProductPlu(day, group, subgroup, i);
        }
        return plus;
    }

    @Override
    public String readProductName(int day, int group, int subgroup, int product) {
        Cell cell = workbook.getSheetAt(day).getRow(xProdName + group * dx).getCell(yProdName + subgroup * dy + product);
        if (cell.getCellTypeEnum() != CellType.STRING || 
                cell.getCellTypeEnum() == CellType.BLANK) {
            return "";
        } else {
            return cell.getStringCellValue().trim();
        }
    }

    @Override
    public int readProductPlu(int day, int group, int subgroup, int product) {
        Cell cell = workbook.getSheetAt(day).getRow(xProdPlu + group * dx).getCell(yProdPlu + subgroup * dy + product);
        if (cell.getCellTypeEnum() != CellType.NUMERIC || 
                cell.getCellTypeEnum() == CellType.BLANK) {
            return 0;
        } else {
            return (int) cell.getNumericCellValue();
        }
    }
}
