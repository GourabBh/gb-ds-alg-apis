package dev.gourab;

import dev.gourab.numbers.Perfect;

public class App {
    public static void main(String[] args) {
        final var perfectAPI = Perfect.getInstance();
        System.out.println(perfectAPI.perfectNumbers(0, 5000));

        final var longVal = 2147483649L;
        System.out.println((int) longVal);
    }
}
