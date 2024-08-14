package model.map.background;

import helper.CanvasHelper;
import helper.SpriteResource;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import model.game.JunimoGame;

import java.util.HashMap;

public class JunimoBackground extends AnimationTimer {
	private final Image backgroundImage;
	private final double x;

	public JunimoBackground(double posX) {

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.backgroundImage = junimoSprite.get("background");
		this.x = posX;

		Stop[] stops = new Stop[] {
				new Stop(0, Color.DARKBLUE),
				new Stop(1, Color.PINK)
		};

	}

	public final void destroy() {
		this.stop();
	}


	@Override
	public void handle(long l) {
		this.drawImage();
	}

	private void drawImage() {
		GraphicsContext backGc = JunimoGame.getInstance().getBackGc();
		CanvasHelper.drawImage(backGc, backgroundImage, x, 0);
	}

	public double getX() {
		return x;
	}
}
