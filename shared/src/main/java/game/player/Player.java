package game.player;

import game.board.field.FieldColor;

public interface Player {
    void makeMove();
    FieldColor getColor();
    FieldColor getEnemyColor();
}
