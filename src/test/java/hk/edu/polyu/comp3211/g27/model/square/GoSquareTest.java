package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.square.SquareTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class GoSquareTest extends SquareTestBase {
    private int SquareIndex = 1;
    private String SquareLabel = "GO";
    @Test
    @DisplayName("Should supply the current player with 1500 HKD")
    public void shouldSupplyMoney() {
        GoSquare goSquare = new GoSquare(SquareIndex,SquareLabel);
        verify(goSquare).onEnter(game);
    }
}
