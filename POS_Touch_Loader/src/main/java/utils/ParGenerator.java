package utils;

import data.Group;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ivan
 */
public class ParGenerator {

    private final ArrayList<String> data;

    public ParGenerator(ArrayList<data.TerminalGroup> configuration, int day, int termGroup) {

        data = new ArrayList();

        Group[] groups = new Group[8];
        //ArrayList<Subgroup> subgroups = new ArrayList();

        for (int c = 0; c < 8; c++) {

            groups[c] = configuration.get(termGroup).getDaysOfWeek()[day].getGroup(c);

//                    for (int d = 0; d < 8; d++) {
//                        if (configuration.get(b).getDaysOfWeek()[day].getGroup(c).getSubgroup(d) != null) {
//
//                            subgroups.add(configuration.get(b).getDaysOfWeek()[day].getGroup(c).getSubgroup(d));
//                        }
//                    }
        }

//        // sorting groups by number
//        for(int i = 0; i < subgroups.size(); i++) {
//            for(int j = subgroups.size() - 1; j > i; j--) {
//                
//                if(Integer.parseInt(subgroups.get(i).getIndex()) > Integer.parseInt(subgroups.get(j).getIndex())) {                   
//                    Collections.swap(subgroups, i, j);
//                }
//            }
//        }
        // sorting subgroups by index
//        for(int i = 0; i < subgroups.size(); i++) {
//            for(int j = subgroups.size() - 1; j > i; j--) {
//                
//                if(Integer.parseInt(subgroups.get(i).getIndex()) > Integer.parseInt(subgroups.get(j).getIndex())) {                   
//                    Collections.swap(subgroups, i, j);
//                }
//            }
//        }
        for (int i = 0; i < 8; i++) {
            if(groups[i] != null)
            data.addAll(Arrays.asList(generateSubgroupBlock(groups[i], i)));
        }

        data.addAll(Arrays.asList(generateGroupBlock(groups)));
    }

    private String[] generateSubgroupBlock(Group group, int groupNumber) {

        String[] out = new String[8];
        Arrays.fill(out, "");

        for (int i = 0; i < 8; i++) {
            String name1, name2, index;

            if (group.getSubgroup(i) != null) {
                String[] names = group.getSubgroup(i).getName().split("::");
                
                if(names.length == 1) {
                    name1 = group.getSubgroup(i).getName().split("::")[0];
                    name2 = "";
                } else {
                    name1 = group.getSubgroup(i).getName().split("::")[0];
                    name2 = group.getSubgroup(i).getName().split("::")[1];
                }
                
                index = group.getSubgroup(i).getIndex();

                while (name1.length() < 12) {
                    name1 = " " + name1;
                }

                while (name2.length() < 18) {
                    name2 = " " + name2;
                }

                out[i] += "PD0" + (10 * (groupNumber + 1) + i) + ":LIST:" + name1 + "*" + index + ":" + name2;
            } else {
                out[i] += "PD0" + (10 * (groupNumber + 1) + i) + ":LIST:            *000:                  ";
            }
        }

        return out;
    }

    private String[] generateGroupBlock(Group[] groups) {

        String[] out = new String[8];
        Arrays.fill(out, "");

        for (int i = 0; i < groups.length; i++) {
            String name1, name2;

            if (groups[i] != null) {
                String[] names = groups[i].getName().split("::");
                
                if(names.length == 1) {
                    name1 = groups[i].getName().split("::")[0];
                    name2 = "";
                } else {
                    name1 = groups[i].getName().split("::")[0];
                    name2 = groups[i].getName().split("::")[1];
                }

                while (name1.length() < 14) {
                    name1 = " " + name1;
                }

                while (name2.length() < 18) {
                    name2 = " " + name2;
                }

                out[i] += "PRES" + (i + 1) + ":DYNA:" + name1 + "0" + (i + 1) + ":" + name2;
            } else {
                out[i] += "PRES" + (i + 1) + ":DYNA:              0" + (i + 1) + ":                  ";
            }
        }

        return out;
    }

    public ArrayList<String> getData() {
        return data;
    }
    
    public File getFile() {
        File tmp = null;
        try {
            tmp = File.createTempFile("~rp", ".tmp");
        } catch (IOException ex) {

        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp), "cp866"))) {
            for (String out : data) {
                bw.write(out + "\r\n");
            }
            bw.flush();
        } catch (IOException ex) {

        }
        return tmp;
    }
}
