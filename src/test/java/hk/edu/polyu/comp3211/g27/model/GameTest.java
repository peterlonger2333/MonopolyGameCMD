package hk.edu.polyu.comp3211.g27.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

public class GameTest {
    @Mock private Player p2;
    @Mock private Player p3;
    @Mock private Player p4;
    @Mock private Player p1;

    private List<Player> players;
    private Game game;

    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);
        players = newArrayList(p1, p2, p3, p4);
        game = new Game(players);
    }

    @Test
    @DisplayName("Should initialise the game with initial players")
    public void shouldInitialiseGame() {
        // players
        assertThat(game.allPlayers(), hasItems(p1, p2, p3, p4));
        assertThat(game.playersLeft(), hasItems(p1, p2, p3, p4));

        // money
        for (Player player : players) {
            assertThat(game.currentMoney(player), equalTo(Game.INITIAL_MONEY));
        }

        // time
        assertThat(game.currentTurn().getPlayer(), equalTo(p1));
        assertThat(game.getRound(), equalTo(1));
    }

    @Test
    @DisplayName("Should increment turn when game proceeds")
    public void shouldUpdateTurn() {
        for (int i = 1; i < 5; i++) {
            Turn nextTurn = game.next();

            assertThat(game.currentTurn(), equalTo(nextTurn));
            assertThat(nextTurn.getPlayer(), equalTo(players.get(i%4)));
        }
    }

    @Test
    @DisplayName("Should increment round count when all players have played")
    public void shouldUpdateRound() {
        for (int i = 0; i < 4; i++) game.next();

        assertThat(game.getRound(), equalTo(2));
    }

    @Test
    @DisplayName("Can read players' square")
    public void canReadPlayerSquare() {
        players.forEach(
                p -> assertThat(game.currentSquare(p), equalTo(SquareFactory.getGoSquare()))
        );
    }

    @Test
    @DisplayName("Can move players around according to current turn")
    public void canMovePlayerByCurrentTurn() {
        Turn currentTurn = game.currentTurn();
        currentTurn.setStepToTake(1);

        game.move();

        assertThat(game.currentSquare(currentTurn.getPlayer()), equalTo(SquareFactory.getSquare(1)));
    }

    @Test
    @DisplayName("Can move players around according to given turn")
    public void canMovePlayerByGivenTurn() {
        Turn mockTurn = Mockito.mock(Turn.class);
        when(mockTurn.getPlayer()).thenReturn(p2);
        when(mockTurn.getOldSquare()).thenReturn(SquareFactory.getGoSquare());
        when(mockTurn.getStepToTake()).thenReturn(2);

        game.move(mockTurn);

        assertThat(game.currentSquare(p2), equalTo(SquareFactory.getSquare(2)));
    }

    @Test
    @DisplayName("Can move players around according to given square")
    public void canMovePlayersBySquare() {
        game.move(p3, SquareFactory.getSquare(3));

        assertThat(game.currentSquare(p3), equalTo(SquareFactory.getSquare(3)));
    }

    @Test
    @DisplayName("Can give and collect player' money")
    public void canManageMoney() {
        int initial = game.currentMoney(p1);

        game.addMoney(100, p1);
        assertThat(game.currentMoney(p1), equalTo(initial + 100));

        game.subtractMoney(100, p1);
        assertThat(game.currentMoney(p1), equalTo(initial));

        game.pay(100, p1, p2);
        assertAll(
                () -> assertThat(game.currentMoney(p1), equalTo(initial - 100)),
                () -> assertThat(game.currentMoney(p2), equalTo(initial + 100))
        );
    }

    @Test
    @DisplayName("Should expel the player after a round ends when he has negative amount of money")
    public void shouldExpelBankruptedPlayer() {
        int currentMoney = game.currentMoney(p1);

        game.subtractMoney(currentMoney + 100, p1);
        for (int i = 0; i < 4; i++) game.next();

        assertThat(game.playersLeft(), not(hasItems(p1)));
        assertThat(game.currentTurn().getPlayer(), equalTo(p2));
    }

    @Test
    @DisplayName("Can grant and revoke property ownership")
    public void canGrantAndRevokeOwnership() {
        String propertyLabel = SquareFactory.getPropertySquare(1).getLabel();

        game.grantOwnership(p1, propertyLabel);
        assertThat(game.propertyHoldingStatus(), hasEntry(p1, hasItem(propertyLabel)));

        game.revokeOwnership(p1, propertyLabel);
        assertThat(game.propertyHoldingStatus(), not(hasEntry(p1, hasItem(propertyLabel))));
    }

    @Test
    @DisplayName("Can put a player in and out of jail")
    public void canPutPlayerInAndOutOfJail() {
        game.putInJail(p1);
        assertThat(game.inJailCheck(p1), equalTo(3));
        assertThat(game.playersInJail(), hasKey(p1));

        game.releaseFromJail(p1);
        assertThat(game.inJailCheck(p1), equalTo(0));
        assertThat(game.playersInJail(), not(hasKey(p1)));
    }

    @Test
    @DisplayName("Can update jail information when round increments")
    public void canUpdateJailStatus() {
        game.putInJail(p1);

        for (int i = 0; i < 4; i++) game.next();

        assertThat(game.inJailCheck(p1), equalTo(2));
    }

    @Test
    @DisplayName("Should end game when there is only one player left after a round")
    public void shouldEndGameWithOnePlayerLeft() {
        game.next();
        for (int i = 1; i < 4; i++){
            game.subtractMoney(Game.INITIAL_MONEY + 100, players.get(i));
            game.next();
        }

        assertThat(game.playersLeft(), hasSize(1));
        assert game.isGameEnd();
    }

    @Test
    @DisplayName("Should end game when round count reaches over 100")
    public void shouldEndGameWhenRoundReachesMaximum() {
        for (int i = 0; i < 100 * 4 + 1; i++) game.next();

        assertThat(game.getRound(), equalTo(101));
        assert game.isGameEnd();
    }
}
