package server;

import game.gamesettings.GameSettings;
import server.exceptions.GameNotFoundException;

import javax.naming.CommunicationException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Server extends ServerSocket {
    private boolean serverRunning = true;
    private List<GameThread> games = new ArrayList<>();

    public Server(int port) throws IOException {
        super(port);
    }

    public void listen() throws IOException, CommunicationException, GameNotFoundException {
        while (serverRunning) {
            Socket newPlayer = accept();
            System.out.println("Hello!");
            BufferedReader newPlayerInputReader = getPlayerInputStreamReader(newPlayer);
            System.out.println("Waiting for player type");
            String joinerType = newPlayerInputReader.readLine();
            System.out.println("Processing...");
            processJoinerType(joinerType, newPlayer);
        }
    }

    private void processJoinerType(String joinerType, Socket player) throws CommunicationException, IOException, GameNotFoundException {
        BufferedReader hostInputReader = getPlayerInputStreamReader(player);
        PrintWriter hostOutoutWriter = getPlayerOutputStreamWriter(player);
        if (joinerType.equals("host")) {
            System.out.println("I got here");
            String line = "You are host";
            hostOutoutWriter.println(line);
            GameSettings settings = setUpGame(hostInputReader);
            GameThread newGame = new GameThread(settings, player);
            games.add(newGame);
            newGame.start();
        }
        else if(joinerType.equals("join")) {
            GameThread possibleGame = findOpenGame();
            possibleGame.addPlayer(player);
        }
    }

    private GameThread findOpenGame() throws GameNotFoundException {
        Optional<GameThread> possibleGame = games.stream().filter(game -> !game.hasStarted())
                .findFirst();
        if(possibleGame.isPresent()) {
            return possibleGame.get();
        }
        else {
            throw new GameNotFoundException();
        }
    }

    private GameSettings setUpGame(BufferedReader hostInputReader) throws IOException {
        String gameOptionsLine = hostInputReader.readLine();
        return new GameSettings(gameOptionsLine);
    }

    private BufferedReader getPlayerInputStreamReader(Socket player) throws IOException {
        return new BufferedReader(new InputStreamReader(player.getInputStream()));
    }

    private PrintWriter getPlayerOutputStreamWriter(Socket player) throws IOException {
        return new PrintWriter(new OutputStreamWriter(player.getOutputStream()), true);
    }
}