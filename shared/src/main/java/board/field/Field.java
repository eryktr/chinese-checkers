package board.field;

import player.FieldColor;

public abstract class Field {
    protected FieldStatus status;
    protected FieldColor color;


    public boolean isFree() {
        return status == FieldStatus.FREE;
    }

    public boolean isHomeField() {
        return !color.equals(FieldColor.NONE);
    }

    public boolean isPartOfBoard() {
        return status != FieldStatus.UNAVAILABLE;
    }

    public FieldColor getColor() {
        return color;
    }



}
