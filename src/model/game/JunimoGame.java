package model.game;


import helper.GameSettings;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import model.cart.Cart;
import model.game.state.*;
import model.junimo.Junimo;
import model.map.JunimoMap;
import model.map.ScoreWriter;

public class JunimoGame implements Runnable {
	private static JunimoGame instance;
	private Scene scene;
	private final Canvas canvas;
	private final GraphicsContext gc;
	private final Canvas backCanvas;
	private final GraphicsContext backGc;
	private final JunimoGameLoseState loseState;
	private final JunimoGamePausedState pausedState;
	private final JunimoGameStartState startState;
	private final JunimoGamePlayState playState;
	private final Thread thread;
	private JunimoMap junimoMap;
	private Junimo junimo;
	private Cart cart;
	private ScoreWriter scoreWriter;
	private JunimoGameBaseState currentState;
	private double score;

	private JunimoGame() {
		this.canvas = new Canvas(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		gc.setImageSmoothing(false);

		this.backCanvas = new Canvas(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
		this.backGc = backCanvas.getGraphicsContext2D();
		backGc.setImageSmoothing(false);

		this.loseState = new JunimoGameLoseState(this);
		this.pausedState = new JunimoGamePausedState(this);
		this.startState = new JunimoGameStartState(this);
		this.playState = new JunimoGamePlayState(this);
		this.thread = new Thread(this);
		this.currentState = this.startState;

		initializeScreen();
	}

	private void initializeScreen() {
		StackPane root = new StackPane();
		root.getChildren().addAll(backCanvas, canvas);

		this.scene = new Scene(root);
	}

	public static JunimoGame getInstance() {
		if (instance == null) {
			instance = new JunimoGame();
		}
		return instance;
	}


	public final void switchState(JunimoGameBaseState nextState) {
		this.currentState.onExitState();
		this.currentState = nextState;
		this.currentState.onEnterState();
	}

	public final void writeScore(double posX) {
		if (this.scoreWriter == null) {
			return;
		}
		this.scoreWriter.updateScore(posX, this.score);
	}


	public final JunimoMap getJunimoMap() {
		return junimoMap;
	}

	public final void setJunimoMap(JunimoMap junimoMap) {
		this.junimoMap = junimoMap;
	}


	public final GraphicsContext getGc() {
		return gc;
	}

	public final GraphicsContext getBackGc() {
		return backGc;
	}

	public final void addScore(double score) {
		this.score += score;
	}

	public final ScoreWriter getScoreWriter() {
		return scoreWriter;
	}

	public final void setScoreWriter(ScoreWriter scoreWriter) {
		this.scoreWriter = scoreWriter;
	}

	public final Junimo getJunimo() {
		return junimo;
	}

	public final void setJunimo(Junimo junimo) {
		this.junimo = junimo;
	}

	public final Cart getCart() {
		return cart;
	}

	public final void setCart(Cart cart) {
		this.cart = cart;
	}

	public final JunimoGameLoseState getLoseState() {
		return loseState;
	}

	public final JunimoGameBaseState getCurrentState() {
		return currentState;
	}

	public final JunimoGamePausedState getPausedState() {
		return pausedState;
	}

	public final JunimoGameStartState getStartState() {
		return startState;
	}

	public final JunimoGamePlayState getPlayState() {
		return playState;
	}


	public Scene getScene() {
		return scene;
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public final void run() {
		do {
			this.currentState.onUpdate();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		} while (true);
	}
}
