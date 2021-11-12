package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

public class GoSquare extends Square{
    public static final int SALARY = 1500;

    public GoSquare(int index, String label){
        super(index, label);
    }

    /**
     * Each time a player lands on or passes by Go square, the player will get HKD 1500 salary.
     *
     * @param game The game which to take effect
     */
    @Override
    public void onEnter(Game game) {
        game.addMoney(
                SALARY,
                game.currentTurn().getPlayer()
        );
    }
}
