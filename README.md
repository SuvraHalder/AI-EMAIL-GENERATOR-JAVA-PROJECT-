
![Logo](https://logos-download.com/wp-content/uploads/2016/10/Java_logo_icon.png)
# Email Generation System

A Java application that generates professional emails using Gemini AI, with options to preview drafts and send final emails.


## Features
- 📧 Generate professional email drafts using AI
- ✉️ Send emails directly via Gmail
- 💾 Store email records in a database
- 🖥️ User-friendly GUI interface
- 🔄 Client-server architecture


## Tech Stack

- **Backend**: Core Java
- **Frontend**: Java Swing
- **APIs**: Gemini 2.0 Flash Api , Java Mail
- **Database**: MySQL ( JDBC Usage )




## API Documentation

[Gemini API Documentation](https://ai.google.dev/gemini-api/docs)


## Optimizations

What optimizations did you make in your code? E.g. refactors, performance improvements, accessibility



## Class Explanations

### Client Side
1. GUIService.java
   - What it does: The main window you see (Swing GUI)
   - Contains:
     * Text fields for email details (To/From/Message)
     * "Send" button
     * Area to show the generated email
   - How it works:
     * Collects your input
     * Shows the AI-generated email
     * Lets you confirm before sending

2. ClientService.java
   - What it does: Talks to the server
   - Important parts:
     * Connects to port 5000
     * Sends data in format: "field1::field2::field3..."
     * Waits for server reply

### Server Side
1. Server.java
   - What it does: Listens for client connections
   - Key behavior:
     * Runs constantly on port 5000
     * Creates new thread for each client
     * Passes data to ChatController

2. ChatController.java
   - What it does: Manages the whole process
   - Steps:
     1. Gets data from client
     2. Creates email using Gemini
     3. Decides to send/save based on user choice

### Helper Services
1. GeminiFacade.java
   - Talks to Gemini AI
   - Input: Your email details
   - Output: Professional email text

2. GmailSender.java
   - Sends actual emails
   - Uses: smtp.gmail.com on port 587
   - Needs: Gmail app password

3. JDBCservice.java
   - Saves email records to database
   - Stores: Sender, Receiver, Date

## How Data Flows
1. You type details in GUI
2. Client sends to Server
3. Server asks Gemini to write email
4. You see the draft in GUI
5. If you confirm, server sends it

## To Run
1. Start server:
   java -cp bin org.example.Server

2. Open client:
   java -cp bin org.example.GUIService

## Config Needed
Create a .env file with:
- Gemini_Api_Key=your_key_here
- APP_PASSWORD_GMAIL=your_gmail_app_password
- JDBC_DRIVER_URL=jdbc:mysql://your_db_url

## Screenshots

![Run The Gui and Give User Inputs](https://github.com/SuvraHalder/AI-EMAIL-GENERATOR-JAVA-PROJECT-/blob/921f32ee5de3d9a4debd503b6b6185607e452449/Screenshot%202025-04-02%20131013.png)

![Now Run The Server](https://github.com/SuvraHalder/AI-EMAIL-GENERATOR-JAVA-PROJECT-/blob/921f32ee5de3d9a4debd503b6b6185607e452449/Screenshot%202025-04-02%20131135.png)

![Now Press Generate Email to Generate Email Body](https://github.com/SuvraHalder/AI-EMAIL-GENERATOR-JAVA-PROJECT-/blob/921f32ee5de3d9a4debd503b6b6185607e452449/Screenshot%202025-04-02%20131351.png)

![Now Check The CheckBox to Finalize the Email and press The Generate email Button to Send The Email](https://github.com/SuvraHalder/AI-EMAIL-GENERATOR-JAVA-PROJECT-/blob/921f32ee5de3d9a4debd503b6b6185607e452449/Screenshot%202025-04-02%20131359.png)

![Email Sent Properly](https://github.com/SuvraHalder/AI-EMAIL-GENERATOR-JAVA-PROJECT-/blob/921f32ee5de3d9a4debd503b6b6185607e452449/Screenshot%202025-04-02%20131438.png)
## Contribution

### Frontend (GUI)
- **Amit Thakur(524110031)**  
  `GUIService.java` - Main application window design  
  `ClientService.java` - Client-server communication handler

- **Pratyush Kumar(524110044)**  
  UI components, input validation, and user experience

### Backend
- **Sayan Pradhan(524410009)**  
  `Server.java` - Socket programming and   
  Network protocol design (`::`-delimited format)

### AI & Email Services
- **Suvra Halder(524410030)**  
  `GeminiFacade.java` - Gemini API integration  
  `GmailSender.java` - JavaMail configuration and delivery

### Database
- **Brahmananda Bhoi(524110061)**  
  `JDBCservice.java` - Database schema and CRUD operations  
  Connection pooling implementation



