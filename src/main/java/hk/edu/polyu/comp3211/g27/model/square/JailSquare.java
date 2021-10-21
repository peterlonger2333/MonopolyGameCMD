package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

public class JailSquare extends Square{
    private final static int TURNS = 3;
    private final static int FINE = 150;

    public JailSquare(int index, String label) {
        super(index, label);
    }

    public static int getTURNS() {
        return TURNS;
    }

    public static int getFINE() {
        return FINE;
    }

    /**
     * If a player lands on Jail Square, he/she is "Just Visiting",
     * and Jail Square take no effect.
     *
     * If a player get Jail Square by "Go To Jail Square", the player cannot make a move.
     * The player can leave by either:
     * 1. Throw doubles in next three rounds.
     * 2. Pay a fine of HKD 150.
     *
     * @param game The game on which to take effect
     */
    @Override
    public void effect(Game game) {

    }
}
