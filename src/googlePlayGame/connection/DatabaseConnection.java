package googlePlayGame.connection;

import googlePlayGame.city.City;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //URL of data base.
    //local host server(our own computer).
    //127.0.0.1 or localhost.
    private static final String URL=
            "jdbc:mysql://localhost:3306/google_play_game";

    //username of admin of database.
    private static final String USERNAME="root";

    //password of admin of the database.
    private static final String PASSWORD="";

    private static Connection connection=null;



    public void connectToDatabase(){
        try {
            this.connection= DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
        } catch (SQLException exception) {
            System.out.println("Please check your connection!!! OR \n"+
                    "Please Check your URL,USERNAME,PASSWORD again for connection!!!");
        }

        finally {
            printConnectionStatus();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
    private void printConnectionStatus(){
        System.out.println(
                connection==null?
                        "The connection to Database is inactive. ":
                        "the connection to database is active"
        );
    }

}
