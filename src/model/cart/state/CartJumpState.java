package model.cart.state;

import model.cart.Cart;
import model.junimo.Junimo;
import model.map.rail.AscendingRail;
import model.map.rail.Rail;
import helper.CanvasHelper;
import helper.GameInput;
import helper.GameSettings;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class CartJumpState extends CartBaseState {

	private int jumpingFrame;
	private double acceleration;

	public CartJumpState(Cart cart) {
		super(cart);
	}

	@Override
	public final void onEnterState() {
		this.acceleration = 0.333333;

		this.jumpingFrame = 0;
		Junimo junimo = this.cart.getJunimo();
		if (this.cart.getCollider().isColliding(junimo.getCollider())) {
//			junimo.speedY += 5;
		}

//		GameInput.getPressedKeys().remove(KeyCode.SPACE);
	}

	@Override
	public final void onChangeState() {
		this.cart.speedY = 0;
	}

	@Override
	public final void onUpdate() {
		this.jumpingFrame += 1;

		if (this.jumpingFrame < 15 && GameInput.getPressedKeys().contains(KeyCode.SPACE)) {
			this.acceleration -= 0.0025;
		} else {
			this.acceleration = 0;
		}

//		System.out.println(this.cart.speedY);

		this.cart.speedY += acceleration;

		this.getCollectibles();
		this.handleGravitation();

		this.handleRotation();


		Rail[] rails = this.getRails(this.getAbsoluteX());
		if (rails[0] == null || rails[1] == null) {
			return;
		}


		this.handleCollision(rails[0]);
		this.handleCollision(rails[1]);
	}

	private void handleGravitation() {
		this.cart.y -= this.cart.speedY;
		this.cart.speedY -= GameSettings.GRAVITY;
	}

	private void handleRotation() {
		if (this.cart.speedY <= 0) {
			this.rotateTo(0, 0.1);
		}
		if (this.cart.speedY > 0) {
			this.rotateTo(-45, 0.1);
		}
	}

	private void handleCollision(Rail rail) {
		if (this.cart.getCollider().getNextCollider(0, -this.cart.speedY).isColliding(rail.getCollider()) && this.cart.speedY < 0) {
			if (rail.getClass() == AscendingRail.class) {
				this.cart.switchState(this.cart.moveState);
				return;
			}

			this.cart.y = rail.getTopY();
			this.cart.speedY = 0;
			this.cart.switchState(this.cart.moveState);
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
