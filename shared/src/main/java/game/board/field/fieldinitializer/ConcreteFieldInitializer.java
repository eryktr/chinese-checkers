package game.board.field.fieldinitializer;

import game.board.Board;
import game.board.BoardInformation;
import game.board.field.IllegalField;

public class ConcreteFieldInitializer extends FieldInitializer {
    public ConcreteFieldInitializer(Board board) {
        this.fields = board.getFields();
    }


    @Override
    protected void initializeIllegalFields() {
        for(int i = 0; i < BoardInformation.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < BoardInformation.NUMBER_OF_DIAGONALS; j++) {
                fields[i][j] = new IllegalField();
            }
        }
    }

    @Override
    protected void initializeHexagonFields() {
        //TODO
    }

    @Override
    protected void initializeRedPlayerFields() {

    }

    @Override
    protected void initializeGreenPlayerFields() {

    }

    @Override
    protected void initializeBluePlayerFields() {

    }

    @Override
    protected void initializeYellowPlayerFields() {

    }

    @Override
    protected void initializePurplePlayerFields() {

    }

    @Override
    protected void initializeOrangePlayerFields() {

    }

}
