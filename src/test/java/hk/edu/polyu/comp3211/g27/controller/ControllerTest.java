package hk.edu.polyu.comp3211.g27.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControllerTest {
    private Game game;

    private PreGameController pre;
    private InGameController in;

    @BeforeEach
    public void setUpEach() {
        game = new Game("my_game", newArrayList(new Player("p1"), new Player("p2")));
        GameHolder.set(game);

        pre = new PreGameController();
        in = new InGameController();
    }

    @Test
    @DisplayName("Can save and load game")
    public void canLoadAndSaveGame() throws IOException {
        String id = in.archive(); // save the game
        pre.loadGame(id); // load the game into current context
        Game loaded = GameHolder.get();

        System.out.println(new ObjectMapper().writeValueAsString(loaded));

        assert loaded.equals(game);
    }

    @Test
    void canReportWhetherGameExists() {
        String id = "NoSuchGame";

    }
}