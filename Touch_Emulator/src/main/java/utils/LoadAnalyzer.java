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
 */package utils;

import java.util.ArrayList;
import data.Department;
import java.util.Arrays;

/**
 *
 * @author ivan
 */
public class LoadAnalyzer {
    private final float[][] load; // load table (x - departments; y - days)
    private final int MAX_MAG_PRODUCTS = 19_980;
    //private final int MAX_POS_PRODUCTS = 320;

    public LoadAnalyzer(ArrayList<Department> configuration) {
        load = new float[7][configuration.size()];

        for (int i = 0; i < configuration.size(); i++) {
            for (int j = 0; j < configuration.get(i).getDaysOfWeek().length; j++) {
                int[] prodCount = new int[7];
                Arrays.fill(prodCount, 0);
                
                data.Day day = configuration.get(i).getDaysOfWeek()[j];
                for (int k = 0; k < day.getGroupCount(); k++) {
                    if (day.getGroup(k) != null) {
                        for (int l = 0; l < day.getGroup(k).getSubgroupCount(); l++) {
                            if (day.getGroup(k).getSubgroup(l) != null) {
                                prodCount[j] += 20;
                            }
                        }
                    }
                }
                load[j][i] = (MAX_MAG_PRODUCTS / prodCount[j]) * 100f;
            }
        }
    }

    public float[][] getDaysLoad() {
        return load;
    }

    public boolean groupIsLoad(int terminalGroup) {
        boolean isLoad = false;        
        for (int j = 0; j < load.length; j++) {
            if (load[j][terminalGroup] != 0) {
                isLoad = true;
                break;
            }
        }
        return isLoad;
    }
    
    public boolean dayIsLoad(int day) {
        boolean isLoad = false;
        for (int j = 0; j < load[day].length; j++) {
            if (load[day][j] != 0) {
                isLoad = true;
                break;
            }
        }
        return isLoad;
    }
    
    public boolean isLoad(int terminalGroup, int day) {
        return load[day][terminalGroup] != 0;
    }
}
