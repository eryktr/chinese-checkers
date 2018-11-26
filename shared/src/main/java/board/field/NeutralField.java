package board.field;

import player.FieldColor;

public class NeutralField extends Field {
    public NeutralField() {
        color = FieldColor.NONE;
        status = FieldStatus.FREE;
    }
}
