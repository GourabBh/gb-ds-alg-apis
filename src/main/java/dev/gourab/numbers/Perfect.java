package dev.gourab.numbers;

import java.util.List;
import java.util.stream.IntStream;

/**
 * A perfect number has the sum of its factors equal to itself.
 * 
 * @author Gourab Bhattacharjee
 */
public class Perfect {
  private static Perfect perfectInstance;

  private Perfect() {
  }

  public static Perfect getInstance() {
    synchronized (Perfect.class) {
      if (perfectInstance != null)
        return perfectInstance;
      perfectInstance = new Perfect();
    }
    return perfectInstance;
  }

  public boolean isPerfect(final int number) {
    if (number < 0)
      throw new IllegalArgumentException("Negative numbers not allowed!");
    return isPerfect(number, 1, 0);
  }

  private boolean isPerfect(final int number, final int divisor, final int divisorSum) {
    if (number == 0)
      return false;
    if (divisor == number)
      return divisorSum == number;
    return isPerfect(number, divisor + 1,
        number % divisor == 0 ? divisor + divisorSum : divisorSum);
  }

  public List<Integer> perfectNumbers(final int start, final int end) {
    if (start > end)
      throw new IllegalArgumentException("start can't be greater than end!");
    return IntStream.rangeClosed(start, end).filter(number -> isPerfect(number)).boxed().toList();
  }
}
