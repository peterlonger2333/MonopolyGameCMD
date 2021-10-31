package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PropertySquareTest {
    private final Player player1 = new Player("testPlayer_1");
    private final Player player2 = new Player("testPlayer_2");
    private final Square initialSquare = new Square(0, "initial_square") {
        @Override
        public void onEnter(Game game) {

        }
    };
    private Turn turn;

    /**
     * Re-initialise `turn` for each individual test
     */
    @BeforeEach
    public void setUpEach() {
        turn = new Turn(player1, initialSquare);
    }

    @Test
    @DisplayName("Should read properties of a property square correctly")
    public void shouldReadProperties() {
        PropertySquare propertySquare = new PropertySquare(1, "test_property", 200, 20);

        assertThat(propertySquare.getPrice(), equalTo(200));
        assertThat(propertySquare.getRent(), equalTo(20));
        assertThat(propertySquare.getLabel(), equalTo("test_property"));
        assertThat(propertySquare.getHolder(), equalTo(null));
    }

    @Test
    @DisplayName("Can grant and revoke property ownership")
    public void canSetAndUnsetOwner() {
        PropertySquare propertySquare = new PropertySquare(1, "test_property", 200, 20);

        propertySquare.setHolder(player1);
        assertThat(propertySquare.getHolder(), equalTo(player1));

        propertySquare.setHolder(null);
        assertThat(propertySquare.getHolder(), equalTo(null));

        propertySquare.setHolder(player2);
        assertThat(propertySquare.getHolder(), equalTo(player2));
    }

    @Test
    @DisplayName("Should prompt current player that he does not have enough money to buy this property")
    public void shouldPromptCurrentPlayerWithNotEnoughMoney() {
        // Given
        Game mockGame = Mockito.mock(Game.class);
        when(mockGame.currentTurn()).thenReturn(turn);
        when(mockGame.currentMoney(player1)).thenReturn(80);
        PrintStream mockOutStream = mock(PrintStream.class);
        System.setOut(mockOutStream);
        PropertySquare propertySquare = new PropertySquare(1, "test-property", 100, 10);

        // When
        propertySquare.onEnter(mockGame);

        // Then
        verify(mockOutStream, times(1)).println("You don't have enough money to buy this property");
    }

    @Test
    @DisplayName("Current player can buy the property when input 'y'")
    public void canBuyProperty() {

    }

    @Test
    @DisplayName("Current player can choose not to buy the property when input 'n'")
    public void canNotBuyProperty() {

    }

    @Test
    @DisplayName("Should only accept 'y' or 'n'")
    public void shouldOnlyAcceptProperInput() {

    }

    @Test
    @DisplayName("Should collect rent when the property is occupied")
    public void shouldCollectRent() {

    }
}
