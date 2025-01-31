package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/game_2048"; // Remplace "game_2048" par le nom de ta base
    private static final String DB_USER = "polytech";
    private static final String DB_PASSWORD = "polytech"; // Mot de passe PostgreSQL

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
