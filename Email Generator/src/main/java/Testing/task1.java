package Testing;
import org.json.JSONObject;

import java.util.*;
import java.io.InputStream;
import java.net.*;

public class task1 {

    public static void main(String[] args) throws Exception {


    URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept" , "application/json");

    int code   = conn.getResponseCode();
    System.out.println(code);

    InputStream  i =  conn.getInputStream();
    Scanner sc  = new Scanner(i);
    StringBuilder s = new StringBuilder();
    while(sc.hasNext()){

        s.append(sc.next());
    }

        String jsonresponse = s.toString();

JSONObject j =  new JSONObject(jsonresponse);

String s1 =  j.getString("body");


        System.out.println(s1);
    }


}
