package game.player;

import game.board.field.FieldColor;
import game.board.piece.Piece;

public final class HumanPlayer implements Player {
    private FieldColor homeColor;
    private FieldColor enemyColor;
    private Piece[] pieces = new Piece[10];

    public HumanPlayer(FieldColor playerColor) {
        homeColor = playerColor;
        enemyColor = FieldColor.getEnemy(playerColor);
    }

    public void makeMove() {

    }
    public FieldColor getColor() {
        return homeColor;
    }
    public FieldColor getEnemyColor() {
        return enemyColor;
    }

    @Override
    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }
}
