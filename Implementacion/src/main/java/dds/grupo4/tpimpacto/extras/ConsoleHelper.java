package dds.grupo4.tpimpacto.extras;

import java.util.Scanner;

public class ConsoleHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        return scanner.nextInt();
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printLine() {
        System.out.println();
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void closeScanner() {
        scanner.close();
    }

}
