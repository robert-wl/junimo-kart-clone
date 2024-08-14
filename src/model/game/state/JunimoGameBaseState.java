package model.game.state;

import model.game.JunimoGame;


public abstract class JunimoGameBaseState {
	protected JunimoGame game;

	protected JunimoGameBaseState(JunimoGame game) {
		this.game = game;
	}

	public abstract void onEnterState();

	public abstract void onExitState();

	public abstract void onUpdate();
}
