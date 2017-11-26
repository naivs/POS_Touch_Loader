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
package models;

import data.Department;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivan
 */
public class DepartmentsTableModel extends AbstractTableModel {
    
    private final String[] columnNames = {"Название",
        "Кассы",
        "Изменена",
        "Тип"};
    private final Object[][] data;
    
    public DepartmentsTableModel(List<Department> terminalGroups) {
        data = new Object[terminalGroups.size()][columnNames.length];
        
        for(int i = 0; i < data.length; i++) {
            data[i][0] = terminalGroups.get(i).toString();
            data[i][1] = terminalGroups.get(i).getTerminalsAsString();
            data[i][2] = terminalGroups.get(i).getModified();
            data[i][3] = terminalGroups.get(i).getType() == 0 ? "Постоянный ассортимент" : "По дням недели";
        }
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
