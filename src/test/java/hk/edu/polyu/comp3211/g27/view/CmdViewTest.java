package hk.edu.polyu.comp3211.g27.view;

import hk.edu.polyu.comp3211.g27.controller.GameHolder;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;

public class CmdViewTest {
    CmdView view = new CmdView();
    Game game = new Game(newArrayList(
            new Player("P1"),
            new Player("P2")
    ));

    @BeforeEach
    void setUp() {
        GameHolder.set(game);
    }

    @Test
    void printGameBoard() {
        System.out.println(view.gameBoard());
    }

    @Test
    void game() {
        System.out.println(view.game());
    }
}
