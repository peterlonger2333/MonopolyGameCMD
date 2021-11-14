package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IncomeTaxSquareTest extends SquareTestBase {
    private IncomeTaxSquare incomeTaxSquare;

    @Override
    @BeforeEach
    public void setUpEach() {
        super.setUpEach();
        incomeTaxSquare = new IncomeTaxSquare(5, "CHANCE");
    }

    @Test
    @DisplayName("Should collect tax according to tax rate")
    public void shouldCollectTax() {
        when(game.currentMoney(player1)).thenReturn(1000);

        incomeTaxSquare.onEnter(game);

        verify(game).subtractMoney((int) (1000 * IncomeTaxSquare.RATE), player1);
    }
}
