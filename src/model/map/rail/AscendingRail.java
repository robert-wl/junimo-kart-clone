package model.map.rail;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;
import model.collideable.CollisionBox;

import java.util.HashMap;

public class AscendingRail extends Rail {
	public AscendingRail(int x, int y) {
		super(x, y);
		this.collider = new CollisionBox(x, y - 16, x + 16, y + 5);

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.facadeImages = FacadeHelper.getFacadeImages();
		this.railImages = SpriteExtract.longSpriteExtract(junimoSprite.get("rail_ascending"), 1);

		this.init();
	}
}
