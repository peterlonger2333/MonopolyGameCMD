package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

// Tests for already-in-jail scenario should be done in GameTest
public class JailSquareTest extends SquareTestBase {
    private int squareIndex=6;
    private String squareLabel = "IN JAIL";
    @Test
    @DisplayName("Should have no effect when player directly lands on this square")
    public void shouldHaveNoEffect() {
        JailSquare jailSquare = new JailSquare(squareIndex,squareLabel);
        verify(jailSquare).onEnter(game);
    }
}
