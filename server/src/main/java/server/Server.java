package server;

import game.Game;
import game.gamebuilder.ConcreteGameBuilder;
import game.gamebuilder.GameBuilder;
import game.gamesettings.GameSettings;
import server.communication.CommunicationData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket {
    private GameBuilder gameBuilder;

    public Server(int port) throws IOException {
        super(port);
        gameBuilder = new ConcreteGameBuilder();
        System.out.println("Server is up and running!");
    }

    public void listen() throws IOException {
        while (true) {
            Socket hostPlayer;
            BufferedReader hostInputStreamReader;
            PrintWriter hostOutputStreamWriter;
            CommunicationData communicationData = new CommunicationData();

            boolean hostConnected = false;


            if (!hostConnected) {
                System.out.println("Waiting for host");
                while (true) {
                    hostPlayer = accept();
                    System.out.println("Waiting for game settings...");
                    hostInputStreamReader = getPlayerInputStreamReader(hostPlayer);
                    String gameOptionsLine = hostInputStreamReader.readLine();
                    GameSettings settings = new GameSettings(gameOptionsLine);
                    Game game = gameBuilder.buildGame(settings);
                    final int number_of_players = settings.getNumberOfHumanPlayers();
                    communicationData.initializeFields(number_of_players);
                    communicationData.addPlayer(hostPlayer);
                }
            }
        }
    }

    private BufferedReader getPlayerInputStreamReader(Socket player) throws IOException {
        return new BufferedReader(new InputStreamReader(player.getInputStream()));
    }

    private PrintWriter getPlayerOutputStreamWriter(Socket player) throws IOException {
        return new PrintWriter(new OutputStreamWriter(player.getOutputStream()));
    }

    private void addPlayer(Socket playerSocket) {

    }


}