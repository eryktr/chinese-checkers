package server.validation;

import game.Game;
import game.board.field.Field;
import game.board.piece.Piece;
import game.player.Player;

public class MoveValidator {
    private Game game;

    public MoveValidator(Game game) {
        this.game = game;
    }

    public boolean isValid(MoveDetails details) throws Exception {
        Field initialField = game.getFieldByCoordinates(details.getInitialRow(), details.getInitialDiagonal());
        Field destinationField = game.getFieldByCoordinates(details.getDestinationRow(), details.getDestinationDiagonal());
        if(initialField.isFree() || !destinationField.isFree()) {
            return false;
        }
        Piece activePiece = game.getPieceByField(initialField);
        if (activePiece.getPieceColor() != details.getPlayer().getColor()) {
            return false;
        }


        return false;
    }

    private boolean sourceOccupiedAndDestFree(Field source, Field dest) {
        return !source.isFree() && dest.isFree();
    }

    private boolean pieceColorMatches(Piece piece, Player player) {
        return piece.getPieceColor() == player.getColor();
    }

    private boolean destinationIsLegal(Field source, Field dest) {
        return moveIsStandard(source, dest) || moveIsJump(source, dest);
    }

    private boolean moveIsStandard(Field source, Field dest) {
        return dest.getRow() == source.getRow() && dest.getDiagonal() == source.getDiagonal() + 1
                || dest.getRow() == source.getRow() && dest.getDiagonal() == source.getDiagonal() - 1
                || dest.getRow() == source.getRow() + 1 && dest.getDiagonal() == source.getDiagonal()
                || dest.getRow() == source.getRow() - 1 && dest.getDiagonal() == source.getDiagonal()
                || dest.getRow() == source.getRow() + 1 && dest.getDiagonal() == source.getDiagonal() - 1
                || dest.getRow() == source.getRow() - 1 && dest.getDiagonal() == source.getDiagonal() + 1;
    }

    private boolean moveIsJump(Field source, Field dest) {
        return true;
    }
}
