package board;

import game.board.field.FieldColor;
import game.board.piece.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PieceCircle extends Circle {
	//TODO
	private Piece piece;
	
	public PieceCircle(Piece piece) {
		this.piece = piece;
		this.setRadius(BoardData.fieldSize);
	}
	
	private void setColor() {
		FieldColor color = this.piece.getPieceColor();
		
		if(color == FieldColor.BLUE)
			this.setFill(Color.BLUE);
		else if(color == FieldColor.GREEN)
			this.setFill(Color.GREEN);
		else if(color == FieldColor.ORANGE)
			this.setFill(Color.ORANGE);
		else if(color == FieldColor.PURPLE)
			this.setFill(Color.PURPLE);
		else if(color == FieldColor.RED)
			this.setFill(Color.RED);
		else if(color == FieldColor.YELLOW)
			this.setFill(Color.YELLOW);
		else if(color == FieldColor.NONE)
			this.setFill(Color.LIGHTGRAY);
	}
}
