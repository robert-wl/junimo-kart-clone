package model.map.rail;

import helper.CanvasHelper;
import helper.CanvasManipulator;
import helper.GameSettings;
import helper.RandomHelper;
import javafx.scene.image.Image;
import model.collideable.CollisionBox;

import java.util.ArrayList;

public class Rail {
	protected ArrayList<Image> railImages;
	protected ArrayList<Image> facadeImages;
	protected ArrayList<Integer> facadeNumber;
	protected CollisionBox collider;
	protected int railNumber;
	protected int x;
	protected int y;

	public Rail(int x, int y) {
		this.x = x;
		this.y = y;
		this.collider = new CollisionBox(x, y, x + 16, y + 5);
	}

	public final void init() {
		this.facadeNumber = new ArrayList<>();

		for (int i = this.y; i < GameSettings.PIXEL_SIZE * GameSettings.MAX_FACADE_LENGTH; i += GameSettings.PIXEL_SIZE) {
			this.facadeNumber.add(RandomHelper.getRandomInt(facadeImages.size()) % 2);
		}

		this.railNumber = RandomHelper.getRandomInt(railImages.size());
	}


	public final void drawImage() {
		CanvasHelper.drawImage(this.railImages.get(this.railNumber), x, y - 24);
//		CanvasHelper.drawRectangle(this.collider.x1, this.collider.y1, this.collider.x2 - this.collider.x1, this.collider.y2 - this.collider.y1);
		this.drawFacade();
	}

	private void drawFacade() {
		int i = y;
		int counter = 0;

//		Logger.log(String.valueOf(i));
		while (i < GameSettings.PIXEL_SIZE * GameSettings.MAX_FACADE_LENGTH) {
//			CanvasManipulator.setBrightness(-0.05 * counter);

			CanvasHelper.drawImage(facadeImages.get(this.facadeNumber.get(counter) + counter / 2), x, i);

			counter += 1;
			i += GameSettings.PIXEL_SIZE;
//			Logger.log("z");
		}
		CanvasManipulator.clearEffect();
	}

	public final int getTopY() {
		return this.y - 24;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final CollisionBox getCollider() {
		return collider;
	}
}
