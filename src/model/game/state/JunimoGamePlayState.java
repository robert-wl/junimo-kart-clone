package model.game.state;

import model.game.JunimoGame;
import model.map.generator.rail.RailGenerator;


public class JunimoGamePlayState extends JunimoGameBaseState {

	public JunimoGamePlayState(JunimoGame game) {
		super(game);
	}

	@Override
	public final void onEnterState() {
		RailGenerator railGenerator = this.game.getJunimoMap().getRailGenerator();
		railGenerator.switchState(railGenerator.normalState);
	}

	@Override
	public void onExitState() {

	}

	@Override
	public final void onUpdate() {
		if (this.game.getJunimoMap() == null) {
			return;
		}
		this.game.getJunimoMap().update();


		if (this.game.getCart().y > 500) {
			this.game.switchState(this.game.getLoseState());
		}
	}
}
