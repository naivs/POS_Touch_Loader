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

/**
 *
 * @author Ivan
 */
public class TerminalGroup {

    public static final int TYPE_ALWAYS = 0;
    public static final int TYPE_DAYS = 1;
    
    private int type;
    private String name;
    private String terminals;
    private DayOfWeek[] daysOfWeek;
    private String startIndex;
    private String modified;

    public TerminalGroup(int TYPE, String name, String terminals, String startIndex) {
        this.type = TYPE;
        this.name = name;
        this.terminals = terminals;
        this.startIndex = startIndex;
        modified = "---";
        
        if (TYPE == TYPE_ALWAYS) {
            daysOfWeek = new DayOfWeek[1];
            daysOfWeek[0] = new DayOfWeek("Все дни");
        } else {
            daysOfWeek = new DayOfWeek[7];
            String[] dNames = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
            for (int i = 0; i < dNames.length; i++) {
                daysOfWeek[i] = new DayOfWeek(dNames[i]);
            }
        }
    }

    public void setDaysOfWeek(DayOfWeek[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
    public DayOfWeek[] getDaysOfWeek() {
        return daysOfWeek;
    }
    
    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
    
    public String getTerminalsAsString() {
        return terminals;
    }
    
    public String getStartIndex() {
        return startIndex;
    }

    public int getType() {
        return type;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTerminals(String terminals) {
        this.terminals = terminals;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
