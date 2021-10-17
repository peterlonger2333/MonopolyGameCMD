package hk.edu.polyu.comp3211.g27.model.square;

import hk.edu.polyu.comp3211.g27.model.Player;

public class PropertySquare extends Square{
    private Player holder;
    private final int price;
    private final int rent;

    public PropertySquare(int index, String label, int price, int rent) {
        super(index, label);
        this.price = price;
        this.rent = rent;
    }

    public Player getHolder() {
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
}
