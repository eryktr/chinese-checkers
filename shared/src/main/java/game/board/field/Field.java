package game.board.field;

public abstract class Field {
    protected FieldStatus status;
    protected FieldColor color;


    public boolean isFree() {
        return status == FieldStatus.FREE;
    }

    public boolean isHomeField() {
        return !color.equals(FieldColor.NONE);
    }

    public boolean isLegal() {
        return status != FieldStatus.ILLEGAL;
    }

    public FieldColor getColor() {
        return color;
    }



}
