package dev.gourab.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * APIs for arrays.
 * 
 * @author Gourab Bhattacharjee
 */
public class ArraysAPI {
  private static ArraysAPI arraysAPI;

  private ArraysAPI() {
  }

  public static ArraysAPI getInstance() {
    synchronized (ArraysAPI.class) {
      if (arraysAPI != null)
        return arraysAPI;
      arraysAPI = new ArraysAPI();
    }
    return arraysAPI;
  }

  /**
   * Find possible largest number with this number.
   * 
   * @param nums integer numbers
   * @return possible largest number
   */
  public String findPossibleLargestNum(final int[] nums) {
    final var largestNum = Arrays.stream(nums)
        .mapToObj(String::valueOf)
        .sorted((a, b) -> (b + a).compareTo(a + b))
        .collect(Collectors.joining(""));
    return largestNum.startsWith("00") ? "0" : largestNum;
  }

  /**
   * A good subarray is one which satisfies two conditions as:
   * a. Sum of the numbers in subarray is divisible by given number k.
   * b. The length of the subarray is at least two.
   * 
   * @param nums
   * @param k
   * @return boolean if good subarray exists
   */
  public boolean checkIfGoodSubArrayExists(final int[] nums, final int k) {
    if (nums == null || nums.length == 0)
      return false;
    final var prefixSumToIdx = new HashMap<Long, Integer>();
    var prefixSum = 0L;

    // If a valid subarray exists which has 0th element included.
    prefixSumToIdx.put(0L, -1);

    for (var i = 0; i < nums.length; ++i) {
      prefixSum += nums[i];
      if (k != 0)
        prefixSum %= k;
      if (prefixSumToIdx.containsKey(prefixSum)) {
        if (i - prefixSumToIdx.get(prefixSum) > 1)
          return true;
      } else {
        prefixSumToIdx.put(prefixSum, i);
      }
    }
    return false;
  }
}
