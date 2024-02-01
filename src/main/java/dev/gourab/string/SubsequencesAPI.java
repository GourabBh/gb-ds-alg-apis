package dev.gourab.string;

/**
 * This singleton class serves as APIs for string subsequences.
 * 
 * @author Gourab Bhattacharjee
 */
public class SubsequencesAPI {
  private static SubsequencesAPI subsequencesAPI;

  private SubsequencesAPI() {
  }

  public static SubsequencesAPI getInstance() {
    synchronized (SubsequencesAPI.class) {
      if (subsequencesAPI != null)
        return subsequencesAPI;
      subsequencesAPI = new SubsequencesAPI();
    }
    return subsequencesAPI;
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
}
