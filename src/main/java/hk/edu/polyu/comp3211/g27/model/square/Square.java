package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.*;
import hk.edu.polyu.comp3211.g27.model.Game;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represent a square on the game board in the Monopoly Game. A {@link Square} is identified by its {@code label} or its
 * {@code index}. Most importantly, a {@link Square} can have an effect on the game state, once a player has landed on it
 * or passed by.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GoSquare.class, name = "GoSquare"),
        @JsonSubTypes.Type(value = ChanceSquare.class, name = "ChanceSquare"),
        @JsonSubTypes.Type(value = FreeParkingSquare.class, name = "FreeParkingSquare"),
        @JsonSubTypes.Type(value = GoToJailSquare.class, name = "GoToJailSquare"),
        @JsonSubTypes.Type(value = IncomeTaxSquare.class, name = "IncomeTaxSquare"),
        @JsonSubTypes.Type(value = JailSquare.class, name = "JailSquare"),
        @JsonSubTypes.Type(value = PropertySquare.class, name = "PropertySquare")
})
public abstract class Square {
    private final int index;
    private final String label;

    @JsonCreator
    public Square(@JsonProperty("index") int index,
                  @JsonProperty("label") @NotNull String label) {
        this.index = index;
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public @NotNull String getLabel() {
        return label;
    }

    /**
     * Carry out this square's effect on the given game.
     *
     * @param game The current game
     */
    public abstract void onEnter(Game game);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return index == square.index && Objects.equals(label, square.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, label);
    }

    @Override
    public String toString() {
        return "Square{" +
                "index=" + index +
                ", label='" + label + '\'' +
                '}';
    }
}
