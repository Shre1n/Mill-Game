package Controller;

import Model.GameState;
import Model.PlayerTurn;

/**
 * Blueprint for Controller with given methods
 */

public interface IController{

    /**
     * Draws the next frame in game.
     * It depends on current game state.
     */

    void nextFrame();

    /**
     * Sets the position of Players input with given parameters.
     * Checks if Player has made a correct Move.
     */

    void userInput(boolean clicked);

    String playerTurn();

    GameState getPlayer1();
    GameState getPlayer2();

    char getPlayer_1();
    char getPlayer_2();

    char[] getBoard();
}
