package model;

public class Tuile {
    protected int value; // Valeur de la tuile

    public Tuile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public boolean canMergeWith(Tuile other) {
        return this.value == other.getValue();
    }
}
