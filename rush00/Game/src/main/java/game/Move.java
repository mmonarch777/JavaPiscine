package game;

import chaselogic.Map;

public class Move {

    public static final String GAME_OVER = "Game over!";
    public static final String WIN = "You win!";

    private final char[][] array;
    private final Integer size;
    private Integer playerY;
    private Integer playerX;
    private final Map map;

    public Move(char[][] array, Integer size, Map map) {
        this.array = array;
        this.size = size;
        this.map = map;
        initPlayerCoordinate();
    }

    private void initPlayerCoordinate() {
        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (array[y][x] == map.getPlayerChar()) {
                    playerY = y;
                    playerX = x;
                }
            }
        }
    }

    public void up() {
        if (array[playerY - 1][playerX] == map.getEmptyChar()) {
            array[playerY - 1][playerX] = map.getPlayerChar();
            array[playerY][playerX] = map.getEmptyChar();
            playerY = playerY - 1;
        } else if (array[playerY - 1][playerX] == map.getGoalChar()) {
            System.out.println(WIN);
            System.exit(0);
        } else if (array[playerY - 1][playerX] == map.getEnemyChar()) {
            System.out.println(GAME_OVER);
            System.exit(0);
        }
    }

    public void down() {
        if (array[playerY + 1][playerX] == map.getEmptyChar()) {
            array[playerY + 1][playerX] = map.getPlayerChar();
            array[playerY][playerX] = map.getEmptyChar();
            playerY = playerY + 1;
        } else if (array[playerY + 1][playerX] == map.getGoalChar()) {
            System.out.println(WIN);
            System.exit(0);
        } else if (array[playerY + 1][playerX] == map.getEnemyChar()) {
            System.out.println(GAME_OVER);
            System.exit(0);
        }
    }

    public void left() {
        if (array[playerY][playerX - 1] == map.getEmptyChar()) {
            array[playerY][playerX - 1] = map.getPlayerChar();
            array[playerY][playerX] = map.getEmptyChar();
            playerX = playerX - 1;
        } else if (array[playerY][playerX - 1] == map.getGoalChar()) {
            System.out.println(WIN);
            System.exit(0);
        } else if (array[playerY][playerX - 1] == map.getEnemyChar()) {
            System.out.println(GAME_OVER);
            System.exit(0);
        }
    }

    public void right() {
        if (array[playerY][playerX + 1] == map.getEmptyChar()) {
            array[playerY][playerX + 1] = map.getPlayerChar();
            array[playerY][playerX] = map.getEmptyChar();
            playerX = playerX + 1;
        } else if (array[playerY][playerX + 1] == map.getGoalChar()) {
            System.out.println(WIN);
            System.exit(0);
        } else if (array[playerY][playerX + 1] == map.getEnemyChar()) {
            System.out.println(GAME_OVER);
            System.exit(0);
        }
    }
}
