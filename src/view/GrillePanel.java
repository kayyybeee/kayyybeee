package view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import model.Grille;

public class GrillePanel extends JPanel {
    private Grille grille;

    public GrillePanel(Grille grille) {
        this.grille = grille;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileSize = Math.min(getWidth(), getHeight()) / 4; // Taille d'une tuile
        g.setFont(new Font("Arial", Font.BOLD, tileSize / 4));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = grille.getTile(i, j).getValue();
                int x = j * tileSize;
                int y = i * tileSize;

                // Choisir la couleur en fonction de la valeur de la tuile
                g.setColor(getTileColor(value));
                g.fillRect(x, y, tileSize, tileSize);

                // Afficher le texte de la tuile si elle n'est pas vide
                if (value != 0) {
                    g.setColor(Color.BLACK); // Couleur du texte
                    String text = String.valueOf(value);
                    int textWidth = g.getFontMetrics().stringWidth(text);
                    int textHeight = g.getFontMetrics().getAscent();
                    g.drawString(text, x + (tileSize - textWidth) / 2, y + (tileSize + textHeight) / 2);
                }
            }
        }
    }
    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    // Méthode pour obtenir la couleur en fonction de la valeur de la tuile
    private Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(238, 228, 218);
            case 4: return new Color(237, 224, 200);
            case 8: return new Color(242, 177, 121);
            case 16: return new Color(245, 149, 99);
            case 32: return new Color(246, 124, 95);
            case 64: return new Color(246, 94, 59);
            case 128: return new Color(237, 207, 114);
            case 256: return new Color(237, 204, 97);
            case 512: return new Color(237, 200, 80);
            case 1024: return new Color(237, 197, 63);
            case 2048: return new Color(237, 194, 46);
            default: return new Color(205, 193, 180); // Couleur pour les tuiles vides
        }
    }
}
