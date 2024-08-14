package model.map.boulder;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Boulder {
	private final ArrayList<Image> boulderSprite;

	public Boulder() {
		SpriteResource spriteResource = SpriteResource.getInstance();
		this.boulderSprite = SpriteExtract.longSpriteExtract(spriteResource.getJunimoSprite().get("boulder"), 1);
	}
}
