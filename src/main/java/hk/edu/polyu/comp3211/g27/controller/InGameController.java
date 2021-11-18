package hk.edu.polyu.comp3211.g27.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hk.edu.polyu.comp3211.g27.model.Dices;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.SquareFactory;
import hk.edu.polyu.comp3211.g27.model.Turn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InGameController {
    private final Random dice = new Random(0x3211);
    /**
     * Enter into an interactive session with the current player representing the player's current turn. A turn can
     * be logically divided into three phases: preMove, move, and postMove. The three phases may not all be carried out,
     * for example, when the player is currently in jail.
     *
     * This method automatically increments the "turn count".
     */
    public void doTurn() throws IOException {
        if (game().inJailCheck(turn().getPlayer()) != 0) {
            SquareFactory.getJailSquare().onPrisonerTurn(game());
            return;
        }

        move();

        postMove();

        game().next();
    }

    private void move() {
        System.out.println("Round: " + game().getRound() + ", " + game().currentTurn().getPlayer().getId() + "'s turn");
        int[] draw = Dices.makeDraw();
        int move = draw[0] + draw[1];

        turn().setStepToTake(move);
        game().move();
    }

    private void postMove() {
        System.out.println("You are current on square: " + turn().getNewSquare().getLabel() + "| index: "
                + turn().getNewSquare().getIndex()); // report new position

        if (!turn().getOldSquare().equals(SquareFactory.getGoToJailSquare())) { // not from GoToJail to Jail
            if (turn().getNewSquare().getIndex() < turn().getOldSquare().getIndex()
                    && !turn().getNewSquare().equals(SquareFactory.getGoSquare())) { // passed GoSquare
                SquareFactory.getGoSquare().onEnter(game()); // add money
            }
        }

        turn().getNewSquare().onEnter(game());
    }

    /**
     * Check whether the game is in the ending state.
     *
     * @return true if the game ends after a turn
     */
    public boolean isGameEnd() {
        return game().isGameEnd();
    }

    /**
     * Archive the game.
     *
     * @return id of the game archived
     */
    public String archive() throws IOException {
        String json = new ObjectMapper().writeValueAsString(game());

        File file = new File("./.archive/" + game().getId() + ".json");
        file.getParentFile().mkdirs(); //TODO: handle exception
        FileWriter writer = new FileWriter(file);

        writer.write(json);
        writer.close();

        return game().getId();
    }

    private Game game() {
        Game game = GameHolder.get();
        if (game == null) throw new IllegalStateException();

        return game;
    }

    private Turn turn() {
        return game().currentTurn();
    }
}
