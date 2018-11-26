package game.board;

import game.board.field.Field;
import exceptions.IllegalNumberOfPlayersException;

public class Board {
    private final int NUMBER_OF_ROWS = 17;
    private final int NUMBER_OF_DIAGONALS = 17;
    private final int[] FIELDS_PER_DIAGONAL = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    private Field[][] fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_DIAGONALS];


    public Board() {}

    private void initializeFields() {}
    private void initializeUnavailableFields() {}
    private void intializeColoredFields() {}
    private void initializeNeutralFields() {}
}
