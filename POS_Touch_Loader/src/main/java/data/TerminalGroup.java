package data;

/**
 *
 * @author Ivan
 */
public class TerminalGroup {

    private final String name;
    private final String terminals;
    private DayOfWeek[] daysOfWeek;
    private boolean isRemoved;

//    public TerminalGroup(String name) {
//        this.name = name;
//        terminals = new ArrayList();
//    }

    public TerminalGroup(String name, String terminals) {
        this.name = name;
        this.terminals = terminals;
        daysOfWeek = new DayOfWeek[7];
        
        String[] dNames = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
        for (int i = 0; i < dNames.length; i++) {
            daysOfWeek[i] = new DayOfWeek(dNames[i]);
        }
    }

    public void setDaysOfWeek(DayOfWeek[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
    public DayOfWeek[] getDaysOfWeek() {
        return daysOfWeek;
    }
    
    public String getTerminalsAsString() {
        return terminals;
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
