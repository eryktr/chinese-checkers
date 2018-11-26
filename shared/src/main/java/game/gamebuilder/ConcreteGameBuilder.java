package game.gamebuilder;

import game.Game;
import game.board.Board;
import game.board.piece.Piece;
import game.player.Player;

public class ConcreteGameBuilder implements GameBuilder {

    @Override
    public Game buildGame(int numberOfPlayers) {
        Board board = buildBoard(numberOfPlayers);
        Player[] players = buildPlayers(numberOfPlayers);
        Piece[] pieces = buildPieces(numberOfPlayers);
        return null;
    }

    @Override
    public Board buildBoard(int numberOfPlayers) {
        return null;
    }

    @Override
    public Player[] buildPlayers(int numberOfPlayers) {
        return new Player[0];
    }

    @Override
    public Piece[] buildPieces(int numberOfPlayers) {
        return new Piece[0];
    }
}
