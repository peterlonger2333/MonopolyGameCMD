package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.Square;

import java.util.List;

public class Game {
    private static class ScoreBoard {

    }

    private List<Player> allPlayers;
    private List<Player> currentPlayers;
    private int currentPlayerIdx;

    private ScoreBoard scoreBoard;

    public int currentMoney(Player player) { return 0; }
    public void addMoney(int amount, Player player) {}
    public void subtractMoney(int amount, Player player) {}
    public void pay(int amount, Player payer, Player payee) {}

    public Square currentSquare(Player player) {
        return null;
    }

    public Square move(Player player, int numStep) {
        return null;
    }

    public boolean isGameEnd() { return false; }
    public void next() {} // transit to next turn
}
