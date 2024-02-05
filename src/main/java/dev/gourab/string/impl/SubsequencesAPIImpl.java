package dev.gourab.string.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.gourab.string.CheckIfSubsequenceHandler;
import dev.gourab.string.SubsequencesAPI;

/**
 * This singleton class serves as APIs for string subsequences.
 * 
 * @author Gourab Bhattacharjee
 */
public class SubsequencesAPIImpl implements SubsequencesAPI, CheckIfSubsequenceHandler {
  private static SubsequencesAPIImpl subsequencesAPIImpl;

  private SubsequencesAPIImpl() {
  }

  public static SubsequencesAPIImpl getInstance() {
    synchronized (SubsequencesAPI.class) {
      if (subsequencesAPIImpl != null)
        return subsequencesAPIImpl;
      subsequencesAPIImpl = new SubsequencesAPIImpl();
    }
    return subsequencesAPIImpl;
  }

  public int maxProductLengthOfTwoComplementarySubsets(final String string) {
    if (string == null || string.isBlank() || string.isEmpty())
      throw new IllegalArgumentException("Provide a non-null, non-blank, non-empty string input!");
    /*
     * 1 << string.length() means 2^string.length(). Why 2^(length of string)? Since
     * a character can either or not be considered.
     */
    final var totalSubsets = 1 << string.length();
    /*
     * Subset array lengths represented as array of integers.
     * The binary representation of each subset represents whether a character of
     * input string is considered or not. 0 means a character is not considered, 1
     * means character is included.
     */
    final var subsetLengths = new int[totalSubsets];

    // Subsets are considered from 1 till 2^totalSubsets - 1.
    for (var subset = 1; subset < totalSubsets; ++subset) {
      /*
       * If there is only one set bit (1) for an integer (like 16, binary -> 10000),
       * it means only one character is considered, then its maximum length will be 1.
       */
      if (Integer.bitCount(subset) == 1)
        subsetLengths[subset] = 1;
      else {
        // Find set bits at LSB and MSB.

        /*
         * subset & -subset will help to determine the first set bit at or before LSB.
         * counts number of zeros after set bit till LSB.
         */
        final var setBitLSB = Integer.numberOfTrailingZeros(subset & -subset);

        /*
         * Size of integer - 32-bit. Difference between the last index (31) and number
         * of zeros until a set-bit gives the first set bit at or after MSB.
         */
        final var setBitMSB = 31 - Integer.numberOfLeadingZeros(subset);

        /*
         * Dynamic programming approach is used to calculate results of 3 scenarios
         * (which are previously calculated in some previous i - x iterations, x = 1, 2,
         * 3...i)
         */

        // Scenario 1: Length of the subset if setBitLSB number of subsets are excluded.
        final var subsetLenExcLSB = subsetLengths[subset - (1 << setBitLSB)];

        // Scenario 2: Length of the subset if setBitMSB number of subsets are excluded.
        final var subsetLenExcMSB = subsetLengths[subset - (1 << setBitMSB)];

        /*
         * Scenario 3: Length of the subset if both setBitLSB & setBitMSB number of
         * subsets are excluded.
         */
        final var subsetLenExcLSBMSB = subsetLengths[subset - (1 << setBitLSB) - (1 << setBitMSB)];

        // Check if characters at set bits (LSB and MSB) are same.
        // 2 will be added to length of the subset if characters are same.
        final var toAdd = string.charAt(setBitLSB) == string.charAt(setBitMSB) ? 2 : 0;

        /*
         * Check which of 3 scenarios outputs the maximum length. Add toAdd contribution
         * to the result.
         */
        subsetLengths[subset] = Integer.max(subsetLenExcLSB,
            Integer.max(subsetLenExcMSB, subsetLenExcLSBMSB + toAdd));
      }
    }

    /*
     * Now that, the subset lengths are filled, lets find out the maximum product of
     * two complementary subsets.
     */
    var maxProduct = Integer.MIN_VALUE;
    for (var subset = 1; subset < totalSubsets; ++subset) {
      // Binary representation of this will be exact complement of current subset.
      final var subsetComp = totalSubsets - subset - 1;
      maxProduct = Integer.max(maxProduct, subsetLengths[subset] * subsetLengths[subsetComp]);
    }

    return maxProduct;
  }

