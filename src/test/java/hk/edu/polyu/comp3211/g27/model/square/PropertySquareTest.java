package hk.edu.polyu.comp3211.g27.model.square;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PropertySquareTest extends SquareTestBase {
    private PropertySquare propertySquare;

    @BeforeEach
    @Override
    public void setUpEach() {
        super.setUpEach();
        propertySquare = new PropertySquare(1, "test_property", 100, 10);
    }

    @Test
    @DisplayName("Should read properties of a property square correctly")
    public void shouldReadProperties() {
        assertThat(propertySquare.getPrice(), equalTo(100));
        assertThat(propertySquare.getRent(), equalTo(10));
        assertThat(propertySquare.getLabel(), equalTo("test_property"));
        assertThat(propertySquare.getHolder(), is(nullValue()));
    }

    @Test
    @DisplayName("Can grant and revoke property ownership")
    public void canSetAndUnsetOwner() {
        propertySquare.setHolder(player1);
        assertThat(propertySquare.getHolder(), equalTo(player1));

        propertySquare.setHolder(null);
        assertThat(propertySquare.getHolder(), is(nullValue()));

        propertySquare.setHolder(player2);
        assertThat(propertySquare.getHolder(), equalTo(player2));
    }

    @Test
    @DisplayName("Should prompt current player that he does not have enough money to buy this property")
    public void shouldPromptCurrentPlayerWithNotEnoughMoney() {
        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(80);

        propertySquare.onEnter(game);

        verify(printStream, times(1)).println("You don't have enough money to buy this property");
    }

    @Test
    @DisplayName("Current player can buy the property when input 'y'")
    public void canBuyProperty() {
        InputStream mockInputStream = new ByteArrayInputStream("y".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(120);

        propertySquare.onEnter(game);

        verify(game, times(1)).subtractMoney(100, player1); // check that the money is paid
        assertThat(propertySquare.getHolder(), equalTo(player1));
    }

    @Test
    @DisplayName("Current player can choose not to buy the property when input 'n'")
    public void canNotBuyProperty() {
        InputStream mockInputStream = new ByteArrayInputStream("n".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        propertySquare.onEnter(game);

        verify(printStream).print("Do you want to buy this property?");
        verify(printStream).println("See you next time!");
    }

    @Test
    @DisplayName("Should only accept 'y' or 'n'")
    public void shouldOnlyAcceptProperInput() {
        InputStream mockInputStream = new ByteArrayInputStream("x".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(120);

        assertThrows(IllegalArgumentException.class, () -> propertySquare.onEnter(game));
    }

    @Test
    @DisplayName("Should collect rent when the property is occupied")
    public void shouldCollectRent() {
        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(120);
        propertySquare.setHolder(player2);

        propertySquare.onEnter(game);

        // TODO: verify that *either* `pay` is called *or* the following two methods are called
        // check that rents are paid
        verify(game).subtractMoney(10, player1);
        verify(game).addMoney(10, player2);
    }
}
