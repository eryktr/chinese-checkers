package game.board.field.fieldinitializer;

import game.board.Board;
import game.board.BoardInformation;
import game.board.field.*;

public class ConcreteFieldInitializer extends FieldInitializer {
    public ConcreteFieldInitializer(Board board) {
        this.fields = board.getFields();
    }

    private int[][] hexagonBounds = {{8, 12}, {7, 12}, {6, 12}, {5, 12}, {4, 12}, {4, 11}, {4, 10}, {4, 9}, {4, 8}};

    @Override
    protected void initializeIllegalFields() {
        for (int i = 0; i < BoardInformation.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < BoardInformation.NUMBER_OF_DIAGONALS; j++) {
                fields[i][j] = new IllegalField();
            }
        }
    }

    @Override
    protected void initializePlayerHomeFields() {
        int color_number = 0;
        for (int[][] playerHomeCoordinates : BoardInformation.PLAYER_FIELDS_COORDINATES) {
            for (int[] pieceCoordinates : playerHomeCoordinates) {
                fields[pieceCoordinates[0]][pieceCoordinates[1]] = new HomeField(FieldColor.fromNumber(color_number));
            }
            color_number++;
        }
    }

    @Override
    protected void initializeHexagonFields() {
        for (int i = 4; i <= 12; i++) {
            int lower_bound = hexagonBounds[i - 4][0];
            int upper_bound = hexagonBounds[i - 4][1];
            for (int j = lower_bound; j <= upper_bound; j++) {
                fields[i][j] = new NeutralField();
            }
        }
    }

}
