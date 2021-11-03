package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test that the {@link SquareFactory} fetches the correct squares according to our game board
 */
public class SquareFactoryTest {

    private int squareIndex = 1;
    private String squareLabel = "FREE PARKING";

    private int[] propertySquareIndex = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    private String[] propertySquareLabel = {"CENTRAL", "Wan Chai", "Stanley", "Shek O", "Mong Kok", "Tsing Yi", "Satin",
            "Tuen Mun", "Tai Po", "Sai Kung", "Yuen Long", "Tai O"};

    private int[] chanceSquareIndex = new int[]{9, 13, 19};
    private String chanceSquareLabel = "CHANCE";

    private int goSquareIndex = 1;
    private String goSquareLabel = "GO";

    private int goToJailSquareIndex = 16;
    private String goToJailSquareLabel = "GO TO JAIL";

    private int incomeTaxSquareIndex = 4;
    private String incomeTaxSquareLabel = "INCOME TAX";

    @Test
    @DisplayName("Fetch free parking square test.")
    public void fetchSquareTest(){
        Square square = SquareFactory.getSquare(squareIndex);
        assertEquals(squareLabel,square.getLabel());
    }

    @Test
    @DisplayName("Fetch property square test.")
    public void fetchPropertySquareTest(){
        for (int i = 0; i < propertySquareIndex.length; i++){
            PropertySquare propertySquare = SquareFactory.getPropertySquare(propertySquareIndex[i]);
            assertEquals(propertySquareLabel[i],propertySquare.getLabel());
        }
    }

    @Test
    @DisplayName("Fetch chance square test.")
    public void fetchChanceSquareTest(){
        for (int i = 0; i < chanceSquareIndex.length; i++) {
            ChanceSquare chanceSquare = SquareFactory.getChanceSquare(chanceSquareIndex[i]);
            assertEquals(chanceSquareLabel, chanceSquare.getLabel());
        }
    }

    @Test
    @DisplayName("Fetch go square test.")
    public void fetchGoSquareTest(){
        GoSquare goSquare = SquareFactory.getGoSquare();
        assertEquals(goSquareIndex,goSquare.getIndex());
        assertEquals(goSquareLabel,goSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch go to jail square test.")
    public void fetchGoToJailSquareTest(){
        GoToJailSquare goToJailSquare = SquareFactory.getGoToJailSquare();
        assertEquals(goToJailSquareIndex,goToJailSquare.getIndex());
        assertEquals(goToJailSquareLabel,goToJailSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch income tax square test.")
    public void fetchIncomeTaxSquareTest(){
        IncomeTaxSquare incomeTaxSquare = SquareFactory.getIncomeTaxSquare();
        assertEquals(incomeTaxSquareIndex,incomeTaxSquare.getIndex());
        assertEquals(incomeTaxSquareLabel,incomeTaxSquare.getLabel());
    }
}
