package dev.gourab.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.gourab.string.impl.SubsequencesAPIImpl;

public class StringTestAPI {

  private final SubsequencesAPI subsequencesAPI;

  public StringTestAPI(final SubsequencesAPI subsequencesAPI) {
    this.subsequencesAPI = subsequencesAPI;
  }

  public void maxProductShouldBe9() {
    final var input = "leetcodecom";
    assertEquals(subsequencesAPI.maxProductLengthOfTwoComplementarySubsets(input), 9);
  }

  public void longestPrefixShouldBefl() {
    final var input = new String[] { "flower", "flow", "flight" };
    assertEquals(subsequencesAPI.findLongestCommonPrefixAmong(input), "fl");
  }

  public void abcShouldBeSubsequenceOfaxbdcef() {
    final var subsequencesAPI = SubsequencesAPIImpl.getInstance();
    assertTrue(subsequencesAPI.isSubsequence("axbdcef", "abc"));
  }

  public void zeroAndSixShouldBeIndicesForAnagramStrings() {
    final var ip = "cbaebabacd";
    final var sub = "abc";
    assertEquals(subsequencesAPI
        .findFirstIndicesOfSubsequenceAnagrams(ip, sub).toString(), "[0, 6]");
  }
}
