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


    void writeTurn();



    /**
     * Draw game field with lines
     */

    void drawField();



}
