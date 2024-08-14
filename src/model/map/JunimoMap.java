package model.map;


import helper.CanvasHelper;
import helper.GameSettings;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import model.collectables.Collectables;
import model.game.JunimoGame;
import model.map.background.JunimoBackground;
import model.map.generator.BackgroundGenerator;
import model.map.generator.rail.RailGenerator;
import model.map.rail.Rail;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class JunimoMap extends AnimationTimer {
	private final GraphicsContext backGc;
	private final ConcurrentHashMap<Integer, Collectables> collectablesList;
	private final RailGenerator railGenerator;
	private final ConcurrentHashMap<Integer, Rail> railList;
	private ArrayList<JunimoBackground> backgroundList;
	private double backCurrentX = 0;
	private double currentX = 0;
	private double translatedX = 0;

	public JunimoMap(GraphicsContext backGc) {
		this.railList = new ConcurrentHashMap<>();
		this.backgroundList = new ArrayList<>();
		this.collectablesList = new ConcurrentHashMap<>();
		this.backGc = backGc;
		this.railGenerator = new RailGenerator(this);
	}

	private void handleBackgroundGeneration() {
		JunimoBackground background = BackgroundGenerator.generateBackground((int) backCurrentX);

		if (background != null) {
			this.backgroundList.add(background);
			this.backgroundList.get(0).destroy();
			this.backgroundList.remove(0);
		}
	}

	public double getTranslatedX() {
		return translatedX;
	}

	@Override
	public final void start() {
		currentX += GameSettings.INITIAL_RAIL_AMOUNT;
		CanvasHelper.moveCanvas(-16, 0);

		super.start();

		this.backgroundList = BackgroundGenerator.generateInitialBackground();
	}

	@Override
	public final void stop() {
		super.stop();
		this.collectablesList.forEach((key, collectables) -> collectables.stop());
		this.backgroundList.forEach(background -> background.stop());
	}

	public RailGenerator getRailGenerator() {
		return railGenerator;
	}

	@Override
	public final void handle(long l) {

		double translateAmount = GameSettings.SCREEN_SPEED;
		CanvasHelper.clearScreen(translatedX, 0);
		CanvasHelper.clearScreen(this.backGc, backCurrentX, 0);


		translatedX += translateAmount;
		CanvasHelper.moveCanvas(-translateAmount, 0);
		CanvasHelper.moveCanvas(this.backGc, -translateAmount * 0.05, 0);
		this.railGenerator.updateState();
		backCurrentX += translateAmount * 0.05;
		currentX += translateAmount / GameSettings.PIXEL_SIZE;

		this.railList.values().forEach(rail -> rail.drawImage());
		JunimoGame.getInstance().writeScore(translatedX);
		JunimoGame.getInstance().getScoreWriter().start();
	}

	public final void update() {
		this.handleBackgroundGeneration();
		this.railGenerator.updateLogicState();
	}

	public final ConcurrentHashMap<Integer, Rail> getRailList() {
		return railList;
	}

	public final ConcurrentHashMap<Integer, Collectables> getCollectablesList() {
		return collectablesList;
	}
}
