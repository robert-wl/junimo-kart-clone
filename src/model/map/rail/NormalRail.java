package model.map.rail;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.image.Image;

import java.util.HashMap;

public class NormalRail extends Rail {
	public NormalRail(int x, int y) {
		super(x, y);

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.facadeImages = FacadeHelper.getFacadeImages();
		this.railImages = SpriteExtract.longSpriteExtract(junimoSprite.get("rail_normal"), 2);

		this.init();
	}
}
