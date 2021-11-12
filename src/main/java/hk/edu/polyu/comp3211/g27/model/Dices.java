package hk.edu.polyu.comp3211.g27.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Dices {
    public static Random random = new Random(0x3211);

    public static int[] draw() {
        return new int[] {
                random.nextInt(4) + 1,
                random.nextInt(4) + 1,
        };
    }

    public static int[] makeDraw() throws IOException {
        System.out.print("Hit enter to draw dices");
        System.in.read();

        int[] dices = Dices.draw();
        System.out.println("You've drawn: " + Arrays.toString(dices));

        return dices;
    }
}
