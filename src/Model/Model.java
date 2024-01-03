/**
 *  @author Robin Hahn
 *  @version 1.0
 *  @since 2023-12-12
 *  Fundamentals and Logic for Application named 'The Mill game'.
 *  All Classes in this package should only be visible or referenced in Controller package.
 *
 *
 */

package Model;

import java.util.Arrays;

/**
 * The Logic of the Application Mill game.
 * WHITE is Player 1.
 * BLACK is Player 2.
 * Checks the Players turn (Black or White) and start a new game.
 * Checks State of Player to circle through each state.
 * States can be 'SET','MOVE','JUMP'.
 * Move from player to another position.
 * Steal Stones from enemy with given position.
 */

public class Model {

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
    private int SETWhiteStones = 9;

    /**
     * The default Stone count for settable Stones as Player 2.
     */
    private int SETBlackStones = 9;

    /**
     * The default count for Stones on board for Player 1,
     */

    private int boardWhite = 0;

    /**
     * The default count for Stones on board for Player 2.
     */
    private int boardBlack = 0;

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
     * Main method to test the Mill game and logic.
     * @param args args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        var game = new Model();
        System.out.println(game);
    }

    /**
     * Model Constructor to test the setup the Model to start as default.
     */
    public Model() {
        board = new char[24];
        Arrays.fill(board, EMPTY);
        turn = PlayerTurn.WHITE;
    }


    /**
     * Define a new Game with default EMPTY Layout of Board.
     * Setup GameState of each Player.
     */

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
        player1 = GameState.SET;
        player2 = GameState.SET;

        //System.out.println("Game Start");
    }


    /**
     * Checks is Players input an Empty field.
     * @param pos of user input.
     * @throws RuntimeException if field is taken.
     * @throws IndexOutOfBoundsException if no field is selected.
     * @return wrong entry.
     */
    public boolean isEmptyField(int pos) {
        if (isValidFieldIndex(pos)) {
            if (board[pos] == EMPTY) return true;
            throw new RuntimeException("Field is already taken. Please try again!");
        } else throw new IndexOutOfBoundsException("Valid Positions are [0 , 23]");
    }

    /**
     * Player is in steal Mode if he has made a mill with own Stones.
     * Checks if Player has made a Mill with own Stones. If Mill the switch player into steal Mode.
     * Is position valid and not Empty and opponents Stone than he can steal that Stone.
     * @param pos given position to steal from opponent.
     */

    public void steal(int pos) {
        if (board[pos] == EMPTY) {
            throw new RuntimeException("This field is Empty. This is not a Valid stone to steal. Try another one!");
        }
        if (player1 == GameState.STEAL && isValidFieldIndex(pos) && turn == PlayerTurn.WHITE) {
            if (board[pos] == PLAYER_2 && !isMill(pos, PLAYER_2)) {
                board[pos] = EMPTY;
                boardBlack--;
                turn = PlayerTurn.BLACK;
                if (SETWhiteStones > 0) {
                    player1 = GameState.SET;
                } else if (boardWhite > 3) {
                    player1 = GameState.MOVE;
                } else {
                    player1 = GameState.JUMP;
                }
                if (SETBlackStones > 0) {
                    player2 = GameState.SET;
                } else if (boardBlack > 3) {
                    player2 = GameState.MOVE;
                } else {
                    player2 = GameState.JUMP;
                }
            }
        } else if (player2 == GameState.STEAL && isValidFieldIndex(pos) && turn == PlayerTurn.BLACK) {
            if (board[pos] == PLAYER_1 && !isMill(pos, PLAYER_1)) {
                board[pos] = EMPTY;
                boardWhite--;
                turn = PlayerTurn.WHITE;
                if (SETBlackStones > 0) {
                    player2 = GameState.SET;
                } else if (boardBlack > 3) {
                    player2 = GameState.MOVE;
                } else {
                    player2 = GameState.JUMP;
                }
                if (SETWhiteStones > 0) {
                    player1 = GameState.SET;
                } else if (boardWhite > 3) {
                    player1 = GameState.MOVE;
                } else {
                    player1 = GameState.JUMP;
                }
            }
        }
    }


    /**
     * Sets the Players settable Stones at Position.
     * @param pos given through user input.
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
            } else if (SETWhiteStones == 0) {
                player1 = GameState.MOVE;
            }
        } else if (player2 == GameState.SET && isEmptyField(pos) && turn == PlayerTurn.BLACK) {
            board[pos] = PLAYER_2;
            --SETBlackStones;
            ++boardBlack;
            turn = PlayerTurn.WHITE;
            if (isMill(pos, PLAYER_2)) {
                player2 = GameState.STEAL;
                turn = PlayerTurn.BLACK;
            } else if (SETBlackStones == 0) {
                player2 = GameState.MOVE;
            }
        } else {
            throw new RuntimeException("Gamestate of current Player is not SET. Please use the intended method!");
        }
    }

    /**
     * Calculate the current placed ring of player and neighbor of index.
     * @param pos1 players current position.
     * @param pos2 players future position.
     * @return adjacent indexes
     */
    private boolean isValidMove(int pos1, int pos2) {
        int temp = whichSquare(pos1);
        if (pos1 % 2 == 0) {
            return ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 7) % 8) + temp == pos2;
        } else {
            if (temp == 8)
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 8) % 24) == pos2 || ((pos1 + 16)) % 24 == pos2;
            else if (temp == 0)
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 8) % 24) == pos2;
            else
                return ((pos1 - 1) % 8) + temp == pos2 || ((pos1 + 1) % 8) + temp == pos2 || ((pos1 + 16)) % 24 == pos2;
        }
    }

    /**
     * Move player
     * @param pos1 players current position.
     * @param pos2 players future position.
     */
    public void move(int pos1, int pos2) { // 16, 23
        if (!isGameOver()) {
            if (isEmptyField(pos2) && player1 != GameState.SET && turn == PlayerTurn.WHITE) {
                if (player1 == GameState.MOVE) {
                    if (!isValidMove(pos1, pos2))
                        throw new RuntimeException("Move is not possible! Positions must be adjacent.");
                }
                if (board[pos1] != PLAYER_1)
                    throw new RuntimeException("The choosen stone to move does not equal the current Player.");
                board[pos2] = board[pos1];
                board[pos1] = EMPTY;
                turn = PlayerTurn.BLACK;
                if (isMill(pos2, PLAYER_1)) {
                    player1 = GameState.STEAL;
                    turn = PlayerTurn.WHITE;
                }
            } else if (isEmptyField(pos2) && player2 != GameState.SET && turn == PlayerTurn.BLACK) {
                if (player2 == GameState.MOVE) {
                    if (!isValidMove(pos1, pos2))
                        throw new RuntimeException("Move is not possible! Positions must be adjacent.");
                }
                if (board[pos1] != PLAYER_2)
                    throw new RuntimeException("The choosen stone to move does not equal the current Player.");
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
     * Has Player WHITE won the Game.
     * @return player WHITE won.
     */
    public boolean hasPlayer1Won() {
        return boardBlack == 2 && player1 == GameState.JUMP;
    }

    /**
     * Has Player BLACK won the Game.
     * @return player BLACK won.
     */
    public boolean hasPlayer2Won() {
        return boardWhite == 2 && player2 == GameState.JUMP;
    }

    /**
     * Checks if Player has already won the game.
     * @return winning player.
     */
    public boolean isGameOver() {
        return hasPlayer1Won() || hasPlayer2Won();
    }

    /**
     * Calculate the Mill with adjacent index and ring placements of player Stone.
     * @param pos of player stone.
     * @param player to check the line.
     * @return players Mill.
     */
    private boolean isMill(int pos, char player) {
        // Check for mühle in row, column
        int temp = whichSquare(pos);
        if (pos % 2 == 0) {
            return board[((pos + 1) % 8) + temp] == player && board[((pos + 2) % 8) + temp] == player || board[((pos + 7) % 8) + temp] == player && board[((pos + 6) % 8) + temp] == player;
        } else {
            return board[(pos + 8) % 24] == player && board[(pos + 16) % 24] == player || board[((pos + 1) % 8) + temp] == player && board[((pos - 1) % 8) + temp] == player;
        }
    }

    /**
     * In which Square has the Player placed their stones.
     * @param pos of stone
     * @return ring
     */
    private int whichSquare(int pos) {
        return (pos < 8) ? 0 : (pos < 16 ? 8 : 16);
    }


    /**
     * Valid field of Players input.
     * @param field to check if the move is possible.
     * @return possible move
     */
    private boolean isValidFieldIndex(int field) {
        //Check for valid Filed index
        return field >= 0 && field < board.length;
    }

    /**
     * Gets the current Turn from Player for access in Model.
     * @return turn
     */
    public String getTurn() {
        return turn.toString();
    }

    /**
     * Gets the current state from Player 1 for access in Model.
     * @return state
     */

    public GameState getPlayer1() {
        return player1;
    }

    /**
     * Gets the current state from Player 2 for access in Model.
     * @return state
     */

    public GameState getPlayer2() {
        return player2;
    }

    /**
     * Gets the current board setup to access in Model.
     * @return board
     */

    public char[] getBoard() {
        return board;
    }

    /**
     * restarts game with a clear board
     */
    public void setEMPTY(){
        Arrays.fill(board, EMPTY);
        turn = PlayerTurn.valueOf("WHITE");
    }

    public char getEMPTY() {
        return EMPTY;
    }

    public char getPLAYER_1() {
        return PLAYER_1;
    }

    public char getPLAYER_2() {
        return PLAYER_2;
    }

    public void setSETWhiteStones(int SETWhiteStones) {
        this.SETWhiteStones = SETWhiteStones;
    }

    public void setSETBlackStones(int SETBlackStones) {
        this.SETBlackStones = SETBlackStones;
    }

    public void setBoardWhite(int boardWhite) {
        this.boardWhite = boardWhite;
    }

    public void setBoardBlack(int boardBlack) {
        this.boardBlack = boardBlack;
    }

    /**
     * Visualization in Console with state and player turn.
     * @return console output.
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