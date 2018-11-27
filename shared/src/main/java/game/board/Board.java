package game.board;

import game.board.field.Field;
import game.board.field.fieldinitializer.FieldInitializer;

public class Board {
    public static final int NUMBER_OF_ROWS = 17;
    public static final int NUMBER_OF_DIAGONALS = 17;
    public static final int[] FIELDS_PER_DIAGONAL = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    public static final int[] FIELDS_PER_ROW = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    public static final int PLAYER_HOME_HEIGHT = 4;
    private Field[][] fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_DIAGONALS];
    private FieldInitializer fieldInitializer;


    public Board() {}

    public void initializeFields() {
        fieldInitializer.initializeFields();
    }

    public Field[][] getFields() {
        return fields;
    }
}
