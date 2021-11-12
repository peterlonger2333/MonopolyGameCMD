package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.*;
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

        verify(game).addMoney(intThat(
                new ArgumentMatcher<Integer>() {
                    @Override
                    public boolean matches(Object argument) {
                        return (int) argument >= -300 && (int) argument <= 200;
                    }
                }
        ), eq(player1));
    }
}
