package game.board.field.fieldinitializer;

import game.board.field.Field;

public abstract class FieldInitializer {
    protected Field[][] fields;

    public void initializeFields() {
        initializeIllegalFields();
        initializeHexagonFields();
        initializePlayerHomeFields();
    }
    protected  void initializePlayerHomeFields() {
        initializeRedPlayerFields();
        initializeGreenPlayerFields();
        initializeBluePlayerFields();
        initializeYellowPlayerFields();
        initializePurplePlayerFields();
        initializeOrangePlayerFields();
    }
    protected abstract void initializeIllegalFields();
    protected abstract void initializeHexagonFields();

    protected abstract void initializeRedPlayerFields();
    protected abstract void initializeGreenPlayerFields();
    protected abstract void initializeBluePlayerFields();
    protected abstract void initializeYellowPlayerFields();
    protected abstract void initializePurplePlayerFields();
    protected abstract void initializeOrangePlayerFields();

}
