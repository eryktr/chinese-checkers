package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket {
    public Server(int port) throws IOException
    {

        super(port);
        System.out.println("Server is up and running!");
    }

    public void listen() throws IOException {
        Socket hostPlayer;
        BufferedReader hostInputStreamReader;
        PrintWriter hostOutputStreamWriter;
        Socket[] playerSockets;
        BufferedReader[] playerInputStreamReaders;
        PrintWriter[] playerOutputStreamWriters;
        boolean hostConnected = false;
        boolean[] playersConnected;

        if (!hostConnected) {
            System.out.println("Waiting for host");
            while (true) {
                hostPlayer = accept();
                System.out.println("Waiting for game settings...");
                hostInputStreamReader = getPlayerInputStreamReader(hostPlayer);
                String gameOptionsLine = hostInputStreamReader.readLine();
            }
        }
    }

    private BufferedReader getPlayerInputStreamReader(Socket player) throws IOException {
        return new BufferedReader(new InputStreamReader(player.getInputStream()));
    }

    private PrintWriter getPlayerOutputStreamWriter(Socket player) throws IOException {
        return new PrintWriter(new OutputStreamWriter(player.getOutputStream()));
    }
}