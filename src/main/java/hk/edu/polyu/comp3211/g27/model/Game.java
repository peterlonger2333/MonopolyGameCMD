package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.Square;
import java.util.List;
import java.util.Map;

/**
 * The central model of the Monopoly Game. Essentially, a game is an abstraction that holds the following information:
 *  1. players of the game
 *  2. players' status, such as their current money and current holdings of property
 *  3. a mapping from players to their current occupied square
 *  4. "Time" of the game, i.e. {@link Turn}. This includes total elapsed time, as well as current time.
 */
public class Game {
    /* Handling player information */
    public List<Player> allPlayers() { return null; }
    public List<Player> playersLeft() { return null; }
    public void grantOwnership(Player player, String property) {}
    public void revokeOwnership(Player player, String property) {}
    public Map<Player, List<String>> propertyHoldingStatus() { return null; }
    public List<String> propertyHoldingStatusFor(Player player) { return null; }

    public int inJailCheck(Player player) { return 0; }
    public Map<Player, Integer> playersInJail() { return null; }

    /* Handling players' score */
    public int currentMoney(Player player) { return 0; }
    public void addMoney(int amount, Player player) {}
    public void subtractMoney(int amount, Player player) {}
    public void pay(int amount, Player payer, Player payee) {}

    /* Handling player's movement */
    public Square currentSquare(Player player) { return null; }
    public void move() {}
    public void move(Turn turn) {}
    public void move(Player player, Square toSquare) {}

    /* Handling in-game status */
    public int getRound() { return 0; }
    public boolean isGameEnd() { return false; }
    public Turn currentTurn() { return null; }
    public Turn next() { return null; } // transit to next turn and returns it
}
