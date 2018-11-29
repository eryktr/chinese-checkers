package server.communication;

import java.io.*;
import java.net.Socket;

public final class CommunicationData {
    private Socket[] playerSockets;
    private BufferedReader[] playerInputReaders;
    private PrintWriter[] playerOutputWriters;
    private boolean[] playersConnected;
    int currentNumberOfPlayers = 0;

    public CommunicationData() {

    }

    public void setUp(final int numberOfHumanPlayers) {
        playerSockets = new Socket[numberOfHumanPlayers];
        playerInputReaders = new BufferedReader[numberOfHumanPlayers];
        playerOutputWriters = new PrintWriter[numberOfHumanPlayers];
        playersConnected = new boolean[numberOfHumanPlayers];
    }

    public void addPlayer(Socket playerSocket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
        playerSockets[currentNumberOfPlayers] = playerSocket;
        playerInputReaders[currentNumberOfPlayers] = bufferedReader;
        playerOutputWriters[currentNumberOfPlayers] = printWriter;
        playersConnected[currentNumberOfPlayers] = true;
        currentNumberOfPlayers++;
    }


}
