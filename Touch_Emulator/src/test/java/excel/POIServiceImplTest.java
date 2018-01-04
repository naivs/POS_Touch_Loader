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
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ivan Naumov
 */
public class POIServiceImplTest {

    private static File tableFile;
    private XLSXService instance;

    private static final String sheetName = "Понедельник";
    /*
        the first part must be have max 14 symbols
        the second part must be have max 18 symbols
        divides by "::"
     */
    private static final String groupName = "Завтраки БлиныFFF::Вареники Пельмени FFF";
    /*
        the first part must be have max 12 symbols
        the second part must be have max 18 symbols
        divides by "::"
     */
    private static final String subgroupName = "СвежевыжатыеFFF::соки              FFF";
    private static final String productName = "Сливочный торт";
    private static final int productPlu = 112233;
    //for EAN-13 test
    private static final long ean_13Plu = 1234567891234L;

    @SuppressWarnings("CallToPrintStackTrace")
    private XLSXService getServiceInstance() {
        try {
            instance = new POIServiceImpl(tableFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return instance;
    }

    @BeforeClass
    @SuppressWarnings("CallToPrintStackTrace")
    public static void setUpClass() {
        tableFile = new File("test.xlsx");

        try {
            tableFile.createNewFile();
            Workbook workbook = new XSSFWorkbook();
            Cell cell1;
            Cell cell2;
            //create sheet
            workbook.createSheet(sheetName);
            Sheet sheet = workbook.getSheetAt(0);
            //create groups
            cell1 = sheet.createRow(0).createCell(1, CellType.STRING);
            cell2 = sheet.createRow(0).createCell(2, CellType.STRING);
            cell1.setCellValue(groupName.split("::")[0]);
            cell1.setCellValue(groupName.split("::")[1]);
            cell1 = sheet.createRow(0).createCell(29, CellType.STRING);
            cell2 = sheet.createRow(0).createCell(30, CellType.STRING);
            cell1.setCellValue(groupName.split("::")[0]);
            cell1.setCellValue(groupName.split("::")[1]);
            //create subgroups
            cell1 = sheet.createRow(2).createCell(0, CellType.STRING);
            cell2 = sheet.createRow(3).createCell(0, CellType.STRING);
            cell1.setCellValue(subgroupName.split("::")[0]);
            cell1.setCellValue(subgroupName.split("::")[1]);
            cell1 = sheet.createRow(16).createCell(28, CellType.STRING);
            cell2 = sheet.createRow(17).createCell(28, CellType.STRING);
            cell1.setCellValue(subgroupName.split("::")[0]);
            cell1.setCellValue(subgroupName.split("::")[1]);
            //create products
            cell1 = sheet.createRow(2).createCell(0, CellType.NUMERIC);
            cell2 = sheet.createRow(3).createCell(0, CellType.STRING);
            
            workbook.write(new FileOutputStream(tableFile));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    @SuppressWarnings("CallToPrintStackTrace")
    public static void tearDownClass() {
        System.out.print("test file delete...          ");
        String result = tableFile.delete() ? "[OK]" : "[fail]";
        System.out.println(result);
    }

    @Before
    @SuppressWarnings("CallToPrintStackTrace")
    public void setUp() {
        instance = getServiceInstance();
    }

    @After
    public void tearDown() {
        instance.close();
    }

    @Test
    public void testIsStatic() {
        System.out.println("isStatic");
        boolean expResult = true;
        boolean result = !(instance.getDayNames().length > 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of isDayEmpty method, of class POIServiceImpl.
     */
    @Test
    public void testIsDayEmpty() {
        System.out.println("isDayEmpty");
        int day = 0;
        POIServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isDayEmpty(day);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGroupEmpty method, of class POIServiceImpl.
     */
    @Test
    public void testIsGroupEmpty() {
        System.out.println("isGroupEmpty");
        int day = 0;
        int group = 0;
        POIServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isGroupEmpty(day, group);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSubgroupEmpty method, of class POIServiceImpl.
     */
    @Test
    public void testIsSubgroupEmpty() {
        System.out.println("isSubgroupEmpty");
        int day = 0;
        int group = 0;
        int subgroup = 0;
        POIServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isSubgroupEmpty(day, group, subgroup);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isProductEmpty method, of class POIServiceImpl.
     */
    @Test
    public void testIsProductEmpty() {
        System.out.println("isProductEmpty");
        int day = 0;
        int group = 0;
        int subgroup = 0;
        int product = 0;
        POIServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isProductEmpty(day, group, subgroup, product);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupNames method, of class POIServiceImpl.
     */
    @Test
    public void testGetGroupNames() {
        System.out.println("getGroupNames");
        int day = 0;
        int group = 0;
        POIServiceImpl instance = null;
        String[] expResult = null;
        String[] result = instance.getGroupNames(day, group);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubgroupNames method, of class POIServiceImpl.
     */
    @Test
    public void testGetSubgroupNames() {
        System.out.println("getSubgroupNames");
        int day = 0;
        int group = 0;
        int subgroup = 0;
        POIServiceImpl instance = null;
        String[] expResult = null;
        String[] result = instance.getSubgroupNames(day, group, subgroup);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductNames method, of class POIServiceImpl.
     */
    @Test
    public void testGetProductNames() {
        System.out.println("getProductNames");
        int day = 0;
        int group = 0;
        int subgroup = 0;
        POIServiceImpl instance = null;
        String[] expResult = null;
        String[] result = instance.getProductNames(day, group, subgroup);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductPlu method, of class POIServiceImpl.
     */
    @Test
    public void testGetProductPlu() {
        System.out.println("getProductPlu");
        int day = 0;
        int group = 0;
        int subgroup = 0;
        POIServiceImpl instance = null;
        int[] expResult = null;
        int[] result = instance.getProductPlu(day, group, subgroup);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
