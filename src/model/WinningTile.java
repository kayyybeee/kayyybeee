package model;

import model.NumberedTile;
import model.Tuile;

public class WinningTile extends NumberedTile {

    public WinningTile(int value) {
        super(value); // Initialiser avec 2048 ou plus
    }

    public boolean isWinningTile() {
        return this.value >= 2048; // Condition de victoire
    }

    public void celebrateWin() {
        // Code pour célébrer la victoire
    }

    public static class EmptyTile extends Tuile {

        public EmptyTile() {
            super(0); // Une tuile vide a toujours une valeur de 0
        }

        @Override
        public boolean isEmpty() {
            return true; // Une tuile vide retourne toujours true
        }
    }
}
