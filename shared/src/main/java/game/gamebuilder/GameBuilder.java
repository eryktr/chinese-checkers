package game.gamebuilder;

import game.Game;
import game.board.Board;
import game.board.piece.Piece;
import game.player.Player;

public interface GameBuilder {
    Game buildGame(int numberOfPlayers);
    Board buildBoard(int numberOfPlayers);
    Player[] buildPlayers(int numberOfPlayers);
    Piece[] buildPieces(int numberOfPlayers);
}
