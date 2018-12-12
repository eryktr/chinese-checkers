package server;

import game.Game;
import game.board.field.Field;
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
    private Piece lastMovedPiece;

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
                    MoveDetails newMoveDetails = listenForMove();
                    makeMove(newMoveDetails);
                    endMove(newMoveDetails);
                }
                catch (Exception ex) {
                    System.out.println("Making move error");
                    ex.printStackTrace();
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
        Player currentPlayer = game.getPlayerByNumber(currentPlayerNumber);
        do {
            String moveLine = playerInputReader.readLine();
            //playerPrinrWriter.println("move: " + moveLine + " " + currentPlayerNumber);
            //System.out.println("move: " + moveLine + " " + currentPlayerNumber);
            //this.communicationData.sendMessageToAllPlayers("move: " + moveLine + " " + currentPlayerNumber);
            details = new MoveDetails(currentPlayer, moveLine);
            isMoveLegal = validator.isValid(details, hasJumped);
            //makeMove(details);
        } while (!isMoveLegal);
        return details;
    }

    private void makeMove(MoveDetails details) throws Exception {
        int initialRow = details.getInitialRow();
        int initialDiagonal = details.getInitialDiagonal();
        int destinationRow = details.getDestinationRow();
        int destinationDiagonal = details.getDestinationDiagonal();

        Field initialField = game.getFieldByCoordinates(initialRow, initialDiagonal);
        Field destinationField = game.getFieldByCoordinates(destinationRow, destinationDiagonal);
        System.out.println("initialField: " + initialField.positionToString());
        for(Piece piece: game.getPieces()) {
        	System.out.println(piece.getPosition().positionToString());
        }
        Piece targetPiece = game.getPieceByField(initialField);

        initialField.setStatus(FieldStatus.FREE);
        targetPiece.setField(destinationField);
        destinationField.setStatus(FieldStatus.OCCUPIED);
        this.communicationData.sendMessageToAllPlayers("move: " + details.moveToString() + " " + currentPlayerNumber);

        lastMovedPiece = targetPiece;
        hasJumped = validator.moveIsJump(initialField, destinationField);
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

    public void endMove(MoveDetails details) throws Exception {
        if (!hasPossibleJumps(lastMovedPiece)) {
            hasJumped = false;
            //this.communicationData.sendMessageToAllPlayers("move: " + details.moveToString() + " " + currentPlayerNumber);
            currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
        }
    }

    private boolean hasPossibleJumps(Piece piece) throws Exception {//dodałam && hasJumped
        return validator.hasPossibleJumps(piece) && hasJumped;
    }
}