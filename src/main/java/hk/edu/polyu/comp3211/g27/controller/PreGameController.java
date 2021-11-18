package hk.edu.polyu.comp3211.g27.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hk.edu.polyu.comp3211.g27.model.Game;

import java.io.*;
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
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("help.txt");
        Reader reader = new InputStreamReader(is);

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            throw new IllegalStateException();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
