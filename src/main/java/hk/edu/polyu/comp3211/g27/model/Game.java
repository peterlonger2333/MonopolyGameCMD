package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.PropertySquare;
import hk.edu.polyu.comp3211.g27.model.square.Square;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * The central model of the Monopoly Game. Essentially, a game is an abstraction that holds the following information:
 *  1. players of the game
 *  2. players' status, such as their current money and current holdings of property
 *  3. a mapping from players to their current occupied square
 *  4. "Time" of the game, i.e. {@link Turn}. This includes total elapsed time, as well as current time.
 */
public class Game {
    public static final int INITIAL_MONEY = 1500;
    public static final int JAIL_TERM = 3;
    public static final int MAX_ROUND = 100;

    private final List<Player> allPlayers;
    private List<Player> currentPlayers;

    private final Map<Player, Square> board;
    private final Map<Player, Integer> bank;
    private final Map<Player, List<String>> propertyHolding;
    private final Map<Player, Integer> jail; // only holds current players

    private Turn currentTurn;
    private int playerIndex;
    private int round;

    /**
     * Instantiate a Game with initial players. The play order is given by the list. List index is interpreted as playing order.
     *
     * @param players an ordered list of initial game players.
     */
    public Game(@NotNull List<Player> players) {
        this.allPlayers = players;
        this.currentPlayers = newArrayList(players); // make a new copy

        // initialise board
        board = new HashMap<>();
        allPlayers.forEach(
                it -> board.put(it, SquareFactory.getGoSquare())
        );

        // initialise bank
        bank = new HashMap<>();
        allPlayers.forEach(
                it -> bank.put(it, INITIAL_MONEY)
        );

        // initialise property holding
        propertyHolding = new HashMap<>();
        allPlayers.forEach(
                it -> propertyHolding.put(it, newArrayList())
        );

        // initialise jail
        jail = new HashMap<>();
        allPlayers.forEach(
                it -> jail.put(it, 0)
        );

        // set the current turn
        playerIndex = 0;
        currentTurn = new Turn(currentPlayers.get(playerIndex), SquareFactory.getGoSquare());
        round = 1; // as well as round
    }

    /* ------------------------------------ */
    /* -------- Player Information -------- */
    /* ------------------------------------ */

    public List<Player> allPlayers() {
        return allPlayers;
    }
    public List<Player> playersLeft() {
        return currentPlayers;
    }

    /* ------------------------------------------- */
    /* -------- Player - Property mapping -------- */
    /* ------------------------------------------- */

    public void grantOwnership(Player player, String property) throws IllegalArgumentException {
        // check that the player is in the game
        if (!allPlayers.contains(player)) // grant ownership to player bankrupted?
            throw new IllegalArgumentException("Player is not in the game");

        // check that the property exist
        PropertySquare propertySquare = SquareFactory.getPropertySquare(property);
        if (propertySquare == null)
            throw new IllegalArgumentException("Property not found");

        propertySquare.setHolder(player);
        propertyHolding.get(player).add(property);
    }

    public void revokeOwnership(Player player, String property) {
        // check that the player is in the game
        if (!allPlayers.contains(player)) // grant ownership to player bankrupted?
            throw new IllegalArgumentException("Player is not in the game");

        // check that the property exist
        PropertySquare propertySquare = SquareFactory.getPropertySquare(property);
        if (propertySquare == null)
            throw new IllegalArgumentException("Property not found");

        // remain silent if the player does not own the property
        propertySquare.setHolder(null);
        propertyHolding.get(player).remove(property);
    }

    public Map<Player, List<String>> propertyHoldingStatus() {
        // return a copy instead of the internal model
        return new HashMap<>(propertyHolding);
    }

    public List<String> propertyHoldingStatusFor(Player player) {
        // check that the player is in the game
        if (!allPlayers.contains(player)) // grant ownership to player bankrupted?
            throw new IllegalArgumentException("Player is not in the game");

        // return a copy instead of the internal model
        return newArrayList(propertyHolding.get(player));
    }

