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

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivan
 */
public class UploaderTableModel extends AbstractTableModel {

    private final String[] columnNames;
    private final Object[][] data;

    public UploaderTableModel(String[] columnNames, float[][] data) {
        super();
        this.columnNames = new String[columnNames.length + 3];
        this.data = new String[7][this.columnNames.length];
        
        System.arraycopy(columnNames, 0, this.columnNames, 1, columnNames.length);
        this.columnNames[0] = "";
        this.columnNames[this.columnNames.length - 2] = "Загрузка дня";
        this.columnNames[this.columnNames.length - 1] = "Статус";
        
        this.data[0][0] = "Понедельник";
        this.data[1][0] = "Вторник";
        this.data[2][0] = "Среда";
        this.data[3][0] = "Четверг";
        this.data[4][0] = "Пятница";
        this.data[5][0] = "Суббота";
        this.data[6][0] = "Воскресенье";
        
        for (int i = 0; i < this.data.length; i++) {
            float dayLoad = 0f;
            for (int j = 1; j < this.data[i].length - 2; j++) {
                    this.data[i][j] = String.format("%.2f", data[i][j - 1]) + "%";
                    dayLoad += data[i][j - 1];
            }
            this.data[i][this.data[i].length - 2] = String.format("%.2f", dayLoad) + "%";
            
            if(dayLoad <= 70.0f) {
                this.data[i][this.data[i].length - 1] = "[ok]";
            } else if (dayLoad < 90.0f) {
                this.data[i][this.data[i].length - 1] = "[warn]";
            } else {
                this.data[i][this.data[i].length - 1] = "[crit]";
            }
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
