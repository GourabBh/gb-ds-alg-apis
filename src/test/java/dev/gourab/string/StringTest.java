package dev.gourab.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringTest {

  @Test
  public void maxProductShouldBe9() {
    final var subsequencesAPI = SubsequencesAPI.getInstance();
    final var input = "leetcodecom";
    assertEquals(subsequencesAPI.maxProductLengthOfTwoComplementarySubsets(input), 9);
  }
}
