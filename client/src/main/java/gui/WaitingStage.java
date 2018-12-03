package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WaitingStage extends Stage {
	public WaitingStage() {
		this.setTitle("Chinese checkers");
		Label waitLabel = new Label("Waiting");
		waitLabel.setFont(new Font(20));
		StackPane root = new StackPane();
        root.getChildren().add(waitLabel);
        Scene scene = new Scene(root, 400, 300);
        this.setScene(scene);
	}
}
