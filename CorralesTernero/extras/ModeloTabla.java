package extras;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import mdlaf.utils.MaterialColors;

public class ModeloTabla extends DefaultTableModel implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	private static Color color;
	public boolean isCellEditable(int row, int co) {
		return co == this.getColumnCount() - 1 && getColumnName(getColumnCount() - 1).equals("Sacrificar");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(color ==null)
			color=table.getBackground();
		
		if (Integer.parseInt(table.getValueAt(row, 1).toString().split(" ")[0]) >= 50) {
			c.setBackground(MaterialColors.YELLOW_700);
			c.setForeground(Color.BLACK);
		}else {
			c.setBackground(color);
			c.setForeground(Color.WHITE);
		}
		return c;
	}
}
