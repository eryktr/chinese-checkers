package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import client.Client;
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
	private Client client;
	private boolean active;
	private List<PieceCircle> pieces;
	
	public BoardStage(Game game, int numberOfPlayer, Client client) {
		this.game = game;
		this.setResizable(false);
		this.activePiece = null;
		this.player = game.getPlayerByNumber(numberOfPlayer);
		this.client = client;
		this.active = false;
		this.pieces = new ArrayList<PieceCircle>();
		
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
		this.pieces.add(pieceCircle);
		group.getChildren().add(pieceCircle);
	}
	
	private boolean isMyElement(BoardElement element) {
		return element.getColor().equals(player.getColor());
	}
	
	private PieceCircle getPieceCircle(Piece piece) throws Exception {
		for(PieceCircle pieceCircle: this.pieces) {
			if(pieceCircle.getPiece() == piece)
				return pieceCircle;
		}
		throw new Exception("Piece doesn't exist");
	}
	
	public void activate() {
		this.active = true;
	}
	
	public void makeMove(String moveLine) throws Exception {
		//TODO
		String[] line = moveLine.split(" ");
		int playerNumber = Integer.parseInt(line[5]);
		
		if(game.getPlayerByNumber(playerNumber) != this.player) {
		int initialRow = Integer.parseInt(line[1]);
		int initialDiagonal = Integer.parseInt(line[2]);
		Piece piece = game.getPieceByField(game.getFieldByCoordinates(initialRow, initialDiagonal));
		int destRow = Integer.parseInt(line[3]);
		int destDiagonal = Integer.parseInt(line[4]);
		Field newPosition = game.getFieldByCoordinates(destRow, destDiagonal);
		this.getPieceCircle(piece).move(newPosition, client);
		}
	}

	@Override
	public void handle(MouseEvent event) {
		Object source = event.getSource();
		BoardElement element = (BoardElement) source;
		
		if(this.active == true && element.isPiece() && isMyElement(element)) {
			this.activePiece = (PieceCircle) element;
		}
		else if(element.isField() && this.active == true) {
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
					this.activePiece.move(newPosition, client);
					this.activePiece = null;
					this.active = false;
				}
			}
		}
	}
}
