package database;

import java.sql.Timestamp;

public class ScoreEntry {
    private String playerName;
    private int score;
    private Timestamp dateCreated;

    public ScoreEntry(String playerName, int score, Timestamp dateCreated) {
        this.playerName = playerName;
        this.score = score;
        this.dateCreated = dateCreated;
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public Timestamp getDateCreated() { return dateCreated; }

    @Override
    public String toString() {
        return String.format("%s: %d points (%s)", playerName, score, dateCreated);
    }
}