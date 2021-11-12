package hk.edu.polyu.comp3211.g27;

import hk.edu.polyu.comp3211.g27.controller.InGameController;
import hk.edu.polyu.comp3211.g27.controller.PostGameController;
import hk.edu.polyu.comp3211.g27.controller.PreGameController;
import hk.edu.polyu.comp3211.g27.view.CmdView;

import java.io.IOException;
import java.util.Scanner;

/**
 * The game entry point.
 */
public class Client {
    private Scanner scanner;
    private GameState state;
    private PreGameController pre;
    private InGameController in;
    private PostGameController post;
    private CmdView view;

    public static void main(String[] args) {
        // register a signal handler on SIGINT to quit the current game
        // prompt the user if he wants to save the game
    }

    public void run() throws IOException {
        state = GameState.PRE_GAME;

        while (true) {
            if (state == GameState.PRE_GAME) preGame();
            else if (state == GameState.IN_GAME) inGame();
            else postGame();
        }
    }

    private void preGame() {
        System.out.print("Start new game (1) or Load Existing Game (2): ");
        int option = scanner.nextInt();

        if (option == 1) {
            startNewGame();
        } else if (option == 2){
            loadGame();
        } else {
            // exception handling
        }

        state = GameState.IN_GAME;
    }

    private void startNewGame() {

    }

    private void loadGame() {

    }

    private void inGame() throws IOException {
        System.out.println(view.game());
        in.doTurn();
        if (in.isGameEnd()) state = GameState.POST_GAME;
    }

    private void postGame() {
        post.reportEndGameStatus();
    }
}

enum GameState {
    PRE_GAME,
    IN_GAME,
    POST_GAME
}