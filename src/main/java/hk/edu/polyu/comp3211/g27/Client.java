package hk.edu.polyu.comp3211.g27;

import hk.edu.polyu.comp3211.g27.controller.GameHolder;
import hk.edu.polyu.comp3211.g27.controller.InGameController;
import hk.edu.polyu.comp3211.g27.controller.PostGameController;
import hk.edu.polyu.comp3211.g27.controller.PreGameController;
import hk.edu.polyu.comp3211.g27.exception.GameAbortException;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.view.CmdView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The game entry point.
 */
public class Client implements Runnable {
    private static final String welcome = "Welcome to Monopoly\n";
    private static final String menu = new StringBuilder()
            .append("---Menu--\n")
            .append("\t1. Start New Game\n")
            .append("\t2. Load Existing Game\n")
            .append("\t3. Help\n")
            .append("\tReminder: check the Help manual first to have a better experience ^-^\n")
            .toString();
    private static final String playerPrompt = "Enter number of players";

    private GameState state;
    private final PreGameController pre = new PreGameController();
    private final InGameController in = new InGameController();
    private final PostGameController post = new PostGameController();
    private final CmdView view = new CmdView();

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }

    public void run()  {
        System.out.print(welcome);

        state = GameState.PRE_GAME; // set the state to PRE_GAME

        while (true) {
            try {
                if (state == GameState.PRE_GAME) preGame();
                else if (state == GameState.IN_GAME) inGame();
                else postGame();
            } catch (GameAbortException gameAbortException) {
                boolean saveGame = Utils.readYN("Do you want to save the game");
                if (saveGame) {
                    try {
                        String id = in.archive();
                        System.out.println("Game saved, id: " + id);
                        System.exit(0);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                        System.exit(1);
                    }
                } else {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void preGame() throws IOException {
        System.out.print(menu);
        int option = Integer.parseInt(Utils.readOption("Enter you choice", "1", "2", "3"));

        switch (option) {
            case 1: // start new game
                startNewGame();
                break;
            case 2: // load existing game
                loadGame();
                break;
            case 3: // help
                pre.help(); // game state doesn't change
                break;
            default:
                throw new IllegalStateException();
        }
    }

    private void startNewGame() {
        // dictate that there be 4-6 players
        int playerCnt = Integer.parseInt(Utils.readOption(playerPrompt, "2", "3", "4", "5", "6")); // #Players

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCnt; i++) {
            players.add(new Player("P" + (i+1)));
        }

        GameHolder.set(new Game(players));

        // change game state
        state = GameState.IN_GAME;
    }

    private void loadGame() throws IOException {
        printArchive();

        boolean found = false;
        String id = "";

        while (!found) {
            id = Utils.readLine("Enter the game id");
            found = hasGame(id);
        }

        // load the game to GameHolder
        pre.loadGame(id);

        // change game state
        state = GameState.IN_GAME;
    }

    private void printArchive() {
        String format = "\t\t\t%-40s|%-24s";
        List<File> archiveFiles = getGameFiles();
        archiveFiles.sort((o1, o2) -> { // most recent first
            return (int) (o1.lastModified() - o2.lastModified());
        });

        System.out.println("Games in archive: ");
        System.out.println(String.format(format, "Game ID", "Date"));
        System.out.println("\t\t\t----------------------------------------------------------------"); // 54 '-'s
        archiveFiles.forEach(it -> {
            System.out.println(String.format(format, it.getName().split("\\.")[0], Utils.longToDate(it.lastModified())));
        });
    }

    /**
     * Load game from .archive
     *
     * @param id game id (file name)
     * @return the loaded game else null
     */
    private boolean hasGame(String id) {
        return getGameIdsFromArchive().contains(id);
    }

    private List<String> getGameIdsFromArchive() {
        File archiveFolder = new File("./.archive");
        File[] listOfFiles = archiveFolder.listFiles();
        listOfFiles[0].lastModified();
        List<String> fileNames = Arrays.stream(listOfFiles)
                .map(File::getName)
                .map(it -> it.split("\\.")[0])
                .collect(Collectors.toList());

        return fileNames;
    }

    private List<File> getGameFiles() {
        File archiveFolder = new File("./.archive");
        return Arrays.asList(archiveFolder.listFiles());
    }

    private void inGame() throws IOException {
        System.out.println(view.game());
        in.doTurn();
        if (in.isGameEnd()) state = GameState.POST_GAME;
    }

    private void postGame() {
        post.reportEndGameStatus();

        // go to initial state
        state = GameState.PRE_GAME;
    }
}

enum GameState {
    PRE_GAME,
    IN_GAME,
    POST_GAME
}