package helper;

import java.security.SecureRandom;
import java.util.Random;

public class RandomHelper {
	private static final Random random = new SecureRandom();

	private RandomHelper() {
	}

	public static int getRandomInt(int max) {
		return random.nextInt(max) % max;
	}

	public static int getRandomInt(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("Max must be greater than min");
		}

		Random random = new SecureRandom();
		return random.nextInt(max - min + 1) + min;
	}

	public static int getRandomInt(int minLow, int minHigh, int maxLow, int maxHigh) {
		if (minLow >= minHigh || maxLow >= maxHigh || minHigh >= maxLow) {
			throw new IllegalArgumentException("Max must be greater than min");
		}

		int randomInt;
		Random random = new SecureRandom();
		do {
			randomInt = random.nextInt(maxHigh - minLow + 1) + minLow;
		} while (randomInt > minHigh && randomInt < maxLow);


		return randomInt;
	}

	public static boolean getRandomBoolean(double chance) {
		return random.nextDouble() < chance;
	}
}
