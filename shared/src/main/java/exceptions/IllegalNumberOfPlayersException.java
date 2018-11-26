package exceptions;

public class IllegalNumberOfPlayersException extends Exception {
    public IllegalNumberOfPlayersException() {
        super("Chinese checkers can be played by 2, 4 or 6 players.");
    }
}
