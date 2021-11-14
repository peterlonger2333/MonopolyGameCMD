package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

public class FreeParkingSquare extends Square{

    public FreeParkingSquare(int index, String label){
        super(index, label);
    }

    /**
     * Free parking square has no effect on game.
     *
     * @param game The game which to take effect
     */
    @Override
    public void onEnter(Game game) {}
}
