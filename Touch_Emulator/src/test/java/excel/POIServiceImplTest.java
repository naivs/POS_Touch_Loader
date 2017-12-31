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
    
    public POIServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isStatic method, of class POIServiceImpl.
     */
    @Test
    public void testIsStatic() {
        System.out.println("isStatic");
        POIServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isStatic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
