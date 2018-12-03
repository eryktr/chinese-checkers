package gui;

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
		this.stage.close();
		WaitingStage newStage = new WaitingStage();
		newStage.show();
	}
}
