package dev.gourab.numbers;

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
    return isPerfect(number, divisor + 1, number % divisor == 0 ? divisor + divisorSum : divisorSum);
  }
}
