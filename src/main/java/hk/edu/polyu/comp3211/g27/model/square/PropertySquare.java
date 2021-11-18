package hk.edu.polyu.comp3211.g27.model.square;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hk.edu.polyu.comp3211.g27.Utils;
import hk.edu.polyu.comp3211.g27.model.Game;
import hk.edu.polyu.comp3211.g27.model.Player;
import hk.edu.polyu.comp3211.g27.model.Turn;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

/**
 * Represent "Property Squares" in the Monopoly Game. This type of square has extra characteristics - price, rent and the
 * current property holder.
 */
public class PropertySquare extends Square{
    private @Nullable Player holder;
    private final int price;
    private final int rent;

    @JsonCreator
    public PropertySquare(@JsonProperty("index") int index,
                          @JsonProperty("label") String label,
                          @JsonProperty("price") int price,
                          @JsonProperty("rent") int rent) {
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

    public void setHolder(@Nullable Player holder) {
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
        Turn currentTurn = game.currentTurn();
        Player currentPlayer = currentTurn.getPlayer();

        if (holder == null) { // when this property is not occupied
            if (game.currentMoney(currentPlayer) < price) { // if the player don't have enough money
                System.out.println("The square is not owned but you do not have enough money.");
            } else { // ask if the player wants this property
                boolean buy = Utils.readYN("Do you want to buy this property");

                if (buy) { // buy this property
                    game.grantOwnership(currentPlayer, getLabel());
                    game.subtractMoney(price, currentPlayer);
                } else { // don't buy
                    System.out.println("See you next time!");
                }
            }
        } else { // pay the rent
            System.out.println("You have landed on " + holder + "'s property, paying rent " + rent);
            game.pay(rent, currentPlayer, holder);
        }
    }
}
