package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.element.LineSeparator;

import Modelo.ModeloSigProceso;
import Vista.VentanaPrincipal;
import material.extras.AccionComponente;

public class ControladorInformes implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloSigProceso modelo;

	public ControladorInformes(VentanaPrincipal vista, ModeloSigProceso modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.informes.btnUbicacion) {
			JFileChooser chooser = new JFileChooser("E:\\Escritorio");
			int opcion = chooser.showSaveDialog(vista);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				vista.informes.txtUbicacion
						.setText(f.toString().endsWith(".pdf") ? f.toString() : f.toString() + ".pdf");
			}
			return;
		}

		String ruta = vista.informes.txtUbicacion.getText();

		if (ruta.length() == 0) {
			vista.informes.showMessage("Primero elige una ruta", true);
			AccionComponente.sacudir(vista.informes.txtUbicacion, 500, 20);
			return;
		}

		if (new File(ruta).exists()) {
			if (JOptionPane.showConfirmDialog(vista,
					"El archivo actual ya existe, ¿desea sobreescribirlo?") != JOptionPane.OK_OPTION)
				return;
		}

		try {
			generarPDF(modelo.getCriasSigProceso(), "E:\\eclipse-workspace\\Base de datos\\Resources\\logo.png",
					ruta.endsWith(".pdf") ? ruta : ruta + ".pdf");
			vista.informes.showMessage("PDF generado con éxito.", false);
		} catch (Exception ex) {
			vista.informes.showMessage("Error al generar archivo: " + ex.getMessage(), true);
		}
	}

	private void generarPDF(Vector<Vector<String>> objetoCria, String rutaImagen, String salida) throws Exception {
		PdfWriter writer = new PdfWriter(salida);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf);

		doc.setMargins(30, 30, 30, 30);

		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

		Image imagen = new Image(ImageDataFactory.create("Resources\\cow.png"));
		imagen.setWidth(120).setHeight(120).setHorizontalAlignment(HorizontalAlignment.CENTER);

		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));

		doc.add(new Paragraph("Culiacán, Sinaloa a " + formateador.format(new Date())).setFont(font)
				.setTextAlignment(TextAlignment.RIGHT));
		doc.add(imagen);

		doc.add(new Paragraph("Se muestran algunos de los atributos de las crías que han permanecido durante más de 5 meses en los corrales.").setFont(font).setFontSize(14f)
				.setTextAlignment(TextAlignment.LEFT));
		
		doc.add(new Paragraph("Informe de crías: Corrales Ternero").setFont(bold).setFontSize(20f)
				.setTextAlignment(TextAlignment.CENTER));

		Table tabla = new Table(5);
		tabla.setWidth(540);

		tabla.addHeaderCell(new Cell().add(new Paragraph("ID CRÍA").setFont(bold)).setHeight(40).setWidth(150)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
		tabla.addHeaderCell(new Cell().add(new Paragraph("CLASIFICACIÓN").setFont(bold)).setWidth(220)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
		tabla.addHeaderCell(new Cell().add(new Paragraph("FECHA DE ENTRADA").setFont(bold)).setWidth(280)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
		tabla.addHeaderCell(new Cell().add(new Paragraph("TIEMPO").setFont(bold)).setWidth(220)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
		tabla.addHeaderCell(new Cell().add(new Paragraph("VECES EN CUARENTENA").setFont(bold)).setWidth(350)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));

		for (int i = 0; i < objetoCria.size(); i++) {
			tabla.addCell(new Cell().add(new Paragraph(objetoCria.get(i).get(0))).setFont(font)
					.setTextAlignment(TextAlignment.CENTER));
			tabla.addCell(new Cell().add(new Paragraph(objetoCria.get(i).get(1))).setFont(font)
					.setTextAlignment(TextAlignment.CENTER));
			tabla.addCell(new Cell().add(new Paragraph(objetoCria.get(i).get(2))).setFont(font)
					.setTextAlignment(TextAlignment.CENTER));

			int dias = Integer.parseInt(objetoCria.get(i).get(3));
			int meses = dias / 30;
			int nuevosDias = dias % 30;

			tabla.addCell(new Cell().add(new Paragraph(meses + " meses y " + nuevosDias + " días")).setFont(font)
					.setTextAlignment(TextAlignment.CENTER));
			tabla.addCell(new Cell().add(new Paragraph(objetoCria.get(i).get(4))).setFont(font)
					.setTextAlignment(TextAlignment.CENTER));
		}

		doc.add(tabla);
		doc.close();
	}
}
