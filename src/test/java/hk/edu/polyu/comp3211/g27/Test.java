package hk.edu.polyu.comp3211.g27;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        InputStream mockInputStream = new ByteArrayInputStream("Hello\n world".getBytes(StandardCharsets.UTF_8));
        System.setIn(mockInputStream);

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }
}
