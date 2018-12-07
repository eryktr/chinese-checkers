package board;

import game.board.field.FieldColor;

public interface BoardElement {
	public boolean isField();
	public boolean isPiece();
	public FieldColor getColor();
}
