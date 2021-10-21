package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.Square;

import java.util.HashMap;
import java.util.List;

// Does the game have the notion of a "turn"
// Turn is the clock of the game, according to which its state transit.
public class Game {
    /* Handling player information */
    public List<Player> allPlayers() { return null; }
    public List<Player> playersLeft() { return null; }
    public HashMap<Player, Integer> playersInJail() { return null; }

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
    public boolean isGameEnd() { return false; }
    public Turn currentTurn() { return null; }
    public Turn next() { return null; } // transit to next turn and returns it
}
