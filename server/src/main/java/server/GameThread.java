package server;

import game.Game;
import game.board.field.Field;
import game.board.field.FieldColor;
import game.board.field.FieldStatus;
import game.board.piece.Piece;
import game.gamesettings.GameSettings;
import game.player.Player;
import server.communication.CommunicationData;
import server.validation.MoveDetails;
import server.validation.GameAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GameThread extends Thread {
    private boolean started = false;
    private boolean isOver = false;
    private CommunicationData communicationData = new CommunicationData();
    private Game game;
    private GameAnalyzer validator;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayerNumber;
    private boolean hasJumped = false;
    private Piece lastMovedPiece = null;
    private boolean skip = false;

    public GameThread(GameSettings settings, Socket host, BufferedReader br, PrintWriter pw) throws IOException {
        communicationData.setUp(settings.getNumberOfHumanPlayers());
        game = new Game(settings);
        //game.setUp();
        addPlayer(host, br, pw);
        validator = new GameAnalyzer(game);
    }

    @Override
    public void run()  {

        Random rand = new Random();
        while(numberOfJoinedPlayers < game.getNumberOfHumanPlayers()) {
            System.out.println("Waiting for " + (game.getNumberOfHumanPlayers() - numberOfJoinedPlayers)  + "more players");
            try {
                synchronized(this) {
                    wait(5000);
                }
            }
            catch (InterruptedException ex) {}
        }
        communicationData.sendMessageToAllPlayers("Game started");
        game.setUp();
        started = true;
        currentPlayerNumber = rand.nextInt(game.getNumberOfPlayers());
        while (!isOver) {
            if(!game.getPlayerByNumber(currentPlayerNumber).isBot()) {
                try {
                	if(!this.isWinner(currentPlayerNumber)) {
                		MoveDetails newMoveDetails = listenForMove();
                		makeMove(newMoveDetails);
                	}
                    endMove();
                }
                catch (Exception ex) {
                    System.out.println("Making move error");
                    ex.printStackTrace();
                }
            }
            else {
            	//obecny gracz jest botem
            	try {
            		if(!this.isWinner(currentPlayerNumber)) {
            			Player botPlayer = game.getPlayerByNumber(currentPlayerNumber);
            			boolean isMoveLegal = false;
            			MoveDetails moveDetails;
            			do {
            				moveDetails = validator.makeBotsMove(botPlayer);
            				isMoveLegal = validator.isValid(moveDetails, hasJumped, lastMovedPiece);
            			}
            			while(!isMoveLegal);
            			
            			makeMove(moveDetails);
            			endMove();
            		}
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
            }
        }
    }

    private MoveDetails listenForMove() throws Exception {
        boolean isMoveLegal = false;
        MoveDetails details;
        System.out.println("Waiting for move of player "+currentPlayerNumber);
        BufferedReader playerInputReader = communicationData.getInputReaderByNumber(currentPlayerNumber);
        PrintWriter playerPrinrWriter = communicationData.getPrintWriterByNumber(currentPlayerNumber);
        playerPrinrWriter.println("Your turn.");
        
        /*if(lastMovedPiece != null)
        this.communicationData.sendMessageToAllPlayers("lastMovedPiece " + this.lastMovedPiece.positionToString());
        else
        	this.communicationData.sendMessageToAllPlayers("lastMovedPiece is null");*/
        
        Player currentPlayer = game.getPlayerByNumber(currentPlayerNumber);
        do {
            String moveLine = playerInputReader.readLine();
            
            if(moveLine.contains("skip")) {
            	this.skip = true;
            	return null;
            }
            details = new MoveDetails(currentPlayer, moveLine);
            isMoveLegal = validator.isValid(details, hasJumped, lastMovedPiece);
        } while (!isMoveLegal);
        return details;
    }

    private void makeMove(MoveDetails details) throws Exception {
    	if(details != null) {
    	
            int initialRow = details.getInitialRow();
            int initialDiagonal = details.getInitialDiagonal();
            int destinationRow = details.getDestinationRow();
            int destinationDiagonal = details.getDestinationDiagonal();

            Field initialField = game.getFieldByCoordinates(initialRow, initialDiagonal);
            Field destinationField = game.getFieldByCoordinates(destinationRow, destinationDiagonal);
            /*System.out.println("initialField: " + initialField.positionToString());
            for(Piece piece: game.getPieces()) {
                System.out.println(piece.getPosition().positionToString());
            }*/
            Piece targetPiece = game.getPieceByField(initialField);
            
            /*if(targetPiece != null)
                this.communicationData.sendMessageToAllPlayers("targetPiece " + targetPiece.positionToString());
                else
                	this.communicationData.sendMessageToAllPlayers("targetPiece is null");*/

            initialField.setStatus(FieldStatus.FREE);
            targetPiece.setField(destinationField);
            destinationField.setStatus(FieldStatus.OCCUPIED);
            this.communicationData.sendMessageToAllPlayers("move: " + details.moveToString() + " " + currentPlayerNumber);

            lastMovedPiece = targetPiece;
            hasJumped = validator.moveIsJump(initialField, destinationField);
    	}
    }
    
    private boolean isWinner(int number) {
    	Player player = this.game.getPlayerByNumber(number);
    	return this.game.isWinner(player);
    }

    public void addPlayer(Socket player, BufferedReader br, PrintWriter pw) throws IOException {
        communicationData.addPlayer(player, br, pw);
        GameSettings gameSettings = this.game.getSettings();
        pw.println(gameSettings.toString());
        numberOfJoinedPlayers++;
    }

    public boolean hasStarted() {
        return started;
    }
    
    public void endMove() throws Exception {
    	if(lastMovedPiece == null) {
    		
    		if(this.hasFinished(currentPlayerNumber) && !this.isWinner(currentPlayerNumber)) {
            	this.addWinner(currentPlayerNumber);
            	this.communicationData.sendMessageToAllPlayers("winner " + currentPlayerNumber);
            }
            if(this.over())
            	this.isOver = true;
    		
    		currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
    		hasJumped = false;
    	}
    	else if (!hasPossibleJumps(lastMovedPiece)) {
            hasJumped = false;
            lastMovedPiece = null;
            
            if(this.hasFinished(currentPlayerNumber) && !this.isWinner(currentPlayerNumber)) {
            	this.addWinner(currentPlayerNumber);
            	this.communicationData.sendMessageToAllPlayers("winner " + currentPlayerNumber);
            }
            if(this.over())
            	this.isOver = true;
            
            currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
        }
    	else {
    		if(this.skip == true) {
    			
    			if(this.hasFinished(currentPlayerNumber) && !this.isWinner(currentPlayerNumber)) {
                	this.addWinner(currentPlayerNumber);
                	this.communicationData.sendMessageToAllPlayers("winner " + currentPlayerNumber);
                }
                if(this.over())
                	this.isOver = true;
    			
    			currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
    			hasJumped = false;
    			lastMovedPiece = null;
    			skip = false;
    		}
    	}
    }

    private boolean hasPossibleJumps(Piece piece) throws Exception {//dodałam && hasJumped
        return validator.hasPossibleJumps(piece) && hasJumped;
    }
    
    private boolean over() {
    	/*for(int number=0; number<this.game.getNumberOfPlayers(); number++) {
    		if(!this.validator.hasFinished(this.game.getPlayerByNumber(number))) {
    			return false;
    		}
    	}
    	return true;*/
    	
    	return this.game.getNumberOfPlayers() - 1 == this.game.getNumberOfWinners();
    }
    
    private boolean hasFinished(int number) {
    	return this.validator.hasFinished(this.game.getPlayerByNumber(number));
    }
    
    private void addWinner(int number) {
    	this.game.addWinner(this.game.getPlayerByNumber(number));
    }
}