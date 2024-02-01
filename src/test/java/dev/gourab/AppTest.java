package dev.gourab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.gourab.numbers.Perfect;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void sixShouldBePerfect() {
        final var perfectAPI = Perfect.getInstance();
        final var isPerfect = perfectAPI.isPerfect(6);
        assertTrue(isPerfect);
    }
}
