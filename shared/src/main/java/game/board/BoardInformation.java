package game.board;

public final class BoardInformation {
    public static final int NUMBER_OF_ROWS = 17;
    public static final int NUMBER_OF_DIAGONALS = 17;
    public static final int[][] RED_PLAYER_FIELDS_COORDINATES = {
            {13, 4}, {13, 5}, {13, 6}, {13, 7},
            {14, 4}, {14, 5}, {14, 6},
            {15, 4}, {15, 5},
            {16, 4}
    };
    public static final int[][] GREEN_PLAYER_FIELDS_COORDINATES = {
            {3, 13}, {3, 12}, {3, 11}, {3, 10},
            {2, 13}, {2, 12}, {2, 11},
            {1, 13}, {1, 12},
            {0, 13}
    };
    public static final int[][] BLUE_PLAYER_FIELDS_COORDINATES = {
            {4, 4}, {4, 5}, {4, 6}, {4, 7},
            {5, 4}, {5, 5}, {5, 6},
            {6, 4}, {6, 5},
            {7, 4}
    };
    public static final int[][] YELLOW_PLAYER_FIELDS_COORDINATES = {
            {4, 13}, {4, 14}, {4, 15}, {4, 16},
            {5, 13}, {5, 14}, {5, 15},
            {6, 13}, {6, 14},
            {7, 13}
    };
    public static final int[][] PURPLE_PLAYER_FIELDS_COORDINATES = {
            {12, 3}, {12, 2}, {12, 1}, {12, 0},
            {11, 3} , {11, 2}, {11, 1},
            {10, 3}, {10, 2},
            {9, 3}
    };
    public static final int[][] ORANGE_PLAYER_FIELDS = {
            {12, 12}, {12, 11}, {12, 10}, {12, 9},
            {11, 12}, {11, 11}, {11, 10},
            {10, 12}, {10, 11},
            {9, 12}
    };

    private BoardInformation(){}
}
