package main;

import helper.CanvasHelper;
import helper.GameInput;
import helper.GameSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.game.JunimoGame;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		JunimoGame game = JunimoGame.getInstance();

		Scene scene = game.getScene();

		CanvasHelper.setScale(GameSettings.JUNIMO_GAME_SCALE, GameSettings.JUNIMO_GAME_SCALE);
		CanvasHelper.setScale(game.getBackGc(), 4, 4);
		game.getCurrentState().onEnterState();

		GameInput.getInstance(scene);

		stage.setFullScreen(true);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}