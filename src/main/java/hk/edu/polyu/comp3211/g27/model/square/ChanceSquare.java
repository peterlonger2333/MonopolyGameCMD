package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;

import java.util.Random;

public class ChanceSquare extends Square{
    private final static int MULTIPLE = 10;

    @JsonCreator
    public ChanceSquare(@JsonProperty("index") int index,
                        @JsonProperty("label") String label) {
        super(index, label);
    }

    private int getAmount(){
        return ((new Random().nextInt(50) - 30 + 1) * MULTIPLE);
    }

    /**
     * The player landing on Chance Square either:
     * 1. Gains up to HKD 200.
     * 2. Loses up to HKD 300.
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {
        int amount = getAmount();
        game.addMoney(
                amount,
                game.currentTurn().getPlayer()
        );
        if(amount > 0)
            System.out.println("You have gained: $" + amount);
        else if(amount < 0)
            System.out.println("You have lost: $" + Math.abs(amount));
        else
            System.out.println("You neither gain or lose money.");
    }
}
