package data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ivan
 */
public class TerminalGroup {

    private final String name;
    private ArrayList<String> terminals;
    private DayOfWeek[] daysOfWeek;
    private boolean isRemoved;

//    public TerminalGroup(String name) {
//        this.name = name;
//        terminals = new ArrayList();
//    }

    public TerminalGroup(String name, ArrayList<String> terminals) {
        this.name = name;
        this.terminals = new ArrayList();
        this.terminals = terminals;
        daysOfWeek = new DayOfWeek[7];
        
        String[] dNames = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
        for (int i = 0; i < dNames.length; i++) {
            daysOfWeek[i] = new DayOfWeek(dNames[i]);
        }
    }

    public void addTerminal(String name) {
        terminals.add(name);
    }

    public void addAllTerminals(String[] names) {
        terminals.addAll(Arrays.asList(names));
    }

    public void removeTerminal(String name) {
        terminals.remove(name);
    }

    public void removeAllTerminals(String[] names) {
        terminals.removeAll(Arrays.asList(names));
    }

    public void setDaysOfWeek(DayOfWeek[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
//    public void createDaysOfWeek() {
//        daysOfWeek = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
//        
//        for (String dayName : dayNames) {
//            daysOfWeek.add(new DayOfWeek(dayName));
//        }
//    }
    
    public DayOfWeek[] getDaysOfWeek() {
        return daysOfWeek;
    }
    
    public ArrayList<String> getTerminals() {
        return terminals;
    }
    
    public String getTerminalsAsString() {
        
        String out = "";
        
        for(String terminal : terminals) {
            out += "-" + terminal;
        }
        
        out = out.substring(1);
        
        return out;
    }
    
//    private boolean isConfigured() {
//        if(daysOfWeek == null) {
//            return false;
//        } else if(daysOfWeek.isEmpty()) {
//            return false;
//        } else {
//            boolean check = false;
//            
//            for(DayOfWeek day : daysOfWeek) {
//                if(day.isConfigured()) {
//                    check = true;
//                    break;
//                }
//            }
//            
//            return check;
//        }
//    }
    
//    public void setConfigured(int day, boolean isConfigured) {
//        configuredDaysOfWeek[day] = isConfigured;
//        whenConfigured[day] = Calendar.getInstance().getTime().toString();
//    }
//
//    public void setAllCongigured(boolean isConfigured) {
//        for (int i = 0; i < configuredDaysOfWeek.length; i++) {
//            configuredDaysOfWeek[i] = isConfigured;
//            whenConfigured[i] = Calendar.getInstance().getTime().toString();
//        }
//    }
//
//    public String[] getConfiguredData() {
//        String[] configuredData = new String[7];
//
//        for (int i = 0; i < configuredDaysOfWeek.length; i++) {
//            if (configuredDaysOfWeek[i]) {
//                configuredData[i] = whenConfigured[i];
//            } else {
//                configuredData[i] = "";
//            }
//        }
//
//        return configuredData;
//    }
    
    public void setRemoved(boolean statement) {
        isRemoved = statement;
    }
    
    public boolean isRemoved() {
        return isRemoved;
    }
    
    @Override
    public String toString() {
        
//        if(isRemoved) {
//            return name + " [удалена]";
//        } else if(isConfigured()) {
//            return name + " [не настроена]";
//        }
        
        return name;
    }
}
