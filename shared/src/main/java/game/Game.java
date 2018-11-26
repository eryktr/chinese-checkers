package game;

import game.board.Board;
import game.player.Player;

public class Game {
    private Player[] players;
    private Board board;

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
