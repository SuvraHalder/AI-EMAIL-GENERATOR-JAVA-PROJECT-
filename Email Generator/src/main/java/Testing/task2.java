package Testing;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class task2 {

    public static void main(String[] args) {
    try {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept" , "application/json");
        conn.setRequestProperty("Content-Type" , "application/json");
        conn.setDoOutput(true);


        JSONObject json = new JSONObject();
        json.put("name", "Alice");
        json.put("email", "alice@example.com");
        json.put("course", "Java Programming");


        OutputStream os = conn.getOutputStream();

        os.write(json.toString().getBytes());

        InputStream is = conn.getInputStream();
        Scanner sc  =  new Scanner(is);
        StringBuilder sb =  new StringBuilder();

        while(sc.hasNextLine()){

            sb.append(sc.next());

        }

        System.out.println(sb);
        JSONObject j =  new JSONObject(sb.toString());


        System.out.println("ID : " + j.getInt("id"));
        System.out.println("Name : "+j.getString("name"));
        System.out.println("Course : "+j.getString("course") );




    }catch (Exception e){

        e.printStackTrace();
    }

    }
}
