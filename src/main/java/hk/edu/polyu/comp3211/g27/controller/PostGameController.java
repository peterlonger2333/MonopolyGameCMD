package hk.edu.polyu.comp3211.g27.controller;

import hk.edu.polyu.comp3211.g27.model.Game;

public class PostGameController {
    /**
     * Report the game status, including but not limited to:
     *  1. Why the game ends
     *  2. What is the result of the game
     *  3. Summary of the game
     */
    public void reportEndGameStatus() {
        // why the game ends
        if (game().getRound() > 100) {
            System.out.println("Game ends as round exceeds 100");
            return;
        }

        // is there a winner?
        if (game().getCurrentPlayers().isEmpty()) {
            System.out.println("There is no winner");
        } else {
            System.out.println("Congratulations " + game().getCurrentPlayers().get(0) + "! You are the true monopolist!");
        }
    }

    private Game game() {
        return GameHolder.get();
    }
}
