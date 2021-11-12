package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * A convenient and efficient place to store and fetch {@link Square}s in a game board.
 */
public class SquareFactory {
    public static final int SQUARE_CNT = 20; // number of squares in the game

    public static @Nullable Square getSquare(int index) {
        return null;
    }

    public static @Nullable Square getSquare(String label) {
        return null;
    }

    public static @Nullable PropertySquare getPropertySquare(int index) {
        return null;
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @param label the square label
     * @return the property square with label
     */
    public static @Nullable PropertySquare getPropertySquare(String label) {
        return null;
    }

    public static ChanceSquare getChanceSquare(int index) {
        return null;
    }

    public static @NotNull GoSquare getGoSquare() {
        return null;
    }

    public static @NotNull GoToJailSquare getGoToJailSquare() {
        return null;
    }

    public static @NotNull IncomeTaxSquare getIncomeTaxSquare() {
        return null;
    }

    public static @NotNull JailSquare getJailSquare() {return null;}
}
