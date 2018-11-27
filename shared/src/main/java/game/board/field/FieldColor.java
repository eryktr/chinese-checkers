package game.board.field;

public enum FieldColor {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    PURPLE,
    ORANGE,
    NONE;

    public static FieldColor fromNumber(int number) {
        switch (number) {
            case 0:
                return FieldColor.RED;
            case 1:
                return FieldColor.GREEN;
            case 2:
                return FieldColor.BLUE;
            case 3:
                return FieldColor.YELLOW;
            case 4:
                return FieldColor.PURPLE;
            case 5:
                return FieldColor.ORANGE;
            default:
                return FieldColor.NONE;
        }
    }

    public static FieldColor getEnemy(FieldColor color) {
        switch (color) {
            case RED:
                return GREEN;
            case GREEN:
                return RED;
            case BLUE:
                return ORANGE;
            case ORANGE:
                return BLUE;
            case PURPLE:
                return YELLOW;
            case YELLOW:
                return PURPLE;
            default:
                return NONE;
        }
    }
}
