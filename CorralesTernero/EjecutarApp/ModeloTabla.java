package EjecutarApp;

import javax.swing.table.DefaultTableModel;


public class ModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int co) {
		return co == this.getColumnCount()-1;
	}
}

