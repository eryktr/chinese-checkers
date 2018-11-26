package game.board.field;

public class NeutralField extends Field {
    public NeutralField() {
        color = FieldColor.NONE;
        status = FieldStatus.FREE;
    }
}
