package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class PropertySquareTest extends SquareTestBase {
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
        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(80);
        PropertySquare propertySquare = new PropertySquare(1, "test-property", 100, 10);

        // When
        propertySquare.onEnter(game);

        // Then
        verify(printStream, times(1)).println("You don't have enough money to buy this property");
    }

    @Test
    @DisplayName("Current player can buy the property when input 'y'")
    public void canBuyProperty() {

    }

    @Test
    @DisplayName("Current player can choose not to buy the property when input 'n'")
    public void canNotBuyProperty() throws IOException {
        InputStream mockInputStream = new ByteArrayInputStream("n".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        PropertySquare propertySquare = new PropertySquare(1, "test-prop", 100, 10);
        propertySquare.onEnter(game);

        verify(printStream).print("Do you want to buy this property?");
        verify(printStream).println("See you next time!");
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
