package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import exceptions.IllegalNumberOfPlayersException;

public class EventForCreateGameStage implements EventHandler<ActionEvent> {
	private Stage stage;
	private TextField playersTextField;
	private TextField botsTextField;
	
	public EventForCreateGameStage(Stage stage, TextField playersTextField, TextField botsTextField) {
		this.stage = stage;
		this.playersTextField = playersTextField;
		this.botsTextField = botsTextField;
	}
	
	private int getNumber(TextField textField) throws IllegalNumberOfPlayersException {
		int number;
		try {
			number = Integer.parseInt(textField.getText());
			if(number!=2 && number!=4 && number!=5 && number!=6)
				throw new IllegalNumberOfPlayersException();
		}
		catch(NumberFormatException e) {
			throw new IllegalNumberOfPlayersException();
		}
		
		return number;
	}

	@Override
	public void handle(ActionEvent event) {
				int players;
				int bots;
				
				try {
					players = this.getNumber(this.playersTextField);
				}
				catch(IllegalNumberOfPlayersException e) {
					this.playersTextField.setText(e.getMessage());
				}
				
				try {
					bots = this.getNumber(this.botsTextField);
				}
				catch(IllegalNumberOfPlayersException e) {
					this.botsTextField.setText(e.getMessage());
				}
				
				this.stage.close();
	}
}
