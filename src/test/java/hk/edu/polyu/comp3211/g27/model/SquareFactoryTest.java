package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test that the {@link SquareFactory} fetches the correct squares according to our game board
 */
public class SquareFactoryTest {

    private static final int[] squareIndex = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private static final String[] squareLabel = {"GO","Central","Wan Chai","INCOME TAX",
            "Stanley","IN JAIL","Shek O","Mong Kok","CHANCE",
            "Tsing Yi","FREE PARKING","Shatin","CHANCE","Tuen Mun",
            "Tai Po","GO TO JAIL","Sai Kung","Yuen Long","CHANCE","Tai O"};

    private static final int freeParkingSquareIndex = 11;
    private static final String freeParkingSquareLabel = "FREE PARKING";

    private static final int[] propertySquareIndex = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    private static final String[] propertySquareLabel = {"Central", "Wan Chai", "Stanley",
            "Shek O", "Mong Kok", "Tsing Yi", "Shatin",
            "Tuen Mun", "Tai Po", "Sai Kung", "Yuen Long", "Tai O"};
    private static final int[] propertySquarePrice = {800,700,600,400,500,400,700,400,500,400,400,600};
    private static final int[] propertySquareRent = {90,65,60,10,40,15,75,20,25,10,25,25};

    private static final int[] chanceSquareIndex = new int[]{9, 13, 19};
    private static final String chanceSquareLabel = "CHANCE";

    private static final int goSquareIndex = 1;
    private static final String goSquareLabel = "GO";

    private static final int goToJailSquareIndex = 16;
    private static final String goToJailSquareLabel = "GO TO JAIL";

    private static final int incomeTaxSquareIndex = 4;
    private static final String incomeTaxSquareLabel = "INCOME TAX";

    private static final int jailSquareIndex = 6;
    private static final String jailSquareLabel = "IN JAIL/JUST VISITING";

    @Test
    @DisplayName("Fetch square test.")
    public void fetchSquareTest(){
        for(int i = 0; i < squareIndex.length; i++){
            Square square = SquareFactory.getSquare(squareIndex[i]-1);

            assertEquals(squareLabel[i], square.getLabel());
        }
    }

    @Test
    @DisplayName("Fetch free parking square test.")
    public void fetchFreeParkingSquareTest(){
        FreeParkingSquare freeParkingSquare = SquareFactory.getFreeParkingSquare();

        assertEquals(freeParkingSquareIndex, freeParkingSquare.getIndex());
        assertEquals(freeParkingSquareLabel, freeParkingSquare.getLabel());
    }

    @Test
    @DisplayName("Fetch property square test by index.")
    public void fetchPropertySquareByIndexTest(){
        for (int i = 0; i < propertySquareIndex.length; i++){
            PropertySquare propertySquare = SquareFactory.getPropertySquare(propertySquareIndex[i]-1);

            assertEquals(propertySquareLabel[i], propertySquare.getLabel());
            assertEquals(propertySquarePrice[i], propertySquare.getPrice());
            assertEquals(propertySquareRent[i], propertySquare.getRent());
        }
    }

    @Test
    @DisplayName("Fetch property square test by label.")
    public void fetchPropertySquareByLabelTest(){
        for (int i = 0; i < propertySquareLabel.length; i++){
            PropertySquare propertySquare = SquareFactory.getPropertySquare(propertySquareLabel[i]);

            assertEquals(propertySquareIndex[i], propertySquare.getIndex());
            assertEquals(propertySquarePrice[i], propertySquare.getPrice());
            assertEquals(propertySquareRent[i], propertySquare.getRent());
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
