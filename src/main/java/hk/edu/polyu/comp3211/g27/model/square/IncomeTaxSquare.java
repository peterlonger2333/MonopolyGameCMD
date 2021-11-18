package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hk.edu.polyu.comp3211.g27.model.Game;

public class IncomeTaxSquare extends Square{
    public static final double RATE = 0.1;

    @JsonCreator
    public IncomeTaxSquare(@JsonProperty("index") int index,
                           @JsonProperty("label") String label) {
        super(index, label);
    }

    /**
     * The player landing on Income Tax Square will be charged 10% of total money
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {
        int tax =  (int) (Math.floor((game.currentMoney(game.currentTurn().getPlayer()) * RATE)/10.0)*10.0);
        System.out.println("Paying tax: $" + tax);

        game.subtractMoney(
                tax,
                game.currentTurn().getPlayer()
        );
    }
}
