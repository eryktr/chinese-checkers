package game.board.field;

public class IllegalField extends Field {
    public IllegalField() {
        color= FieldColor.NONE;
        status=FieldStatus.ILLEGAL;
    }
}
