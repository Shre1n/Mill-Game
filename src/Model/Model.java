package Model;

public class Model {

    private final String EMPTY = "-";

    private final String PLAYER_1 = "■";
    private final String PLAYER_2 = "□";


    private String[] board = new String[50];

    private int indexCounter = 0;

    private PlayerTurn turn;

    private GameState gameState;

    private int moves;


    public static void main(String[] args) {
        var game = new Model();
    }

    Model() {
        newGame();
    }

    public void newGame() {
        board = new String[]{
                EMPTY,"-----", EMPTY, "-----", EMPTY, //first row
                "|",            "|",            "|",
                EMPTY, EMPTY, EMPTY, //second row
                EMPTY, EMPTY, EMPTY, //third row
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, //middle row
                EMPTY, EMPTY, EMPTY, //third row
                EMPTY, EMPTY, EMPTY, //second row
                EMPTY, EMPTY, EMPTY //first row
        };


        moves = 0;
    }

    private void initGame(){
        for (char c: board) {

        }
    }


    public char getField(int field){
        if(isValidFieldIndex(field)){
            return board[field];
        } else {
            throw new IndexOutOfBoundsException("No Valid Index!");
        }
    }

    private boolean isValidFieldIndex(int field){
        return field >= 0 && field < board.length;
    }

    public char playerMove(){
        return (moves % 2 == 0) ? PLAYER_1 : PLAYER_2;
    }

    public void move(int field){
        if (!isGameOver){
            if (isValidFieldIndex(field)){

            }
        }
    }






}
