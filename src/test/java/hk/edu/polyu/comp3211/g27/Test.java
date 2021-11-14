package hk.edu.polyu.comp3211.g27;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.serial.PlayerKeyDeserializer;
import hk.edu.polyu.comp3211.g27.model.square.GoSquare;
import hk.edu.polyu.comp3211.g27.model.square.Square;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        GoSquare go = new GoSquare(0, "GO");
        TestGame game = new TestGame(new HashMap<>());

        game.set(p1, go);
        game.set(p2, go);

        ObjectMapper mapper = new ObjectMapper();
        String jsonGame = mapper.writeValueAsString(game);
        System.out.println(jsonGame);

        game = mapper.readValue(jsonGame, TestGame.class);

        System.out.println(game);
    }
}

class TestGame {
    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    private Map<Player, Square> board;

    public TestGame() {
    }

    public TestGame(Map<Player, Square> board) {
        this.board = board;
    }

    public Map<Player, Square> getBoard() {
        return board;
    }

    public void setBoard(Map<Player, Square> board) {
        this.board = board;
    }

    public void set(Player player, Square square) {
        board.put(player, square);
    }

    @Override
    public String toString() {
        return "TestGame{" +
                "board=" + board +
                '}';
    }
}