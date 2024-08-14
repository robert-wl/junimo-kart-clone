package helper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import model.game.JunimoGame;

public class CanvasManipulator {

	public static void clearEffect() {
		GraphicsContext gc = JunimoGame.getInstance().getGc();

		gc.setEffect(null);
	}
}
