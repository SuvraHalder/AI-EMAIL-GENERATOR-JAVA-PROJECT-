package org.example;

public class GeminiFacade {

    private final GeminiService geminiService;
    private final GmailSender gmailService ;
    private final JDBCservice dbService;
    private String Answer =  "";
    String to = "halder.06.suvro@gmail.com";
    String from  = "suvrohalder09@gmail.com";

    public GeminiFacade() {
        this.geminiService = new GeminiService();
        this.gmailService = new GmailSender() ;
        this.dbService = new JDBCservice();
    }

    public String getGeminiAnswer(String question) {
        Answer = geminiService.getAnswer(question);
        return Answer;
    }

    public String sendEmail(){


        String subject  = "Sending Email Using Gmail";


        boolean b = gmailService.sendEmail(to , from , subject , Answer);

        if(b){

            return ("Email Sent Successfully");
        }
        else{

            return ("Error");
        }
    }

    public String  sendData(){

        boolean b = dbService.sendData(to ,from);

        if(b){

            return ("Email Sent Successfully");
        }
        else{

            return ("Error");
        }


    }

}
