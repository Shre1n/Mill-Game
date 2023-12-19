package Model;

public class Model {

    private final char EMPTY = '⌗';

    private final char PLAYER_1 = '■';
    private final char PLAYER_2 = '□';

    private Graph graph;

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
                board[0],                   board[1],                       board[2],                                        //first row
                            board[8],       board[9],      board[10],                                                //second row
                                   board[16],board[17],board[18],                                                          //third row
                board[7],board[15],board[23],          board[19],board[11], board[3],                                        //middle row
                                   board[22],board[21],board[20],                                                          //third row
                            board[14],       board[13],        board[12],                                                //second row
                board[6],                    board[5],                      board[4]                                         //first row
        };


        moveCount = 0;
    }

    public char getPlayer(){
        return (moveCount % 2 == 0) ? PLAYER_1 : PLAYER_2;
    }

    private boolean checkPlayer1Won(){
        return winCondition(PLAYER_1);
    }

    private boolean checkPlayer2Won(){
        return winCondition(PLAYER_2);
    }


    private boolean winCondition(char player){
        //See Skizze for better understanding
        if (board[pos] % 2 == 0) {
            isEdge(pos)
        }

    }

    private boolean isEdge(int posAlready, int posAdded){
        if ()
    }





    private boolean isValidFieldIndex(int field){
        return field >= 0 && field < board.length;
    }








}
