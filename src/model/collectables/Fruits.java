package model.collectables;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Fruits extends Collectables {
	public Fruits(int posX, int posY, int fruitType) {
		super(posX, posY);
		this.setPoints(getScore(fruitType));

		SpriteResource spriteResource = SpriteResource.getInstance();
		ArrayList<Image> collectableImages = SpriteExtract.longSpriteExtract(spriteResource.getJunimoSprite().get("fruits"), 3);
		this.collectableImages = new CopyOnWriteArrayList<>(collectableImages);

		this.setAnimationSettings(0, fruitType, fruitType);
	}

	private int getScore(int fruitType) {
		if (fruitType == 0) {
			return 100;
		}
		if (fruitType == 1) {
			return 500;
		}
		if (fruitType == 2) {
			return 1000;
		}
		return 0;
	}
}
