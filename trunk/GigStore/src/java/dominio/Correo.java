/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class Correo {
    private String from;
    private String to;
    private String subject;
    private String text;

    public Correo() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void send() throws MessagingException {
        String host = "smtp.gmail.com";
        String userid = "brillantetes@gmail.com";
        String password = "brillantetes2012";
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.setProperty("mail.transport.protocol", "smtps");
            props.put("mail.smtp.user", userid);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtps.auth", "true");
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            InternetAddress fromAddress = null;
            InternetAddress toAddress = null;
            InternetAddress[] multipleAddress = new InternetAddress[to.split(";").length];

            try {
                fromAddress = new InternetAddress(from);
                if(to.contains(";")){
                    String[] paras=to.split(";");
                    for(int i=0;i<paras.length;i++){
                        multipleAddress[i]=new InternetAddress(paras[i]);
                    }
                }else{
                    toAddress = new InternetAddress(to);
                }
            } catch (AddressException e) {
                throw new AddressException(e.getMessage());
            }

            message.setFrom(fromAddress);
            if(multipleAddress.length>1){
                message.setRecipients(RecipientType.BCC, multipleAddress);
            }
            else{
                message.setRecipient(RecipientType.TO, toAddress);
            }
            message.setSubject(subject);
            message.setContent(text, "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtps");
            transport.connect(host, userid, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new MessagingException(e.getMessage());
        }
    }
}
