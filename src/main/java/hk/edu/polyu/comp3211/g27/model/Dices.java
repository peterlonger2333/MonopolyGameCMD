package hk.edu.polyu.comp3211.g27.model;

import hk.edu.polyu.comp3211.g27.Utils;
import hk.edu.polyu.comp3211.g27.exception.GameAbortException;

import java.util.Arrays;
import java.util.Random;

public class Dices {
    public static Random random = new Random(System.currentTimeMillis()); // randomize random seed

    public static int[] draw() {
        return new int[] {
                random.nextInt(4) + 1,
                random.nextInt(4) + 1,
        };
    }

    public static int[] makeDraw() throws GameAbortException {
        String input = Utils.readLine("Hit enter to draw dice");
        if (input.equalsIgnoreCase("Quit")) throw new GameAbortException();

        int[] dices = Dices.draw();
        System.out.println("You've drawn: " + Arrays.toString(dices));

        return dices;
    }
}
