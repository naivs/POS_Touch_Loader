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
package utils;

import data.Group;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public class ParGenerator {

    private final ArrayList<String> data;

    public ParGenerator(data.Day day) {
        data = new ArrayList();
        
        for (int a = 0; a < day.getComponentsCount(); a++) {
            generateSubgroupBlock(day.getGroup(a), a);
        }

        generateGroupBlock(day.getGroupsAsStringArray());
    }

    private void generateSubgroupBlock(Group group, int groupNumber) {
        StringBuilder subgroupString;

        for (int i = 0; i < group.getComponentsCount(); i++) {
            subgroupString = new StringBuilder("PD0__:LIST:            *___:                  ");
            String name1, name2, index;

            String[] names = group.getSubgroup(i).getName().split("::");
            if (names.length == 1) {
                name1 = names[0];
                name2 = "";
            } else {
                name1 = names[0];
                name2 = names[1];
            }

            index = group.getSubgroup(i).getIndex();

            subgroupString.replace(3, 4, String.valueOf(groupNumber + 1));
            subgroupString.replace(4, 5, String.valueOf(i));
            subgroupString.replace(11, 11 + name1.length(), name1);
            subgroupString.replace(24, 27, index);
            subgroupString.replace(28, 28 + name2.length(), name2);

            data.add(subgroupString.toString());
        }
    }

    private void generateGroupBlock(String[] groups) {
        StringBuilder groupString;

        for (int i = 0; i < groups.length; i++) {
            groupString = new StringBuilder("PRES_:DYNA:              0_:                  ");
            String name1, name2;

            String[] names = groups[i].split("::");
            switch (names.length) {
                case 0:
                    name1 = "";
                    name2 = "";
                    break;
                case 1:
                    name1 = names[0];
                    name2 = "";
                    break;
                default:
                    name1 = names[0];
                    name2 = names[1];
                    break;
            }
               
            groupString.replace(4, 5, String.valueOf(i + 1));
            groupString.replace(11, 11 + name1.length(), name1);
            groupString.replace(26, 27, String.valueOf(i + 1));
            groupString.replace(28, 28 + name2.length(), name2);
            
            data.add(groupString.toString());
        }
    }

    public ArrayList<String> getData() {
        return data;
    }
    
    public File getFile() {
        File tmp = null;
        try {
            tmp = File.createTempFile("~rp", ".tmp");
        } catch (IOException ex) {
            System.err.println("Unable to create .tmp file: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp), "cp866"))) {
            for (String out : data) {
                bw.write(out + "\r\n");
            }
            bw.flush();
        } catch (IOException ex) {
            System.err.println("I/O Error while write data: " + ex.getMessage());
        }
        return tmp;
    }
}
