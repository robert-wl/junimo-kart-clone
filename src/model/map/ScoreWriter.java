package model.map;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ScoreWriter extends AnimationTimer {
	private final GraphicsContext gc;
	private final ArrayList<Image> coinUI;
	private double score;
	private double posX;
	private int animationStage;

	public ScoreWriter(GraphicsContext gc) {
		this.gc = gc;

		SpriteResource spriteResource = SpriteResource.getInstance();
		coinUI = SpriteExtract.longSpriteExtract(spriteResource.getJunimoSprite().get("coin_ui"), 3);
	}

	public final void updateScore(double posX, double score) {
		this.animationStage = (this.animationStage + 1) % 3;
		this.score = score;
		this.posX = posX;
	}


	@Override
	public final void handle(long now) {
		this.gc.setFill(javafx.scene.paint.Color.WHITE);
		this.gc.drawImage(this.coinUI.get(this.animationStage), posX + 15, 0);
		Font font = Font.font("Comic Sans MS", 12);
		gc.setFont(font);
		this.gc.fillText("" + (int) this.score, posX + 30, 12.1);
	}
}
