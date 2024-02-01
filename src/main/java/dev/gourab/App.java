package dev.gourab;

import dev.gourab.numbers.Perfect;

public class App {
    public static void main(String[] args) {
        final var perfectAPI = Perfect.getInstance();
        System.out.println(perfectAPI.perfectNumbers(0, 5000));
    }
}
