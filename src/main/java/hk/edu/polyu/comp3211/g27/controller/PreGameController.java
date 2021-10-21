package hk.edu.polyu.comp3211.g27.controller;

public class PreGameController {
    /**
     * Start a new game. The game can subsequently be fetched from the [GameHolder].
     *
     * The current method interactively reads game parameter from user. Alternatively, delegate the reading task to
     * the "front-end" and only takes a bunch of parameters on this method.
     */
    public void newGame() {}

    /**
     * Load an existing game. The game can subsequently be fetched from the [GameHolder]
     *
     * @param gameId Id of the game to be loaded
     * @throws IllegalArgumentException when the id is not found
     */
    public void loadGame(String gameId) throws IllegalArgumentException {
        this.gameId = gameId;
    }
    
    /**
     * Get the help menu.
     *
     * This method provides an entry to the let the user read the software manual.
     */
    public void Help(){}
    
}
