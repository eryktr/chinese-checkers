package gui;

import java.io.IOException;

import board.BoardStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EventForJoinButton implements EventHandler<ActionEvent> {
	private Stage stage;
	
	public EventForJoinButton(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		//TODO
		this.stage.close();
	}
}
