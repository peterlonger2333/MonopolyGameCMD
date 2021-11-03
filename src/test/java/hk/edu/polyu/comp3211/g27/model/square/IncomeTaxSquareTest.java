package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class IncomeTaxSquareTest extends SquareTestBase {
    private int squareIndex=4;
    private String squareLabel = "INCOME TAX";
    @Test
    @DisplayName("Should collect tax according to tax rate")
    public void shouldCollectTax() {
        IncomeTaxSquare incomeTaxSquare = new IncomeTaxSquare(squareIndex,squareLabel);
        verify(incomeTaxSquare).onEnter(game);
    }
}
