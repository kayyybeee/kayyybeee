package model;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private Tuile[][] tiles;
    private int score;

    public Grille() {
        tiles = new Tuile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new WinningTile.EmptyTile(); // Initialise la grille avec des tuiles vides
            }
        }
        score = 0; // Initialisation du score à 0
        addRandomTile(); // Ajoute la première tuile
        addRandomTile(); // Ajoute la deuxième tuile
    }

    public int getScore() {
        return score;
    }

    public Tuile getTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean isGameOver() {
        // Vérifie s'il y a des cases vides
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].isEmpty()) {
                    return false;
                }
            }
        }

        // Vérifie s'il y a des fusions possibles horizontalement
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j].getValue() == tiles[i][j + 1].getValue()) {
                    return false;
                }
            }
        }

        // Vérifie s'il y a des fusions possibles verticalement
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                if (tiles[i][j].getValue() == tiles[i + 1][j].getValue()) {
                    return false;
                }
            }
        }

        // Si aucune des conditions n'est remplie, le jeu est fini
        return true;
    }

    public boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            Tuile[] row = tiles[i];
            moved |= slideAndMergeRow(row);
        }
        if (moved) {
            addRandomTile();
        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            Tuile[] row = tiles[i];
            reverse(row);
            moved |= slideAndMergeRow(row);
            reverse(row);
        }
        if (moved) {
            addRandomTile();
        }
        return moved;
    }

    public boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            Tuile[] column = new Tuile[4];
            for (int i = 0; i < 4; i++) {
                column[i] = tiles[i][j];
            }
            moved |= slideAndMergeRow(column);
            for (int i = 0; i < 4; i++) {
                tiles[i][j] = column[i];
            }
        }
        if (moved) {
            addRandomTile();
        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            Tuile[] column = new Tuile[4];
            for (int i = 0; i < 4; i++) {
                column[i] = tiles[i][j];
            }
            reverse(column);
            moved |= slideAndMergeRow(column);
            reverse(column);
            for (int i = 0; i < 4; i++) {
                tiles[i][j] = column[i];
            }
        }
        if (moved) {
            addRandomTile();
        }
        return moved;
    }

    private boolean slideAndMergeRow(Tuile[] row) {
        boolean moved = false;
        List<Tuile> newRow = new ArrayList<>();

        // Récupérer les valeurs non nulles (non-vides)
        for (Tuile tile : row) {
            if (!tile.isEmpty()) {
                newRow.add(tile);
            }
        }

        // Fusionner les tuiles
        for (int i = 0; i < newRow.size() - 1; i++) {
            if (newRow.get(i).getValue() == newRow.get(i + 1).getValue()) {
                int mergedValue = newRow.get(i).getValue() * 2;
                score += mergedValue;
                if (mergedValue >= 2048) {
                    newRow.set(i, new WinningTile(mergedValue)); // Tuile gagnante
                } else {
                    newRow.set(i, new MergedTile(mergedValue)); // Fusion normale
                }
                newRow.remove(i + 1); // Supprime la deuxième tuile après la fusion
                newRow.add(new WinningTile.EmptyTile()); // Ajoute une tuile vide à la fin
                moved = true;
            }
        }

        // Compléter avec des tuiles vides si nécessaire
        while (newRow.size() < 4) {
            newRow.add(new WinningTile.EmptyTile());
        }

        // Mettre à jour la rangée et vérifier si un mouvement a été fait
        for (int i = 0; i < row.length; i++) {
            if (row[i].getValue() != newRow.get(i).getValue()) {
                row[i] = newRow.get(i);
                moved = true;
            }
        }

        return moved;
    }

    private void reverse(Tuile[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            Tuile temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private void addRandomTile() {
        List<int[]> emptyTiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].isEmpty()) {
                    emptyTiles.add(new int[]{i, j});
                }
            }
        }

        if (!emptyTiles.isEmpty()) {
            int[] pos = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
            tiles[pos[0]][pos[1]] = new NumberedTile(Math.random() < 0.9 ? 2 : 4); // Ajoute une nouvelle tuile numérotée
        }
    }
}
