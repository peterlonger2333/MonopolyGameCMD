package hk.edu.polyu.comp3211.g27.model.serial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hk.edu.polyu.comp3211.g27.model.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test Wrapper around a Map with player as the key
 */
class TestWrapper {
    private final Player player = new Player("P1");

    @JsonDeserialize(keyUsing = PlayerKeyDeserializer.class)
    public Map<Player, Integer> playerMap = new HashMap<Player, Integer>() {
        {
            put(player, 1);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestWrapper that = (TestWrapper) o;
        return Objects.equals(playerMap, that.playerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerMap);
    }

    @Override
    public String toString() {
        return "TestWrapper{" +
                "playerMap=" + playerMap +
                '}';
    }
}

class PlayerKeyDeserializerTest {
    ObjectMapper mapper = new ObjectMapper();
    TestWrapper wrapper = new TestWrapper();

    @Test
    void canDeserializePlayerAsKey() throws JsonProcessingException {
        String jsonWrapper = "{\"playerMap\":{\"Player{id='P1'}\":1}}";
        TestWrapper loaded = mapper.readValue(jsonWrapper, TestWrapper.class);

        assertThat(loaded, equalTo(wrapper));
    }
}