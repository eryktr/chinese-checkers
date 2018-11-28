package game.gamebuilder;

import game.Game;
import game.board.Board;
import game.board.field.Field;
import game.board.field.FieldColor;
import game.board.piece.Piece;
import game.gamesettings.GameSettings;
import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;

public class ConcreteGameBuilder implements GameBuilder {

    private Game game;
    private Board board;
    private Player[] players;
    private Piece[] pieces;

    private enum PlayerType {HUMAN, BOT}

    ;

    @Override
    public Game buildGame(final int numberOfHumanPlayers, final int numberOfBots) {
        game = new Game();
        board = buildBoard();
        pieces = buildPieces(numberOfHumanPlayers);
        players = buildPlayers(numberOfHumanPlayers, numberOfBots);
        game.setBoard(board);
        game.setPlayers(players);
        game.setPieces(pieces);
        return game;
    }

    public Game buildGame(GameSettings settings) {
        final int numberOfHumanPlayers = settings.getNumberOfHumanPlayers();
        final int numberOfBotPlayers = settings.getNumberOfBots();
        return buildGame(numberOfHumanPlayers, numberOfBotPlayers);
    }

    private Board buildBoard() {
        Board board = new Board();
        return board;
    }

    private Piece[] buildPieces(final int numberOfPlayers) {
        Piece[] pieces = new Piece[10 * numberOfPlayers];
        int currentCounter = 0;
        for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
            FieldColor currentPlayerColor = FieldColor.fromNumber(playerNumber);
            for (Field field : board.getFields(currentPlayerColor)) {
                pieces[currentCounter] = new Piece(field, currentPlayerColor);
                currentCounter++;
            }
        }
        return pieces;
    }

    private Player[] buildPlayers(final int numberOfHumanPlayers, final int numberOfBots) {
        final int numberOfPlayers = numberOfBots + numberOfHumanPlayers;
        Player[] players = new Player[numberOfPlayers];
        initializeHumanPlayers(numberOfHumanPlayers);
        initializeBotPlayers(numberOfBots, numberOfHumanPlayers);
        return players;
    }

    private void initializeHumanPlayers(final int numberOfHumanPlayers) {
        for (int i = 0; i < numberOfHumanPlayers; i++) {
            initializePlayer(PlayerType.HUMAN, i, numberOfHumanPlayers);
        }
    }

    private void initializeBotPlayers(final int numberOfBotPlayers, final int numberOfHumanPlayers) {
        for (int i = 0; i < numberOfHumanPlayers; i++) {
            initializePlayer(PlayerType.BOT, i, numberOfHumanPlayers);
        }
    }

    private void initializePlayer(PlayerType type, int playerNumber, int numberOfHumanPlayers) {
        FieldColor newPlayerColor = FieldColor.fromNumber(playerNumber);
        Player newPlayer = null;
        switch (type) {
            case HUMAN:
                newPlayer = new HumanPlayer(newPlayerColor);
                players[playerNumber] = newPlayer;
                break;
            case BOT:
                newPlayer = new BotPlayer(newPlayerColor);
                players[playerNumber + numberOfHumanPlayers] = newPlayer;
                break;
        }
        Piece[] newPlayerPieces = game.getPlayerPieces(newPlayerColor);
        newPlayer.setPieces(newPlayerPieces);
    }
}
