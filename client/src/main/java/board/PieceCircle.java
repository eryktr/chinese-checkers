package board;

import game.board.field.Field;
import game.board.field.FieldColor;
import game.board.field.FieldStatus;
import game.board.piece.Piece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PieceCircle extends Circle implements BoardElement {
	private Piece piece;
	
	public PieceCircle(Piece piece, BoardStage boardStage) {
		this.piece = piece;
		this.setRadius(BoardData.fieldSize);
		this.setColor();
		this.setCoordinates(this.piece.getPosition());
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, boardStage);
	}
	
	//TODO
	public void move(Field newPosition) {
        this.setCoordinates(newPosition);
        this.piece.setField(newPosition);
        piece.getPosition().setStatus(FieldStatus.FREE);
		newPosition.setStatus(FieldStatus.OCCUPIED);
	}
	
	private void setCoordinates(Field field) {
		this.setCenterX(this.calculateX(field));
		this.setCenterY(this.calculateY(field));
	}
	
	private double calculateX(Field field) {
		double r = getRadius();
		double d = BoardData.gapSize;
		double row = field.getRow();
		double diagonal = field.getDiagonal();
		return ((2*r +d)/2)*row + 
				((2*r+d)*(Math.sqrt(3)) / 2)* diagonal;
	}
	
	private double calculateY(Field field) {
		double r = getRadius();
		double d = BoardData.gapSize;
		double row = field.getRow();
		return r+(2*r+d)*row;
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
	}

	@Override
	public boolean isField() {
		return false;
	}

	@Override
	public boolean isPiece() {
		return true;
	}
}
