package game.player;

import game.board.field.FieldColor;
import game.board.piece.Piece;

public interface Player {
    void makeMove();
    FieldColor getColor();
    FieldColor getEnemyColor();
    void setPieces(Piece[] pieces);
}
