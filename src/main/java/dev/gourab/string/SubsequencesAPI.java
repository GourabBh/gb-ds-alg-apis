package dev.gourab.string;

import java.util.List;

/**
 * This singleton class serves as APIs for string subsequences.
 * 
 * @author Gourab Bhattacharjee
 */
public interface SubsequencesAPI {
  int maxProductLengthOfTwoComplementarySubsets(final String string);

  String findLongestCommonPrefixAmong(final String[] strings);

  List<Integer> findFirstIndicesOfSubsequenceAnagrams(final String string, final String subString);

  int countPalindromicSubsequences(final String string);
}
