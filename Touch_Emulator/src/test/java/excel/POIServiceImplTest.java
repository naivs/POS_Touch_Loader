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
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static POISerializer poiSerializer;
    private static XLSXService xlsxService;

    private static ContentDispenser dispenser;

    private static final String[] dayNames = {
        "Понедельник",
        "Вторник",
        "Среда",
        "Четверг",
        "Пятница",
        "Суббота",
        "Воскресенье"
    };

    //for EAN-13 test
    private static final long ean_13Plu = 1234567891234L;

    private final int day1 = 1, day2 = 5;
    private final int group1 = 0, group2 = 4;
    private final int subGroup1 = 4, subGroup2 = 6;

//    @BeforeClass
//    @SuppressWarnings("CallToPrintStackTrace")
//    public static void setUpClass() {
//
//    }
//
//    @AfterClass
//    @SuppressWarnings("CallToPrintStackTrace")
//    public static void tearDownClass() {
//
//    }
    @Before
    @SuppressWarnings("CallToPrintStackTrace")
    public void setUp() {
        tableFile = new File("test.xlsx");

        try {
            tableFile.createNewFile();
            poiSerializer = new POISerializer(tableFile);
            dispenser = new ContentDispenser();

            poiSerializer.createDays(dayNames);
            poiSerializer.applyPattern();
            xlsxService = new POIServiceImpl(tableFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            fail();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @After
    @SuppressWarnings("CallToPrintStackTrace")
    public void tearDown() {
        try {
            poiSerializer.close();
            xlsxService.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }

        System.out.print("test file delete...          ");
        String result = tableFile.delete() ? "[OK]" : "[fail]";
        System.out.println(result);
    }

//    @Test
//    @SuppressWarnings("CallToPrintStackTrace")
//    public void testExcelSerialization() {
//        System.out.println("excelSerialization");
//
//        try {
//            poiSerializer.createDays(dayNames);
//            poiSerializer.applyPattern();
//            for (int i = 0; i < dayNames.length; i++) {
//                poiSerializer.createGroups(i, dispenser.getGroupNames());
//                for (int j = 0; j < 8; j++) {
//                    poiSerializer.createSubgroups(i, j, dispenser.getSubgroupNames());
//                    for (int p = 0; p < 8; p++) {
//                        poiSerializer.createProductNames(i, j, p, dispenser.getProductNames());
//                        poiSerializer.createProductPlus(i, j, p, dispenser.getProductPlus());
//                    }
//                }
//            }
//
//            poiSerializer.write();
//            System.out.println("Test file created succecsfully!");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            fail();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            fail();
//        }
//    }
    @Test
    public void testIsStatic() {
        System.out.println("isStatic");
        assertArrayEquals(dayNames, xlsxService.getDayNames());

        boolean expResult = false;
        boolean result = !(xlsxService.getDayNames().length >= 7);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of isDayEmpty method, of class POIServiceImpl.
//     */
//    @Test
//    public void testIsDayEmpty() {
//        System.out.println("isDayEmpty");
//        int day = 0;
//        POIServiceImpl instance = null;
//        boolean expResult = false;
//        boolean result = instance.isDayEmpty(day);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isGroupEmpty method, of class POIServiceImpl.
//     */
//    @Test
//    public void testIsGroupEmpty() {
//        System.out.println("isGroupEmpty");
//        int day = 0;
//        int group = 0;
//        POIServiceImpl instance = null;
//        boolean expResult = false;
//        boolean result = instance.isGroupEmpty(day, group);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isSubgroupEmpty method, of class POIServiceImpl.
//     */
//    @Test
//    public void testIsSubgroupEmpty() {
//        System.out.println("isSubgroupEmpty");
//        int day = 0;
//        int group = 0;
//        int subgroup = 0;
//        POIServiceImpl instance = null;
//        boolean expResult = false;
//        boolean result = instance.isSubgroupEmpty(day, group, subgroup);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isProductEmpty method, of class POIServiceImpl.
//     */
//    @Test
//    public void testIsProductEmpty() {
//        System.out.println("isProductEmpty");
//        int day = 0;
//        int group = 0;
//        int subgroup = 0;
//        int product = 0;
//        POIServiceImpl instance = null;
//        boolean expResult = false;
//        boolean result = instance.isProductEmpty(day, group, subgroup, product);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//

    /**
     * Test of getGroupNames method, of class POIServiceImpl.
     */
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void testGetGroupNames() {
        System.out.println("getGroupNames");
        String[] groups = dispenser.getGroupNames();

        try {
            poiSerializer.createGroups(day1, groups);
            poiSerializer.createGroups(day2, groups);
            poiSerializer.write();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        }

        assertArrayEquals(groups, xlsxService.getGroupNames(day1));
        assertArrayEquals(groups, xlsxService.getGroupNames(day2));
    }

    /**
     * Test of getSubgroupNames method, of class POIServiceImpl.
     */
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void testGetSubgroupNames() {
        System.out.println("getSubgroupNames");
        String[] subGroups = dispenser.getSubgroupNames();

        try {
            poiSerializer.createSubgroups(day1, group1, subGroups);
            poiSerializer.createSubgroups(day2, group2, subGroups);
            poiSerializer.write();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        }

        assertArrayEquals(subGroups, xlsxService.getSubgroupNames(day1, group1));
        assertArrayEquals(subGroups, xlsxService.getSubgroupNames(day2, group2));
    }

    /**
     * Test of getProductNames method, of class POIServiceImpl.
     */
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void testGetProductNames() {
        System.out.println("getProductNames");
        String[] productNames = dispenser.getProductNames();

        try {
            poiSerializer.createProductNames(day1, group1, subGroup1, productNames);
            poiSerializer.createProductNames(day2, group2, subGroup2, productNames);
            poiSerializer.write();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        }

        assertArrayEquals(productNames, xlsxService.getProductNames(day1, group1, subGroup1));
        assertArrayEquals(productNames, xlsxService.getProductNames(day2, group2, subGroup2));
    }

    /**
     * Test of getProductPlu method, of class POIServiceImpl.
     */
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void testGetProductPlu() {
        System.out.println("getProductPlu");
        int[] productPlus = dispenser.getProductPlus();

        try {
            poiSerializer.createProductPlus(day1, group1, subGroup1, productPlus);
            poiSerializer.createProductPlus(day2, group2, subGroup2, productPlus);
            poiSerializer.write();
        } catch (IOException ex) {
            ex.printStackTrace();
            fail();
        }

        assertArrayEquals(productPlus, xlsxService.getProductPlu(day1, group1, subGroup1));
        assertArrayEquals(productPlus, xlsxService.getProductPlu(day2, group2, subGroup2));
    }
}
