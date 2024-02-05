package dev.gourab.arrays;

public class Stock {
  private static Stock stockAPI;

  private Stock() {
  }

  public static Stock getInstance() {
    synchronized (Stock.class) {
      if (stockAPI != null)
        return stockAPI;
      stockAPI = new Stock();
    }
    return stockAPI;
  }

  /**
   * Returns maximum profit
   * 
   * @param stockPrices list of stock prices [7, 1, 5, 3, 6, 4]
   * @return int maximum profit
   */
  public int findMaximumProfit(final int[] stockPrices) {
    var hold = Integer.MIN_VALUE;
    var sold = 0;
    for (final var stockPrice : stockPrices) {
      sold = Integer.max(sold, stockPrice + hold);
      hold = Integer.max(hold, sold - stockPrice);
    }
    return sold;
  }
}
