package model.map.generator.rail;


import helper.GameSettings;
import model.map.JunimoMap;
import model.map.generator.rail.state.*;

public class RailGenerator {
	public final RailGeneratorSuddenState suddenState;
	public final RailGeneratorNormalState normalState;
	public final RailGeneratorVoidState voidState;
	public final RailGeneratorFlatState flatState;
	private final JunimoMap junimoMap;
	public int currentY;
	public int currentX;
	private RailGeneratorBaseState currentState;

	public RailGenerator(JunimoMap junimoMap) {
		this.junimoMap = junimoMap;
		this.currentY = GameSettings.RAIL_INITIAL_Y;
		this.normalState = new RailGeneratorNormalState(this);
		this.suddenState = new RailGeneratorSuddenState(this);
		this.voidState = new RailGeneratorVoidState(this);
		this.flatState = new RailGeneratorFlatState(this);
		this.currentState = this.flatState;
		this.currentState.onEnterState();
	}

	public final JunimoMap getJunimoMap() {
		return junimoMap;
	}

	public final void switchState(RailGeneratorBaseState nextState) {
		this.currentState.onExitState();
		this.currentState = nextState;
		this.currentState.onEnterState();
	}

	public final void updateState() {
		this.currentState.onUpdate();
	}

	public final void updateLogicState() {
		this.currentState.onUpdateLogic();
	}
}
