package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.Turn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;

public abstract class SquareTestBase {
    @Mock protected Player player1;
    @Mock protected Player player2;
    @Mock protected Square initialSquare;

    @Mock protected Turn turn;

    @Mock protected Game game;

    @Mock protected PrintStream printStream;

    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);

        when(player1.getId()).thenReturn("p1");
        when(player2.getId()).thenReturn("p2");

        when(initialSquare.getLabel()).thenReturn("initialSquare");
        when(initialSquare.getIndex()).thenReturn(0);

        when(turn.getOldSquare()).thenReturn(initialSquare);
        when(turn.getPlayer()).thenReturn(player1);

        System.setOut(printStream);
    }
}
