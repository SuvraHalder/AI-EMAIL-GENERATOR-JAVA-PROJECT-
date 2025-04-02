package Testing;

import org.example.GmailSender;

public class App {

    public static void main(String[] args) {

        GmailSender gEmailSender  =  new GmailSender();
        String to = "halder.06.suvro@gmail.com";
        String from  = "suvrohalder09@gmail.com";
        String subject  = "Sending Email Using Gmail";
        String text = "This is a example send using gmail and java";

        boolean b = gEmailSender.sendEmail(to , from , subject , text);

        if(b){

            System.out.println("Email Sent Successfully");
        }
        else{

            System.out.println("Error");
        }

    }

}
