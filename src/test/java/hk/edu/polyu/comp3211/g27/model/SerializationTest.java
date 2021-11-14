package hk.edu.polyu.comp3211.g27.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hk.edu.polyu.comp3211.g27.model.square.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SerializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void playerTest() throws JsonProcessingException {
        Player player = new Player("player-1");

        String json = mapper.writeValueAsString(player);
        Player mapped = mapper.readValue(json, Player.class);

        assertThat(player, equalTo(mapped));
    }

    @ParameterizedTest
    @MethodSource("squareProvider")
    public void basicSquareTest(Square square) throws JsonProcessingException {
        String json = mapper.writeValueAsString(square);
        Square mapped = mapper.readValue(json, square.getClass());

        assertThat(square, equalTo(mapped));
    }

    @Test
    public void propertySquareCanMapHolder() throws JsonProcessingException {
        Player holder = new Player("p1");
        PropertySquare square = new PropertySquare(3, "CENTRAL", 100, 10);
        square.setHolder(holder);

        String json = mapper.writeValueAsString(square);
        Square mapped = mapper.readValue(json, PropertySquare.class);

        assertThat(square, equalTo(mapped));
    }

    @Test
    public void turnTest() throws JsonProcessingException {
        Turn turn = new Turn(
                new Player("p1"),
                new GoSquare(1, "GO")
        );

        String json = mapper.writeValueAsString(turn);
        Turn mapped = mapper.readValue(json, Turn.class);

        assertThat(turn, equalTo(mapped));
    }

    @Test
    public void game() throws JsonProcessingException {
        Game game = new Game(newArrayList(
                new Player("p1"), new Player("p2")
        ));

        String json = mapper.writeValueAsString(game);
        Game mapped = mapper.readValue(json, Game.class);

        assertThat(game, equalTo(mapped));
    }

    public static Stream<Square> squareProvider() {
        return Stream.of(
                new GoSquare(1, "GO"),
                new ChanceSquare(2, "CHANCE"),
                new PropertySquare(3, "CENTRAL", 100, 10),
                new JailSquare(4, "JAIL/JUST VISITING"),
                new GoToJailSquare(5, "GO-TO-JAIL"),
                new IncomeTaxSquare(6, "TAX")
        );
    }
}
