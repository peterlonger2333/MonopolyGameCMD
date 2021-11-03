package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class ChanceSquareTest extends SquareTestBase {
    private int[] SquareIndex={9,13,19};
    private String SquareLabel= "CHANCE";
    @Test
    @DisplayName("Should affect current player's money between -300 and 200")
    public void shouldAffectMoney() {
        for (int i=0;i<2;i++){
            ChanceSquare chanceSquare =new ChanceSquare(SquareIndex[i],SquareLabel);
            verify(chanceSquare).onEnter(game);
        }
    }
}
