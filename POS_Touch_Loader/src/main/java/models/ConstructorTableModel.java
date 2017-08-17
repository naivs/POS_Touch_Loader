package models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ConstructorTableModel extends AbstractTableModel {

    private final String[] columnNames = {"", ""};
    private final Object[][] data;
    
    public ConstructorTableModel(String[] data, boolean[] selectionData) {
        super();
        
        this.data = new Object[data.length][2];
        
        for(int i = 0; i < data.length; i++) {
            this.data[i][0] = selectionData[i];
            this.data[i][1] = data[i];
        }
    }
    
    public ArrayList<String> getSelectedData() {
        
        ArrayList<String> selData = new ArrayList<String>();
        
        for (Object[] data1 : data) {
            if ((Boolean) data1[0]) {
                selData.add(data1[1].toString());
            }
        }
        
        return selData;
    }
    
    public boolean isAnyChecked() {
        for (Object[] data1 : data) {
            if ((Boolean) data1[0]) {
                return true;
            }
        }
        
        return false;
    }
    
    public void clearSelection() {
        for (Object[] data1 : data) {
            data1[0] = false;
        }
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public void setValueAt(Object value, int row, int col) {

        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /*
         * Don't need to implement this method unless your table's
         * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return col < 1;
    }
}
