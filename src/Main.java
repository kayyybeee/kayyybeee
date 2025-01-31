import view.FenetrePrincipale;
import database.DatabaseManager;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Test de la connexion à la base de données
        try (Connection conn = DatabaseManager.connect()) {
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
            } else {
                System.err.println("Failed to connect to PostgreSQL.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Lancer la fenêtre principale du jeu
        new FenetrePrincipale();
    }
}

