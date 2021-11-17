package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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

        verify(printStream).println("The square is not owned but you do not have enough money.");
    }

    @Test
    @DisplayName("Current player can buy the property when input 'y'")
    public void canBuyProperty() {
        InputStream mockInputStream = new ByteArrayInputStream("y\n".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);
        Utils.scanner = new Scanner(mockInputStream);

        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(120);

        propertySquare.onEnter(game);

        verify(game, times(1)).subtractMoney(100, player1); // check that the money is paid
    }

    @Test
    @DisplayName("Current player can choose not to buy the property when input 'n'")
    public void canNotBuyProperty() {
        when(game.currentMoney(player1)).thenReturn(200);
        InputStream mockInputStream = new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        propertySquare.onEnter(game);

        verify(printStream).println("See you next time!");
    }

    @Test
    @DisplayName("Should collect rent when the property is occupied")
    public void shouldCollectRent() {
        when(game.currentTurn()).thenReturn(turn);
        when(game.currentMoney(player1)).thenReturn(120);
        propertySquare.setHolder(player2);

        propertySquare.onEnter(game);

        // TODO: verify that *either* `pay` is called *or* the following two methods are called
        // check that rent is paid
        verify(game).pay(10, player1, player2);
    }
}
