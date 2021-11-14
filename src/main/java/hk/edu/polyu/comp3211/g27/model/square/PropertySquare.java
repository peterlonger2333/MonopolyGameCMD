package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represent "Property Squares" in the Monopoly Game. This type of square has extra characteristics - price, rent and the
 * current property holder.
 */
public class PropertySquare extends Square{
    private @Nullable Player holder;
    private final int price;
    private final int rent;

    public PropertySquare(int index, String label, int price, int rent) {
        super(index, label);
        this.price = price;
        this.rent = rent;
    }

    /**
     * Get the current holder of the property. {@code Null} value indicates that this square is not bought by any player
     * yet.
     *
     * @return current holder of this property.
     */
    public @Nullable Player getHolder() {
        return holder;
    }

    public void setHolder(Player holder) {
        this.holder = holder;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    /**
     * There are two scenarios.
     *
     * 1. The current property is not occupied. Ask if the current player would like to buy and update the game.
     * 2. The current property is occupied. If the current player is the owner, do nothing. Else collect rent from
     * the current player.
     *
     * @param game The game on which to take effect
     */
    @Override
    public void onEnter(Game game) {
        System.out.print("Do you want to buy this property?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next("[yn]");
        if (Objects.equals(input, "n")) System.out.println("See you next time!");
    }
}
