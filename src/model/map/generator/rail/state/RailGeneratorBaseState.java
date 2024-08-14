package model.map.generator.rail.state;


import helper.GameSettings;
import helper.RandomHelper;
import model.map.generator.rail.RailGenerator;
import model.map.rail.NormalRail;
import model.map.rail.Rail;

import java.util.concurrent.ConcurrentHashMap;

public abstract class RailGeneratorBaseState {
	protected RailGenerator railGenerator;
	protected Rail lastRail;

	protected RailGeneratorBaseState(RailGenerator railGenerator) {
		this.railGenerator = railGenerator;
	}

	public abstract void onEnterState();

	public abstract void onExitState();

	public abstract void onUpdate();

	public abstract void onUpdateLogic();

	protected final void cleanRail() {
		int maxRailAmount = GameSettings.INITIAL_RAIL_AMOUNT;
		ConcurrentHashMap<Integer, Rail> railList = this.railGenerator.getJunimoMap().getRailList();
		Rail currentRail = railList.get((this.railGenerator.currentX - maxRailAmount));

		if (currentRail != null) {
			railList.remove((this.railGenerator.currentX - maxRailAmount));
		}
	}

	protected final void handleSwitchState() {
		if (RandomHelper.getRandomBoolean(GameSettings.RAIL_SUDDEN_CHANCE) && lastRail != null && lastRail.getClass() == NormalRail.class) {
			this.railGenerator.switchState(this.railGenerator.suddenState);
			return;
		}
		if (RandomHelper.getRandomBoolean(GameSettings.RAIL_VOID_CHANCE) && lastRail != null && lastRail.getClass() == NormalRail.class) {
			this.railGenerator.switchState(this.railGenerator.voidState);
		}
	}
}
