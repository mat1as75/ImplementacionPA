package mail;

import espotify.config.ConfigReader;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class MailHandler {
    public static Boolean sendEmail(String toEmail, String subject, String body) {
        /**
         * Cree un nuevo correo de gmail para usar el servidor smtp de Google, y asi
         * no tener que configurar un servidor SMTP localmente.
         * Desde Junio/2024 Google no permite el inicio de sesion de apps inseguras
         * unicamente con usuario y contraseña, por lo que es necesario crear una 
         * contraseña de aplicacion desde la configuracion de la cuenta de Google.
         * Ademas ahora solo se permite pedir una contraseña de aplicacion si se tiene activada 
         * la verificacion en dos pasos, por lo que tuve que vincular mi cuenta y numero
         * personal para activarla. La contraseña que se puso aca en senderPassword es esa 
         * contraseña.
         */   
        final String senderEmail = ConfigReader.getSenderEmail();
        final String senderPassword = ConfigReader.getSenderEmailPassword();
        String host =  ConfigReader.getSMTPhost();
        int port = ConfigReader.getSMTPport();

        /**
         * http://www.aboutmyip.com/AboutMyXApp/DevNullSmtp.jsp
         * Si quieren probar enviar el correo al servidor SMTP tonto DevNull 
         * que sugiere la letra de la tarea 3 hay que cambiar:
         * 1. el host por la direccion 127.0.0.1
         * 2. el puerto por el 25
         * 3. la sesion sin autenticacion:
         *      Session session = Session.getInstance(props, null);
         * 4. remover o setear en false las siguientes propiedades: 
         *      a. mail.smtp.auth 
         *      b. mail.smtp.starttls.enable
         * 
         * Y antes de ejecutar la funcion que envia el correo iniciar el servidor DevNull: 
         * 1. usar 'sudo java -jar DevNullSmtp.jar' para ejecutar la aplicacion
         * 2. presionar Start Server
         * 
         */
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        
        Session session = null;
        
        if (ConfigReader.getProperties() == null) {
            session = Session.getInstance(props, null);
        } else {
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            //autenticacion del correo emisor
            session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });
        }
        
        try {
            
            MimeMessage msg =  new MimeMessage(session);
            //defino el tipo de contenido como html
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            
            //set de la direccion de origen
            msg.setFrom(new InternetAddress(senderEmail));
            
            //set del asunto
            msg.setSubject(subject, "UTF-8");
            
            //set del cuerpo
            msg.setContent(body, "text/html; charset=UTF-8");
            
            //set de la fecha de envio
            msg.setSentDate(new Date());
            
            //set de la direccion destino
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            //envia el mensaje
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String buildMsgBody(
            String nombreUsuario, 
            String tipoSuscripcion, 
            String fechaDeSolicitud
            ) {
        
        String precioSuscripcion = "";
        switch (tipoSuscripcion) {
            case "Semanal" -> precioSuscripcion = "$ 2.99";
            case "Mensual" -> precioSuscripcion = "$ 7.99";
            case "Anual" -> precioSuscripcion = "$ 79.9";
        }
        
        LocalDate fechaDeHoy = LocalDate.now();
        LocalDate fechaDeVencimiento = null;
        
        switch (tipoSuscripcion) {
            case "Semanal" -> fechaDeVencimiento = fechaDeHoy.plusWeeks(1L);
            case "Mensual" -> fechaDeVencimiento = fechaDeHoy.plusMonths(1L);
            case "Anual" -> fechaDeVencimiento = fechaDeHoy.plusYears(1L);
        }
        
        String open = "<body> <h3> Aviso de Espotify </h3> <section> ";
        String message = "Estimado/a " + nombreUsuario + "," 
                + "<br><p>Su suscripción en Espotify ha sido aprobada y se encuentra Vigente."
                + "<br><br>Detalles de la suscripción:" 
                + "<br>Tipo: " + tipoSuscripcion + " - " + precioSuscripcion 
                + "<br>Fecha de solicitud: " + fechaDeSolicitud 
                + "<br>Fecha de aprobación: " + fechaDeHoy.toString()
                + "<br>Fecha de fin: " + fechaDeVencimiento
                + "<br><br>" 
                + "Gracias por preferinos, <br>Saludos,<br>Espotify</p><br>";
        String close = "</section> </body>";
        
        return (open + message + close);
    }
    
    public static String buildMsgSubject() {
        LocalDate today = LocalDate.now();
        String todayString = today.format(DateTimeFormatter.ISO_DATE);
        String subject = "[Espotify] [" + todayString + "]";
        
        return subject;
    }
}
