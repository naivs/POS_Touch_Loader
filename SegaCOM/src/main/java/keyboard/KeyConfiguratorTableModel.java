package keyboard;

import java.awt.event.KeyEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivan
 */
public class KeyConfiguratorTableModel extends AbstractTableModel {

    private final String[] columnNames = {"", "Gamepad 1", "Gamepad 2"};
    private final String[][] data = new String[12][3];
    private KeyMap keyMap;
    
    public KeyConfiguratorTableModel(KeyMap keyMap) {
        super();
        data[0][0] = "A";
        data[1][0] = "B";
        data[2][0] = "C";
        data[3][0] = "X";
        data[4][0] = "Y";
        data[5][0] = "Z";
        data[6][0] = "UP";
        data[7][0] = "DOWN";
        data[8][0] = "LEFT";
        data[9][0] = "RIGHT";
        data[10][0] = "START";
        data[11][0] = "MODE";
        
        this.keyMap = keyMap;
        update();
    }
    
    private void update() {
        for(int i = 0; i < data.length; i++) {
            for (int j = 1; j < data[i].length; j++) {
                data[i][j] = keyMap.getKey(i, j - 1) == 0 ? "-" : KeyEvent.getKeyText(keyMap.getKey(i, j - 1));
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = String.valueOf(value);
    }
    
    public void setValues(KeyMap keyMap) {
        this.keyMap = keyMap;
        update();
    }
}
