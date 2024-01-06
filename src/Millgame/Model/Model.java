package Millgame.Model;

import java.util.Arrays;

/**
 * The Logic of the Application Mill game.
 * WHITE is Player 1.
 * BLACK is Player 2.
 * Checks the Players turn (Black or White) and start a new game.
 * Checks State of Player to circle through each state.
 * States can be 'SET','MOVE','JUMP', 'STEAL'.
 * Move from player to another position.
 * Steal Stones from enemy with given position.
 * This Class and its Methods are typically used in the Controller component and compare states of View with states of Model.
 */

public class Model implements IModel {

    /**
     * The default field entry for empty fields.
     */
    private final char EMPTY = '⌗';

    /**
     * The default field entry for Player 1 fields.
     */
    private final char PLAYER_1 = '■';
    /**
     * The default field entry for Player 2 fields.
     */
    private final char PLAYER_2 = '□';

    /**
     * The default Stone count for settable Stones as Player 1.
     */
    private int SETWhiteStones;

    /**
     * The default Stone count for settable Stones as Player 2.
     */
    private int SETBlackStones;

    /**
     * The default count for Stones on board for Player 1,
     */

    private int boardWhite;

    /**
     * The default count for Stones on board for Player 2.
     */
    private int boardBlack;

    /**
     * The default field setup.
     */

    private char[] board;

    /**
     * Declare Enum variable of PlayerTurn.
     */

    private PlayerTurn turn;

    /**
     * Declare Enum variable of GameState for Player 1.
     */
    private GameState player1;

    /**
     * Declare Enum variable of GameState for Player 2.
     */
    private GameState player2;


    /**
     * Constructor of Model to start a new game
     */
    public Model() {
        newGame();
    }

    /**
     * Define a new Game with default EMPTY Layout of Board.
     * Setup GameState of each Player.
     * Fills all available board positions with the Empty Character.
     */

    public void newGame() {
        board = new char[24];
        Arrays.fill(board, EMPTY);
        player1 = GameState.SET;
        player2 = GameState.SET;
        turn = PlayerTurn.WHITE;
        boardBlack = 0;
        boardWhite = 0;
        SETBlackStones = 9;
        SETWhiteStones = 9;
    }


    /**
     * {@inheritDoc}
     * @throws RuntimeException if field is taken.
     * @throws IndexOutOfBoundsException if no field is selected.
     */
    public boolean isEmptyField(int pos) {
        if (isValidFieldIndex(pos)) {
            if (board[pos] == EMPTY) return true;
            throw new RuntimeException("Field is already taken. Please try again!");
        } else throw new IndexOutOfBoundsException("Valid Positions are [0 , 23]");
    }

    /**
     * {@inheritDoc}
     */
    public void steal(int pos) {
        if (board[pos] == EMPTY)
            throw new RuntimeException("You cannot steal from an empty field.");
        if (player1 == GameState.STEAL && isValidFieldIndex(pos) && turn == PlayerTurn.WHITE) {
            if (board[pos] == PLAYER_2 && !isMill(pos, PLAYER_2)) {
                board[pos] = EMPTY;
                boardBlack--;
                turn = PlayerTurn.BLACK;
                checkPlayerState();
            } else if (board[pos] == PLAYER_2 && isMill(pos, PLAYER_2))
                throw new RuntimeException("You cannot steal a stone in a mill.");
            else throw new RuntimeException("You cannot steal your own stones.");
        } else if (player2 == GameState.STEAL && isValidFieldIndex(pos) && turn == PlayerTurn.BLACK) {
            if (board[pos] == PLAYER_1 && !isMill(pos, PLAYER_1)) {
                board[pos] = EMPTY;
                boardWhite--;
                turn = PlayerTurn.WHITE;
                checkPlayerState();
            } else if (board[pos] == PLAYER_1 && isMill(pos, PLAYER_1))
                throw new RuntimeException("You cannot steal a stone in a mill.");
            else throw new RuntimeException("You cannot steal your own stones.");
        }
    }

