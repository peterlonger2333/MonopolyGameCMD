package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GoToJailSquareTest extends SquareTestBase {
    private GoToJailSquare goToJailSquare;

    @Override
    @BeforeEach
    public void setUpEach() {
        super.setUpEach();
        goToJailSquare = new GoToJailSquare(10, "GOTOJAIL");
    }

    @Test
    @DisplayName("Should put the current player in jail")
    public void shouldPutInJail() {
        goToJailSquare.onEnter(game);

        verify(game).putInJail(player1);
    }
}
