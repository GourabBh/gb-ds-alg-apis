package dev.gourab.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StockTest {

  @Test
  public void maxProfitShouldBe7() {
    final var stockAPI = Stock.getInstance();
    final int[] stockPrices = { 7, 1, 5, 3, 6, 4 };
    assertEquals(stockAPI.findMaximumProfit(stockPrices), 7);
  }
}
