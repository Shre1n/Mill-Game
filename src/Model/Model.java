package Model;

import java.util.Arrays;

public class Model {

    private final String EMPTY = "⌗";

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
                EMPTY,"------------------",EMPTY,"-------------------",EMPTY + "\n",
                 "|",                       "|",                        "|"  + "\n",
                 "|",         EMPTY,       EMPTY,       EMPTY,          "|"  + "\n",
                 "|",          "|",         "|",         "|",           "|"  + "\n",
                 "|",          "|", EMPTY, EMPTY, EMPTY, "|",           "|"  + "\n",
                 "|",          "|",  "|",          "|",  "|",           "|"  + "\n",
                EMPTY      ,  EMPTY,EMPTY,        EMPTY,EMPTY,         EMPTY + "\n",
                 "|",          "|",  "|",          "|",  "|",           "|"  + "\n",
                 "|",          "|", EMPTY, EMPTY, EMPTY, "|",           "|"  + "\n",
                 "|",          "|",         "|",         "|",           "|"  + "\n",
                 "|",         EMPTY,       EMPTY,       EMPTY,          "|"  + "\n",
                 "|",                       "|",                        "|"  + "\n",
                EMPTY,"------------------",EMPTY,"------------------", EMPTY + "\n"
        };


        moves = 0;
        System.out.println(Arrays.toString(board));
    }






    private boolean isValidFieldIndex(int field){
        return field >= 0 && field < board.length;
    }








}
