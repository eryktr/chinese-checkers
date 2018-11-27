package game.player;

import game.board.field.FieldColor;

public final class HumanPlayer implements Player {
    public FieldColor homeColor;
    public FieldColor enemyColor;

    public HumanPlayer(FieldColor playerColor) {
        homeColor = playerColor;
        enemyColor = FieldColor.getEnemy(playerColor);
    }

    public void makeMove() {}
    public FieldColor getColor() {
        return homeColor;
    }
    public FieldColor getEnemyColor() {
        return enemyColor;
    }
}
