package game.board.field.fieldinitializer;

import game.board.field.Field;

public abstract class FieldInitializer {
    protected Field[][] fields;

    public void initializeFields() {
        initializeIllegalFields();
        initializeHexagonFields();
        initializePlayerHomeFields();
    }

    protected abstract void initializePlayerHomeFields();

    protected abstract void initializeIllegalFields();

    protected abstract void initializeHexagonFields();

}
