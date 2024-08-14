package model.map.generator.rail.state;


import helper.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.game.JunimoGame;
import model.map.JunimoMap;
import model.map.generator.rail.RailGenerator;
import model.map.rail.NormalRail;
import model.map.rail.Rail;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class RailGeneratorFlatState extends RailGeneratorBaseState {
	private double lastX;
	private double transparency;
	private boolean isStarting;

	private ArrayList<Image> titleScreen;

	public RailGeneratorFlatState(RailGenerator railGenerator) {
		super(railGenerator);
	}

	@Override
	public final void onEnterState() {
		ConcurrentHashMap<Integer, Rail> railList = this.railGenerator.getJunimoMap().getRailList();
		int createAmount = GameSettings.INITIAL_RAIL_AMOUNT;
		for (int i = 0; i < createAmount; i++) {
			railList.put(i, new NormalRail(
					i * GameSettings.PIXEL_SIZE,
					railGenerator.currentY * GameSettings.PIXEL_SIZE));
		}

		this.railGenerator.currentX = createAmount - 1;

		this.lastX = this.railGenerator.currentX - 1;
		this.transparency = 1;
		this.isStarting = false;

		SpriteResource spriteResource = SpriteResource.getInstance();
		this.titleScreen = SpriteExtract.longSpriteExtract(spriteResource.getJunimoSprite().get("title"), 1);
	}

	@Override
	public void onExitState() {

	}

	@Override
	public final void onUpdate() {
		if (isStarting) {
			this.transparency -= 0.01;
			this.transparency = Math.max(this.transparency, 0);
		}

		if (isStarting && this.transparency == 0) {
			JunimoGame.getInstance().switchState(JunimoGame.getInstance().getPlayState());
		}
		JunimoMap map = JunimoGame.getInstance().getJunimoMap();
		CanvasHelper.setTransparency(transparency);
		CanvasHelper.drawImage(this.titleScreen.get(0), map.getTranslatedX() + 100, 60);
		CanvasHelper.setTransparency(1);
		lastX += GameSettings.SCREEN_SPEED / GameSettings.PIXEL_SIZE;

		if (GameInput.getPressedKeys().contains(KeyCode.SPACE)) {
			this.isStarting = true;
		}
	}

	@Override
	public final void onUpdateLogic() {
		if (this.railGenerator.currentX >= lastX) {
			return;
		}

		this.cleanRail();

		ConcurrentHashMap<Integer, Rail> railList = this.railGenerator.getJunimoMap().getRailList();
		railList.put(this.railGenerator.currentX, new NormalRail(
				this.railGenerator.currentX * GameSettings.PIXEL_SIZE,
				this.railGenerator.currentY * GameSettings.PIXEL_SIZE));


		this.railGenerator.currentX += 1;
		this.handleSwitchState();
	}
}
