package database;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    // Méthode pour sauvegarder les scores dans la base de données
    public static void saveScore(String playerName, int score) {
        String sql = "INSERT INTO scores (name, score) VALUES (?, ?);";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    // Méthode pour récupérer les trois meilleurs scores
    public static List<String> getTopThreeScores() {
        List<String> topScores = new ArrayList<>();
        String sql = "SELECT name, score FROM scores ORDER BY score DESC LIMIT 3";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (((ResultSet) rs).next()) {
                String scoreEntry = rs.getString("name") + " - " + rs.getInt("score");
                topScores.add(scoreEntry);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving scores: " + e.getMessage());
        }
        return topScores;
    }
}
