package game;

import game.board.Board;
import game.board.field.FieldColor;
import game.board.piece.Piece;
import game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Player[] players;
    private Piece[] pieces;
    private List<Player> winners = new ArrayList<>();
    private Board board;

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Piece[] getPlayerPieces(FieldColor playerColor) {
        return (Piece [])Arrays.stream(pieces).filter(piece -> piece.getPieceColor() == playerColor).toArray();
    }

    public boolean anybodyWon() {
        return false;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    };

    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }
}