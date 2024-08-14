package model.game.state;

import model.cart.Cart;
import model.game.JunimoGame;
import model.junimo.Junimo;
import model.map.JunimoMap;
import model.map.ScoreWriter;

public class JunimoGameStartState extends JunimoGameBaseState {


	public JunimoGameStartState(JunimoGame game) {
		super(game);
	}

	@Override
	public final void onEnterState() {
		Junimo junimo = new Junimo();
		Cart cart = new Cart(junimo);
		junimo.setCart(cart);
		ScoreWriter scoreWriter = new ScoreWriter(this.game.getGc());
		JunimoMap junimoMap = new JunimoMap(this.game.getBackGc());

		junimoMap.start();
		junimo.start();
		cart.start();
		scoreWriter.start();


		this.game.setJunimo(junimo);
		this.game.setCart(cart);
		this.game.setScoreWriter(scoreWriter);
		this.game.setJunimoMap(junimoMap);

		this.game.getThread().start();
	}

	@Override
	public void onExitState() {

	}

	@Override
	public final void onUpdate() {
		this.game.getJunimoMap().update();
	}
}
