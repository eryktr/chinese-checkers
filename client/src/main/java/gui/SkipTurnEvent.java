package gui;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SkipTurnEvent implements EventHandler<ActionEvent> {
	private Client client;
	private Stage stage;
	
	public SkipTurnEvent(Client client, Stage stage) {
		this.client = client;
		this.stage = stage;
	}
	@Override
	public void handle(ActionEvent event) {
		client.sendOption("skip");
		this.stage.close();
	}
}