    /* ------------------------------------ */
    /* ------------ Jail Status ----------- */
    /* ------------------------------------ */

    /**
     * {@code Jail} is a mapping from {@code Player} to their round left in the jail. For example, if round left is 0,
     * this means the player is not in jail.
     *
     * @return the jail status
     */
    public Map<Player, Integer> playersInJail() {
        // return a copy
        return new HashMap<>(jail);
    }

    public int inJailCheck(Player player) {
        playerInGameCheckLeft(player);

        return jail.get(player);
    }

    public void putInJail(Player player) {
        playerInGameCheckLeft(player);

        jail.put(player, JAIL_TERM + 1); // 1 is added to offset this round
    }

    public void releaseFromJail(Player player) {
        // remain silent if the player is not in jail
        playerInGameCheckLeft(player);

        jail.put(player, 0);
    }

    /* ------------------------------------ */
    /* ----------- Score Status ----------- */
    /* ------------------------------------ */
    public int currentMoney(Player player) {
        return bank.get(player);
    }

    public void addMoney(int amount, Player player) {
        playerInGameCheckAll(player);

        bank.put(player, bank.get(player) + amount);
    }

    public void subtractMoney(int amount, Player player) {
        playerInGameCheckAll(player);

        bank.put(player, bank.get(player) - amount);
    }

    public void pay(int amount, Player payer, Player payee) {
        // must check to ensure atomicity
        playerInGameCheckAll(payer);
        playerInGameCheckAll(payee);

        addMoney(amount, payee);
        subtractMoney(amount, payer);
    }

    /* ------------------------------------ */
    /* ---------- Player Movement --------- */
    /* ------------------------------------ */

    public Square currentSquare(Player player) {
        playerInGameCheckAll(player);

        return board.get(player);
    }

    public void move() {
        move(currentTurn);
    }

    public void move(Turn turn) {
        // check if the turn is properly initialised
        playerInGameCheckLeft(turn.getPlayer());
        if (turn.getStepToTake() == 0) throw new IllegalStateException("Step not set");

        int newSquareInd = (turn.getOldSquare().getIndex() + turn.getStepToTake()) % 20;  //WARNING: the square index must start from 0
        board.put(turn.getPlayer(), SquareFactory.getSquare(newSquareInd));  //WARNING: notice the square effect does not take place here
    }

    public void move(Player player, Square toSquare) {
        playerInGameCheckLeft(player);

        board.put(player, toSquare);
    }

    /* ------------------------------------ */
    /* ------------- Game Time ------------ */
    /* ------------------------------------ */

    public int getRound() {
        return round;
    }

    public boolean isGameEnd() {
        if (round > MAX_ROUND) return true;
        if (currentPlayers.size() == 1) return true;

        return false;
    }

    public Turn currentTurn() {
        return currentTurn;
    }

    /**
     * Transit to next turn. Increment round count if necessary.
     * @return next turn
     */
    public Turn next() {
        // if we need to wrap around
        boolean isWrap = currentPlayers.indexOf(currentTurn.getPlayer()) == currentPlayers.size() - 1;

        if (isWrap) {
            playerIndex = 0;

            // a new round also starts
            round += 1;

            // remember to update jail round
            jail.forEach((p, c) -> {
                            if (c != 0) jail.put(p, c-1);
                        });

            // handle player update
            currentPlayers = currentPlayers.stream().filter(it -> bank.get(it) >= 0).collect(Collectors.toList());

            //TODO: may need to clean up the jail
        } else {
            playerIndex += 1;
        }

        currentTurn = new Turn(currentPlayers.get(playerIndex), board.get(currentPlayers.get(playerIndex)));
        return currentTurn;
    }


    private void playerInGameCheckAll(Player player) {
        if (!allPlayers.contains(player))
            throw new IllegalArgumentException("Player is not in the game");
    }

    private void playerInGameCheckLeft(Player player) {
        if (!currentPlayers.contains(player))
            throw new IllegalArgumentException("Player is not in the game");
    }
}
