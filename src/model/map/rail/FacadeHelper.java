package model.map.rail;

import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class FacadeHelper {
	private static ArrayList<Image> facadeImages;

	private FacadeHelper() {
	}

	public static ArrayList<Image> getFacadeImages() {
		if (facadeImages == null) {
			facadeImages = new ArrayList<>();
			HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
			facadeImages = SpriteExtract.longSpriteExtract(junimoSprite.get("facade"), 2);


			Image facadeOne = facadeImages.get(0);
			Image facadeTwo = facadeImages.get(1);

			for (int i = 0; i < 20; i++) {
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setBrightness(-0.05 * i);
				ImageView facadeOneView = new ImageView(facadeOne);
				ImageView facadeTwoView = new ImageView(facadeTwo);
				facadeOneView.setEffect(colorAdjust);
				facadeTwoView.setEffect(colorAdjust);
				facadeImages.add(facadeOneView.getImage());
				facadeImages.add(facadeTwoView.getImage());
			}
		}
		return facadeImages;
	}
}
