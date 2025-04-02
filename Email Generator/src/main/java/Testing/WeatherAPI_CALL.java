package Testing;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

public class WeatherAPI_CALL {

    public static void main(String[] args) {
        try {

            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=22.57&longitude=88.36&current_weather=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept" , "application/json");

            System.out.println(conn.getResponseCode());
            InputStream  in = conn.getInputStream();
            Scanner sc = new Scanner(in);
            StringBuilder s = new StringBuilder();
            while(sc.hasNextLine()){

                s.append(sc.nextLine());


            }
            System.out.println(s);
            JSONObject json =  new JSONObject(s.toString());
            JSONObject json1 =  json.getJSONObject("current_weather");

            System.out.println("Current Weather ");
            System.out.println("Temperature : "+json1.getInt("temperature")+"°C");
            System.out.println("Wind Speed : "+json1.getInt("windspeed")+" kmph ");




        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
