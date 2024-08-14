package model.collectables;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Coins extends Collectables {
	public Coins(int posX, int posY) {
		super(posX, posY, 25);

		SpriteResource spriteResource = SpriteResource.getInstance();
		ArrayList<Image> collectableImages = SpriteExtract.longSpriteExtract(spriteResource.getJunimoSprite().get("coins"), 12);
		this.collectableImages = new CopyOnWriteArrayList<>(collectableImages);

		this.setAnimationSettings(10, 0, 12);
	}
}
