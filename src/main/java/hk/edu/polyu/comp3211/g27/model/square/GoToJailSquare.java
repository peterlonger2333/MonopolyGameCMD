package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

public class GoToJailSquare extends Square {

    public GoToJailSquare(int index, String label) {
        super(index, label);
    }

    /**
     * The player landing on Go To Jail Square
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {

    }
}
