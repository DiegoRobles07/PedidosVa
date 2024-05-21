package ejercicio.pedidosva;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GestorCorreo {

    public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        String remitente = "freddysfoodservicesv@outlook.com"; // Cambia esto a tu correo corporativo
        final String usuario = "freddysfoodservicesv@outlook.com"; // Cambia esto a tu correo corporativo
        final String clave = "freddy123"; // Cambia esto a tu contraseña

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.office365.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getInstance(propiedades, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
            return true; // Devuelve true si el correo se envió correctamente
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurrió un error al enviar el correo
        }
    }

}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
