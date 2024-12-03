package business.util;

import java.util.Random;

/**
 * A set of static methods for using random. Useful for testing purposes (we can
 * easily mock those methods).
 * 
 * @author Aldric Vitali Silvestre
 */
public class RandomUtils {

	// Can specify a seed if we want to do some tests
	private static final Random random = new Random();

	public static boolean randomBool() {
		return random.nextBoolean();
	}
	
	public static int randomInt() {
		return random.nextInt();
	}
	
	/**
	 * Returns a random number in range [min, max[
	 */
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * Returns a number in range [0.0, 1.0[ 
	 */
	public static double randomDouble() {
		return random.nextDouble();
	}
	
	/**
	 * Returns a number in range [min, max[ 
	 */
	public static double randomDouble(double min, double max) {
		return min + random.nextDouble() * (max - min);
	}
}
