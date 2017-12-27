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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ivan Naumov
 */
public class POIServiceImpl implements XLSXService {

    private final int xGroup = 2;
    private final int yGroup = 1;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isProductEmpty(int day, int group, int subgroup, int product) {
        if(workbook.getSheetAt(day).get)
    }

    @Override
    public String[] getGroupNames(int day, int group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getSubgroupNames(int day, int group, int subgroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getProductNames(int day, int group, int subgroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] getProductPlu(int day, int group, int subgroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Class readCellContent(int sheet, int x, int y) {
        XSSFCell cell = workbook.getSheetAt(sheet).getRow(x).getCell(y);
        
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
                    case CellType.
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:

                    }
    }
}
