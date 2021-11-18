package hk.edu.polyu.comp3211.g27;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean readYN(String prompt) {
        String option = "a";
        
        while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
            System.out.print(prompt + "? (Y/n)");
            option = scanner.nextLine();
        }

        return option.equalsIgnoreCase("Y");
    }

    public static String readOption(String prompt, String... options) {
        String input = "not an input";

        while (!Arrays.asList(options).contains(input)) {
            System.out.print(prompt + ": ");
            input = scanner.nextLine();
        }

        return input;
    }

    public static String readLine(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static String longToDate(long timeStamp) {
        Date date = new Date(timeStamp);
        return dateFormat.format(date);
    }
}
