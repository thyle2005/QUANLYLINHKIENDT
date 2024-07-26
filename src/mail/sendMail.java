package mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMail {

    public void sendMailTo(String mailTo, String sub, String body) throws UnsupportedEncodingException, MessagingException {
        String fromEmail = "thyle20051208@gmail.com";
        String password = "daluzbpggtymfdrn";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Demo Send mail with java"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(sub, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false));
            System.out.println("Message is ready");
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com", fromEmail, password);
            trans.sendMessage(msg, msg.getAllRecipients());

            System.out.println("EMail Sent Successfully!!");
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw e;
        }
    }
}
