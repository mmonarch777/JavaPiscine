package game;

import chaselogic.ChaseLogic;
import chaselogic.Map;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.util.Scanner;

public class RunGame {

    private static final String MOVEMENTS = "1 or w - UP, 2 or s - DOWN, 3 or a - LEFT, 4 or d - RIGHT";
    private static final String CONFIRM = "Confirm enemy move by entering 8, or don't confirm entering 7";
    private static final String HELP = "1 or w - UP; 2 or s - DOWN, 3 or a - LEFT, 4 or d - RIGHT, 9 - EXIT";
    private static final String MSG = "Your move, press 5 for help";
    private static final String BAD_COLOR = "Error: bad color in property!";
    private static final String DEV_MODE = "dev";
    private static final String UP = "w";
    private static final String DOWN = "s";
    private static final String LEFT = "a";
    private static final String RIGHT = "d";
    private static final String UP2 = "1";
    private static final String DOWN2 = "2";
    private static final String LEFT2 = "3";
    private static final String RIGHT2 = "4";
    private static final String EXIT = "9";
    private static final String YES = "8";
    private static final String NO = "7";
    private static final String HELP_SELECTOR = "5";

    private final char[][] array;
    private final Map map;
    private final Integer size;
    private final String profile;

    public RunGame(char[][] array, Map map, Integer size, String profile) {
        this.array = array;
        this.map = map;
        this.size = size;
        this.profile = profile;
    }

    public void run() {
        ColoredPrinter coloredPrinter = new ColoredPrinter
                .Builder(1, false)
                .foreground(Ansi.FColor.BLACK)
                .build();
        printMatrix(coloredPrinter);

        Scanner scanner = new Scanner(System.in);
        Move move = new Move(array, size, map);
        ChaseLogic chaseLogic = new ChaseLogic(array, size, map);

        while (true) {
            System.out.println(MSG);
            String s = scanner.nextLine();

            if (s.equals(EXIT)) {
                break;
            }

            switch (s) {
                case UP:
                case UP2:
                    move.up();
                    break;
                case DOWN:
                case DOWN2:
                    move.down();
                    break;
                case LEFT:
                case LEFT2:
                    move.left();
                    break;
                case RIGHT:
                case RIGHT2:
                    move.right();
                    break;
                case HELP_SELECTOR:
                    System.out.println(HELP);
                    continue;
                default:
                    System.out.println(MOVEMENTS);
                    continue;
            }

            if (profile.equals(DEV_MODE)) {
                printMatrix(coloredPrinter);
                System.out.println();
                System.out.println(CONFIRM);
                String input;
                while (true) {
                    input = scanner.nextLine();
                    if (input.equals(YES)) {
                        chaseLogic.move();
                        break;
                    }
                    if (input.equals(NO)) {
                        break;
                    }
                    System.out.println(CONFIRM);
                }
                printMatrix(coloredPrinter);
            } else {
                chaseLogic.move();
                printMatrix(coloredPrinter);
            }
        }
    }

    private void printMatrix(ColoredPrinter coloredPrinter) {
        try {
            Ansi.BColor.valueOf(map.getEmptyColor());
            Ansi.BColor.valueOf(map.getPlayerColor());
            Ansi.BColor.valueOf(map.getGoalColor());
            Ansi.BColor.valueOf(map.getWallColor());
            Ansi.BColor.valueOf(map.getEnemyColor());
        } catch (IllegalArgumentException e) {
            System.err.println(BAD_COLOR);
            System.exit(-1);
        }

        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
                if (array[y][x] == map.getEmptyChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getEmptyColor()));
                } else if (array[y][x] == map.getPlayerChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getPlayerColor()));
                } else if (array[y][x] == map.getGoalChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getGoalColor()));
                } else if (array[y][x] == map.getWallChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getWallColor()));
                } else if (array[y][x] == map.getEnemyChar()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(map.getEnemyColor()));
                }
                coloredPrinter.print(array[y][x]);
            }
            System.out.println();
        }
    }
}
