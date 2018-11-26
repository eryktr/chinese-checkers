package game;

import exceptions.IllegalNumberOfPlayersException;
import game.board.Board;
import game.board.piece.Piece;
import game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private Piece[] pieces;
    private List<Player> winners;
    private Board board;

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Game(final int numberOfPlayers) throws IllegalNumberOfPlayersException {
        players = new Player[numberOfPlayers];
        pieces = new Piece[10 * numberOfPlayers];
        winners = new ArrayList<>();
        board = new Board();
    }

    public boolean anybodyWon() {
        return false;
    }
}