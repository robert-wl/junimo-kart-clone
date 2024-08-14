package model.map.generator;


import helper.GameSettings;
import helper.RandomHelper;
import model.collectables.Coins;
import model.collectables.Collectables;
import model.collectables.Fruits;
import model.game.JunimoGame;

import java.util.concurrent.ConcurrentHashMap;

public class CollectablesGenerator {
	private CollectablesGenerator() {
	}

	public static void generate(int posX, int posY) {

		ConcurrentHashMap<Integer, Collectables> collectableList = JunimoGame.getInstance().getJunimoMap().getCollectablesList();

		if (collectableList.size() > 40) {
			collectableList.remove(getLowestKey(collectableList));
		}


		if (RandomHelper.getRandomBoolean(GameSettings.COIN_CHANCE)) {
			Coins coins = new Coins(posX * GameSettings.PIXEL_SIZE, posY * GameSettings.PIXEL_SIZE);
			collectableList.put(posX, coins);
			return;
		}
		if (RandomHelper.getRandomBoolean(GameSettings.CHERRY_CHANCE)) {
			Fruits fruits = new Fruits(posX * GameSettings.PIXEL_SIZE, (posY - RandomHelper.getRandomInt(2, 3)) * GameSettings.PIXEL_SIZE, 0);
			collectableList.put(posX, fruits);
			return;
		}
		if (RandomHelper.getRandomBoolean(GameSettings.ORANGE_CHANCE)) {
			Fruits fruits = new Fruits(posX * GameSettings.PIXEL_SIZE, (posY - RandomHelper.getRandomInt(2, 3)) * GameSettings.PIXEL_SIZE, 1);
			collectableList.put(posX, fruits);
			return;
		}
		if (RandomHelper.getRandomBoolean(GameSettings.GRAPE_CHANCE)) {
			Fruits fruits = new Fruits(posX * GameSettings.PIXEL_SIZE, (posY - RandomHelper.getRandomInt(2, 3)) * GameSettings.PIXEL_SIZE, 2);
			collectableList.put(posX, fruits);
		}
	}

	private static int getLowestKey(ConcurrentHashMap<Integer, Collectables> map) {
		int lowestKey = Integer.MAX_VALUE;

		for (Integer key : map.keySet()) {
			if (key < lowestKey) {
				lowestKey = key;
			}
		}

		return lowestKey != Integer.MAX_VALUE ? lowestKey : -1;
	}
}
