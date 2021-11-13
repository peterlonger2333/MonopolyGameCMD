package hk.edu.polyu.comp3211.g27.model;

import java.util.Objects;

/**
 * Represent a player in the game. A player is identified by a String Id.
 */
public class Player {
    private String id;

    public Player() {
    }

    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return id;
    }
}
