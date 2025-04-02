package org.example;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GeminiService {

    private final String apiKey = System.getenv("Gemini_Api_Key");
    private final String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    String Answer ="";
    public String getAnswer(String question) {

        try {

            URL url = new URL(apiUrl + "?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);



            String payload = "{" +
                    "  \"contents\": [{" +
                    "    \"parts\": [{" +
                    "      \"text\": \"" + question + "\"" +
                    "    }]" +
                    "  }]," +
                    "  \"generationConfig\": {" +
                    "    \"temperature\": 0.9," +
                    "    \"topK\": 1," +
                    "    \"topP\": 1," +
                    "    \"maxOutputTokens\": 2048" +
                    "  }," +
                    "  \"safetySettings\": [{" +
                    "    \"category\": \"HARM_CATEGORY_HARASSMENT\"," +
                    "    \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"" +
                    "  }, {" +
                    "    \"category\": \"HARM_CATEGORY_HATE_SPEECH\"," +
                    "    \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"" +
                    "  }, {" +
                    "    \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\"," +
                    "    \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"" +
                    "  }, {" +
                    "    \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\"," +
                    "    \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"" +
                    "  }]" +
                    "}";

            OutputStream os = conn.getOutputStream();
                os.write(payload.getBytes());


            Scanner responseScanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            while (responseScanner.hasNextLine()) {
                response.append(responseScanner.nextLine());
            }

            JSONObject json = new JSONObject(response.toString());
            Answer = json
                    .getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");

            return Answer;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}