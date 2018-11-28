package game.gamebuilder;

import game.Game;
import game.board.Board;
import game.board.field.Field;
import game.board.field.FieldColor;
import game.board.piece.Piece;
import game.player.HumanPlayer;
import game.player.Player;

public class ConcreteGameBuilder implements GameBuilder {

    private Game game;
    private Board board;
    private Player[] players;
    private Piece[] pieces;

    @Override
    public Game buildGame(final int numberOfPlayers) {
        game = new Game();
        board = buildBoard();
        players = buildPlayers(numberOfPlayers);
        pieces = buildPieces(numberOfPlayers);
        game.setBoard(board);
        game.setPlayers(players);
        game.setPieces(pieces);
        return game;
    }

    private Board buildBoard() {
        Board board = new Board();
        return board;
    }

    private Player[] buildPlayers(final int numberOfPlayers) {
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new HumanPlayer(FieldColor.fromNumber(i));
        }
        return players;
    }

    private Piece[] buildPieces(final int numberOfPlayers) {
        Piece[] pieces = new Piece[10 * numberOfPlayers];
        int currentCounter = 0;
        for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
            FieldColor currentPlayerColor = FieldColor.fromNumber(playerNumber);
            for (Field field : board.getFields(currentPlayerColor) ) {
                pieces[currentCounter] = new Piece(field, currentPlayerColor);
                currentCounter++;
            }
        }
        return pieces;
    }
}
