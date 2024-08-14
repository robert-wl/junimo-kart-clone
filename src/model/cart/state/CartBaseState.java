package model.cart.state;

import model.cart.Cart;
import model.collectables.Collectables;
import model.game.JunimoGame;
import model.map.rail.Rail;
import helper.GameSettings;
import javafx.scene.image.Image;

public abstract class CartBaseState {
	protected Cart cart;
	protected double angle;

	protected CartBaseState(Cart cart) {
		this.cart = cart;
	}

	public abstract void onEnterState();

	public abstract void onChangeState();

	public abstract void onUpdate();

	public abstract void drawImage();

	protected final void rotateTo(double targetAngle, double speed) {
		double angleDifference = targetAngle - this.angle;
		this.angle += angleDifference * speed;
	}

	protected final void getCollectibles() {
		int key = (int) (this.cart.x / 16);
		Collectables collectables = JunimoGame.getInstance().getJunimoMap().getCollectablesList().get(key);

		if (collectables == null) {
			return;
		}

		if (this.cart.getCollider().isColliding(collectables.getCollider())) {
			collectables.pickUp(collectables.getPoints());
		}
	}

	protected final int[] getAbsoluteX() {
		int absoluteX = (int) (this.cart.x + GameSettings.SCREEN_SPEED) / 16;
		int nextAbsoluteX = (int) (this.cart.x + GameSettings.SCREEN_SPEED + 8) / 16;

		return new int[] { absoluteX, nextAbsoluteX };
	}

	protected final Rail[] getRails(int[] absolutePositions) {
		Rail rail = JunimoGame.getInstance().getJunimoMap().getRailList().get(absolutePositions[0]);
		Rail nextRail = JunimoGame.getInstance().getJunimoMap().getRailList().get(absolutePositions[1]);


		return new Rail[] { rail, nextRail };
	}

	protected final Image getCartImage() {
		int imageNumber = 0;
		if (this.cart.getCollider().getNextCollider(0, this.cart.speedY).isColliding(this.cart.getJunimo().getCollider())) {
			imageNumber = 1;
		}

		return this.cart.getCartImages().get(imageNumber);
	}

}
