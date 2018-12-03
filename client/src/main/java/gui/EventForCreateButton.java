package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EventForCreateButton implements EventHandler<ActionEvent> {
	private Stage stage;
	
	public EventForCreateButton(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		this.stage.close();
		CreateGameStage newStage = new CreateGameStage();
		newStage.show();
	}
}
