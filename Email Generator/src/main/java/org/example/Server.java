// Server.java
package org.example;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    // Read client data
                    String receivedData = reader.readLine();
                    if (receivedData != null) {
                        String[] parts = receivedData.split("::");
                        if (parts.length == 7) {
                            // Process request
                            ChatController controller = new ChatController();
                            String response = controller.processRequest(
                                    parts[0], parts[1], parts[2],
                                    parts[3], parts[4], parts[5], parts[6]
                            );

                            // Send response
                            writer.println(response);
                        }
                    }
                }
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}