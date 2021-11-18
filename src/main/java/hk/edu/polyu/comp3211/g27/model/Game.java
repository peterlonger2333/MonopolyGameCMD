package hk.edu.polyu.comp3211.g27.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hk.edu.polyu.comp3211.g27.model.serial.PlayerKeyDeserializer;
import hk.edu.polyu.comp3211.g27.model.square.PropertySquare;
import hk.edu.polyu.comp3211.g27.model.square.Square;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * The central model of the Monopoly Game. Essentially, a game is an abstraction that holds the following information:
 *  1. players of the game
 *  2. players' status, such as their current money and current holdings of property
 *  3. a mapping from players to their current occupied square
 *  4. "Time" of the game, i.e. {@link Turn}. This includes total elapsed time, as well as current time.
 */
public class Game {
    private final String id;
    public static final int INITIAL_MONEY = 1500;
    public static final int JAIL_TERM = 3;
    public static final int MAX_ROUND = 100;

    private final List<Player> allPlayers;
    private final List<Player> currentPlayers;

    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    private final Map<Player, Square> board;
    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    private final Map<Player, Integer> bank;
    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    private final Map<Player, List<String>> propertyHolding;
    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    private final Map<Player, Integer> jail; // only holds current players

    private Turn currentTurn;
    private int playerIndex;
    private int round;

    /**
     * Instantiate a Game with initial players. The play order is given by the list. List index is interpreted as playing order.
     *
     * @param players an ordered list of initial game players.
     */
    public Game(@NotNull String id, @NotNull List<Player> players) {
        this.id = id; //TODO: handle duplication
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

    public Game(List<Player> players) {
        this(UUID.randomUUID().toString(), players);
    }

    @JsonCreator
    public Game(
            @JsonProperty("id") String id,
            @JsonProperty("allPlayers") List<Player> allPlayers,
            @JsonProperty("currentPlayers") List<Player> currentPlayers,
            @JsonProperty("board") Map<Player, Square> board,
            @JsonProperty("bank") Map<Player, Integer> bank,
            @JsonProperty("propertyHolding") Map<Player, List<String>> propertyHolding,
            @JsonProperty("jail") Map<Player, Integer> jail,
            @JsonProperty("currentTurn") Turn currentTurn,
            @JsonProperty("playerIndex") int playerIndex,
            @JsonProperty("round") int round
    ) {
        this.id = id;
        this.allPlayers = allPlayers;
        this.currentPlayers = currentPlayers;
        this.board = board;
        this.bank = bank;
        this.propertyHolding = propertyHolding;
        this.jail = jail;
        this.currentTurn = currentTurn;
        this.playerIndex = playerIndex;
        this.round = round;
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
        PropertySquare propertySquare = SquareFactory.getPropertySquare(2);
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
        PropertySquare propertySquare = SquareFactory.getPropertySquare(2);
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

        int newSquareInd = (turn.getOldSquare().getIndex() + turn.getStepToTake() - 1) % 20;  //WARNING: the square index must start from 0
        board.put(turn.getPlayer(), SquareFactory.getSquare(newSquareInd));  //WARNING: notice the square effect does not take place here
        currentTurn.setNewSquare(SquareFactory.getSquare(newSquareInd));
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

    @JsonIgnore
    public boolean isGameEnd() {
        if (round > MAX_ROUND) return true;
        return currentPlayers.size() == 1 || currentPlayers.size() == 0; // it is possible that after a round no player is left
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
            currentPlayers.forEach(p -> { // report
                if (bank.get(p) < 0) System.out.println(p.getId() + " has negative earnings. Exited");
            });
            currentPlayers.removeIf(it -> bank.get(it) < 0);

            // release property if holder exits
            propertyHolding.forEach( // cleanup the squares themselves
                    (player, properties) -> {
                        if (!currentPlayers.contains(player)) {
                            for (String property : properties) {
                                SquareFactory.getPropertySquare(property).setHolder(null);
                            }
                        }
                    }
            );


            // TODO: inefficient iteration
            new HashSet<>(propertyHolding.keySet()).forEach(it -> { // remove from table
                if (!currentPlayers.contains(it)) {
                    propertyHolding.remove(it);
                }
            });

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

    public String getId() {
        return this.id;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Turn currentTurn) {
        this.currentTurn = currentTurn;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Map<Player, Square> getBoard() {
        return board;
    }

    public Map<Player, Integer> getBank() {
        return bank;
    }

    public Map<Player, List<String>> getPropertyHolding() {
        return propertyHolding;
    }

    public Map<Player, Integer> getJail() {
        return jail;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return playerIndex == game.playerIndex &&
                round == game.round &&
                id.equals(game.id) &&
                allPlayers.equals(game.allPlayers) &&
                currentPlayers.equals(game.currentPlayers) &&
                board.equals(game.board) && bank.equals(game.bank) &&
                propertyHolding.equals(game.propertyHolding) &&
                jail.equals(game.jail) &&
                currentTurn.equals(game.currentTurn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", allPlayers=" + allPlayers +
                ", currentPlayers=" + currentPlayers +
                ", board=" + board +
                ", bank=" + bank +
                ", propertyHolding=" + propertyHolding +
                ", jail=" + jail +
                ", currentTurn=" + currentTurn +
                ", playerIndex=" + playerIndex +
                ", round=" + round +
                '}';
    }
}
