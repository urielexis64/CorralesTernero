package Modelo;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ModeloCorreoElectronico {

	private static String des = new DesEncrip(
			"%%>>$ª$+%%>>$$>+%%>>$$>+%%>>$¶>!%%>>$¶>+%%>>$¶$+%%>>$$>!%%>>$ª$!%%>>$ª$!%%>>$¶>!%%>>$¶>+%%>>$¶>!%%>>$¶$!%%>>$¶$+%%>>$$>+%%>>$¶$!%%>>$ª$!%%>>$$>+%%>>$¶>+%%>>$¶>+%%>>$ª$+%%>>$¶$+%%>>$¶>!%%>>$¶>!%%>>$ª$+%%>>$$>+%%>>$$>+%%>>$¶>!%%>>$¶>+%%>>$¶$+%%>>$¶$+%%>>$ª$!%%>>$ª$+%%>>$¶>+%%>>$$>+%%>>$$>!%%>>$$>!%%>>$¶$+%%>>$$>+%%>>$ª$!%%>>$ª$!%%>>$ª$!%%>>$¶>+%%>>$¶>!%%>>$$>+%%>>$¶$+%%>>$¶$!%%>>$¶$!%%>>$ª$!%%>>$ª$!%%>>$$>!%%>>$¶>+%%>>$ª$!%%>>$¶$+%%>>$$>!%%>>$ª$!%%>>$ª$!%%>>$ª$+%%>>$¶>+%%>>$¶>+%%>>$¶>+",
			8792, 8792, true, 1).getGuardar();
	private static String des2 = new DesEncrip(
			"%%>>$¶$+%%>>$¶>!%%>>$¶>!%%>>$ª$!%%>>$¶$!%%>>$¶>+%%>>$¶>!%%>>$¶$+%%>>$¶$+%%>>$¶$!%%>>$¶$!%%>>$ª$!%%>>$$>+%%>>$$>+%%>>$¶>!%%>>$¶>+%%>>$¶$+%%>>$$>!%%>>$ª$!%%>>$ª$!%%>>$ª$+%%>>$¶>+%%>>$ª$!%%>>$¶$!%%>>$¶$+%%>>$¶>!%%>>$¶>!%%>>$ª$!%%>>$¶$!%%>>$¶>+%%>>$¶>+%%>>$ª$+%%>>$¶$+%%>>$¶>!%%>>$ª$!%%>>$¶$+%%>>$¶>!%%>>$¶>!%%>>$ª$+%%>>$$>+",
			8792, 8792, true, 1).getGuardar();

	public static void enviarCorreos(String asunto, String cuerpo) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(unoDos(true), unoDos(false));
			}
		});
		Message can = new MimeMessage(session);
		can.setFrom(new InternetAddress("prueba@hotmail.com"));
		can.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(unoDos(true)));
		can.setSubject(asunto);
		can.setText(cuerpo);
