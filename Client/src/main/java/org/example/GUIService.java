package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class GUIService {
    private JTextField receiverField, postField, orgField, addressField, senderField, questionField;
    private JTextArea responseArea;
    private JCheckBox sendCheckbox;

    public GUIService() {
        // Create frame
        JFrame frame = new JFrame("Email Generator");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        String[] labels = {"Recipient Name", "Post", "Organization", "Address", "Your Name", "Message"};

        // Create fields
        receiverField = new JTextField();
        postField = new JTextField();
        orgField = new JTextField();
        addressField = new JTextField();
        senderField = new JTextField();
        questionField = new JTextField();

        // Add components to input panel
        inputPanel.add(new JLabel(labels[0]));
        inputPanel.add(receiverField);
        inputPanel.add(new JLabel(labels[1]));
        inputPanel.add(postField);
        inputPanel.add(new JLabel(labels[2]));
        inputPanel.add(orgField);
        inputPanel.add(new JLabel(labels[3]));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel(labels[4]));
        inputPanel.add(senderField);
        inputPanel.add(new JLabel(labels[5]));
        inputPanel.add(questionField);

        // Confirmation Checkbox
        inputPanel.add(new JLabel("Send Email Immediately?"));
        sendCheckbox = new JCheckBox();
        inputPanel.add(sendCheckbox);

        // Response Area
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(responseArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Generated Email"));

        // Send Button
        JButton sendButton = new JButton("Generate Email");
        sendButton.addActionListener(this::handleSend);

        // Layout
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(sendButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void handleSend(ActionEvent e) {
        try {
            // Get all field values
            String receiver = receiverField.getText();
            String post = postField.getText();
            String org = orgField.getText();
            String address = addressField.getText();
            String sender = senderField.getText();
            String question = questionField.getText();
            String flag = Boolean.toString(sendCheckbox.isSelected());

            // Connect to server
            try (Socket socket = new Socket("localhost", 5000);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                // Send request
                String request = String.join("::", receiver, post, org, address, sender, question, flag);
                writer.println(request);

                // Read response
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }

                // Update GUI
                SwingUtilities.invokeLater(() -> {
                    responseArea.setText(response.toString());
                    if (sendCheckbox.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Email sent successfully!");
                    }
                });
            }
        } catch (IOException ex) {
            SwingUtilities.invokeLater(() -> {
                responseArea.setText("Error: " + ex.getMessage());
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIService());
    }
}