    private void checkPlayerState() {
        if (SETBlackStones > 0) player2 = GameState.SET;
        else if (boardBlack > 3) player2 = GameState.MOVE;
        else player2 = GameState.JUMP;
        if (SETWhiteStones > 0) player1 = GameState.SET;
        else if (boardWhite > 3) player1 = GameState.MOVE;
        else player1 = GameState.JUMP;
    }


    /**
     * {@inheritDoc}
     * @throws RuntimeException unexpected use of method.
     */
    public void setPlayer(int pos) {
        if (player1 == GameState.SET && isEmptyField(pos) && turn == PlayerTurn.WHITE) {
            board[pos] = PLAYER_1;
            --SETWhiteStones;
            ++boardWhite;
            turn = PlayerTurn.BLACK;
            if (isMill(pos, PLAYER_1)) {
                player1 = GameState.STEAL;
                turn = PlayerTurn.WHITE;
            } else if (SETWhiteStones == 0) player1 = GameState.MOVE;
        } else if (player2 == GameState.SET && isEmptyField(pos) && turn == PlayerTurn.BLACK) {
            board[pos] = PLAYER_2;
            --SETBlackStones;
            ++boardBlack;
            turn = PlayerTurn.WHITE;
            if (isMill(pos, PLAYER_2)) {
                player2 = GameState.STEAL;
                turn = PlayerTurn.BLACK;
            } else if (SETBlackStones == 0) player2 = GameState.MOVE;
        } else
            throw new IllegalArgumentException("Gamestate of current Player is not SET. Please use the intended method!");

    }

