package Model;

import java.util.Arrays;

public class Model {

    private final char EMPTY = '⌗';

    private final char PLAYER_1 = '■';
    private final char PLAYER_2 = '□';

    private final int WhiteStones = 9;
    private final int BlackStones = 9;

    private char[] board;
    private final int indexCounter = 0;

    private final PlayerTurn turn;

    private GameState gameState;

    private int moveCount;


    public static void main(String[] args) {
        var game = new Model();
    }

    Model() {
        board = new char[24];
        Arrays.fill(board, EMPTY);
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

    public char[] getBoard(){
        return board;
    }

    private GameState playerState(int pos, PlayerTurn playerTurn) {
        char player = (playerTurn == PlayerTurn.WHITE) ? 'W' : 'B';
        if (player == 'W' && hasMuehle(pos, player) || player == 'B' && hasMuehle(pos, player)) return GameState.STEAL;
        if (BlackStones == 3 || WhiteStones == 3 || (BlackStones == 3 && WhiteStones == 3)){
            moveCount++;
            return GameState.JUMP;
        }
        moveCount++;
        return GameState.MOVE;
    }

    public boolean isEmptyField(int pos){
        if (isValidFieldIndex(pos)) return board[pos] == EMPTY;
        else throw new IndexOutOfBoundsException("Valid Positions are [0 , 23]");
    }

    private void steal(int pos){
        if (gameState == GameState.STEAL && isValidFieldIndex(pos)){
            char player = (turn == PlayerTurn.WHITE) ? 'W' : 'B';
            if (board[pos] != player && board[pos] != EMPTY) {

            }
        }
    }


    public void movePlayer(int pos) {
        if (!isGameOver() && gameState == GameState.MOVE) {
            if (!isValidFieldIndex(pos)) {
                throw new IndexOutOfBoundsException("This is not a valid Index!");
            } else {
                PlayerTurn playerTurn = (moveCount % 2 == 0) ? PlayerTurn.WHITE : PlayerTurn.BLACK;
                playerState(pos, playerTurn);
            }
        }
    }

    private boolean hasPlayer1Won(){
        return BlackStones == 0;
    }

    private boolean hasPlayer2Won(){
        return WhiteStones == 0;
    }


    public boolean isGameOver(){
        return hasPlayer1Won() || hasPlayer2Won() || moveCount >= board.length;
    }

    private boolean hasMuehle(int pos, char player) {
        // Check for mühle in row, column
        int temp = whichSquare(pos);
        if (pos % 2 == 0) {
            return board[((pos + 1) % 8) + temp] == player && board[((pos + 2) % 8) + temp] == player || board[((pos - 1) % 8) + temp] == player && board[((pos - 2) % 8) + temp] == player;
        } else {
            return board[(pos + 8) % 24] == player && board[(pos + 16) % 24] == player || board[((pos + 1) % 8) + temp] == player && board[((pos - 1) % 8) + temp] == player;
        }
    }

    private int whichSquare(int pos) {
        return (pos < 8) ? 0 : (pos < 16 ? 8 : 16);
    }


    private boolean isValidFieldIndex(int field) {
        //Check for valid Filed index
        return field >= 0 && field < board.length;
    }


}
