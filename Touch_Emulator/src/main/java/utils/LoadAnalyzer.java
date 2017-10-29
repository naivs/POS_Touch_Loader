package utils;

import java.util.ArrayList;
import data.TerminalGroup;

/**
 *
 * @author ivan
 */
public class LoadAnalyzer {

    private float[][] load;

    final int MAX_PROD = 20 * 8 * 8;

    public LoadAnalyzer(ArrayList<TerminalGroup> configuration) {

        load = new float[7][MAX_PROD];
        int[][] prodCount = new int[7][MAX_PROD];

        for (int i = 0; i < configuration.size(); i++) {
            for (int j = 0; j < configuration.get(i).getDaysOfWeek().length; j++) {

                prodCount[j][i] = 0;

                for (int k = 0; k < configuration.get(i).getDaysOfWeek()[j].getGroupCount(); k++) {
                    if (configuration.get(i).getDaysOfWeek()[j].getGroup(k) != null) {
                        for (int l = 0; l < configuration.get(i).getDaysOfWeek()[j].getGroup(k).getSubgroupCount(); l++) {
                            if (configuration.get(i).getDaysOfWeek()[j].getGroup(k).getSubgroup(l) != null) {
                                prodCount[j][i] += configuration.get(i).getDaysOfWeek()[j].getGroup(k).getSubgroup(l).getProductCount();
                            }
                        }
                    }
                }

                load[j][i] = prodCount[j][i] / (MAX_PROD / 100f);
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