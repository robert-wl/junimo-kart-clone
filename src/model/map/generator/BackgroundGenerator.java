package model.map.generator;


import model.map.background.JunimoBackground;

import java.util.ArrayList;

public class BackgroundGenerator {
	private static int currentX = 0;

	private BackgroundGenerator() {
	}

	public static ArrayList<JunimoBackground> generateInitialBackground() {
		ArrayList<JunimoBackground> backgroundList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			JunimoBackground background = new JunimoBackground(i * 96);
			background.start();
			backgroundList.add(background);
			currentX += 96;
		}

		return backgroundList;
	}

	public static JunimoBackground generateBackground(int lastX) {
		if (currentX >= lastX + 96 * 9) {
			return null;
		}

		JunimoBackground background = new JunimoBackground(currentX);
		background.start();

		currentX += 96;
		return background;
	}
}
