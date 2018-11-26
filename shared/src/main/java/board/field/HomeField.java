package board.field;

import player.FieldColor;

public class HomeField extends Field {
    public HomeField(FieldColor owner) {
        this.color = owner;
        this.status = FieldStatus.OCCUPIED;
    }
}
