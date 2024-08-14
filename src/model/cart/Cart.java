package model.cart;

import model.collideable.CollisionBox;
import model.cart.state.CartBaseState;
import model.cart.state.CartJumpState;
import model.cart.state.CartMoveState;
import model.junimo.Junimo;
import helper.GameSettings;
import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart extends AnimationTimer {
	public final double speedX;
	public final CartMoveState moveState;
	public final CartJumpState jumpState;
	private final CollisionBox collider;
	private final ArrayList<Image> cartImages;
	private final Junimo junimo;
	public double speedY;
	public double y;
	public double x;
	private CartBaseState currentState;

	public Cart(Junimo junimo) {
		this.x = junimo.getX();
		this.y = junimo.getY();
		this.speedX = 100;
		this.speedY = 0;
		this.junimo = junimo;
		this.moveState = new CartMoveState(this);
		this.jumpState = new CartJumpState(this);
		this.collider = new CollisionBox(this.x, this.y, this.x + GameSettings.PIXEL_SIZE, this.y + GameSettings.PIXEL_SIZE);

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.cartImages = SpriteExtract.longSpriteExtract(junimoSprite.get("cart"), 2);

		this.currentState = this.jumpState;
	}

	public final void switchState(CartBaseState state) {
		this.currentState.onChangeState();
		this.currentState = state;
		this.currentState.onEnterState();
	}

	public final void onUpdate() {
		this.collider.updateCollider(
				this.x,
				this.y,
				this.x + GameSettings.PIXEL_SIZE,
				this.y + (double) (GameSettings.PIXEL_SIZE * 3) / 2);
//		CanvasHelper.drawRectangle(this.collider.x1, this.collider.y1, this.collider.x2 - this.collider.x1, this.collider.y2 - this.collider.y1);
	}


	@Override
	public final void handle(long l) {
		this.currentState.drawImage();
		this.x += GameSettings.SCREEN_SPEED;
		this.currentState.onUpdate();
		this.onUpdate();
	}

	public final CartBaseState getCurrentState() {
		return currentState;
	}

	public final ArrayList<Image> getCartImages() {
		return cartImages;
	}

	public final Junimo getJunimo() {
		return junimo;
	}

	public final CollisionBox getCollider() {
		return collider;
	}

}
