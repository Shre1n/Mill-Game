package Model;

import java.util.Arrays;

public class Model {

    private final char EMPTY = '⌗';

    private final char PLAYER_1 = '■';
    private final char PLAYER_2 = '□';

    private int SETWhiteStones = 4;
    private int SETBlackStones = 4;

    private int boardWhite = 0;
    private int boardBlack = 0;

    private char[] board;

    private PlayerTurn turn;

    private GameState player1;
    private GameState player2;

    public static void main(String[] args) {
        var game = new Model();
        game.newGame();
        game.setPlayer(0);
        game.setPlayer(16);
        game.setPlayer(2);
        game.setPlayer(3);
        game.setPlayer(9);
        game.setPlayer(5);
        game.setPlayer(6);
        game.setPlayer(19);
        game.move(9, 1);
        game.steal(16);
        game.move(5, 11);
        game.steal(6);
        game.move(0, 7);
        game.move(3, 4);
        game.move(7, 0);
        game.steal(4);
        System.out.println(game);
    }

    public Model() {
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
        player1 = GameState.SET;
        player2 = GameState.SET;

        System.out.println("Game Start");
    }

    public boolean isEmptyField(int pos) {
        if (isValidFieldIndex(pos)) {
            if (board[pos] == EMPTY) return true;
            throw new RuntimeException("Field is already taken. Please try again!");
        } else throw new IndexOutOfBoundsException("Valid Positions are [0 , 23]");
    }

    private char identifyPlayer() {
        return (turn == PlayerTurn.WHITE) ? PLAYER_1 : PLAYER_2;
    }

    private void steal(int pos) {
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

    public boolean hasPlayer1Won() {
        return boardBlack == 2;
    }

    public boolean hasPlayer2Won() {
        return boardWhite == 2;
    }

    public boolean isGameOver() {
        return hasPlayer1Won() || hasPlayer2Won();
    }

    private boolean isMill(int pos, char player) {
        // Check for mühle in row, column
        int temp = whichSquare(pos);
        if (pos % 2 == 0) {
            return board[((pos + 1) % 8) + temp] == player && board[((pos + 2) % 8) + temp] == player || board[((pos + 7) % 8) + temp] == player && board[((pos + 6) % 8) + temp] == player;
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