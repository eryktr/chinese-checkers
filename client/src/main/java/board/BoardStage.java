package board;

import game.Game;
import game.board.field.Field;
import game.board.piece.Piece;
import game.player.Player;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BoardStage extends Stage implements EventHandler<MouseEvent> {
	private Game game;
	private Player player;
	private PieceCircle activePiece;
	
	public BoardStage(Game game, int numberOfPlayer) {
		this.game = game;
		this.setResizable(false);
		this.activePiece = null;
		this.player = game.getPlayerByNumber(numberOfPlayer);
		
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
	
	private boolean isMyElement(BoardElement element) {
		return element.getColor().equals(player.getColor());
	}

	@Override
	public void handle(MouseEvent event) {
		//TODO
		Object source = event.getSource();
		BoardElement element = (BoardElement) source;
		
		if(element.isPiece() && isMyElement(element)) {
			this.activePiece = (PieceCircle) element;
		}
		else if(element.isField()) {
			FieldCircle fieldCircle = (FieldCircle) element;
			Field newPosition = fieldCircle.getField();
			boolean isMovePossible = false;
			if(this.activePiece != null) {
				try {
					isMovePossible = activePiece.isMovePossible(newPosition,game);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(isMovePossible) {
					this.activePiece.move(newPosition);
					this.activePiece = null;
				}
			}
		}
	}
}
