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
     * If a player has not made a correct Move then show the User what's wrong.
     * @param clicked checks the x and y for mouse clicked
     */

    void userInput(boolean clicked);

    /**
     * gets the current color of player
     * @return string
     */
    String playerTurn();

    /**
     * changes boolean of restart.
     * This is to check if the restart Button has been pressed.
     */
    void setRestartGame();

    /**
     * gets the current state of Player 1.
     * This is to display the current game state of Player one in view.
     * The User have the information of their current state.
     * @return state
     */

    GameState getPlayer1State();

    /**
     * gets the current state of Player 2.
     * This is to display the current game state of Player one in view.
     * The User have the information of their current state.
     * @return state
     */
    GameState getPlayer2State();

    /**
     * gets the default player 1 character.
     * This is to get the current Character of a position.
     * @return char player 1
     */
    char getPlayer_1();

    /**
     * gets the default player 2 character.
     * This is to get the current Character of a position.
     * @return char player 2
     */
    char getPlayer_2();

    /**
     * gets the Board and its layout.
     * This is to redraw the board after all inputs from User.
     * @return char[]
     */
    char[] getBoard();

    /**
     * gets the Size of Application to Display.
     * @return size
     */
    int getSIZE();
}
