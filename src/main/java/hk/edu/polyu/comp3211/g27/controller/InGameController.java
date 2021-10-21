package hk.edu.polyu.comp3211.g27.controller;

public class InGameController {
    /**
     * Enter into an interactive session with the current player representing the player's current turn. A turn can
     * be logically divided into three phases: preMove, move, and postMove. The three phases may not all be carried out,
     * for example, when the player is currently in jail.
     *
     * For extensibility, a "CommunicationChannel" can be passed to this method. The current channel (without parameter)
     * is the standard input and output streams.
     *
     * This method automatically updates the turn to the next player.
     */
    
    private int turn;
    
    public void doTurn() {

    }

    private void preMove() {
        // in-jail check
    }

    private void move() {
        // update game board
    }

    private void postMove() {
        // cary out the square effect
    }
    
    /**
     * update the property of the player.
     */
    public void updateProperty(){}

    /**
     * Check whether the game is in the ending state
     * @return true if the game ends after a turn
     */
    public boolean isGameEnd() {
        return false;
    }

    /**
     * Archive the game.
     *
     * @return id of the game archived
     */
    public String archive() {
        return "";
    }
}
