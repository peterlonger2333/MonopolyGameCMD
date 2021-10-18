package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;

// Who interprets the effect of a square?
public abstract class Square {
    private final int index;
    private final String label;

    public Square(int index, String label) {
        this.index = index;
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Carry out this square's effect on the given game
     * @param game The current game
     */
    public abstract void effect(Game game);
}
