package board;

import client.Client;
import game.Game;
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

	public void move(Field newPosition, Client client) {
		client.sendOption(piece.getPosition().positionToString() + " " + newPosition.positionToString());
		//System.out.println(piece.getPosition().positionToString() + " " + newPosition.positionToString());
        this.setCoordinates(newPosition);
        piece.getPosition().setStatus(FieldStatus.FREE);
		newPosition.setStatus(FieldStatus.OCCUPIED);
		this.piece.setField(newPosition);
		
	}
	
	private boolean pieceOnEnemyField() {
		FieldColor enemyColor = FieldColor.getEnemy(getColor());
		if(getColor().equals(enemyColor))
			return true;
		return false;
	}
	
	private boolean leaveEnemyField(Field newPosition) {
		if(pieceOnEnemyField()) {
			FieldColor enemyColor = FieldColor.getEnemy(getColor());
			if(newPosition.getColor().equals(enemyColor))
				return false;
			return true;
		}
		else
			return false;
	}
	
	private boolean moveIsJump(Field newPosition, Game game) throws Exception {
		int row = piece.getPosition().getRow();
		int diagonal = piece.getPosition().getDiagonal();
		int newRow = newPosition.getRow();
		int newDiagonal = newPosition.getDiagonal();
	
		return (newRow==row-2 && newDiagonal==diagonal+2 && isOccupied(row-1, diagonal+1, game)) 
				|| (newRow==row && newDiagonal==diagonal+2 && isOccupied(row, diagonal+1, game))
				|| (newRow==row+2 && newDiagonal==diagonal && isOccupied(row+1, diagonal, game)) 
				|| (newRow==row+2 && newDiagonal==diagonal-2 && isOccupied(row+1, diagonal-1, game)) 
				|| (newRow==row && newDiagonal==diagonal-2 && isOccupied(row, diagonal-1, game)) 
				|| (newRow==row-2 && newDiagonal==diagonal && isOccupied(row-1, diagonal, game));
	}
	
	private boolean isOccupied(int row, int diagonal, Game game) throws Exception {
		return !game.getFieldByCoordinates(row, diagonal).isFree();
	}
	
	private boolean moveIsStandard(Field newPosition) {
		int row = piece.getPosition().getRow();
		int diagonal = piece.getPosition().getDiagonal();
		int newRow = newPosition.getRow();
		int newDiagonal = newPosition.getDiagonal();
		
		return (newRow==row-1 && newDiagonal==diagonal+1) || (newRow==row && newDiagonal==diagonal+1)
				|| (newRow==row+1 && newDiagonal==diagonal) || (newRow==row+1 && newDiagonal==diagonal-1) 
				|| (newRow==row && newDiagonal==diagonal-1) || (newRow==row-1 && newDiagonal==diagonal);
	}
	
	public boolean isMovePossible(Field newPosition, Game game) throws Exception {
		return (moveIsStandard(newPosition) || moveIsJump(newPosition, game))
				&& !leaveEnemyField(newPosition);
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

	@Override
	public FieldColor getColor() {
		return piece.getPieceColor();
	}
	
	public int getRow() {
		return this.piece.getPosition().getRow();
	}
	
	public int getDiagonal() {
		return this.piece.getPosition().getDiagonal();
	}
	
	public Piece getPiece() {
		return this.piece;
	}
}
