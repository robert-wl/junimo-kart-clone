package helper;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameInput {
	private static GameInput gameInput;
	private static final ArrayList<KeyCode> pressedKeys = new ArrayList<>();
	private static final ArrayList<MouseButton> mouseClicks = new ArrayList<>();

	private GameInput(Scene scene) {
		handlePlayerInput(scene);
	}

	public static GameInput getInstance(Scene scene) {
		if (gameInput == null) {
			gameInput = new GameInput(scene);
		}
		return gameInput;
	}

	public static ArrayList<KeyCode> getPressedKeys() {
		return pressedKeys;
	}

	private void handlePlayerInput(Scene scene) {
		KeyCode[] allowedKeys = { KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.W, KeyCode.Z, KeyCode.SPACE };
		List<KeyCode> allowedKeysList = Arrays.asList(allowedKeys);

		scene.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (!pressedKeys.contains(keyCode) && allowedKeysList.contains(keyCode)) {
				pressedKeys.add(keyCode);
			}
		});

		scene.setOnKeyReleased(e -> {
			KeyCode keyCode = e.getCode();
			pressedKeys.remove(keyCode);
		});

		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			mouseClicks.add(e.getButton());
		});

		scene.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			mouseClicks.remove(e.getButton());
		});
	}
}
