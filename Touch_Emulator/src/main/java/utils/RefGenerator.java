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
import data.Product;
import data.Subgroup;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ivan
 */
public class RefGenerator {

    private final ArrayList<String> data;

    public RefGenerator(data.Day day) {

        data = new ArrayList();

        // getting all subgroups per day
        ArrayList<Subgroup> subgroups = new ArrayList();

        for (int c = 0; c < day.getComponentsCount(); c++) {
            Group group = (Group) day.getComponent(c);
            for (int d = 0; d < group.getComponentsCount(); d++) {
                Subgroup subgroup = (Subgroup) group.getComponent(d);
                subgroups.add(subgroup);
            }
        }

        // sorting subgroups by index
        for (int i = 0; i < subgroups.size(); i++) {
            for (int j = subgroups.size() - 1; j > i; j--) {
                if (subgroups.get(i).getIndex() > subgroups.get(j).getIndex()) {
                    Collections.swap(subgroups, i, j);
                }
            }
        }

        // add lines to data
        subgroups.forEach((subgroup) -> {
            data.addAll(Arrays.asList(generateBlock(subgroup)));
        });
    }

    private String[] generateBlock(Subgroup subgroup) {

        String[] out = new String[subgroup.getComponentsCount()];
        Arrays.fill(out, "");
        String number; //must be len 2;
        String plu; //must be len = 16

        for (int i = 0; i < subgroup.getComponentsCount(); i++) {

            number = String.valueOf(i + 1);
            if (number.length() == 1) {
                number = "0" + number;
            }

//            if (subgroup.getProduct(i) != null) {
                plu = String.valueOf(((Product) subgroup.getComponent(i)).getPlu());

                while (plu.length() < 16) {
                    plu = " " + plu;
                }

                out[i] += "*" + subgroup.getIndex() + ":  " + number + ":" + plu + ":0000:                    ";
//            } else {
//                out[i] += "*" + subgroup.getIndex() + ":  " + number + ":                :0000:                    ";
//            }
        }

        return out;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public File getFile() {
        File tmp = null;
        try {
            tmp = File.createTempFile("~pr", ".tmp");
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
