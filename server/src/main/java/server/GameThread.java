package server;

import game.Game;
import game.gamebuilder.ConcreteGameBuilder;
import game.gamebuilder.GameBuilder;
import game.gamesettings.GameSettings;
import game.player.Player;
import server.communication.CommunicationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class GameThread extends Thread {
    private boolean started = false;
    private boolean isOver = false;
    private CommunicationData communicationData = new CommunicationData();
    private Game game;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayerNumber;

    public GameThread(GameSettings settings, Socket host) throws IOException {
        communicationData.setUp(settings.getNumberOfHumanPlayers());
        communicationData.addPlayer(host);
        game = new Game(settings);
    }

    @Override
    public void run()  {

        Random rand = new Random();
        while(numberOfJoinedPlayers < game.getNumberOfHumanPlayers()) {
            System.out.println("Waiting for " + (game.getNumberOfHumanPlayers() - numberOfJoinedPlayers)  + "more players");
            try {
                wait(5000);
            } catch (InterruptedException ex) {}
        }
        game.setUp();
        started = true;
        currentPlayerNumber = rand.nextInt(game.getNumberOfPlayers());
        while(!isOver) {
            if(!game.getPlayerByNumber(currentPlayerNumber).isBot()) {
                BufferedReader playerInputReader = communicationData.getInputReaderByNumber(currentPlayerNumber);
                String moveLine;
                try {
                    moveLine = playerInputReader.readLine();
                }
                catch (IOException ex) {}
            }
        }

    }

    public void addPlayer(Socket player) throws IOException {
        communicationData.addPlayer(player);
        numberOfJoinedPlayers++;
    }

    public boolean hasStarted() {
        return started;
    }
}
