package board;

import board.field.Field;
import exceptions.IllegalNumberOfPlayersException;

public class Board {
    private final int NUMBER_OF_ROWS = 17;
    private final int NUMBER_OF_FIELDS_PER_ROW = 13;
    private Field[][] fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_FIELDS_PER_ROW];
    private final int[] rowLength = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 14, 4, 3, 2, 1};
    private final int[] rowOffset = {};

    public Board(int number_of_players) throws IllegalNumberOfPlayersException {
        if(number_of_players != 2 && number_of_players != 4 && number_of_players != 6) {
            throw new IllegalNumberOfPlayersException();
        }

        initializeFields();
    }

    private void initializeFields() {

    }

    private void initializeUnavailableFields() {

    }
    private void intializeColoredFields() {}
    private void initializeNeutralFields() {}
}
