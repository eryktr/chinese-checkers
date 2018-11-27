package game.board.field.fieldinitializer;

import game.board.Board;
import game.board.field.Field;

public abstract class FieldInitializer {
    protected Field[][] fields;

    public void initializeFields() {
        initializeIllegalFields();
        initializeAvailableFields();
        initializePlayerHomeFields();
    }

    protected abstract void initializeIllegalFields();
    protected abstract void initializeAvailableFields();
    protected abstract void initializePlayerHomeFields();

}
