package helper;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class SpriteResource {

	private static SpriteResource spriteResource;
	private HashMap<String, Image> junimoSprite;

	private SpriteResource() {
		setJunimoSprite();
	}

	public static SpriteResource getInstance() {
		if (spriteResource == null) {
			spriteResource = new SpriteResource();
		}
		return spriteResource;
	}
	private void setJunimoSprite() {
		junimoSprite = new HashMap<>();
		junimoSprite.put("facade", new Image("file:resource/rail/rail_facade_1.png"));
		junimoSprite.put("rail_normal", new Image("file:resource/rail/rail_normal.png"));
		junimoSprite.put("rail_ascending", new Image("file:resource/rail/rail_ascending.png"));
		junimoSprite.put("rail_descending", new Image("file:resource/rail/rail_descending.png"));
		junimoSprite.put("junimo", new Image("file:resource/junimo/junimo.png"));
		junimoSprite.put("cart", new Image("file:resource/junimo/cart.png"));
		junimoSprite.put("background", new Image("file:resource/background/background.png"));
		junimoSprite.put("coins", new Image("file:resource/collectables/coins.png"));
		junimoSprite.put("fruits", new Image("file:resource/collectables/fruits.png"));
		junimoSprite.put("coin_ui", new Image("file:resource/ui/coins.png"));
		junimoSprite.put("roadblock", new Image("file:resource/obstacle/roadblock.png"));
		junimoSprite.put("title", new Image("file:resource/start/title.png"));
	}

	public HashMap<String, Image> getJunimoSprite() {
		return junimoSprite;
	}
}
