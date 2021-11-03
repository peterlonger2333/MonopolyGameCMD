package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GoToJailSquareTest extends SquareTestBase {
    private int squareIndex=16;
    private String squareLabel = "GO TO JAIL";
    @Test
    @DisplayName("Should put the current player in jail")
    public void shouldPutInJail() {
        GoToJailSquare goToJailSquare=new GoToJailSquare(squareIndex,squareLabel);
        verify(goToJailSquare).onEnter(game);
    }
}
