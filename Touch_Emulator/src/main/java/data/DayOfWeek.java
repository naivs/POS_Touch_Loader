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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan
 */
public class DayOfWeek {

    private final String name;
    private final List<Group> groups;
    
    public DayOfWeek(String name) {
        this.name = name;
        groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
    
    public void addAllGroups(Group[] groups) {
        this.groups.addAll(Arrays.asList(groups));
    }

    public void removeGroup(int group) {
        groups.remove(group);
    }
    
    public void deleteAllGroups() {
        groups.clear();
    }

    public String[] getGroupsAsStringArray() {
        String[] grp = new String[8];
        Arrays.fill(grp, "");
        
        for (int i = 0; i < groups.size(); i++) {
                grp[i] = groups.get(i).toString();
        }
        return grp;
    }

    public DefaultTableModel getGroupsAsTableModel() {
        DefaultTableModel dtm = new DefaultTableModel(8, 1);

        int i = 0;
        for (Group grp : groups) {

            if (grp != null) {
                dtm.setValueAt(grp.toString(), i, 0);
            }
            i++;
        }

        return dtm;
    }

    public int getGroupCount() {
        return groups.size();
    }

    public Group getGroup(int index) {
        return groups.get(index);
    }

    public Group getGroup(String name) {
        return groups.get(groups.indexOf(name));
    }
    
    public int getGroupNumber(String name) {
        return groups.indexOf(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
