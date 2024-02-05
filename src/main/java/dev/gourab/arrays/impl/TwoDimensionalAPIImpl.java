package dev.gourab.arrays.impl;

import java.util.Arrays;

import dev.gourab.arrays.TwoDimensionalAPI;

public class TwoDimensionalAPIImpl implements TwoDimensionalAPI {

  @Override
  public final long optimalScore(final int[][] grid) {
    var optimalScore = Long.MAX_VALUE;
    final var pathLength = grid[0].length;

    var sumPath1st = Arrays.stream(grid[0]).asLongStream().sum();
    var sumPath2nd = 0L;

    for (var i = 0; i < pathLength; ++i) {
      sumPath1st -= grid[0][i];
      // Long.max operation - A player trying to collect max. points.
      /*
       * Long.min operation - The opponent player trying to minimize the overall
       * points collected by the player.
       */
      optimalScore = Long.min(optimalScore, Long.max(sumPath1st, sumPath2nd));
      /*
       * If any modification up on variables happened at the last of any iteration
       * which is part of an expression, the modified variable will be used at the
       * next/future iteration. (About the very first iteration, the same variable
       * already has a value set to zero initially!)
       */
      sumPath2nd += grid[1][i];
    }

    return optimalScore;
  }

}
