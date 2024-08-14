package model.junimo.state;

import helper.GameSettings;
import model.cart.Cart;
import model.junimo.Junimo;

public class JunimoFallingState extends JunimoBaseState {
	public JunimoFallingState(Junimo junimo) {
		super(junimo);
	}

	@Override
	public void onEnter() {

	}

	@Override
	public final void onUpdate() {
		this.junimo.x += GameSettings.SCREEN_SPEED;

		Cart cart = this.junimo.getCart();

//		Logger.log(String.valueOf(this.junimo.speedY));
		if (cart.getCollider().isColliding(this.junimo.getCollider()) && this.junimo.speedY < 0) {
			this.junimo.switchState(this.junimo.inCartState);
			return;
		}

		if (this.junimo.y >= cart.y) {
			this.junimo.switchState(this.junimo.inCartState);
			return;
		}


		this.junimo.speedY -= GameSettings.GRAVITY;
		this.junimo.y -= this.junimo.speedY;

	}

	@Override
	public void onExit() {

	}
}
