package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test that the {@link SquareFactory} fetches the correct squares according to our game board
 */
public class SquareFactoryTest {

    private static final int freeParkingSquareIndex = 1;
    private static final String freeParkingSquareLabel = "FREE PARKING";

    private static final int[] propertySquareIndex = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    private static final String[] propertySquareLabel = {"Central", "Wan Chai", "Stanley",
                                                         "Shek O", "Mong Kok", "Tsing Yi", "Shatin",
                                                         "Tuen Mun", "Tai Po", "Sai Kung", "Yuen Long", "Tai O"};

    private static final int[] chanceSquareIndex = new int[]{9, 13, 19};
    private static final String chanceSquareLabel = "CHANCE";

    private static final int goSquareIndex = 1;
    private static final String goSquareLabel = "GO";

    private static final int goToJailSquareIndex = 16;
    private static final String goToJailSquareLabel = "GO TO JAIL";

    private static final int incomeTaxSquareIndex = 4;
    private static final String incomeTaxSquareLabel = "INCOME TAX";

    private static final int jailSquareIndex = 6;
    private static final String jailSquareLabel = "IN JAIL";

    @Test
    @DisplayName("Fetch free parking square test.")
    public void fetchFreeParkingSquareTest(){
        FreeParkingSquare freeParkingSquare = SquareFactory.getFreeParkingSquare();

        assertEquals(freeParkingSquareLabel, freeParkingSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch property square test.")
    public void fetchPropertySquareTest(){
        for (int i = 0; i < propertySquareIndex.length; i++){
            PropertySquare propertySquare = SquareFactory.getPropertySquare(propertySquareIndex[i]-1);

            assertEquals(propertySquareLabel[i], propertySquare.getLabel());
        }
    }

    @Test
    @DisplayName("Fetch chance square test.")
    public void fetchChanceSquareTest(){
        for (int squareIndex : chanceSquareIndex) {
            ChanceSquare chanceSquare = SquareFactory.getChanceSquare(squareIndex-1);

            assertEquals(chanceSquareLabel, chanceSquare.getLabel());
        }
    }

    @Test
    @DisplayName("Fetch go square test.")
    public void fetchGoSquareTest(){
        GoSquare goSquare = SquareFactory.getGoSquare();

        assertEquals(goSquareIndex, goSquare.getIndex());
        assertEquals(goSquareLabel, goSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch go to jail square test.")
    public void fetchGoToJailSquareTest(){
        GoToJailSquare goToJailSquare = SquareFactory.getGoToJailSquare();

        assertEquals(goToJailSquareIndex, goToJailSquare.getIndex());
        assertEquals(goToJailSquareLabel, goToJailSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch income tax square test.")
    public void fetchIncomeTaxSquareTest(){
        IncomeTaxSquare incomeTaxSquare = SquareFactory.getIncomeTaxSquare();

        assertEquals(incomeTaxSquareIndex, incomeTaxSquare.getIndex());
        assertEquals(incomeTaxSquareLabel, incomeTaxSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch jail square test.")
    public void fetchJailSquareTest(){
        JailSquare jailSquare = SquareFactory.getJailSquare();

        assertEquals(jailSquareIndex, jailSquare.getIndex());
        assertEquals(jailSquareLabel, jailSquare.getLabel());
    }
}
