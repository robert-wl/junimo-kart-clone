package model.collectables;

import model.collideable.CollisionBox;
import model.game.JunimoGame;
import helper.CanvasHelper;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.util.concurrent.CopyOnWriteArrayList;

public class Collectables extends AnimationTimer {
	protected final int posX;
	protected final int posY;
	private final CollisionBox collider;
	protected CopyOnWriteArrayList<Image> collectableImages;
	private int points;
	private int minAnimationStage;
	private int maxAnimationNumber;
	private int maxAnimationStage;
	private int animationNumber;
	private int animationStage;

	public Collectables(int posX, int posY, int points) {
		this.posX = posX;
		this.posY = posY;
		this.points = points;
		this.animationNumber = 0;
		this.animationStage = 0;
		this.maxAnimationNumber = 0;
		this.maxAnimationStage = 0;
		this.minAnimationStage = 0;
		this.collectableImages = new CopyOnWriteArrayList<>();
		this.collider = new CollisionBox(posX, posY, posX + 16, posY + 16);
		this.start();
	}

	public Collectables(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.animationNumber = 0;
		this.animationStage = 0;
		this.maxAnimationNumber = 0;
		this.maxAnimationStage = 0;
		this.minAnimationStage = 0;
		this.collectableImages = new CopyOnWriteArrayList<>();
		this.collider = new CollisionBox(posX, posY, posX + 16, posY + 16);
		this.start();
	}

	public final int getPoints() {
		return points;
	}

	public final void setPoints(int points) {
		this.points = points;
	}

	@Override
	public final void handle(long l) {
		this.updateAnimationStage();
		this.drawImage();
	}

	protected final void setAnimationSettings(int maxAnimationNumber, int minAnimationStage, int maxAnimationStage) {
		this.maxAnimationNumber = maxAnimationNumber;
		this.minAnimationStage = minAnimationStage;
		this.animationStage = minAnimationStage;
		this.maxAnimationStage = maxAnimationStage;
	}

	private void updateAnimationStage() {
		this.animationNumber += 1;

		if (this.animationNumber == this.maxAnimationNumber) {
			this.animationNumber = 0;
			this.animationStage += 1;
		}

		if (this.animationStage == this.maxAnimationStage) {
			this.animationStage = minAnimationStage;
		}
	}

	public final void drawImage() {
		try {
			CanvasHelper.drawImage(this.collectableImages.get(this.animationStage), this.posX, this.posY);
		} catch (ArrayIndexOutOfBoundsException ignored) {

		}
	}

	public final CollisionBox getCollider() {
		return this.collider;
	}

	public final void pickUp(double score) {
		this.stop();
		JunimoGame.getInstance().getJunimoMap().getCollectablesList().remove(this.posX / 16);
		JunimoGame.getInstance().addScore(score);
	}
}
