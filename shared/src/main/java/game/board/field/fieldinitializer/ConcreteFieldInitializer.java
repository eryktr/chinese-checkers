package game.board.field.fieldinitializer;

import game.board.Board;
import game.board.field.IllegalField;

public class ConcreteFieldInitializer extends FieldInitializer {
    public ConcreteFieldInitializer(Board board) {
        this.fields = board.getFields();
    }


    @Override
    protected void initializeIllegalFields() {
        for(int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < Board.NUMBER_OF_DIAGONALS; j++) {
                fields[i][j] = new IllegalField();
            }
        }
    }

    @Override
    protected void initializeAvailableFields() {
        //TODO
    }

    @Override
    protected void initializePlayerHomeFields() {
        //TODO
    }
}
