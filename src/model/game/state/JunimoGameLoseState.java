package model.game.state;

import model.game.JunimoGame;

public class JunimoGameLoseState extends JunimoGameBaseState {

	public JunimoGameLoseState(JunimoGame game) {
		super(game);
	}

	@Override
	public final void onEnterState() {
		this.game.getJunimoMap().stop();
		this.game.getJunimo().stop();
		this.game.getCart().stop();
		this.game.getScoreWriter().stop();
	}

	@Override
	public void onExitState() {

	}

	@Override
	public void onUpdate() {

	}
}
