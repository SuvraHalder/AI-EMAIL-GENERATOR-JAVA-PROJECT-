// ChatController.java
package org.example;

public class ChatController {
    private final GeminiFacade geminiFacade = new GeminiFacade();

    public String processRequest(String receiverName, String post, String organization,
                                 String address, String senderName, String question, String flag) {

        // Generate email template
        String fullInfo = String.format(
                "Compose a professional email to: %s (%s) at %s. From: %s. Content: %s",
                receiverName, post, organization, senderName, question +"just give email at response no placeholders should be there just need email at response if something missing assume it"
        );

        // Get Gemini response
        String answer = geminiFacade.getGeminiAnswer(fullInfo);

        // Send email if confirmed
        if (Boolean.parseBoolean(flag.toLowerCase())) {
            geminiFacade.sendEmail();
            geminiFacade.sendData();
            return "EMAIL SENT:\n" + answer;
        }

        return "DRAFT:\n" + answer;
    }
}