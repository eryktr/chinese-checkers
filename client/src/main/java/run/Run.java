package run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import client.Client;
import client.ConnectionCredentials;
import gui.EventForCreateButton;
import gui.EventForJoinButton;
import gui.ServerInfoStage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Run extends Application {
	private Client client;
	
    public static void main(String[] args) {
    	launch(args);
    	
        //Scanner scanner = new Scanner(System.in);
        /*Socket socket = new Socket("localhost", 8080);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ListenerThread lt = new ListenerThread(br, out);
        lt.start();*/

        //String string = scanner.nextLine();
        //out.println(string);
        //String line = br.readLine();
        //System.out.println(line);

        /*while (true) {
            String stringg = scanner.nextLine();
            out.println(stringg);
        }*/
    }
    
    @Override
	public void start(Stage startStage) throws IOException {
		ConnectionCredentials credentials = new ConnectionCredentials();
		ServerInfoStage infoStage = new ServerInfoStage(credentials);
		infoStage.showAndWait();
    	this.client = new Client(credentials.address, credentials.port);
		startStage.setTitle("Chinese checkers");
		
		Button createButton = new Button("Create");
		Button joinButton = new Button("Join");
		createButton.setMinSize(100, 20);
		joinButton.setMinSize(100, 20);
		createButton.setOnAction(new EventForCreateButton(startStage, client));
		joinButton.setOnAction(new EventForJoinButton(startStage, client));
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.add(createButton, 0, 0);
		gridPane.add(joinButton, 0, 1);
		
		Scene scene = new Scene(gridPane, 400, 300);
		startStage.setScene(scene);
		startStage.show();
	}
}

