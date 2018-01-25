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

    private final int xGroup = 1;
    private final int yGroup = 0;
    private final int xSubgroup = 0;
    private final int ySubgroup = 2;
    private final int xProdPlu = 2;
    private final int yProdPlu = 2;
    private final int xProdName = 3;
    private final int yProdName = 2;

    private final Workbook workbook;

    public POIServiceImpl(File file) throws FileNotFoundException, IOException {
        workbook = new XSSFWorkbook(new FileInputStream(file));
    }

    @Override
    public boolean isDayEmpty(int day) {
        boolean isEmpty = true;
        for (int i = 0; i < 8; i++) {
            if (!isGroupEmpty(day, i)) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public boolean isGroupEmpty(int day, int group) {
        boolean isEmpty = true;
        for (int i = 0; i < 8; i++) {
            if (!isSubgroupEmpty(day, group, i)) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public boolean isSubgroupEmpty(int day, int group, int subgroup) {
        boolean isEmpty = true;
        for (int i = 0; i < 20; i++) {
            if (!isProductEmpty(day, group, subgroup, i)) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public boolean isProductEmpty(int day, int group, int subgroup, int product) {
        return (readProductName(day, group, subgroup, product).equals("")
                && readProductPlu(day, group, subgroup, product) == 0);
    }

    @Override
    public String[] getDayNames() {
        int count = workbook.getNumberOfSheets();
        String[] names = (count >= 1 && count < 7) ? new String[1] : new String[7];

        for (int i = 0; i < names.length; i++) {
            names[i] = workbook.getSheetAt(i).getSheetName();
        }
        return names;
    }

    @Override
    public String[] getGroupNames(int day) {
        int dx = 4;

        String[] names = new String[8];
        for (int i = 0; i < names.length; i++) {
            Cell cellFirst = workbook.getSheetAt(day).getRow(yGroup).getCell(xGroup + i * dx);
            Cell cellSecond = workbook.getSheetAt(day).getRow(yGroup).getCell(xGroup + i * dx + 1);

            if (cellFirst == null || cellFirst.getCellTypeEnum() == CellType.STRING
                    || cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] = cellFirst.getStringCellValue().trim();
            }

            if (cellSecond == null || cellSecond.getCellTypeEnum() == CellType.STRING
                    || cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] += "::" + cellSecond.getStringCellValue().trim();
            }
        }
        return names;
    }

    @Override
    public String[] getSubgroupNames(int day, int group) {
        int dx = 4;
        int dy = 20;

        String[] names = new String[8];
        for (int i = 0; i < names.length; i++) {
            Cell cellFirst = workbook.getSheetAt(day).getRow(ySubgroup + i * dy).getCell(xSubgroup + group * dx);
            Cell cellSecond = workbook.getSheetAt(day).getRow(ySubgroup + i * dy + 10).getCell(xSubgroup + group * dx);

            if (cellFirst == null || cellFirst.getCellTypeEnum() == CellType.STRING
                    || cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] = cellFirst.getStringCellValue().trim();
            }

            if (cellSecond == null || cellSecond.getCellTypeEnum() == CellType.STRING
                    || cellFirst.getCellTypeEnum() == CellType.BLANK) {
                names[i] += "::" + cellSecond.getStringCellValue().trim();
            }
        }
        return names;
    }

    @Override
    public String[] getProductNames(int day, int group, int subgroup) {
        String[] names = new String[20];
        for (int i = 0; i < names.length; i++) {
            names[i] = readProductName(day, group, subgroup, i);
        }
        return names;
    }

    @Override
    public int[] getProductPlu(int day, int group, int subgroup) {
        int[] plus = new int[20];
        for (int i = 0; i < plus.length; i++) {
            plus[i] = readProductPlu(day, group, subgroup, i);
        }
        return plus;
    }

    @Override
    public String readProductName(int day, int group, int subgroup, int product) {
        try {
            int dx = 4;
            int dy = 20;

            Cell cell = workbook.getSheetAt(day).getRow(yProdName + subgroup * dy + product).getCell(xProdName + group * dx);
            if (cell == null || cell.getCellTypeEnum() != CellType.STRING
                    || cell.getCellTypeEnum() == CellType.BLANK) {
                return "";
            } else {
                return cell.getStringCellValue().trim();
            }
        } catch (Exception ex) {
            System.err.println(
                    String.format(
                            "Cell failure: day=%d group=%d subgroup=%d product=%d", day + 1, group + 1, subgroup + 1, product + 1)
            );
            throw ex;
        }
    }

    @Override
    public int readProductPlu(int day, int group, int subgroup, int product) {
        try {
            int dx = 4;
            int dy = 20;

            Cell cell = workbook.getSheetAt(day).getRow(yProdPlu + subgroup * dy + product).getCell(xProdPlu + group * dx);
            if (cell == null || cell.getCellTypeEnum() != CellType.NUMERIC
                    || cell.getCellTypeEnum() == CellType.BLANK) {
                return 0;
            } else {
                return (int) cell.getNumericCellValue();
            }
        } catch (Exception ex) {
            System.err.println(
                    String.format(
                            "Cell failure: day=%d group=%d subgroup=%d product=%d", day + 1, group + 1, subgroup + 1, product + 1)
            );
            throw ex;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void close() {
        try {
            workbook.close();
        } catch (IOException ex) {
            System.err.println("Unable to close stream.");
            ex.printStackTrace();
        }
    }
}
