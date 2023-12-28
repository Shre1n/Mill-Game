package View;

/**
 * Blueprint for View with given methods
 */


public interface IView {

    /**
     * Draws the GameOver screen with the winning player
     * @param number
     */
    void drawGG(String color);


    /**
     * Draw game field with lines
     */

    void drawField();



}
