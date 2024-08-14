package model.map.generator.rail.state;


import helper.GameSettings;
import helper.RandomHelper;
import model.map.generator.CollectablesGenerator;
import model.map.generator.rail.RailGenerator;
import model.map.rail.AscendingRail;
import model.map.rail.DescendingRail;
import model.map.rail.NormalRail;
import model.map.rail.Rail;

import java.util.concurrent.ConcurrentHashMap;


public class RailGeneratorNormalState extends RailGeneratorBaseState {
	private int lastElevationChange;
	private double randomElevationChance;
	private double lastX;

	public RailGeneratorNormalState(RailGenerator railGenerator) {
		super(railGenerator);
		this.lastElevationChange = 0;
	}

	@Override
	public final void onEnterState() {
		this.lastElevationChange = 0;
		this.lastX = this.railGenerator.currentX - 1;
	}

	@Override
	public void onExitState() {

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
		this.handleElevationChange();

		ConcurrentHashMap<Integer, Rail> railList = this.railGenerator.getJunimoMap().getRailList();
		Rail choosenRail = this.chooseRail(this.railGenerator.currentX, this.railGenerator.currentY);
		this.lastRail = choosenRail;
		railList.put(this.railGenerator.currentX, choosenRail);

		railGenerator.currentY += this.lastElevationChange;
		this.lastElevationChange = 0;


		this.railGenerator.currentX += 1;
		this.handleSwitchState();
	}

	private void handleElevationChange() {
		randomElevationChance += 0.01;

		if (RandomHelper.getRandomBoolean(randomElevationChance)) {
			lastElevationChange = RandomHelper.getRandomInt(-1, 1);
			randomElevationChance = 0;

			if (this.railGenerator.currentY - GameSettings.RAIL_INITIAL_Y > GameSettings.HEIGHT_RANGE) {
				lastElevationChange = -1;
				return;
			}
			if (this.railGenerator.currentY - GameSettings.RAIL_INITIAL_Y < -GameSettings.HEIGHT_RANGE) {
				lastElevationChange = 1;
			}
		}
	}

	private Rail chooseRail(int posX, int posY) {
		if (this.lastElevationChange == -1) {
			return new AscendingRail(
					posX * GameSettings.PIXEL_SIZE,
					posY * GameSettings.PIXEL_SIZE);
		}

		if (this.lastElevationChange == 1) {
			return new DescendingRail(
					posX * GameSettings.PIXEL_SIZE,
					posY * GameSettings.PIXEL_SIZE);
		}

		return new NormalRail(
				posX * GameSettings.PIXEL_SIZE,
				posY * GameSettings.PIXEL_SIZE);
	}
}
