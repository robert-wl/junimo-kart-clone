package helper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.game.JunimoGame;

public class CanvasHelper {
	private CanvasHelper() {
	}

	public static void drawImage(Image image, double posX, double posY) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.drawImage(image, posX, posY);
	}

	public static void drawImage(GraphicsContext gc, Image image, double posX, double posY) {
		gc.drawImage(image, posX, posY);
	}

	public static void drawImage(Image image, double posX, double posY, double width, double height) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.drawImage(image, posX, posY, width, height);
	}

	public static void drawImageCentered(Image image, double posX, double posY, double width, double height) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.save();
		gc.translate(posX, posY);
		gc.translate(-width / 2, -height / 2);
		gc.drawImage(image, 0, 0, width, height);
		gc.restore();
	}

	public static void setTransparency(double transparency) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.setGlobalAlpha(transparency);
	}

	public static void drawImageRotatedCentered(Image image, double posX, double posY, double width, double height, double angle) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.save();
		gc.translate(posX, posY);
		gc.rotate(angle);
		gc.translate(-width / 2, -height / 2);
		gc.drawImage(image, 0, 0, width, height);
		gc.restore();
	}

	public static void drawImageRotate(GraphicsContext gc, Image image, double pivotX, double pivotY, double angle) {
		gc.save();
		gc.translate(pivotX, pivotY);
		gc.rotate(angle);
		gc.drawImage(image, 5, -16, image.getWidth(), image.getHeight());
		gc.restore();
	}

	public static void drawRectangle(double posX, double posY, double width, double height) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.fillRect(posX, posY, width, height);
	}

	public static void moveCanvas(double x, double y) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.translate(x, y);
	}

	public static void moveCanvas(GraphicsContext gc, double x, double y) {
		gc.translate(x, y);
	}

	public static void setScale(double x, double y) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();
		gc.scale(x, y);
	}

	public static void setScale(GraphicsContext gc, double x, double y) {
		gc.scale(x, y);
	}

	public static void clearScreen() {
		GraphicsContext gc = JunimoGame.getInstance().getGc();

		gc.clearRect(0, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
	}

	public static void clearScreen(double initialX, double initialY) {
		GraphicsContext gc = JunimoGame.getInstance().getGc();

		gc.clearRect(initialX, initialY, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
	}

	public static void clearScreen(GraphicsContext gc, double initialX, double initialY) {

		gc.clearRect(initialX, initialY, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
	}

	public static double centerXScale() {
		return GameSettings.SCREEN_WIDTH / (2 * GameSettings.GAME_SCALE);
	}

	public static double centerYScale() {
		return GameSettings.SCREEN_HEIGHT / (2 * GameSettings.GAME_SCALE) - 16 * GameSettings.GAME_SCALE;
	}


}
