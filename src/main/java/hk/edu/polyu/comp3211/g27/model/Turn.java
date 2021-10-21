package hk.edu.polyu.comp3211.g27.model;

import com.sun.istack.internal.NotNull;
import hk.edu.polyu.comp3211.g27.model.square.Square;

/**
 * {@link Turn} is the clock of the game. It carries all information for a player's round.
 */
public class Turn {
    @NotNull private final Player player;
    @NotNull private final Square oldSquare;
    private int stepToTake = 0;
    @NotNull private Square newSquare;

    public Turn(@NotNull Player player,@NotNull Square oldSquare) {
        this.player = player;
        this.oldSquare = oldSquare;
    }

    public void setStepToTake(int stepToTake) {
        this.stepToTake = stepToTake;
    }

    public void setNewSquare(@NotNull Square newSquare) {
        this.newSquare = newSquare;
    }

    public Player getPlayer() {
        return player;
    }

    public Square getOldSquare() {
        return oldSquare;
    }

    public int getStepToTake() {
        return stepToTake;
    }

    public Square getNewSquare() {
        return newSquare;
    }
}
