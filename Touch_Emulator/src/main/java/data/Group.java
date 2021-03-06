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
package data;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class Group {

    private final String name;
    private Subgroup[] subgroups;
    
    public Group(String name) {
        this.name = name;
        subgroups = new Subgroup[8];
    }

    public void addSubgroup(Subgroup subgroup) {
        for (int i = 0; i < subgroups.length; i++) {
            if (subgroups[i] == null) {
                subgroups[i] = subgroup;
                break;
            }
        }
    }

    public void removeSubgroup(int index) {
        subgroups[index] = null;
    }
    
    public void setSubgroups(Subgroup[] subgroups) {
        this.subgroups = subgroups;
    }

    public String getName() {
        return name;
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
        //}
    }

    public void downSubroup(int subgroup) {
        int first = subgroup, second = subgroup + 1;

        //if (first < 7) {
            Subgroup buf = subgroups[second];
            subgroups[second] = subgroups[first];
            subgroups[first] = buf;
        //}
    }

    @Override
    public String toString() {

//        if(isDeleted) {
//            return "[is deleted]";
//        }
        return name;
    }
}
