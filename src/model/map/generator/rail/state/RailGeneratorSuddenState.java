package model.map.generator.rail.state;


import helper.GameSettings;
import helper.RandomHelper;
import model.map.generator.CollectablesGenerator;
import model.map.generator.rail.RailGenerator;
import model.map.rail.NormalRail;
import model.map.rail.Rail;

import java.util.concurrent.ConcurrentHashMap;

public class RailGeneratorSuddenState extends RailGeneratorBaseState {
	private int suddenRailElevation;
	private int suddenRailAmount;
	private int generatedRailAmount;
	private double lastX;

	public RailGeneratorSuddenState(RailGenerator railGenerator) {
		super(railGenerator);
	}

	@Override
	public final void onEnterState() {
		this.lastX = this.railGenerator.currentX - 1;
		this.suddenRailElevation = RandomHelper.getRandomInt(-4, -2, 2, 4);
		this.suddenRailAmount = RandomHelper.getRandomInt(3, 6);
		this.generatedRailAmount = 0;

		if ((this.railGenerator.currentY + this.suddenRailElevation) - GameSettings.RAIL_INITIAL_Y > GameSettings.HEIGHT_RANGE) {
			suddenRailElevation *= -1;
		}
		if ((this.railGenerator.currentY + this.suddenRailElevation) - GameSettings.RAIL_INITIAL_Y < -GameSettings.HEIGHT_RANGE) {
			suddenRailElevation *= -1;
		}

		this.railGenerator.currentY += this.suddenRailElevation;
	}

	@Override
	public final void onExitState() {
		if (RandomHelper.getRandomBoolean(GameSettings.RAIL_SUDDEN_ROLLBACK_CHANCE)) {
			this.railGenerator.currentY -= this.suddenRailElevation;
		}
	}

	@Override
	public final void onUpdate() {
		lastX += GameSettings.SCREEN_SPEED / GameSettings.PIXEL_SIZE;
	}

	@Override
	public final void onUpdateLogic() {
		if (this.railGenerator.currentX >= lastX) {
			return;
		}

		CollectablesGenerator.generate(this.railGenerator.currentX, this.railGenerator.currentY - 1);

		this.cleanRail();

		ConcurrentHashMap<Integer, Rail> railList = this.railGenerator.getJunimoMap().getRailList();
		railList.put(this.railGenerator.currentX, new NormalRail(this.railGenerator.currentX * GameSettings.PIXEL_SIZE, this.railGenerator.currentY * GameSettings.PIXEL_SIZE));

		this.generatedRailAmount += 1;
		this.railGenerator.currentX += 1;

		if (this.generatedRailAmount >= this.suddenRailAmount) {
			this.railGenerator.switchState(this.railGenerator.normalState);
		}
	}
}
