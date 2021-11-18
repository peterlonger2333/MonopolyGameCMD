package hk.edu.polyu.comp3211.g27.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hk.edu.polyu.comp3211.g27.model.Game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
    public void loadGame(String gameId) throws IllegalArgumentException, IOException {
        // games are stored in ./.archive

        File file = Paths.get(".archive", gameId + ".json").toFile();
        if (!file.exists()) throw new IllegalArgumentException("Game not found: " + gameId);
        Game game = new ObjectMapper().readValue(file, Game.class);

        GameHolder.set(game);
    }
    
    /**
     * Directly print the help manual to the screen. The simplest implementation is to print out the rule line by line to
     * {@code System.out}. Alternatively, enter an interactive reading session like *less* (a linux program)
     */
    public void help() {
        System.out.println("*********************************************************************");
        System.out.println("               Welcome to The Monopoly Game!");
        System.out.println("The game is played with a board divided into 20 squares, and a pair of \n" +
                "four-sided (tetrahedral) dice and it can accommodate two to six players. Besides playing the \n" +
                "game, Players should also be able to save and load a game.");
        System.out.println("**********************************************************************");
        System.out.println("Here shows the announcement and work  flow:");
        System.out.println("1. Players have money and can own properties. Each player starts with \n" +
                "HKD 1500 and no property.");
        System.out.println("2. All players start from the first square ('Go').");
        System.out.println("3. Players take turns in rolling the dice and advancing their respective tokens clockwise on the \n" +
                "board. After reaching square 20, a token moves to square 1 again.");
        System.out.println("4. Certain squares take effect on a player (see below) when her token \n" +
                "passes or lands on the square. For example, they can change the player’s amount of money.");
        System.out.println("5. If after taking a turn a player has a negative amount of money, she \n" +
                "retires from the game. All her properties become unowned.");
        System.out.println("6. A round consists of all players taking their turns once.");
        System.out.println("7. The game ends either if there is only one player left or after 100 rounds. The winner is \n" +
                "the player with the most money at the end of the game. Ties (multiple winners) are possible.");
        System.out.println("************************************************************************");
        System.out.println("Here shows 7 different types of squares in the Game Board:");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Property squares");
        System.out.println("They contain the name and the price of the property and can be owned by players. If a player lands on an unowned \n" +
                "property, she can choose to buy it for the written price or do nothing. If a player lands \n" +
                "on a property owned by another player, she has to pay a rent.");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Here shows the Price and Rent amount:");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("Pos       Name       Price      Rent");
        System.out.println("2        Central      800       90");
        System.out.println("3        Wan Chai     700       65");
        System.out.println("5        Stanley      600       60");
        System.out.println("7        Shek O       400       10");
        System.out.println("8        Mong Kok     500       40");
        System.out.println("10       Tsing Yi     400       15");
        System.out.println("12       Shatin       700       75");
        System.out.println("14       Tuen Mun     400       20");
        System.out.println("15       Tai Po       500       25");
        System.out.println("17       Sai Kung     400       10");
        System.out.println("18       Yuen Long    400       25");
        System.out.println("20       Tai O        600       25");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("GO");
        System.out.println(" Every time a player passes through (not necessarily lands on) this square, she \n" +
                "gets HKD 1500 salary.");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Chance");
        System.out.println("If a player lands on one of these squares, she either gains a random amount \n" +
                "(multiple of 10) up to HKD 200 or loses a \n" +
                "random amount (multiple of 10) up to HKD 300.");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Income tax");
        System.out.println("If a player lands on this square,she pays 10% of her money (rounded down to \n" +
                "a multiple of 10) as tax.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Free parking");
        System.out.println("This square has no effect.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Go to Jail");
        System.out.println(" If a player lands on this square, she immediately goes to the 'In Jail' part of the 'In Jail/Just Visiting' square.");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("In Jail/Just Visiting");
        System.out.println("If a player lands on this square, she is 'Just Visiting': the square has no effect.");
        System.out.println("If the player got here by landing on 'Go to Jail', she is in jail and cannot make a move.");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Tip 1: A player gets out of jail by either throwing doubles (i.e., both dice \n" +
                "coming out the same face up) on any of her next three turns (if she succeeds in doing \n" +
                "this, she immediately moves forward by the number of spaces shown by her doubles \n" +
                "throw) or paying a fine of HKD 150 before she rolls the dice on either of her next two \n" +
                "turns.");
        System.out.println("Tip 2: If the player does not throw doubles by her third turn, she must pay the HKD 150 \n" +
                "fine. She then gets out of jail and immediately moves forward the number of spaces \n" +
                "shown by her throw. ");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("                       ^-^  Enjoy your game!   ");
        System.out.println("**********************************************************************************");}
}
