package org.example;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;


public class GmailSender {


    public boolean  sendEmail(String  to , String from , String subject  , String text) {

        boolean flag =  false;
        Properties p = new Properties();

        p.put("mail.smtp.auth" , true);
        p.put("mail.smtp.starttls.enable" , true);
        p.put("mail.smtp.port" , "587");
        p.put("mail.smtp.host" , "smtp.gmail.com");
        String username = "suvrohalder09@gmail.com";
        String  password = System.getenv("APP_PASSWORD_GMAIL");

        // Session Create

        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username , password);
            }
        });

        //Message
        try{

            Message message  = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            flag  = true ;
        }
        catch(Exception e){

            e.printStackTrace();

        }
        return flag ;
    }

}
