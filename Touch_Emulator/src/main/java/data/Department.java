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
public class Department extends Subgroup {

    private String terminals;
    private String modified;
    
    public Department(String name, String terminals, int startIndex, int number) {
        super(name, startIndex, number);
        this.terminals = terminals;
        modified = "---";
    }
    
    public String getModified() {
        return modified;
    }
    
    public String getTerminalsAsString() {
        return terminals;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setTerminals(String terminals) {
        this.terminals = terminals;
    }
}
