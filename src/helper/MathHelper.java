package helper;

public class MathHelper {
	private MathHelper() {
	}

	public static double getDistance(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
}
