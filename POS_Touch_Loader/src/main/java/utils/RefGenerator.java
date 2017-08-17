package utils;

import data.Subgroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ivan
 */
public class RefGenerator {

    private final ArrayList<String> data;

    public RefGenerator(ArrayList<data.TerminalGroup> configuration, int day) {

        data = new ArrayList();

        // getting all subgroups per day
        ArrayList<Subgroup> subgroups = new ArrayList();

        for (int b = 0; b < configuration.size(); b++) {

            for (int c = 0; c < 8; c++) {
                if (configuration.get(b).getDaysOfWeek()[day].getGroup(c) != null) {

                    for (int d = 0; d < 8; d++) {
                        if (configuration.get(b).getDaysOfWeek()[day].getGroup(c).getSubgroup(d) != null) {

                            subgroups.add(configuration.get(b).getDaysOfWeek()[day].getGroup(c).getSubgroup(d));
                        }
                    }
                }
            }
        }

        // sorting subgroups by index
        for(int i = 0; i < subgroups.size(); i++) {
            for(int j = subgroups.size() - 1; j > i; j--) {
                
                if(Integer.parseInt(subgroups.get(i).getIndex()) > Integer.parseInt(subgroups.get(j).getIndex())) {                   
                    Collections.swap(subgroups, i, j);
                }
            }
        }
        
        // add lines to data
        for(Subgroup subgroup : subgroups) {
            data.addAll(Arrays.asList(generateBlock(subgroup)));
        }
    }

    private String[] generateBlock(Subgroup subgroup) {

        String[] out = new String[20];
        Arrays.fill(out, "");
        String number; //must be len 2;
        String plu; //must be len = 16

        for (int i = 0; i < 20; i++) {

            number = String.valueOf(i + 1);
            if (number.length() == 1) {
                number = "0" + number;
            }

            if (subgroup.getProduct(i) != null) {
                plu = subgroup.getProduct(i).getPlu();

                while (plu.length() < 16) {
                    plu = " " + plu;
                }

                out[i] += "*" + subgroup.getIndex() + ":  " + number + ":" + plu + ":0000:                    ";
            } else {
                out[i] += "*" + subgroup.getIndex() + ":  " + number + ":                :0000:                    ";
            }
        }

        return out;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