    /**
     * Calculate the current placed ring of player and neighbor of index.
     * @param pos1 players current position.
     * @param pos2 players future position.
     * @return adjacent indexes
     */
    private boolean isValidMove(int pos1, int pos2) {
        int temp = whichSquare(pos1);
        if(pos1 < 0 || pos1 > 23 || pos2 < 0 || pos2 > 23) throw new IndexOutOfBoundsException("The choosen position is not valid!");
        if (pos1 % 2 == 0) return ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 7) % 8) + temp == pos2;
        else {
            if (temp == 8)
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 8) % 24) == pos2 || ((pos1 + 16)) % 24 == pos2;
            else if (temp == 0)
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 8) % 24) == pos2;
            else
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 16)) % 24 == pos2;
        }
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException if positions are not adjacent.
     * Or Player tries to move an Opponent stone.
     * Or if game is already over.
     */
    public void move(int pos1, int pos2) { // 16, 23
        if (!isGameOver() && !isDraw()) {
            if (isEmptyField(pos2) && player1 != GameState.SET && turn == PlayerTurn.WHITE) {
                if (player1 == GameState.MOVE) {
                    if (!isValidMove(pos1, pos2) && board[pos1] == PLAYER_1)
                        throw new RuntimeException("Move is not possible! Positions must be adjacent.");
                }
                if (board[pos1] != PLAYER_1)
                    throw new RuntimeException("You can only move the stone of the current Player.");
                board[pos2] = board[pos1];
                board[pos1] = EMPTY;
                turn = PlayerTurn.BLACK;
                if (isMill(pos2, PLAYER_1)) {
                    player1 = GameState.STEAL;
                    turn = PlayerTurn.WHITE;
                }
            } else if (isEmptyField(pos2) && player2 != GameState.SET && turn == PlayerTurn.BLACK) {
                if (player2 == GameState.MOVE) {
                    if (!isValidMove(pos1, pos2) && board[pos1] == PLAYER_1)
                        throw new RuntimeException("Move is not possible! Positions must be adjacent.");
                }
                if (board[pos1] != PLAYER_2)
                    throw new RuntimeException("You can only move the stone of the current Player.");
                board[pos2] = board[pos1];
                board[pos1] = EMPTY;
                turn = PlayerTurn.WHITE;
                if (isMill(pos2, PLAYER_2)) {
                    player2 = GameState.STEAL;
                    turn = PlayerTurn.BLACK;
                }
            }
        } else throw new RuntimeException("Game is already over! :( ");
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPlayer1Won() {
        return boardBlack == 2 && player2 == GameState.JUMP;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPlayer2Won() {
        return boardWhite == 2 && player1 == GameState.JUMP;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isGameOver() {
        return hasPlayer1Won() || hasPlayer2Won();
    }

    /**
     * {@inheritDoc}
     */

    public boolean isDraw() {
        boolean isDraw = true;
        GameState currentPlayer = (turn == PlayerTurn.WHITE) ? player1 : player2;
        char stealPlayer = (turn == PlayerTurn.WHITE) ? PLAYER_2 : PLAYER_1;
        if (currentPlayer == GameState.STEAL) {
            for (int i = 0; i < board.length; i++) {
                if (board[i] == stealPlayer) {
                    if (!isMill(i, board[i])) isDraw = false;
                }
            }
            return isDraw;
        } return false;
    }

    private boolean isMill(int pos, char player) {
        // Check for mühle in row, column
        int temp = whichSquare(pos);
        if (pos % 2 == 0)
            return board[((pos + 1) % 8) + temp] == player && board[((pos + 2) % 8) + temp] == player || board[((pos + 7) % 8) + temp] == player && board[((pos + 6) % 8) + temp] == player;
        else
            return board[(pos + 8) % 24] == player && board[(pos + 16) % 24] == player || board[((pos + 1) % 8) + temp] == player && board[((pos - 1) % 8) + temp] == player;

    }

    private int whichSquare(int pos) {
        return (pos < 8) ? 0 : (pos < 16 ? 8 : 16);
    }

    private boolean isValidFieldIndex(int field) {
        //Check for valid Filed index
        return field >= 0 && field < board.length;
    }

    /**
     * {@inheritDoc}
     */
    public String getTurn() {
        return turn.toString();
    }

    /**
     * {@inheritDoc}
     */

    public GameState getPlayer1State() {
        return player1;
    }

    /**
     * {@inheritDoc}
     */

    public GameState getPlayer2State() {
        return player2;
    }

    /**
     * {@inheritDoc}
     */
    public char[] getBoard() {
        return board;
    }

    /**
     * {@inheritDoc}
     */

    public char getEMPTY() {
        return EMPTY;
    }

    /**
     * {@inheritDoc}
     */
    public char getPLAYER_1() {
        return PLAYER_1;
    }

    /**
     * {@inheritDoc}
     */

    public char getPLAYER_2() {
        return PLAYER_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (!isGameOver()) {
            String s = board[0] + "                  " + board[1] + "                   " + board[2] + "\n";
            s += "      " + board[8] + "            " + board[9] + "            " + board[10] + "\n";
            s += "             " + board[16] + "     " + board[17] + "     " + board[18] + "\n";
            s += board[7] + "     " + board[15] + "      " + board[23] + "           " + board[19] + "     " + board[11] + "      " + board[3] + "\n";
            s += "             " + board[22] + "     " + board[21] + "     " + board[20] + "\n";
            s += "      " + board[14] + "            " + board[13] + "            " + board[12] + "\n";
            s += board[6] + "                  " + board[5] + "                   " + board[4];
            s += "\n Next Players Turn: " + turn;
            GameState statePlayer = (turn == PlayerTurn.WHITE ? player1 : player2);
            s += "\n State of Player: " + statePlayer;
            s += "\n Available Stones Player 1: " + SETWhiteStones;
            s += "\n Available Stones Player 2: " + SETBlackStones;
            return s;
        } else return (turn == PlayerTurn.WHITE) ? "Black has won!" : "White has won";
    }
}