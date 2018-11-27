package game;

import exceptions.IllegalNumberOfPlayersException;
import game.board.Board;
import game.board.piece.Piece;
import game.gamebuilder.ConcreteGameBuilder;
import game.gamebuilder.GameBuilder;
import game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private Piece[] pieces;
    private List<Player> winners;
    private Board board;
    private GameBuilder gameBuilder;

    public Game(final int numberOfPlayers) throws IllegalNumberOfPlayersException {
        gameBuilder = new ConcreteGameBuilder();
        players = new Player[numberOfPlayers];
        pieces = new Piece[10 * numberOfPlayers];
        winners = new ArrayList<>();
        board = new Board();
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public boolean anybodyWon() {
        return false;
    }
}