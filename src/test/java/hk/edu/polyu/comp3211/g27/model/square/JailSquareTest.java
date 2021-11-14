package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        // well, just test that the current player is put in jail (a common misunderstanding)
        jailSquare.onEnter(game);

        verify(game, times(0)).putInJail(player1);
    }
}
