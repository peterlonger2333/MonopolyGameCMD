package hk.edu.polyu.comp3211.g27;

import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);

    public static String readOption() {
        String option = "a";

        while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
            System.out.print("Do you want to buy this property (Y/n): ");
            option = scanner.nextLine();
        }

        return option;
    }
}
