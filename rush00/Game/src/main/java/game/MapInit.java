package game;

import chaselogic.Map;

import java.util.Random;

public class MapInit {

    private static final Character border = 'Q';

    private char[][] array;
    private final Map map;
    private final Integer enemiesCount;
    private final Integer wallsCount;
    private final Integer size;
    private final Random random;

    public MapInit(Map map, Integer enemiesCount, Integer wallsCount, Integer size) {
        this.map = map;
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.size = size;
        this.random = new Random();
    }


    public char[][] init() {
        initMapEmpty();
        initMapEnemy();
        initMapWall();
        initMapPlayer();
        initMapGoal();
        return array;
    }

    private void initMapGoal() {
        while (true) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                if (array[y][x] == map.getEmptyChar()) {
                    array[y][x] = map.getGoalChar();
                    break;
                }
            }
        }
    }

    private void initMapPlayer() {
        while (true) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                if (array[y][x] == map.getEmptyChar()) {
                    if (!checkAround(y, x)) {
                        array[y][x] = map.getPlayerChar();
                        break;
                    }
                }
            }
        }
    }

    private boolean checkAround(Integer y, Integer x) {
        if (array[y - 1][x - 1] == map.getEnemyChar()) {
            return true;
        } else if (array[y - 1][x] == map.getEnemyChar()) {
            return true;
        } else if (array[y - 1][x + 1] == map.getEnemyChar()) {
            return true;
        } else if (array[y + 1][x - 1] == map.getEnemyChar()) {
            return true;
        } else if (array[y + 1][x] == map.getEnemyChar()) {
            return true;
        } else if (array[y + 1][x + 1] == map.getEnemyChar()) {
            return true;
        } else if (array[y][x - 1] == map.getEnemyChar()) {
            return true;
        } else return array[y][x + 1] == map.getEnemyChar();
    }

    private void initMapWall() {
        Integer count = 0;
        while (count < wallsCount) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (y == 1 || y.equals(size) || x == 1 || x.equals(size)) {
                continue;
            }
            if (array[y][x] == map.getEmptyChar()) {
                array[y][x] = map.getWallChar();
                count++;
            }
        }
    }

    private void initMapEnemy() {
        Integer count = 0;
        while (count < enemiesCount) {
            Integer y = random.nextInt(size) + 1;
            Integer x = random.nextInt(size) + 1;
            if (array[y][x] == map.getEmptyChar()) {
                array[y][x] = map.getEnemyChar();
                count++;
            }
        }
    }

    private void initMapEmpty() {
        array = new char[size + 2][size + 2];
        for (int y = 0; y < size + 2; y++) {
            for (int x = 0; x < size + 2; x++) {
                if (y == 0 || x == 0 || y == size + 1 || x == size + 1) {
                    array[y][x] = border;
                } else {
                    array[y][x] = map.getEmptyChar();
                }
            }
        }
    }
}
