package model.junimo.state;

import helper.GameSettings;
import model.cart.Cart;
import model.junimo.Junimo;

public class JunimoInCartState extends JunimoBaseState {
	public JunimoInCartState(Junimo junimo) {
		super(junimo);
	}

	@Override
	public void onEnter() {

	}

	@Override
	public final void onUpdate() {
		Cart cart = this.junimo.getCart();

		this.junimo.x += GameSettings.SCREEN_SPEED;
		this.junimo.speedY -= GameSettings.GRAVITY;
		this.junimo.y -= this.junimo.speedY;

		if (this.junimo.y >= cart.y) {
			this.junimo.y = cart.y;
			this.junimo.speedY = 0;
			return;
		}

		if (cart.getCurrentState() == cart.jumpState) {
			this.junimo.switchState(this.junimo.fallingState);
		}
	}

	@Override
	public void onExit() {

	}
}
