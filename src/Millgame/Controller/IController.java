package Millgame.Controller;

import Millgame.Model.GameState;

/**
 * Blueprint for Controller with given methods
 */

public interface IController{

    /**
     * draws the next frame in game.
     * it depends on current game state.
     */

    void nextFrame();

    /**
     * Sets the position of Players input with given parameters.
     * Checks if Player has made a correct Move.
     * Typically used in View to send the MouseX and MouseY into Controller.
     *
     * @param clicked checks the x and y for mouse clicked
     */

    void userInput(boolean clicked);

    /**
     * gets the current color of player
     * @return string
     */
    String playerTurn();

    /**
     * changes gamestate to restart
     */
    void setRestartGame();

    /**
     * gets the current state of Player 1
     * @return state player 1
     */

    GameState getPlayer1();

    /**
     * gets the current state of Player 2
     * @return state player 2
     */
    GameState getPlayer2();

    /**
     * gets the default player 1 character
     * @return char player 1
     */
    char getPlayer_1();

    /**
     * gets the default player 2 character
     * @return char player 2
     */
    char getPlayer_2();

    /**
     * gets the Board and its layout
     * @return char[]
     */
    char[] getBoard();

    /**
     * gets the Size of Application to Display
     * @return size
     */
    int getSIZE();
}
