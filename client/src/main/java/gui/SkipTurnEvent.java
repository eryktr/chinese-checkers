package gui;

import board.BoardStage;
import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SkipTurnEvent implements EventHandler<ActionEvent> {
	private Client client;
	private BoardStage boardStage;
	
	public SkipTurnEvent(Client client, BoardStage stage) {
		this.client = client;
		this.boardStage = stage;
	}
	@Override
	public void handle(ActionEvent event) {
		client.sendOption("skip");
		boardStage.setLabel("Wait for your turn...");
	}
}
