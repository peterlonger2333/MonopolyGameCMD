package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

import java.util.Random;

public class ChanceSquare extends Square{
    private final static int MULTIPLE = 10;

    public ChanceSquare(int index, String label) {
        super(index, label);
    }

    private int getAmount(){
        return ((new Random().nextInt(50) - 30) * MULTIPLE);
    }

    /**
     * The player landing on Chance Square either:
     *
     * 1. Gains up to HKD 200.
     * 2. Loses up to HKD 300.
     *
     * @param game The game on which to take effect
     */
    @Override
    public void effect(Game game) {

    }
}
