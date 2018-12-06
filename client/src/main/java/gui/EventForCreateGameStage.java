package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventForCreateGameStage implements EventHandler<ActionEvent> {
	private Stage stage;
	private TextField playersTextField;
	private TextField botsTextField;
	
	public EventForCreateGameStage(Stage stage, TextField playersTextField, 
			TextField botsTextField) {
		this.stage = stage;
		this.playersTextField = playersTextField;
		this.botsTextField = botsTextField;
	}
	
	private boolean checkNumbers() {
		int players;
		int bots;
		try {
			players = Integer.parseInt(playersTextField.getText());
			bots = Integer.parseInt(botsTextField.getText());
			
			if(players+bots != 2 && players+bots != 4 && players+bots != 5 && players+bots != 6) {
				return false;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void handle(ActionEvent event) {
		String string;
		if(this.checkNumbers() == true) {
			this.stage.close();
		}
		else {
			this.playersTextField.setText("Chinese checkers can be played by 2, 4, 5 or 6 players.");
			this.botsTextField.setText("Chinese checkers can be played by 2, 4, 5 or 6 players.");
		}
	}
}
