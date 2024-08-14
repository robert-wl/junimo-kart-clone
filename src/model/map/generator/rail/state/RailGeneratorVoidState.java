package model.map.generator.rail.state;


import helper.GameSettings;
import helper.RandomHelper;
import model.map.generator.CollectablesGenerator;
import model.map.generator.rail.RailGenerator;

public class RailGeneratorVoidState extends RailGeneratorBaseState {
	private int voidAmount;
	private int generatedVoidAmount;
	private double lastX;
	private int voidElevation;

	public RailGeneratorVoidState(RailGenerator railGenerator) {
		super(railGenerator);
	}

	@Override
	public final void onEnterState() {
		this.lastX = this.railGenerator.currentX - 1;
		this.generatedVoidAmount = 0;
		this.voidAmount = RandomHelper.getRandomInt(2, 4);

		this.voidElevation = RandomHelper.getRandomInt(-2, 2);

		if (this.voidElevation - Math.abs(this.voidElevation) > 3) {
			this.voidElevation = 3;
		}

		if ((this.railGenerator.currentY + this.voidElevation) - GameSettings.RAIL_INITIAL_Y > GameSettings.HEIGHT_RANGE) {
			this.voidElevation *= -1;
		}
		if ((this.railGenerator.currentY + this.voidElevation) - GameSettings.RAIL_INITIAL_Y < -GameSettings.HEIGHT_RANGE) {
			this.voidElevation *= -1;
		}

	}

	@Override
	public final void onExitState() {
		this.railGenerator.currentY += this.voidElevation;
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

		CollectablesGenerator.generate(this.railGenerator.currentX, this.railGenerator.currentY - voidAmount);

		this.cleanRail();

		this.generatedVoidAmount += 1;
		this.railGenerator.currentX += 1;

		if (this.generatedVoidAmount >= this.voidAmount) {
			this.railGenerator.switchState(this.railGenerator.normalState);
		}
	}
}
