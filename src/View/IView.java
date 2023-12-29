package View;

/**
 * Blueprint for View with given methods
 */


public interface IView {

    /**
     * Draws the GameOver screen with the winning player
     * @param color
     */
    void drawGG(String color);

    /**
     * Draw game field with lines
     */

    void drawField();

    public void loadImgPlayer1();

    public void loadImgPlayer2();

    public void writeTurn();
    public void removeStone();

    public int getX();

    public int getY();



}
