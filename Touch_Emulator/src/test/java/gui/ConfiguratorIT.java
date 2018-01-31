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
package gui;

import java.io.File;
import java.util.Observable;
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
public class ConfiguratorIT {
    
    public ConfiguratorIT() {
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

    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        Configurator instance = new Configurator();
        instance.update(o, arg);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        File f = null;
        Configurator instance = new Configurator();
        instance.delete(f);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowMessage() {
        System.out.println("showMessage");
        int TYPE = 0;
        String message = "";
        int LEVEL = 0;
        Configurator instance = new Configurator();
        instance.showMessage(TYPE, message, LEVEL);
        fail("The test case is a prototype.");
    }

    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Configurator.main(args);
        fail("The test case is a prototype.");
    }
    
}
