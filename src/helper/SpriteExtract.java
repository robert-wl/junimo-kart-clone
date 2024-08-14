package helper;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SpriteExtract {

	public static ArrayList<Image> longSpriteExtract(Image image, int totalFrame) {
		int sectionWidth = (int) (image.getWidth() / totalFrame);
		int frameHeight = (int) image.getHeight();
		ArrayList<Image> spriteContainer = new ArrayList<>();

		for (int section = 0; section < totalFrame; section++) {
			int startX = section * sectionWidth;
			int endX = startX + sectionWidth;

			WritableImage frameImage = new WritableImage(sectionWidth, frameHeight);
			for (int y = 0; y < frameHeight; y++) {
				for (int x = startX; x < endX; x++) {
					Color pixelColor = image.getPixelReader().getColor(x, y);
					frameImage.getPixelWriter().setColor(x - startX, y, pixelColor);
				}
			}
			spriteContainer.add(frameImage);
		}

		return spriteContainer;
	}

}
