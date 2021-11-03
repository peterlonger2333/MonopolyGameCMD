package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GoSquareTest extends SquareTestBase {
    private GoSquare goSquare;

    @Override
    @BeforeEach
    public void setUpEach() {
        super.setUpEach();
        goSquare = new GoSquare(5, "GO");
    }

    @Test
    @DisplayName("Should supply the current player with 1500 HKD")
    public void shouldSupplyMoney() {
        goSquare.onEnter(game);

        // Technically, this should be "onEnterOrPass"
        verify(game).addMoney(1500, player1);
    }
}
