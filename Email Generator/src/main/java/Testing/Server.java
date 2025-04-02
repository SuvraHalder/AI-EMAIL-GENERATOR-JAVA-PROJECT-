package Testing;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args){
        try {

            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server Witing for response");

            Socket socket =  serverSocket.accept();
            System.out.println(" Client is Connected ");
            Scanner sc  =  new Scanner(socket.getInputStream());
            String s = sc.nextLine();

            System.out.println("Received From Client " + s);

            PrintWriter out  =  new PrintWriter(socket.getOutputStream() , true);
            out.println("Hello Client");

        }
        catch(Exception e){

            e.printStackTrace();

        }



    }

}
