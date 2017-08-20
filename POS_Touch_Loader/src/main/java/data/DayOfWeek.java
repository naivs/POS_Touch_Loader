package data;

import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan
 */
public class DayOfWeek {

    private final String name;
    private Group[] groups;
    private String modifiedDate;

    public DayOfWeek(String name) {
        this(name, Calendar.getInstance().getTime().toString());
    }
    
    public DayOfWeek(String name, String modifiedDate) {
        this.name = name;
        groups = new Group[8];
        this.modifiedDate = modifiedDate;
    }

    public void addGroup(Group group) {

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                groups[i] = group;
                updateTime();
                break;
            }
        }
    }

    public void removeGroup(int group) {
        groups[group] = null;
        updateTime();
    }
    
    public void deleteAllGroups() {
        for (int i = 0; i < groups.length; i++) {
            removeGroup(i);
        }
    }
    
    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public String[] getGroupsAsStringArray() {
        String[] grp = new String[groups.length];

        for (int i = 0; i < grp.length; i++) {
            if (groups[i] != null) {
                grp[i] = groups[i].toString();
            }
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

    public void upGroup(int group) {
        int first = group, second = group - 1;

        //if (first > 0) {
            Group buf = groups[second];
            groups[second] = groups[first];
            groups[first] = buf;
            
            updateTime();
        //}
    }

    public void downGroup(int group) {
        int first = group, second = group + 1;

        //if (first < 7) {
            Group buf = groups[second];
            groups[second] = groups[first];
            groups[first] = buf;
            
            updateTime();
        //}
    }

    public void updateTime() {
        modifiedDate = Calendar.getInstance().getTime().toString();
    }
    
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isConfigured() {

        for (Group grp : groups) {
            if (grp != null) {
                return true;
            }
        }

        return false;
    }

    public int getGroupCount() {
        int count = 0;

        for (Group grp : groups) {
            if (grp != null) {
                count++;
            }
        }

        return count;
    }

    public Group getGroup(int index) {
        return groups[index];
    }

    public Group getGroup(String name) {

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null) {
                if (groups[i].toString().equals(name)) {
                    return groups[i];
                }
            }
        }

        return null;
    }
    
    public int getGroupNumber(String name) {
        
        int number = -1;
        
        for(int i = 0; i < groups.length; i++) {
            if(groups[i] != null)
            if(name.equals(groups[i].toString())) {
                number = i;
            }
        }
        
        return number;
    }
    
    public String getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String toString() {
//        if (lastConfigureDate == null) {
//            lastConfigureDate = "не настроено";
//        }
//
//        return name + " [" + lastConfigureDate + "]";
        return name;
    }
}
