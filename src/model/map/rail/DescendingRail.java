package model.map.rail;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;

import java.util.HashMap;

public class DescendingRail extends Rail {
	public DescendingRail(int x, int y) {
		super(x, y + 16);

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.facadeImages = FacadeHelper.getFacadeImages();
		this.railImages = SpriteExtract.longSpriteExtract(junimoSprite.get("rail_descending"), 1);

		this.init();
	}
}
