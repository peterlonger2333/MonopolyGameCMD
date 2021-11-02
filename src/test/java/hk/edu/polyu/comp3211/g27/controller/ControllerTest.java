package hk.edu.polyu.comp3211.g27.controller;

import hk.edu.polyu.comp3211.g27.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControllerTest {
    @Mock private Game game;

    private PreGameController pre;
    private InGameController in;

    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);
        GameHolder.set(game);

        pre = new PreGameController();
        in = new InGameController();
    }

    @Test
    @DisplayName("Can save and load game")
    public void canLoadAndSaveGame() {
        String id = in.archive();
        pre.loadGame(id);
        Game loaded = GameHolder.get();

        assertThat(game, is(equalTo(loaded)));
    }
}