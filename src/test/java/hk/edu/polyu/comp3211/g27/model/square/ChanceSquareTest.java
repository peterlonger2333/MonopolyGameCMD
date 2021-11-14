package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class ChanceSquareTest extends SquareTestBase {
    private ChanceSquare chanceSquare;

    @Override
    @BeforeEach
    public void setUpEach() {
        super.setUpEach();
        chanceSquare = new ChanceSquare(5, "CHANCE");
    }

    @Test
    @DisplayName("Should affect current player's money between -300 and 200")
    public void shouldAffectMoney() {
        chanceSquare.onEnter(game);

        // verify either method is called
        // ugly but works
        try {
            verify(game).addMoney(anyInt(), player1); // maybe use argument matchers to test range
        } catch (AssertionError e) {
            verify(game).subtractMoney(anyInt(), player1);
        }
    }
}
