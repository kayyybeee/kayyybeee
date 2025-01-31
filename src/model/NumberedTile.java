package model;

public class NumberedTile extends Tuile {

    public NumberedTile(int value) {
        super(value); // La valeur doit être supérieure à 0
    }

    @Override
    public boolean isEmpty() {
        return false; // Cette tuile n'est jamais vide
    }
}
