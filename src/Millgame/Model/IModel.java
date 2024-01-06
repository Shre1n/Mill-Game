package Millgame.Model;

public interface IModel {

    void newGame();

    boolean isEmptyField(int pos);

    void steal(int pos);

    void setPlayer(int pos);

    void move(int pos1, int pos2);

    boolean hasPlayer1Won();

    boolean hasPlayer2Won();

    boolean isGameOver();

    boolean isDraw();

    String getTurn();

    GameState getPlayer1();

    GameState getPlayer2();

    char[] getBoard();

    char getEMPTY();

    char getPLAYER_1();

    char getPLAYER_2();
    String toString();
}
