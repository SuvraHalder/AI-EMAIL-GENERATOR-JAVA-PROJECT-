package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDBCservice {

    public  boolean  sendData( String SenderName , String ReceiverName ){

        String url = System.getenv("JDBC_DRIVER_URL");
        String user = "root";
        String password = System.getenv("DATABASE_PASSWORD");
        boolean b = false;
        try {
            String Query = "insert into data(SenderEmail , ReceiverEmail , DOE ) values(? ,? ,?)";

            Connection conn = DriverManager.getConnection(url , user  , password );

            PreparedStatement ptmst = conn.prepareStatement(Query);
            ptmst.setString(1 , SenderName );
            ptmst.setString(2 , ReceiverName);

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String todayAsString = today.format(formatter);

            ptmst.setString(3 , todayAsString);
            ptmst.executeUpdate();
            ptmst.close();
            conn.close();

        }
        catch (SQLException e) {

           throw new RuntimeException();

        }

       b = true ;


    return b;
    }


}
