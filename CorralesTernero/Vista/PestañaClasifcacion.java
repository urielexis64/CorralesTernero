package Vista;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorClasificacion;
import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.extras.Rutinas;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaClasifcacion extends JPanel {

	private JLabel lblColorMusculo;
	private JLabel lblCriterios, lblCrias1, lblCrias2, lblCrias3;
	private JLabel lblRenglon1, lblRenglon2, lblRenglon3, lblRenglon4;

	private JTable tablaCobertura1, tablaCobertura2, tablaCobertura3;
	private ModeloTabla modeloTabla1, modeloTabla2, modeloTabla3;
	public JScrollPane scrollTabla1, scrollTabla2, scrollTabla3;

	public MaterialButton btnRefrescar;

	public PestañaClasifcacion() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblCriterios = new JLabel("Criterios de clasificación", SwingConstants.CENTER);
		lblCriterios.setBounds(250, 10, 300, 30);
		lblCriterios.setBorder(new RoundedCornerBorder());
		lblCriterios.setFont(Roboto.BLACK.deriveFont(24f));

		lblColorMusculo = new JLabel(Rutinas.AjustarImagen("Resources\\colorMusculo.jpg", 261, 184));
		lblColorMusculo.setBounds(450, 60, 261, 184);
		lblColorMusculo.setBorder(BorderFactory.createDashedBorder(MaterialColors.LIME_900, 15, 10));

		lblRenglon1 = new JLabel("  CLASIFICACIÓN       PORCENTAJE DE GRASA");
		lblRenglon1.setBounds(45, 50, 320, 40);
		lblRenglon1.setBorder(new DropShadowBorder(MaterialColors.BLUE_GRAY_100, 1, true));

		lblRenglon2 = new JLabel("  COBERTURA 1                    0 % - 30 %");
		lblRenglon2.setBounds(50, 90, 300, 30);
		lblRenglon2.setForeground(new Color(193, 244, 56));

		lblRenglon3 = new JLabel("  COBERTURA 2                   31 % - 60 %");
		lblRenglon3.setBounds(50, 140, 300, 30);
		lblRenglon3.setForeground(new Color(193, 244, 56));

		lblRenglon4 = new JLabel("  COBERTURA 3                       +60 %");
		lblRenglon4.setBounds(50, 190, 300, 30);
		lblRenglon4.setForeground(new Color(193, 244, 56));

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setBounds(700, 5, 80, 80);

		lblCrias1 = new JLabel("COBERTURA 1");
		lblCrias1.setBounds(100, 300, 200, 30);
		lblCrias1.setFont(Roboto.BLACK.deriveFont(14f));

		lblCrias2 = new JLabel("COBERTURA 2");
		lblCrias2.setBounds(350, 300, 200, 30);
		lblCrias2.setFont(Roboto.BLACK.deriveFont(14f));

		lblCrias3 = new JLabel("COBERTURA 3");
		lblCrias3.setBounds(600, 300, 200, 30);
		lblCrias3.setFont(Roboto.BLACK.deriveFont(14f));

		hazTablas();

		scrollTabla1 = new JScrollPane(tablaCobertura1);
		scrollTabla1.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTabla1.setBounds(25, 330, 240, 280);

		scrollTabla2 = new JScrollPane(tablaCobertura2);
		scrollTabla2.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTabla2.setBounds(275, 330, 240, 280);

		scrollTabla3 = new JScrollPane(tablaCobertura3);
		scrollTabla3.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTabla3.setBounds(525, 330, 240, 280);

		JSeparator sep1 = new JSeparator();
		JSeparator sep2 = new JSeparator();
		JSeparator sep3 = new JSeparator();
		JSeparator sep4 = new JSeparator(1);
		sep1.setForeground(new Color(193, 244, 56));
		sep1.setBounds(50, 130, 310, 1);
		sep2.setForeground(new Color(193, 244, 56));
		sep2.setBounds(50, 180, 310, 1);
		sep3.setForeground(new Color(193, 244, 56));
		sep3.setBounds(50, 230, 310, 1);
		sep4.setForeground(new Color(193, 244, 56));
		sep4.setBounds(180, 62, 1, 168);
		add(sep1);
		add(sep2);
		add(sep3);
		add(sep4);

		add(lblCriterios);
		add(lblColorMusculo);

		add(lblRenglon1);
		add(lblRenglon2);
		add(lblRenglon3);
		add(lblRenglon4);

		add(btnRefrescar);

		add(lblCrias1);
		add(lblCrias2);
		add(lblCrias3);

		add(scrollTabla1);
		add(scrollTabla2);
		add(scrollTabla3);
	}

	public void setControlador(ControladorClasificacion controlador) {
		btnRefrescar.addActionListener(controlador);
	}

	private void hazTablas() {
		modeloTabla1 = new ModeloTabla();
		modeloTabla2 = new ModeloTabla();
		modeloTabla3 = new ModeloTabla();
		tablaCobertura1 = new JTable(modeloTabla1);
		tablaCobertura2 = new JTable(modeloTabla2);
		tablaCobertura3 = new JTable(modeloTabla3);

		defineColumnas(modeloTabla1);
		defineColumnas(modeloTabla2);
		defineColumnas(modeloTabla3);
	}

	private void defineColumnas(ModeloTabla modelo) {
		modelo.addColumn("<html><h4>ID Cría");
		modelo.addColumn("<html><h4>Corral");
		modelo.addColumn("<html><h4>Alimentación");
		modelo.addColumn("<html><h4>Sensor");
	}

	public void setTabla(Vector<Vector<String>> objetoCria) {
		Vector<String> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<String>();
			nuevaCria.add(objetoCria.get(i).get(0)); // ID
			nuevaCria.add(objetoCria.get(i).get(1)); // CORRAL_ID
			nuevaCria.add(objetoCria.get(i).get(2)); // ALIM_ID
			nuevaCria.add(objetoCria.get(i).get(3)); // SENSOR_ID

			String clasificacion = objetoCria.get(i).get(4);

			switch (clasificacion) {
			case "COBERTURA 1":
				modeloTabla1.addRow(nuevaCria);
				break;
			case "COBERTURA 2":
				modeloTabla2.addRow(nuevaCria);
				break;
			case "COBERTURA 3":
				modeloTabla3.addRow(nuevaCria);
			}
		}
	}

	public void limpiarTabla() {
		((DefaultTableModel) tablaCobertura1.getModel()).setNumRows(0);
		((DefaultTableModel) tablaCobertura2.getModel()).setNumRows(0);
		((DefaultTableModel) tablaCobertura3.getModel()).setNumRows(0);
	}
}