  public String findLongestCommonPrefixAmong(final String[] strings) {
    if (strings == null || strings.length == 0)
      return "";
    // Iterate through the character indices of the first string.
    for (var chIdx = 0; chIdx < strings.length; ++chIdx) {
      // Iterate through the string indices in strings, starting from second string.
      for (var strIdx = 1; strIdx < strings.length; ++strIdx) {
        /*
         * Check two conditions:
         * a. If the character index 'chIdx' is large enough than what the string at
         * index 'strIdx' is.
         * b. If the character 'chIdx' of the first string is not matching with the
         * character chIdx at 'strIdx' th string.
         * 
         * If these two conditions meet, then 'chIdx' is invalid, and we can return a
         * substring from 0 till chIdx - 1 of the first string.
         */
        if (chIdx == strings[strIdx].length()
            || strings[0].charAt(chIdx) != strings[strIdx].charAt(chIdx))
          return strings[0].substring(0, chIdx);
      }
    }
    // Return the entire first word, indicating all strings are same.
    return strings[0];
  }

  @Override
  public boolean isSubsequence(final String string, final String subString) {
    if (string.isEmpty())
      return true;
    var subIdx = 0;
    for (var strIdx = 0; strIdx < string.length(); ++strIdx) {
      /*
       * If 'substring' is a subsequence of 'string', then it must match conditions
       * as:
       * 1. If the characters between two strings match
       * 2. Increasing the substring index by 1 after the match results with the index
       * being equal to subString length.
       */
      if (subString.charAt(subIdx) == string.charAt(strIdx)
          && ++subIdx == subString.length())
        return true;
    }
    return false;
  }

  @Override
  public List<Integer> findFirstIndicesOfSubsequenceAnagrams(String string, String subString) {
    if (string == null || subString == null || string.isBlank()
        || string.isEmpty() || subString.isBlank() || subString.isEmpty() ||
        subString.length() > string.length())
      throw new IllegalArgumentException("Input are either null, or blank or empty or invalid!");

    // Counter array of lowercase alphabets.
    final var subChCnt = new int[26];
    for (final var subCh : subString.toCharArray())
      ++subChCnt[subCh];

    var currSubLen = 0;
    var leftP = 0;

    final var firstIndices = new ArrayList<Integer>();

    for (var rightP = 0; rightP < string.length(); ++rightP) {
      /*
       * If count of string.charAt(rightP) in subChCnt remains zero or greater than 0,
       * then alphabet at rightP in string exists in subString.
       */
      if (--subChCnt[string.charAt(rightP)] >= 0)
        // Decrease substring length by 1 since 1 character used up.
        --currSubLen;

      // While the currSubLen remains 0, check following:
      while (currSubLen == 0) {
        /*
         * If the difference between rightP and leftP in string is equal to the length
         * of substring, then the portion covered by the two pointers is an anagram
         * string. (Since, remember this condition is being checked within currSubLen ==
         * 0 condition where all substring characters are used up)
         */
        if (rightP - leftP + 1 == subString.length())
          firstIndices.add(leftP);
        // Re-add the characters into length using leftP since they are already
        // considered.
        if (++subChCnt[string.charAt(leftP++)] > 0)
          ++currSubLen;
      }
    }
    return firstIndices;
  }

  public int countPalindromicSubsequences(final String string) {
    if (string == null || string.isBlank() || string.isEmpty())
      return 0;

    final var alphabetFirstOcc = new int[26];
    final var alphabetLastOcc = new int[26];

    // This step is crucial.
    Arrays.fill(alphabetFirstOcc, Integer.MAX_VALUE);

    /*
     * Find indices of first and last occurrence of each lowercase alphabets in
     * string.
     */
    for (var chIdx = 0; chIdx < string.length(); ++chIdx) {
      final var ch = string.charAt(chIdx);
      final var alphabetIdx = ch - 'a';
      alphabetFirstOcc[alphabetIdx] = Integer
          .min(alphabetFirstOcc[alphabetIdx], chIdx);
      alphabetLastOcc[alphabetIdx] = chIdx;
    }

    var cnt = 0;
    /*
     * Loop through all the 26 lowercase alphabets, count all the distinct
     * characters between each
     * character's occurence.
     */
    for (var chIdx = 0; chIdx < 26; ++chIdx)
      if (alphabetFirstOcc[chIdx] < alphabetLastOcc[chIdx])
        cnt += (int) string
            .substring(alphabetFirstOcc[chIdx] + 1, alphabetLastOcc[chIdx])
            .chars()
            .distinct()
            .count();

    return cnt;
  }
}
