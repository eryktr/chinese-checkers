package server.validation;

import game.Game;
import game.board.field.Field;
import game.board.piece.Piece;
import game.player.Player;

public class GameAnalyzer {
    private Game game;

    public GameAnalyzer(Game game) {
        this.game = game;
    }

    public boolean isValid(MoveDetails details, boolean hasJumped) throws Exception {
        Field initialField = game.getFieldByCoordinates(details.getInitialRow(), details.getInitialDiagonal());
        Field destinationField = game.getFieldByCoordinates(details.getDestinationRow(), details.getDestinationDiagonal());
        if(!sourceOccupiedAndDestFree(initialField, destinationField))
            return false;
        Piece activePiece = game.getPieceByField(initialField);
        if (!pieceColorMatches(activePiece, details.getPlayer()))
            return false;
        if (!hasJumped)
            return destinationIsLegal(initialField, destinationField);
        else
            return moveIsJump(initialField, destinationField);
    }

    private boolean sourceOccupiedAndDestFree(Field source, Field dest) {
        return !source.isFree() && dest.isFree();
    }

    private boolean pieceColorMatches(Piece piece, Player player) {
        return piece.getPieceColor() == player.getColor();
    }

    private boolean destinationIsLegal(Field source, Field dest) throws Exception {
        return moveIsStandard(source, dest) || moveIsJump(source, dest);
    }

    private boolean moveIsStandard(Field source, Field dest) {
        return     dest.getRow() == source.getRow() && dest.getDiagonal() == source.getDiagonal() + 1
                || dest.getRow() == source.getRow() && dest.getDiagonal() == source.getDiagonal() - 1
                || dest.getRow() == source.getRow() + 1 && dest.getDiagonal() == source.getDiagonal()
                || dest.getRow() == source.getRow() - 1 && dest.getDiagonal() == source.getDiagonal()
                || dest.getRow() == source.getRow() + 1 && dest.getDiagonal() == source.getDiagonal() - 1
                || dest.getRow() == source.getRow() - 1 && dest.getDiagonal() == source.getDiagonal() + 1;
    }

    public boolean moveIsJump(Field source, Field dest) throws Exception {
        int sr = source.getRow();
        int sd = source.getDiagonal();
        int dr = dest.getRow();
        int dd = dest.getDiagonal();

        return     (dr == sr) && (dd == sd + 2) && !field(sr, sd +1).isFree()
                || (dr == sr) && (dd == sd -2) && !field(sr, sd -1).isFree()
                || (dr == sr + 2) && dd == sd && !field(sr + 1, sd).isFree()
                || (dr == sr - 2) && dd == sd && !field(sr -1, sd).isFree()
                || (dr == sr +2) && dd == sd-2 && !field(sr + 1, sd - 1).isFree()
                || (dr == sr - 2) && dd == sd + 2 && !field(sr - 1, sd + 1).isFree();
    }

    public boolean hasPossibleJumps(Piece targetPiece) throws Exception {
        Field position = targetPiece.getPosition();
        int row = position.getRow();
        int diagonal = position.getDiagonal();

        return     !field(row, diagonal + 1).isFree() && field(row, diagonal + 2).isFree()
                || !field(row, diagonal - 1).isFree() && field(row, diagonal -2).isFree()
                || !field(row + 1, diagonal).isFree() && field(row + 2, diagonal).isFree()
                || !field(row - 1, diagonal).isFree() && field(row - 2, diagonal).isFree()
                || !field(row -1, diagonal +1).isFree() && field(row -2, diagonal+2).isFree()
                || !field(row + 1, diagonal - 1).isFree() && field(row + 2, diagonal - 2).isFree();
    }

    private Field field(int row, int diagonal) throws Exception {
        return game.getFieldByCoordinates(row, diagonal);
    }
}