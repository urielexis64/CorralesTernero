package extras;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class JCheckBoxColumn extends DefaultTableModel {

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class clazz = String.class;
      switch (columnIndex) {
        case 0:
          clazz = Integer.class;
          break;
        case 1:
          clazz = Boolean.class;
          break;
      }
      return clazz;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
      return column == getColumnCount()-1;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
      if (aValue instanceof Boolean && column == getColumnCount()-1) {
        Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(getColumnCount()-1, (boolean)aValue);
        fireTableCellUpdated(row, column);
      }
    }

  }