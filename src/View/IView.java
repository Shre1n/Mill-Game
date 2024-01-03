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


    void exceptionRunner(String message);
    void drawTitleScreen();

    public int getX();

    public int getY();

    int getXnew();
    int getYnew();

}
