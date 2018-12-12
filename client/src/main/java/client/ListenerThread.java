package client;

import java.io.BufferedReader;

import board.BoardStage;
import game.Game;
import game.board.piece.Piece;
import game.gamesettings.GameSettings;
import gui.InformationStage;
import gui.YourTurnStage;
import javafx.application.Platform;

public class ListenerThread extends Thread {
    private BufferedReader br;
    private Client client;
    private int playerNumber;
    private Game game;
    private BoardStage boardStage;

    public ListenerThread(BufferedReader br, Client client) {
        this.br = br;
        this.client = client;
    }

    public void run() {
        while(true) {
            try {
                String currentLine;
                if(br.ready() && (currentLine = br.readLine() ) != null) {
                    System.out.println(currentLine);
                    
                    if(isNumber(currentLine)) {
                    	this.playerNumber = Integer.parseInt(currentLine);
                    	System.out.println("I got number "+playerNumber);
                    }
                    
                    else if(isGameSettings(currentLine)) {
                    	System.out.println("I got settings "+currentLine);
                    	this.game = new Game(new GameSettings(currentLine));
                    	game.setUp();
                    }
                    
                    else if(currentLine.contains("Game started")) {
                    	System.out.println("I got game started");
                    	Platform.runLater(()->{
                    		client.closePreviousStage();
                    		
                    		for(Piece piece: game.getPieces()) {
                            	System.out.println(piece.getPosition().positionToString());
                            }
                    		
                    		boardStage = new BoardStage(this.game, this.playerNumber, this.client);
                    		boardStage.show();
                    	});
                    }
                    
                    else if(currentLine.contains("No game found")) {
                    	Platform.runLater(()->{
                    		InformationStage newStage = new InformationStage("Game not found");
                        	newStage.show();
                    	});
                    }
                    
                    else if(currentLine.contains("move")) {
                    	Platform.runLater(()->{
                    		try {
								this.boardStage.makeMove(currentLine);
							} catch (Exception e) {
								e.printStackTrace();
							}
                    	});
                    }
                    
                    else if(currentLine.contains("Your turn.")) {
                    	Platform.runLater(()->{         		
                    		boardStage.activate();
                    		YourTurnStage yourTurnStage = new YourTurnStage(client);
                    		yourTurnStage.show();
                    	});
                    }
                }

            } catch (Exception e) {}
        }
    }
    
    private boolean isNumber(String line) {
    	try {
    		Integer.parseInt(line);
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
    
    private boolean isGameSettings(String line) {
    	if(isNumber(line.substring(0, 1)) && isNumber(line.substring(2, 3)) && line.charAt(1)==' ') {
    		return true;
    	}
    	else
    		return false;
    }
}
