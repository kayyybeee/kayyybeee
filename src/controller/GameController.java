package controller;

import model.Grille; // Assurez-vous que ce package est bien défini
import java.awt.event.KeyEvent; // Ajout de l'import manquant

public class GameController {
    private Grille grille;

    public GameController(Grille grille) {
        this.grille = grille;
    }

    public void handleKeyInput(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                grille.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                grille.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                grille.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                grille.moveRight();
                break;
            default:
                // Optionnel : gérer d'autres entrées
                break;
        }
    }
}
