package data;

/**
 *
 * @author Ivan
 */
public class TerminalGroup {

    private final String name;
    private final String terminals;
    private DayOfWeek[] daysOfWeek;
    private String startIndex;

    public TerminalGroup(String name, String terminals, String startIndex) {
        this.name = name;
        this.terminals = terminals;
        this.startIndex = startIndex;
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
    
    public String getStartIndex() {
        return startIndex;
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
    
    @Override
    public String toString() {
        return name;
    }
}
