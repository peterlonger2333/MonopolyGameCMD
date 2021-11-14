package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.Square;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * {@link Turn} is the clock of the game. It carries all information for a player's round.
 */
public class Turn {
    @NotNull private Player player;
    @NotNull private Square oldSquare;
    private int stepToTake = 0;
    @NotNull private Square newSquare;

    public Turn() {
    }

    public Turn(@NotNull Player player, @NotNull Square oldSquare) {
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setOldSquare(Square oldSquare) {
        this.oldSquare = oldSquare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return stepToTake == turn.stepToTake && player.equals(turn.player) && Objects.equals(oldSquare, turn.oldSquare) && Objects.equals(newSquare, turn.newSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, oldSquare, stepToTake, newSquare);
    }

    @Override
    public String toString() {
        return "Turn{" +
                "player=" + player +
                ", oldSquare=" + oldSquare +
                ", stepToTake=" + stepToTake +
                ", newSquare=" + newSquare +
                '}';
    }
}
