package data;

import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class Group {

    private final String name;
    private Subgroup[] subgroups;
    private final String creationDate;
    private boolean isValid;
    private String modifiedDate;

    public Group(String name) {

        this(name, Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString());
    }
    
    public Group(String name, String creationDate, String modifiedDate) {
        
        this.name = name;
        subgroups = new Subgroup[8];
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }

    public void addSubgroup(Subgroup subgroup) {

        for (int i = 0; i < subgroups.length; i++) {
            if (subgroups[i] == null) {
                subgroups[i] = subgroup;
                updateTime();
                break;
            }
        }
    }
    
    public void updateTime() {
        modifiedDate = Calendar.getInstance().getTime().toString();
    }
    
    public String getModifiedDate() {
        return modifiedDate;
    }

    public void removeSubgroup(int index) {
        subgroups[index] = null;
        updateTime();
    }
    
    public void setSubgroups(Subgroup[] subgroups) {
        this.subgroups = subgroups;
    }

    public String getName() {
        return name;
    }

    public boolean isValid() {
        return isValid;
    }
    
    public void setRemoved(boolean statement) {
        isValid = statement;
    }

    public Subgroup getSubgroup(int index) {
        return subgroups[index];
    }

    public String[] getSubgroupsAsStringArray() {

        String[] sgrp = new String[subgroups.length];

        for (int i = 0; i < sgrp.length; i++) {
            if (subgroups[i] != null) {
                sgrp[i] = subgroups[i].toString();
            }
        }

        return sgrp;
    }

    public DefaultTableModel getSubgroupsAsTableModel() {

        DefaultTableModel dtm = new DefaultTableModel(8, 1);

        int i = 0;
        for (Subgroup sgrp : subgroups) {

            if (sgrp != null) {
                dtm.setValueAt(sgrp.toString(), i, 0);
            }
            i++;
        }

        return dtm;
    }

    public int getSubgroupCount() {
        int count = 0;

        for (Subgroup sgrp : subgroups) {
            if (sgrp != null) {
                count++;
            }
        }

        return count;
    }

    public int getSubgroupNumber(String subgroup) {
        
        int number = -1;
        
        for(int i = 0; i < subgroups.length; i++) {
            if(subgroups[i] != null)
                if(subgroup.equals(subgroups[i].toString())) {
                    number = i;
                }
        }
        
        return number;
    }
    
    public void upSubroup(int subgroup) {
        int first = subgroup, second = subgroup - 1;

        //if (first > 0) {
            Subgroup buf = subgroups[second];
            subgroups[second] = subgroups[first];
            subgroups[first] = buf;
            updateTime();
        //}
    }

    public void downSubroup(int subgroup) {
        int first = subgroup, second = subgroup + 1;

        //if (first < 7) {
            Subgroup buf = subgroups[second];
            subgroups[second] = subgroups[first];
            subgroups[first] = buf;
            updateTime();
        //}
    }

    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {

//        if(isDeleted) {
//            return "[is deleted]";
//        }
        return name;
    }
}
