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
     * @param x input of Player.
     * @param y input of Player.
     */

    void userInput(int x, int y);

    String playerTurn();

    GameState getPlayer1();
}
