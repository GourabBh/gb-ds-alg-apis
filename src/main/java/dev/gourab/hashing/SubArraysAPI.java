package dev.gourab.hashing;

import java.util.HashMap;

/**
 * @author Gourab Bhattacharjee
 */
public class SubArraysAPI {
  private static SubArraysAPI subArraysAPI;

  private SubArraysAPI() {
  }

  public static SubArraysAPI getInstance() {
    synchronized (SubArraysAPI.class) {
      if (subArraysAPI != null)
        return subArraysAPI;
      subArraysAPI = new SubArraysAPI();
    }
    return subArraysAPI;
  }

  public int findLongestSubArrayLengthForSum(final int[] numbers, final int sum) {
    if (numbers == null || numbers.length == 0)
      throw new IllegalArgumentException("Input array can't be null or empty!");
    if (sum < 0)
      throw new IllegalArgumentException("Please provide a positive sum!");
    final var prefixSumToIndex = new HashMap<Long, Integer>();
    var prefixSum = 0L;

    // Put (0, -1) as the initial entry pair in map.
    prefixSumToIndex.put(0L, -1);

    var longestLen = Integer.MIN_VALUE;
    for (var i = 0; i < numbers.length; ++i) {
      prefixSum += numbers[i];
      final var complementSum = prefixSum - sum;
      if (prefixSumToIndex.containsKey(complementSum))
        longestLen = Integer.max(longestLen, i - prefixSumToIndex.get(complementSum));
      prefixSumToIndex.putIfAbsent(prefixSum, i);
    }
    return longestLen;
  }
}
