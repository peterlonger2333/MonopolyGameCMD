package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import org.jetbrains.annotations.NotNull;

/**
 * Represent a square on the game board in the Monopoly Game. A {@link Square} is identified by its {@code label} or its
 * {@code index}. Most importantly, a {@link Square} can have an effect on the game state, once a player has landed on it
 * or passed by.
 */
public abstract class Square {
    private final int index;
    private final String label;

    public Square(int index, @NotNull String label) {
        this.index = index;
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public @NotNull String getLabel() {
        return label;
    }

    /**
     * Carry out this square's effect on the given game.
     *
     * @param game The current game
     */
    public abstract void onEnter(Game game);
}
