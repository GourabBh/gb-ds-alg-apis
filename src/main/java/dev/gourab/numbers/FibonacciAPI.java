package dev.gourab.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class serves as the API for efficient fibonacci computation.
 * 
 * @author Gourab Bhattacharjee
 */
public class FibonacciAPI {

	@FunctionalInterface
	interface FibDFS {
		BigInteger get(final long term);
	}

	private final BigInteger first;
	private final BigInteger second;

	public FibonacciAPI(final long first, final long second) {
		this.first = BigInteger.valueOf(first);
		this.second = BigInteger.valueOf(second);
	}

	public FibonacciAPI() {
		this(0L, 1L);
	}

	public List<BigInteger> fibonacciSeries(final long limitNum) {
		var f = first;
		var s = second;
		final var limit = BigInteger.valueOf(limitNum);
		final var res = new ArrayList<BigInteger>();
		while (f.compareTo(limit) <= 0) {
			res.add(f);
			final var newS = f.add(s);
			f = s;
			s = newS;
		}
		return res;
	}

	public BigInteger fibonacciValue(final long term) {
		final var fibTermToVal = new HashMap<Long, BigInteger>();
		final var fibDFSWrp = new AtomicReference<FibDFS>();

		fibDFSWrp.set((t) -> {
			if (t == 0)
				return first;
			if (t == 1)
				return second;
			if (fibTermToVal.containsKey(t))
				return fibTermToVal.get(t);

			fibTermToVal.put(t, fibDFSWrp.get().get(t - 1).add(fibDFSWrp.get().get(t - 2)));

			return fibDFSWrp.get().get(t);
		});

		return fibDFSWrp.get().get(term);
	}
}
