package Model;

public class Model {

    private final char EMPTY = '⌗';

    private final char PLAYER_1 = '■';
    private final char PLAYER_2 = '□';

    private char[] board;


    private int indexCounter = 0;

    private PlayerTurn turn;

    private GameState gameState;

    private int moveCount;


    public static void main(String[] args) {
        var game = new Model();
    }

    Model() {
        board = new char[24];
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
        turn = PlayerTurn.WHITE;
    }

    public void newGame() {
        board = new char[]{
                board[0], board[1], board[2],                                        //first row
                board[8], board[9], board[10],                                                //second row
                board[16], board[17], board[18],                                                          //third row
                board[7], board[15], board[23], board[19], board[11], board[3],                                        //middle row
                board[22], board[21], board[20],                                                          //third row
                board[14], board[13], board[12],                                                //second row
                board[6], board[5], board[4]                                         //first row
        };


        moveCount = 0;
    }

    public char getPlayerColor(int pos) {
        return board[pos];
    }

    private boolean isMuehleColor(int pos, PlayerTurn color) {
        char playerColor = (color == PlayerTurn.WHITE) ? 'W' : 'B';
        return board[pos] == playerColor;
    }

    public void checkMuehleColor(int pos) {
        char color = getPlayerColor(pos);
        PlayerTurn playerTurn = (color == 'W') ? PlayerTurn.WHITE : PlayerTurn.BLACK;

        if (pos % 2 == 0) ;
    }

    private GameState checkPlayersState(int pos, PlayerTurn color) {
        //Initialize the Player States (W/B) for next move.
        //Is Player White (W) in "Steel" State then Block Player Black (B).
        //Use Gamestate STEEL & MOVE

        GameState gameStatePlayer = (color == PlayerTurn.WHITE && pos % 2 == 0 || color == PlayerTurn.WHITE && !isMuehleEven(pos)) ? GameState.STELL : GameState.MOVE;


        return gameStatePlayer;
    }


    private GameState movePlayer(int pos) {
        if (!isValidFieldIndex(pos)) {
            throw new IndexOutOfBoundsException("This is not a valid Index!");
        } else {
            PlayerTurn playerTurn = (moveCount % 2 == 0) ? PlayerTurn.WHITE : PlayerTurn.BLACK
            return checkPlayersState(pos, playerTurn);
        }
    }


    private boolean isMuehleEven(int pos) {
        //Check for horizontal Lines
        return pos % 2 == 0;
    }

    private boolean isMuehleOdd(int pos) {
        //Check for horizontal Lines
        return;
    }

    private boolean muehleCalculation(int pos) {
        // Check for mühle in row, column
        return pos % 2 == 0 || isMuehleColumn(position);
    }


    private boolean isValidFieldIndex(int field) {
        //Check for valid Filed index
        return field >= 0 && field < board.length;
    }


}
