package dev.gourab.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HashingTest {

  @Test
  public void longestSubArrayLengthShouldBe8ForSum3() {
    final int[] numbers = { 1, -3, 2, -1, 1, 1, 1, 1, 3 };
    final var subArraysAPI = SubArraysAPI.getInstance();
    assertEquals(subArraysAPI.findLongestSubArrayLengthForSum(numbers, 3), 8);
  }
}
