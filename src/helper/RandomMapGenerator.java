package helper;

import java.util.ArrayList;
import java.util.Random;

public class RandomMapGenerator {

    private static RandomMapGenerator randomMapGenerator;

    final private int mapWidth = 80;
    final private int mapHeight = 50;

    private int spawnX;
    private int spawnY;

    private ArrayList<ArrayList<String>> map = new ArrayList<>();

    public static RandomMapGenerator getInstance() {
        if (randomMapGenerator == null) {
            randomMapGenerator = new RandomMapGenerator();
        }
        return randomMapGenerator;
    }

    private RandomMapGenerator() {
        Random random = new Random();
        int lakeX;
        int lakeY;
        int lakeRadius = 3;

        do {
            lakeX = random.nextInt(mapWidth);
        } while (lakeX >= 34);

        do {
            lakeY = random.nextInt(mapHeight);
        } while (lakeY <= 6);

        int housePivot = random.nextInt(11);

        generate2DMap(housePivot, lakeX, lakeY, lakeRadius);

        print2DMap();
    }

    private void generate2DMap(int housePivot, int lakeX, int lakeY, int lakeRadius) {
        for (int i = 0; i < mapHeight; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < mapWidth; j++) {

                int distanceToLakeCenter = (int) Math.sqrt(Math.pow(j - lakeX, 2) + Math.pow(i - lakeY, 2));

                if(isHousePivot(i, j, housePivot)){
                    row.add("HousePivot");
                } else if(isInsideHouse(i, j, housePivot)) {
                    row.add("House");
                } else if(houseSpawn(i, j, housePivot)) {
                    row.add("HouseSpawn");
                    spawnX = j;
                    spawnY = i;
                } else if(isEntranceHouse(i, j, housePivot)){
                    row.add("Entrance");
                } else if (i == 4 && j == 34) {
                    row.add("Mine");
                } else if (distanceToLakeCenter <= lakeRadius) {
                    row.add("Water");
                } else {
                    if (Math.random() < 0.03 && i > 8) {
                        row.add("Tree");
                    } else {
                        row.add("Land");
                    }
                }
            }
            map.add(row);
        }
    }

    private void print2DMap() {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<String>> getTerrain() {
        return map;
    }
    private boolean isInsideHouse(int i, int j, int housePivot) {
        return (i >= housePivot && i < housePivot + 7 && j >= housePivot && j <= housePivot + 8) ||
                ((i == housePivot + 7 || i == housePivot + 8) && j >= housePivot && j <= housePivot + 3) ||
                ((i == housePivot + 7 || i == housePivot + 8) && (j == housePivot + 8 || j == housePivot + 7));
    }

    private boolean isHousePivot(int i, int j, int housePivot){
        return ((i == housePivot + 8) && j == housePivot + 8);
    }

    private boolean isEntranceHouse(int i, int j, int housePivot) {
        return (i == housePivot + 7 || i == housePivot + 8) && j >= housePivot + 4 && j <= housePivot + 6;
    }

    private boolean houseSpawn(int i, int j, int housePivot) {
        return (i == housePivot + 7) && j == housePivot + 5;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

}
