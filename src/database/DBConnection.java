
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String databaseName="client_schedule";
    private static final String DB_URL="jdbc:mysql://localhost:3306/"+databaseName;
    private static final String username="sqlUser";
    private static final String password="Passw0rd!";
    static Connection conn;

    public static void makeConnection() throws Exception{
        conn= DriverManager.getConnection(DB_URL,username,password);
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection() throws Exception{
        conn.close();
    }

//    private static final String protocol = "jdbc";
//    private static final String vendor = ":mysql:";
//    private static final String location = "//localhost/";
//    private static final String databaseName = "client_schedule";
//    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
//    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
//    private static final String userName = "sqlUser"; // Username
//    private static String password = "Passw0rd!"; // Password
//    public static Connection connection;  // Connection Interface
//    static Connection conn;
//
//
//    public static void makeConnection()
//    {
//        try {
//            Class.forName(driver); // Locate Driver
//            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
//            System.out.println("Connection successful!");
//        }
//        catch(Exception e)
//        {
//            System.out.println("Error:" + e.getMessage());
//        }
//    }
//
//        public static Connection getConnection(){
//        return conn;
//    }
//
//    public static void closeConnection() {
//        try {
//            connection.close();
//            System.out.println("Connection closed!");
//        }
//        catch(Exception e)
//        {
//            System.out.println("Error:" + e.getMessage());
//        }
//    }


}
