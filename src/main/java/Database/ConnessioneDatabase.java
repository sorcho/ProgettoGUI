package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    public Connection connection;
    private String url = "jdbc:postgresql://ep-muddy-feather-41247684.eu-central-1.aws.neon.tech/progetto";
    private String user = "sorcho";
    private String password = "OsDunIv40EUm";

    private ConnessioneDatabase() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null)
            instance = new ConnessioneDatabase();
        else if (instance.connection.isClosed())
            instance = new ConnessioneDatabase();
        return instance;
    }

}
