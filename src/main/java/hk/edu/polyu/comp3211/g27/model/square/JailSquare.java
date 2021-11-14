package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hk.edu.polyu.comp3211.g27.model.Dices;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.Turn;

import java.io.IOException;

import static hk.edu.polyu.comp3211.g27.Utils.readOption;

public class JailSquare extends Square{
    public static final int FINE = 150;

    @JsonCreator
    public JailSquare(@JsonProperty("index") int index,
                      @JsonProperty("label") String label) {
        super(index, label);
    }

    /**
     * If a player lands on Jail Square, he/she is "Just Visiting", and Jail Square take no effect.
     *
     * If a player get Jail Square by "Go To Jail Square", the player cannot make a move.
     *
     * The player can leave by either:
     *  1. Throw doubles in next three rounds.
     *  2. Pay a fine of HKD 150.
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {
        // place holder
    }

    /**
     * A player gets out of jail by either throwing doubles (i.e., both dice
     * coming out the same face up) on any of her next three turns (if she succeeds in doing
     * this, she immediately moves forward by the number of spaces shown by her doubles
     * throw) or paying a fine of HKD 150 before she rolls the dice on either of her next two
     * turns. If the player does not throw doubles by her third turn, she must pay the HKD 150
     * fine. She then gets out of jail and immediately moves forward the number of spaces
     * shown by her throw.
     */
    public void onPrisonerTurn(Game game) throws IOException {
        Turn currentTurn = game.currentTurn();
        Player currentPlayer = currentTurn.getPlayer();

        if (game.currentMoney(currentPlayer) < FINE) { // don't have enough money to pay the fine
            playDouble(game);
        } else {
            System.out.print("Do you want to pay the fine before drawing the dice? (Y/n): ");
            String option = readOption();

            if (option.equalsIgnoreCase("y")) { // Pay the fine and let go
                System.out.println("Hmm, seems pretty rich. Goodbye!");
                game.subtractMoney(FINE, currentPlayer);
                game.releaseFromJail(currentPlayer);

                // do a normal turn
                int[] dices = Dices.makeDraw();
                currentTurn.setStepToTake(dices[0] + dices[1]);
                game.move();
            } else {
                playDouble(game);
            }
        }

        game.next(); // transition is delegated from InGameController to here
    }

    private void playDouble(Game game) throws IOException {
        Turn currentTurn = game.currentTurn();
        Player currentPlayer = currentTurn.getPlayer();

        int[] dices = Dices.makeDraw();

        if (dices[0] == dices[1]) { // drawn double
            System.out.println("Lucky one... Goodbye!");
            game.releaseFromJail(currentPlayer);
            currentTurn.setStepToTake(dices[0] + dices[1]);
            game.move();
        } else { // check if this is the player's last term
            if (game.inJailCheck(currentPlayer) == 1) { // this is the last term, the player must pay the fine
                System.out.println("This is your last term - a fine is automatically paid! Goodbye!");

                game.subtractMoney(FINE, currentPlayer);
                game.releaseFromJail(currentPlayer);

                currentTurn.setStepToTake(dices[0] + dices[1]);
                game.move();
            } else { // still in jail, status is automatically updated
                System.out.println("Enjoy your remaining terms.");
            }
        }
    }
}
