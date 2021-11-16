package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Dices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

// Tests for already-in-jail scenario should be done in GameTest
public class JailSquareTest extends SquareTestBase {
    private JailSquare jailSquare;

    @Override
    @BeforeEach
    public void setUpEach() {
        super.setUpEach();
        jailSquare = new JailSquare(11, "JAIL");
    }

    @Test
    @DisplayName("Should have no effect when player directly lands on this square")
    public void shouldHaveNoEffectOnLanding() {
        // well, just test that the current player is not again put in jail (a common misunderstanding)
        jailSquare.onEnter(game);

        verify(game, times(0)).putInJail(player1);
    }

    //TODO: test onPrisonerTurn()

    @Test
    @DisplayName("Player can choose to pay fine")
    public void canPayFine() throws IOException {
        InputStream mockInputStream = new ByteArrayInputStream("y\n".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(150);

        game.putInJail(player1);

        jailSquare.onPrisonerTurn(game);

        verify(game, times(1)).subtractMoney(150, player1); // check that the money is paid
    }
}
