package Millgame.View;

/**
 * Blueprint for View with given methods.
 */


public interface IView {

    /**
     * Draws the GameOver screen with the winning player
     * @param message to display
     */
    void drawGG(String message);

    /**
     * Draw game field with squares and separates them by a value to draw inner squares.
     * Gives player/user updates for their next moves.
     * Moves are always displays in the middle of the board.
     */

    void drawField();

    /**
     * Activates the Thread for image loading.
     * The loading calculates all already placed images and draws them at their positions.
     */
    void activateThread();

    /**
     * Deactivates the Thread for the image loading.
     * This is to not overloading the View with unnecessary images and to avoid image loading while switching screens.
     */
    void deactivateThread();


    /**
     * Exception handler display error in frontend.
     * Everytime the user has information about the current Move.
     * @param message show Error on screen
     */
    void exceptionRunner(String message);

    /**
     * Draws the title Screen with background and introductions.
     */
    void drawTitleScreen();

    /**
     * gets the current x value of user input.
     * @return current x value
     */
    public int getX();

    /**
     * gets the current y value of user input.
     * @return current y value
     */
    public int getY();

    /**
     * gets the x value of dragged image.
     * @return new x value
     */
    int getXnew();

    /**
     * gets the y value of dragged image.
     * @return new y value
     */
    int getYnew();

}
