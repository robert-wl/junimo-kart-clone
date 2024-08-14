package model.cart.state;

import model.cart.Cart;
import model.map.rail.AscendingRail;
import model.map.rail.Rail;
import helper.CanvasHelper;
import helper.GameInput;
import helper.GameSettings;
import helper.MathHelper;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class CartMoveState extends CartBaseState {
	public CartMoveState(Cart cart) {
		super(cart);
	}

	@Override
	public void onEnterState() {
	}

	@Override
	public void onChangeState() {
	}

	@Override
	public final void onUpdate() {
		this.getCollectibles();

		if (GameInput.getPressedKeys().contains(KeyCode.SPACE)) {
			this.cart.switchState(this.cart.jumpState);
			return;
		}

		this.handleCartMovement();
	}

	private void handleCartMovement() {
		Rail[] rails = this.getRails(this.getAbsoluteX());

		if (rails[0] == null || rails[1] == null) {
			this.cart.switchState(this.cart.jumpState);
			return;
		}

		this.ascendingRailHandler(rails[0], rails[1]);

		if (this.cart.getCollider().isColliding(rails[0].getCollider())) {
			this.rotateTo(0, 0.1);
			this.cart.speedY = 0;
			return;
		}

		this.rotateTo(0, 0.1);
		this.cart.y -= this.cart.speedY;
		this.cart.speedY -= GameSettings.GRAVITY;
	}

	private void ascendingRailHandler(Rail rail, Rail nextRail) {
		double distance = MathHelper.getDistance(this.cart.y - this.cart.getJunimo().getY(), 0);
		if (nextRail.getClass() == AscendingRail.class && distance < 10) {
			this.rotateTo(-45, 0.1);
			this.cart.y -= 2;
			return;
		}

		if (rail.getClass() == AscendingRail.class && distance < 10) {
			this.rotateTo(-45, 0.1);
			this.cart.y -= 2;
		}
	}

	@Override
	public final void drawImage() {
		Image cart = this.getCartImage();
		CanvasHelper.drawImageRotatedCentered(cart,
		                                      this.cart.x + cart.getWidth() / 2,
		                                      this.cart.y + cart.getHeight() / 2 + 8,
		                                      cart.getWidth(),
		                                      cart.getHeight(),
		                                      this.angle);
	}
}
