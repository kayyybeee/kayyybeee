package view;

import database.ScoreManager;
import model.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class FenetrePrincipale extends JFrame {
    private GrillePanel grillePanel;
    private ScorePanel scorePanel;
    private Grille grille;

    public FenetrePrincipale() {
        setTitle("Jeu 2048");
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialisation du panneau de score
        scorePanel = new ScorePanel();
        add(scorePanel, BorderLayout.NORTH);

        // Initialisation de la grille
        grille = new Grille();
        grillePanel = new GrillePanel(grille);
        grillePanel.setFocusable(true);
        add(grillePanel, BorderLayout.CENTER);

        // Bouton pour afficher les meilleurs scores
        JButton showScoresButton = new JButton("Show Top 3 Scores");
        showScoresButton.addActionListener(e -> showTopScores());
        add(showScoresButton, BorderLayout.SOUTH);

        // Ajout du gestionnaire d'événements clavier
        grillePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean moved = false;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moved = grille.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moved = grille.moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        moved = grille.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moved = grille.moveRight();
                        break;
                }

                if (moved) {
                    scorePanel.updateScore(grille.getScore());
                    grillePanel.repaint();

                    // Vérifie si le jeu est terminé
                    if (grille.isGameOver()) {
                        endGame();
                    }
                }
            }
        });

        setVisible(true);
        grillePanel.requestFocusInWindow();
    }

    private void showTopScores() {
        List<String> topScores = ScoreManager.getTopThreeScores();
        JOptionPane.showMessageDialog(this, String.join("\n", topScores), "Top 3 Scores", JOptionPane.INFORMATION_MESSAGE);
        grillePanel.requestFocusInWindow(); // Redemander le focus après la fermeture du JOptionPane
    }

    private void endGame() {
        String playerName = JOptionPane.showInputDialog(this, "Game Over! Enter your name:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            ScoreManager.saveScore(playerName, grille.getScore());
            JOptionPane.showMessageDialog(this, "Score saved! Thank you for playing, " + playerName + ".");
        } else {
            JOptionPane.showMessageDialog(this, "Score not saved. You must enter a name.");
        }

        int response = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Restart?", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            grille = new Grille();  // Réinitialiser la grille
            grillePanel.setGrille(grille);
            grillePanel.repaint();
            scorePanel.updateScore(0);
        } else {
            dispose();  // Fermer l'application si l'utilisateur ne veut pas rejouer
        }
    }
}
