package hk.edu.polyu.comp3211.g27.model;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DicesTest {
    @RepeatedTest(10)
    void shouldDrawTwoToEight() {
        assertThat(Arrays.stream(Dices.draw()).sum(),
                is(both(greaterThan(1)).and(lessThan(9))));
    }
}