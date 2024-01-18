

/**
 * Blueprint for Model and their use of Methods.
 */

public interface IModel {


    /**
     * Define a new Game with default EMPTY Layout of Board.
     * Setup GameState of each Player.
     */
    void newGame();

    /**
    * Checks is Players input an Empty field.
    * @param pos of user input.
    * @return wrong entry.
    */

    boolean isEmptyField(int pos);

    /**
     * Player is in steal Mode if he has made a mill with own Stones.
     * Checks if Player has made a Mill with own Stones. If Mill the switch player into steal Mode.
     * Is position valid and not Empty and opponents Stone than he can steal that Stone.
     * @param pos given position to steal from opponent.
     */

    void steal(int pos);

    /**
    * Sets the Players settable Stones at Position.
    * @param pos given through user input.
    */
    void setPlayer(int pos);

    /**
     * Move player from position to position.
     * This is only possible when player 1 or player 2 is in Move state.
     * @param pos1 players current position.
     * @param pos2 players future position.
     */

    void move(int pos1, int pos2);

    /**
     * Has Player WHITE won the Game.
     * @return player WHITE won.
     */
    boolean hasPlayer1Won();

    /**
     * Has Player BLACK won the Game.
     * @return player BLACK won.
     */

    boolean hasPlayer2Won();

    /**
     * Checks if Player has already won the game.
     * @return winning player.
     */

    boolean isGameOver();

    /**
     * Checks if the game is a draw.
     * If Players only have mills and one player is in steal mode.
     * @return boolean draw game
     */

    boolean isDraw();

    /**
     * Gets the current Turn from Player for access in Model.
     * @return turn
     */

    String getTurn();

    /**
     * Gets the current state from Player 1 for access in Model.
     * @return state
     */

    GameState getPlayer1State();

    /**
     * Gets the current state from Player 2 for access in Model.
     * @return state
     */

    GameState getPlayer2State();

    /**
     * Gets the current board setup to access in Model.
     * @return board
     */

    char[] getBoard();

    /**
     * Checks if position of SET,MOVE or STEAL is empty.
     * @return char
     */

    char getEMPTY();

    /**
     * gets the char of player 1
     * @return char player 1
     */
    char getPLAYER_1();

    /**
     * gets the char of player 2
     * @return char player 2
     */

    char getPLAYER_2();

    /**
     * Visualization in Console with state and player turn.
     * @return console output.
     */
    String toString();
}
