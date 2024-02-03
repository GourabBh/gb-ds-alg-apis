package dev.gourab.string;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.gourab.string.impl.SubsequencesAPIImpl;
import dev.gourab.string.impl.SubsequencesImpl;

public class StringTest {
  private final SubsequencesAPI subsequencesAPI;

  // Variable for StringTest instance.
  private final StringTestAPI stringTestAPI;

  public StringTest() {
    subsequencesAPI = SubsequencesAPIImpl.getInstance();
    stringTestAPI = new StringTestAPI(subsequencesAPI);
  }

  @Test
  public void testMaxProduct() {
    stringTestAPI.maxProductShouldBe9();
  }

  @Test
  public void testCommonPrefix() {
    stringTestAPI.longestPrefixShouldBefl();
  }

  @Test
  public void abcShouldBeSubsequenceOfaxbdcef() {
    final var subsequencesAPI = new SubsequencesImpl();
    assertTrue(subsequencesAPI.isSubsequence("axbdcef", "abc"));
  }

  @Test
  public void testAnagramStrings() {
    stringTestAPI.abcShouldBeSubsequenceOfaxbdcef();
  }

}