//		MimeBodyPart messageBodyPart = new MimeBodyPart();
//
//		Multipart multipart = new MimeMultipart();
//		BodyPart mensajeCuerpo = new MimeBodyPart();
//		mensajeCuerpo.setText(cuerpo);
//
//		messageBodyPart = new MimeBodyPart();
//		String file = "E:\\Escritorio\\prueba.pdf";
//		String fileName = "Prueba.pdf";
//		DataSource source = new FileDataSource(file);
//		messageBodyPart.setDataHandler(new DataHandler(source));
//		messageBodyPart.setFileName(fileName);
//		multipart.addBodyPart(messageBodyPart);
//		multipart.addBodyPart(mensajeCuerpo);
//
//		can.setContent(multipart);

		Transport.send(can);
	}

	private static byte[] conversion2(String s) {
		int l = s.length();
		byte[] res = new byte[l / 2];
		for (int i = 0; i < l; i += 2)
			res[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		return res;
	}

	private static String conversion1(String cad) {
		return new BigInteger(cad, (int) (Math.ceil((Byte.MAX_VALUE - (-Byte.MAX_VALUE)) / 32.0)))
				.toString((int) (Math.floor((Byte.MAX_VALUE - (-Byte.MAX_VALUE)) / 15.0)));
	}

	public static String unoDos(boolean b) {
		try {
			return b ? new String(conversion2(conversion1(des)), "UTF-8")
					: new String(conversion2(conversion1(des2)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
}

class DesEncrip {

	public DesEncrip(String PreCambio, int Clave1, int Clave2, boolean paso4, int seleccion) {
		String cambio = PreCambio;
		error = false;

		if (paso4) {
			String resultado3 = "";
			for (int i = 0; i < PreCambio.length(); i++) {
				char compare = PreCambio.charAt(i);
				if (compare == '>') {
					resultado3 = resultado3.concat("0");
				}
				if (compare == '%') {
					resultado3 = resultado3.concat("1");
				}
				if (compare == '+') {
					resultado3 = resultado3.concat("2");
				}
				if (compare == '!') {
					resultado3 = resultado3.concat("3");
				}
				if (compare == ';') {
					resultado3 = resultado3.concat("4");
				}
				if (compare == '‡') {
					resultado3 = resultado3.concat("5");
				}
				if (compare == '-') {
					resultado3 = resultado3.concat("6");
				}
				if (compare == 'ª') {
					resultado3 = resultado3.concat("7");
				}
				if (compare == '¶') {
					resultado3 = resultado3.concat("8");
				}
				if (compare == '$') {
					resultado3 = resultado3.concat("9");
				}
			}

			cambio = "";
			cambio = resultado3;
		}
		int matematica = 0;
		int Xx = 0;
		String resultado = "", NPosi = "", anadir;
		try {
			for (int f = 0; f < cambio.length(); f += 8) {
				anadir = "";
				if (Xx == 0) {
					matematica = Integer.parseInt(cambio.substring(f, 8 + f)) - Clave1;
					NPosi = String.valueOf(matematica);
					if (NPosi.length() == 1) {
						anadir = "0000000";
					} else if (NPosi.length() == 2) {
						anadir = "000000";
					} else if (NPosi.length() == 3) {
						anadir = "00000";
					} else if (NPosi.length() == 4) {
						anadir = "0000";
					} else if (NPosi.length() == 5) {
						anadir = "000";
					} else if (NPosi.length() == 6) {
						anadir = "00";
					} else if (NPosi.length() == 7) {
						anadir = "0";
					}

					resultado = resultado.concat(String.valueOf(anadir + matematica));

					Xx = 1;
				} else {
					matematica = Integer.parseInt(cambio.substring(f, 8 + f)) - Clave2;
					NPosi = String.valueOf(matematica);
					if (NPosi.length() == 1) {
						anadir = "0000000";
					} else if (NPosi.length() == 2) {
						anadir = "000000";
					} else if (NPosi.length() == 3) {
						anadir = "00000";
					} else if (NPosi.length() == 4) {
						anadir = "0000";
					} else if (NPosi.length() == 5) {
						anadir = "000";
					} else if (NPosi.length() == 6) {
						anadir = "00";
					} else if (NPosi.length() == 7) {
						anadir = "0";
					}

					resultado = resultado.concat(String.valueOf(anadir + matematica));

					Xx = 0;
				}
			}
		} catch (StringIndexOutOfBoundsException c) {
		} catch (NumberFormatException c2) {
		}
		cambio = "";
		PreCambio = "";
		PreCambio = resultado;
		for (int x = 0; x < PreCambio.length(); x++) {
			if (PreCambio.charAt(x) == '1') {
				cambio = cambio.concat("0");
			}
			if (PreCambio.charAt(x) == '0') {
				cambio = cambio.concat("1");
			}
		}
		guardar = cambio;
		// Esto lo que hace es guardar en un ArrayList el contenido de la variable
		// guardar que se encuentra concatenada,
		// la separa en grupos de 8 objetos.
		String finaltexto = "";
		int x = 0;
		try {
			for (int f = 0; f < guardar.length(); f += 8) {
				Arraytexto.add(guardar.substring(f, 8 + f)); // Almacenamos en Arraytexto el contenido de la variable
																// guardar en las posiciones asignadas por el metodo
																// subString.
				int Ascci = Integer.parseInt(Arraytexto.get(x), 2); // Pasamos de binario a decimal o AscII.
				char caracter = (char) Ascci; // Pasamos de AscII a su caracter correspondiente.
				finaltexto = finaltexto.concat(String.valueOf(caracter)); // Concatenamos el contenido guardar con el
																			// contenido de conversionCaracter.
				x++;
			}

		} catch (StringIndexOutOfBoundsException e) {
			error = true;
			guardar = "";
		}

		if (error == false) {
			guardar = "";
			guardar = finaltexto;
		}

	}

	public String getGuardar() { // Metodo Getter que lo utilizaremos para devolver lo que la variable guardar
									// lleva almacenado.
		return guardar;
	}

	private boolean error;
	private String guardar;
	private ArrayList<String> Arraytexto = new ArrayList<>();

}
