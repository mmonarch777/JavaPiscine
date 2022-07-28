package chaselogic;

public class ChaseLogic {

    public static final String GAME_OVER = "Game over!";

    private final char[][] array;
    private final Integer size;
    private final Map map;
    private Integer playerY;
    private Integer playerX;

    public ChaseLogic(char[][] array, Integer size, Map map) {
        this.array = array;
        this.size = size;
        this.map = map;
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

    public void move() {
        initPlayerCoordinate();
        char[][] temp = new char[size + 2][size + 2];
        for (int y = 0; y < size + 2; y++) {
            System.arraycopy(array[y], 0, temp[y], 0, size + 2);
        }
        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (array[y][x] == map.getEnemyChar()) {
                    if (checkPlayer(y, x)) {
                        System.out.println(GAME_OVER);
                        System.exit(-1);
                    }
                    moveEnemy(y, x, temp);
                }
            }
        }
        for (int y = 0; y < size + 2; y++) {
            System.arraycopy(temp[y], 0, array[y], 0, size + 2);
        }
    }

    private boolean checkPlayer(int y, int x) {
        return array[y - 1][x] == map.getPlayerChar() || array[y + 1][x] == map.getPlayerChar() ||
                array[y][x - 1] == map.getPlayerChar() || array[y][x + 1] == map.getPlayerChar();
    }

    private void stepLeft(int y, int x, char[][] array) {
        if (this.array[y][x - 1] == map.getEmptyChar()
                && array[y][x - 1] != map.getEnemyChar()) {
            array[y][x - 1] = map.getEnemyChar();
            array[y][x] = map.getEmptyChar();
        }
    }
    private void stepRight(int y, int x, char[][] array) {
        if (this.array[y][x + 1] == map.getEmptyChar()
                && array[y][x + 1] != map.getEnemyChar()) {
            array[y][x + 1] = map.getEnemyChar();
            array[y][x] = map.getEmptyChar();
        }
    }
    private void stepUp(int y, int x, char[][] array) {
        if (this.array[y + 1][x] == map.getEmptyChar()
                && array[y + 1][x] != map.getEnemyChar()) {
            array[y + 1][x] = map.getEnemyChar();
            array[y][x] = map.getEmptyChar();
        }
    }
    private void stepDown(int y, int x, char[][] array) {
        if (this.array[y - 1][x] == map.getEmptyChar()
                && array[y - 1][x] != map.getEnemyChar()) {
            array[y - 1][x] = map.getEnemyChar();
            array[y][x] = map.getEmptyChar();
        }
    }

    private void moveEnemy(int y, int x, char[][] array) {
        if (y > playerY) {
            if (x > playerX) {
                if (x - playerX > y - playerY) {
                   stepLeft(y, x, array);
                } else {
                    stepDown(y, x, array);
                }
            } else {
                if (playerX - x > y - playerY) {
                    stepDown(y, x, array);
                } else {
                    stepRight(y, x, array);
                }
            }
        } else {
            if (x > playerX) {
                if (x - playerX > playerY - y) {
                    stepLeft(y, x, array);
                } else {
                    stepUp(y, x, array);
                }
            } else {
                if (playerX - x > playerY - y) {
                    stepRight(y, x, array);
                } else {
                    stepUp(y, x, array);
                }
            }
        }
    }
}
