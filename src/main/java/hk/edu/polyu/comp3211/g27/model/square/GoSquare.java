package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

public class GoSquare extends Square{
    private static final int SALARY = 1500;

    public GoSquare(int index, String label){
        super(index, label);
    }

    public static int getSALARY() {
        return SALARY;
    }

    /**
     * Each time a player lands on Go square, the player will get HKD 1500 salary.
     *
     * @param game The game which to take effect
     */

    @Override
    public void effect(Game game) {

    }
}
