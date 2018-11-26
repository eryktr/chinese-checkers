package game.board.piece;

import game.board.field.Field;
import game.board.field.FieldColor;

public class Piece {
    private FieldColor pieceColor;
    private Field currentPosition;

    public Field getPosition() {
        return currentPosition;
    }

}
