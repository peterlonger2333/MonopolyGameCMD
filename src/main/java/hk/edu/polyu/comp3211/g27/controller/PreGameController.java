package hk.edu.polyu.comp3211.g27.controller;

import hk.edu.polyu.comp3211.g27.model.Game;

public class PreGameController {
    /**
     * Start a new game. The game can subsequently be fetched from the {@link GameHolder}
     *
     * *Interactively* read game parameters from user. Alternatively, delegate the reading task to
     * the "front-end" and only takes a bunch of parameters on this method.
     *
     * @throws IllegalStateException when a {@link Game} cannot be created
     */
    public void newGame() throws IllegalStateException{}

    /**
     * Load an existing game. The game can subsequently be fetched from the {@link GameHolder}
     *
     * @param gameId Id of the game to be loaded
     * @throws IllegalArgumentException when the id is not found
     */
    public void loadGame(String gameId) throws IllegalArgumentException {}
    
    /**
     * Directly print the help manual to the screen. The simplest implementation is to print out the rule line by line to
     * {@code System.out}. Alternatively, enter an interactive reading session like *less* (a linux program)
     */
    public void help() {}
}
