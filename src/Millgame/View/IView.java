package Millgame.View;

/**
 * Blueprint for View with given methods
 */


public interface IView {

    /**
     * Draws the GameOver screen with the winning player
     * @param color of player
     */
    void drawGG(String color);

    /**
     * Draw game field with squares and separates them by a value to draw inner squares
     */

    void drawField();


    /**
     * Exception handler display error in frontend
     * @param message show Error on screen
     */
    void exceptionRunner(String message);

    /**
     * Draws the title Screen with background and introductions
     */
    void drawTitleScreen();

    /**
     * gets the current x value of userinput
     * @return current x value
     */
    public int getX();

    /**
     * gets the current y value of userinput
     * @return current y value
     */
    public int getY();

    /**
     * gets the x value of dragged image
     * @return new x value
     */
    int getXnew();

    /**
     * gets the y value of dragged image
     * @return new y value
     */
    int getYnew();

}
