package Modelo;

import java.io.File;
import java.util.Properties;
import javax.mail.Message;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnviarEmailFactura {
    private static String emailFrom = "birbumaxi@gmail.com";
    private static String passwordFrom = "caswdqajkwiyfriz";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    
    public String correoDestino;
    
    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }
    
    public String getCorreoDestino() {
        return correoDestino;
    }
    
    public EnviarEmailFactura(String correoDestino) {
        this.correoDestino = correoDestino;
        mProperties = new Properties();
    }
    
    public void EnviarCorreo() {
        crearEmail();
        sendEmail();
    }
    
    private void crearEmail() {
        emailTo = correoDestino;
        subject = "FACTURA BIRBUMAXI";
        content = "Gracias por su compra, vuelva pronto.";
        
        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            
            // Cuerpo del mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content, "ISO-8859-1", "html");
            
            // Parte del archivo adjunto
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            String filename = "C:/Documentos/Factura.pdf"; // Ruta relativa al archivo PDF
            FileDataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(new File(filename).getName());
            
            // Combinar las partes en un multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            
            // Establecer el contenido del mensaje al multipart
            mCorreo.setContent(multipart);
            
        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
