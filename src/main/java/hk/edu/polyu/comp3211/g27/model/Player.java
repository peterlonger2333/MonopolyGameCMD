package hk.edu.polyu.comp3211.g27.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Represent a player in the game. A player is identified by a String Id.
 */
public class Player {
    private final String id;

    @JsonCreator
    public Player(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        // player is used as key in maps
        // changing this will lead to incorrect deserialization of the map
        //TODO: configure jackson

        return "Player{" +
                "id='" + id + '\'' +
                '}';
    }
}
