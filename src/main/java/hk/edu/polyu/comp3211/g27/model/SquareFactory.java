package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.model.square.*;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * A convenient and efficient place to store and fetch {@link Square}s in a game board.
 */
public class SquareFactory {
    public static final int SQUARE_CNT = 20; // number of squares in the game

    private static HashMap<Integer, String> squareList= new HashMap<Integer, String>(){
        {
            put(2,"Central");
            put(3,"Wan Chai");
            put(5,"Stanley");
            put(7,"Shek O");
            put(8,"Mong Kok");
            put(9,"CHANCE");
            put(10,"Tsing Yi");
            put(12,"Shatin");
            put(13,"CHANCE");
            put(14,"Tuen Mun");
            put(15,"Tai Po");
            put(17,"Sai Kung");
            put(18,"Yuen Long");
            put(19,"CHANCE");
            put(20,"Tai O");
        }
    };

    private static HashMap<Integer, Integer> priceList = new HashMap<Integer, Integer>(){
        {
            put(2,800);
            put(3,700);
            put(5,600);
            put(7,400);
            put(8,500);
            put(10,400);
            put(12,700);
            put(14,400);
            put(15,500);
            put(17,400);
            put(18,400);
            put(20,600);
        }
    };

    private static HashMap<Integer, Integer> rentList = new HashMap<Integer, Integer>(){
        {
            put(2,90);
            put(3,65);
            put(5,60);
            put(7,10);
            put(8,40);
            put(10,15);
            put(12,75);
            put(14,20);
            put(15,25);
            put(17,10);
            put(18,25);
            put(20,25);
        }
    };

    public static @Nullable Square getSquare(int index) {return null;}

    public static @Nullable Square getSquare(String label) {
        return null;
    }

    public static @Nullable FreeParkingSquare getFreeParkingSquare(){
        return new FreeParkingSquare(11,"FREE PARKING");
    }

    public static @Nullable PropertySquare getPropertySquare(int index) {
        return new PropertySquare(index, squareList.get(index), priceList.get(index), rentList.get(index));
    }

    /**
     * Return null if no square with {@code label} is found.
     *
     * @param label the square label
     * @return the property square with label
     */
    public static @Nullable PropertySquare getPropertySquare(String label) {
        int index = 0;
        for (int key: squareList.keySet()){
            if (squareList.get(key) == label){
                index = key;
                break;
            }
        }
        return new PropertySquare(index, label, priceList.get(index), rentList.get(index));
    }

    public static ChanceSquare getChanceSquare(int index) {
        return new ChanceSquare(index, squareList.get(index));
    }

    public static @NotNull GoSquare getGoSquare() {
        return new GoSquare(1, "GO");
    }

    public static @NotNull GoToJailSquare getGoToJailSquare() {
        return new GoToJailSquare(16, "GO TO JAIL");
    }

    public static @NotNull IncomeTaxSquare getIncomeTaxSquare() {
        return new IncomeTaxSquare(4, "INCOME TAX");
    }

    public static @NotNull JailSquare getJailSquare() {
        return new JailSquare(6, "JAIL");
    }
}
