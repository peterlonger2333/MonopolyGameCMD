package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.SquareFactory;

public class GoToJailSquare extends Square {

    @JsonCreator
    public GoToJailSquare(@JsonProperty("index") int index,
                          @JsonProperty("label") String label) {
        super(index, label);
    }

    /**
     * The player landing on Go To Jail Square
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {
        Player currentPlayer = game.currentTurn().getPlayer();

        game.move(currentPlayer, SquareFactory.getJailSquare()); // physically put in jail
        game.putInJail(currentPlayer); // TODO: may specify jail term in the API
    }
}
