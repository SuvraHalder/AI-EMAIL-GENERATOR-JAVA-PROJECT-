package Testing;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Use the server's IP address
            Socket socket = new Socket("192.168.221.238", 5000);
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = userInput.readLine(); // Read user input
                output.println(message); // Send message to server

                if (message.equalsIgnoreCase("exit")) {
                    break; // Exit if user types "exit"
                }

                String serverResponse = input.readLine();
                System.out.println("Server: " + serverResponse); // Display server response
            }

            userInput.close();
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}