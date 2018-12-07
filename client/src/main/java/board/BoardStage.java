package board;

import game.Game;
import game.board.field.Field;
import game.board.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BoardStage extends Stage implements EventHandler<MouseEvent> {
	private Game game;
	private PieceCircle activePiece;
	
	public BoardStage(Game game) {
		this.game = game;
		this.setResizable(false);
		this.activePiece = null;
		
		drawBoard();
	}
	
	private void drawBoard() {
		Group group = new Group();
		
		for(Field field: this.game.getBoard().getFields()) {
			this.drawField(field, group);
		}
		
		for(Piece piece: this.game.getPieces()) {
			this.drawPiece(piece, group);
		}
		
		Scene scene = new Scene(group, group.prefWidth(0) * 2, group.prefHeight(0) + BoardData.fieldSize);
		this.setScene(scene);
	}
	
	private void drawField(Field field, Group group) {
		FieldCircle fieldCircle = new FieldCircle(field, this);
		group.getChildren().add(fieldCircle);
	}
	
	private void drawPiece(Piece piece, Group group) {
		PieceCircle pieceCircle = new PieceCircle(piece, this);
		group.getChildren().add(pieceCircle);
	}

	@Override
	public void handle(MouseEvent event) {
		//TODO
		Object source = event.getSource();
		BoardElement element = (BoardElement) source;
		
		if(element.isPiece()) {
			this.activePiece = (PieceCircle) element;
		}
		else {
			FieldCircle fieldCircle = (FieldCircle) element;
			Field newPosition = fieldCircle.getField();
			if(this.activePiece != null) {
				this.activePiece.move(newPosition);
				this.activePiece = null;
			}
		}
	}
}
