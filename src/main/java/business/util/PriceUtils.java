package business.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Static classes to work with BigDecimal as prices.
 * 
 * @author Aldric Vitali Silvestre
 */
public class PriceUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
		return base.multiply(pct).divide(ONE_HUNDRED);
	}

	public static boolean isBetween(BigDecimal input, BigDecimal min, BigDecimal max) {
		return input.compareTo(min) >= 0 && input.compareTo(max) <= 0;
	}

	public static BigDecimal sumPrices(List<BigDecimal> prices) {
		return prices.stream()
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Sum the prices that are in a list of objects. In order to do this, a mapper
	 * must be provided.
	 * 
	 * @param <E>
	 * @param list
	 * @param mapper the function taking an object in the list as a parameter and
	 *               returning a BigDecimal.
	 *               This can be a lambda expression or a method reference (or a
	 *               class implementing the functional interface, but don't do this
	 *               please !)
	 * @return
	 */
	public static <E> BigDecimal sumPricesInObject(List<E> list, Function<E, BigDecimal> mapper) {
		return list.stream()
				.map(mapper)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
