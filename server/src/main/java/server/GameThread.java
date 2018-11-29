package server;

import game.Game;
import game.gamebuilder.ConcreteGameBuilder;
import game.gamebuilder.GameBuilder;
import game.gamesettings.GameSettings;
import server.communication.CommunicationData;
import java.io.IOException;
import java.net.Socket;

public class GameThread extends Thread {
    private boolean started = false;
    private CommunicationData communicationData = new CommunicationData();
    private GameBuilder gameBuilder = new ConcreteGameBuilder();
    private Game game;
    private GameSettings gameSettings;
    private int numberOfHumanPlayers;
    private int numberofBotPlayers;
    private int numberOfJoinedPlayers = 0;

    public GameThread(GameSettings settings, Socket host) throws IOException {
        numberOfHumanPlayers = settings.getNumberOfHumanPlayers();
        numberofBotPlayers = settings.getNumberOfBots();
        communicationData.setUp(numberOfHumanPlayers);
        communicationData.addPlayer(host);
    }

    @Override
    public void run() {
        while(numberOfJoinedPlayers < numberOfHumanPlayers) {
            //waiting
        }
        game = gameBuilder.buildGame(gameSettings);

    }

    public void addPlayer(Socket player) throws IOException {
        communicationData.addPlayer(player);
        numberOfJoinedPlayers++;
    }

    public boolean hasStarted() {
        return started;
    }
}
