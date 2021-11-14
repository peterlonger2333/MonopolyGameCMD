package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * A convenient and efficient place to store and fetch {@link Square}s in a game board.
 */
public class SquareFactory {
    public static final int SQUARE_CNT = 20; // number of squares in the game

    /**
     * Initialize all squares
     */
    private static ArrayList<Square> squareList = new ArrayList<Square>(){
        {
            add(new GoSquare(1,"GO"));
            add(new PropertySquare(2,"Central",800,90));
            add(new PropertySquare(3,"Wan Chai",700,65));
            add(new IncomeTaxSquare(4,"INCOME TAX"));
            add(new PropertySquare(5,"Stanley",600,60));
            add(new JailSquare(6,"IN JAIL"));
            add(new PropertySquare(7,"Shek O",400,10));
            add(new PropertySquare(8,"Mong Kok",500,40));
            add(new ChanceSquare(9,"CHANCE"));
            add(new PropertySquare(10,"Tsing Yi",400,15));
            add(new FreeParkingSquare(11,"FREE PARKING"));
            add(new PropertySquare(12,"Shatin",700,75));
            add(new ChanceSquare(13,"CHANCE"));
            add(new PropertySquare(14,"Tuen Mun",400,20));
            add(new PropertySquare(15,"Tai Po",500,25));
            add(new GoToJailSquare(16,"GO TO JAIL"));
            add(new PropertySquare(17,"Sai Kung",400,10));
            add(new PropertySquare(18,"Yuen Long",400,25));
            add(new ChanceSquare(19,"CHANCE"));
            add(new PropertySquare(20,"Tai O",600,25));
        }
    };

    /**
     * Return null if no square with {@code label} is found.
     *
     * @param index the square index
     * @return the square with label
     */
    public static @Nullable Square getSquare(int index) {
        return squareList.get(index);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @param index the property square index
     * @return the property square with label, price, rent
     */
    public static @Nullable PropertySquare getPropertySquare(int index) {
        return (PropertySquare)squareList.get(index);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @param index the chance square index
     * @return the Chance square with label
     */
    public static @Nullable ChanceSquare getChanceSquare(int index) {
        return (ChanceSquare)squareList.get(index);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @return the Free Parking square with index, label
     */
    public static @Nullable FreeParkingSquare getFreeParkingSquare(){
        return (FreeParkingSquare)squareList.get(11);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @return the Go square with index, label
     */
    public static @NotNull GoSquare getGoSquare() {
        return (GoSquare)squareList.get(1) ;
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @return the Go To Jail square with index, label
     */
    public static @NotNull GoToJailSquare getGoToJailSquare() {
        return (GoToJailSquare)squareList.get(16);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @return the Income Tax square with index, label
     */
    public static @NotNull IncomeTaxSquare getIncomeTaxSquare() {
        return (IncomeTaxSquare)squareList.get(4);
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @return the Jail square with index, label
     */
    public static @NotNull JailSquare getJailSquare() {
        return (JailSquare)squareList.get(6);
    }
}
