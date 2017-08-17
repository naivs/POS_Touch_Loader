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

        this.columnNames = new String[columnNames.length + 1];
        this.columnNames[0] = "";
        for (int i = 1; i < this.columnNames.length; i++) {
            this.columnNames[i] = columnNames[i - 1];
        }

        this.data = new String[7][data[0].length + 1];

        this.data[0][0] = "Понедельник";
        this.data[1][0] = "Вторник";
        this.data[2][0] = "Среда";
        this.data[3][0] = "Четверг";
        this.data[4][0] = "Пятница";
        this.data[5][0] = "Суббота";
        this.data[6][0] = "Воскресенье";

        for (int i = 0; i < data.length; i++) {

            for (int j = 1; j < this.data[i].length; j++) {

                if (data[i][j - 1] == 0) {
                    this.data[i][j] = "---";
                } else {
                    this.data[i][j] = String.format("%.2f", data[i][j - 1]) + "%";
                }
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
