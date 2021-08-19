package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
    public Connection dbConnection;

    public Connection getDbConnection(){
        String databaseName = "Login";
        String databaseUser = "root";
        String databasePassword = "Amar123456";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return dbConnection;
    }
}
