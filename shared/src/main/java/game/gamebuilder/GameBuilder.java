package game.gamebuilder;

import game.Game;
import game.board.Board;
import game.board.piece.Piece;
import game.gamesettings.GameSettings;
import game.player.Player;

public interface GameBuilder {
    Game buildGame(GameSettings settings);
}
