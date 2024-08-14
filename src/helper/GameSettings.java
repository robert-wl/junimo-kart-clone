package helper;

public class GameSettings {
	//FINAL
	public static final double GAME_SCALE = 2.5;
	public static final int PIXEL_SIZE = 16;
	public static final double JUNIMO_GAME_SCALE = 4;

	// JUNIMO
	public static final int INITIAL_RAIL_AMOUNT = 75;
	public static final int RAIL_INITIAL_Y = 11;
	public static final int MAX_FACADE_LENGTH = 20;
	public static final double SCREEN_SPEED = 1.5;
	public static final int HEIGHT_RANGE = 4;
	public static final double GRAVITY = (double) 1 / 9;
	public static final double MAX_VOID_LENGTH = 5;
	public static final double RAIL_SUDDEN_CHANCE = 0.033333333333;
	public static final double RAIL_VOID_CHANCE = 0.02;
	public static final double RAIL_SUDDEN_ROLLBACK_CHANCE = (double) 2 / 3;
	public static final double COIN_CHANCE = 0.1;
	public static final double CHERRY_CHANCE = 0.009;
	public static final double ORANGE_CHANCE = 0.003;
	public static final double GRAPE_CHANCE = 0.001;
	// NON FINAL
	public static double SCREEN_WIDTH = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
	public static double SCREEN_HEIGHT = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

	private GameSettings() {
	}
}
