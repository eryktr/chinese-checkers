package game.board;

import game.board.field.Field;
import game.board.field.fieldinitializer.ConcreteFieldInitializer;
import game.board.field.fieldinitializer.FieldInitializer;
import static game.board.BoardInformation.NUMBER_OF_ROWS;
import static game.board.BoardInformation.NUMBER_OF_DIAGONALS;

public class Board {

    private Field[][] fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_DIAGONALS];
    private FieldInitializer fieldInitializer;

    public Board() {
        fieldInitializer = new ConcreteFieldInitializer(this);
        initializeFields();
    }

    public void initializeFields() {
        fieldInitializer.initializeFields();
    }

    public Field[][] getFields() {
        return fields;
    }
}
