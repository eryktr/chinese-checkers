package board;

import game.board.Board;
import game.board.field.Field;
import board.BoardData;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardStage extends Stage {
	private Board board;
	
	public BoardStage() {
		this.board = new Board();
		this.setResizable(false);
		
		drawBoard();
	}
	
	private void drawBoard() {
		Group group = new Group();
		
		for(Field field: this.board.getFields()) {
			this.drawField(field, group);
		}
		
		Scene scene = new Scene(group, group.prefWidth(0) * 2, group.prefHeight(0) + 10);
		this.setScene(scene);
	}
	
	private void drawField(Field field, Group group) {
		FieldCircle fieldCircle = new FieldCircle(field, BoardData.fieldSize);
		group.getChildren().add(fieldCircle);
	}
}
