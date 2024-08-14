package model.junimo;

import helper.CanvasHelper;
import helper.SpriteExtract;
import helper.SpriteResource;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import model.cart.Cart;
import model.collideable.CollisionBox;
import model.junimo.state.JunimoBaseState;
import model.junimo.state.JunimoFallingState;
import model.junimo.state.JunimoInCartState;

import java.util.ArrayList;
import java.util.HashMap;

public class Junimo extends AnimationTimer {
	public final double speedX;
	public final JunimoFallingState fallingState;
	public final JunimoInCartState inCartState;
	private final ArrayList<Image> junimoImages;
	private final CollisionBox collider;
	public double x;
	public double y;
	public double speedY;
	private JunimoBaseState currentState;
	private double animationNumber;
	private int animationStage;
	private Cart cart;

	public Junimo() {
		this.x = 65;
		this.y = 64;
		this.speedX = 0;
		this.speedY = 0;
		this.animationNumber = 0;
		this.collider = new CollisionBox(this.x, this.y, this.x + 16, this.y + 16);
		this.fallingState = new JunimoFallingState(this);
		this.inCartState = new JunimoInCartState(this);
		this.currentState = this.fallingState;
		this.currentState.onEnter();

		HashMap<String, Image> junimoSprite = SpriteResource.getInstance().getJunimoSprite();
		this.junimoImages = SpriteExtract.longSpriteExtract(junimoSprite.get("junimo"), 4);
	}

	@Override
	public final void handle(long l) {
		this.collider.updateCollider(this.x, this.y, this.x + 16, this.y + 16);
		this.drawImage();
		this.currentState.onUpdate();
	}

	public final double getX() {
		return x;
	}


	public final double getY() {
		return y;
	}

	public final void updatePosition(double posX, double posY, boolean forced) {
		this.x = posX - 0.5;

		if (this.y < posY || !forced) {
			return;
		}


		this.y = posY;
	}

	public final void updatePosition(double posX, double posY) {
		this.x = posX - 0.5;

		if (this.y < posY) {
			return;
		}

//		Logger.log("hai");
		this.y = posY;
	}

	public final void switchState(JunimoBaseState nextState) {
		this.currentState.onExit();
		this.currentState = nextState;
		this.currentState.onEnter();
	}

	public final void updateSpeed(double speedX, double speedY) {
		this.speedY = speedY;
	}

	public final void drawImage() {
		this.animationNumber += 1;

		if (this.animationNumber >= 8) {
			this.animationStage += 1;
			this.animationStage %= 4;

			this.animationNumber = 0;
		}
		Image junimo = this.junimoImages.get(this.animationStage);
		CanvasHelper.drawImageCentered(junimo,
		                               this.x + junimo.getWidth() / 2,
		                               this.y + 2 * junimo.getHeight() / 3,
		                               junimo.getWidth() * 0.75,
		                               junimo.getHeight() * 0.75);
	}

	public final Cart getCart() {
		return cart;
	}

	public final void setCart(Cart cart) {
		this.cart = cart;
	}

	public CollisionBox getCollider() {
		return collider;
	}
}
