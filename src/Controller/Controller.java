package Controller;

import Model.Model;
import View.IView;

public class Controller implements IController{

    /**
     * Declare Model for knowledge of Controller
     */
    private Model millModel;

    /**
     * Blueprint of view
     */
    private IView view;

    /**
     * Sets the size of the Application
     */
    private final int SIZE = 1000;

    private boolean gameBoardDrawn = false;


    /**
     * Refer model to Controller and set this model
     * @param model as Mill Model
     */

    public void setModel(Model model) {
        this.millModel = model;
    }


    /**
     * Refer view to Controller and set this view
     * @param view as Mill Model
     */

    public void setView(IView view) {
        this.view = view;
    }


    /**
     * Draw Game with dependencies of given game state
     */
    @Override
    public void nextFrame() {
        if (millModel.isGameOver()){
            if (millModel.hasPlayer1Won()) view.drawGG("White");
            else view.drawGG("Black");
        }

        if (!gameBoardDrawn){
            view.drawGame();
            gameBoardDrawn = true;
        }

    }


    /**
     * Sets the position of Players input with given parameters.
     * Checks if Player has made a correct Move.
     * @param x takes the x-axis of player input.
     * @param y takes the y-axis of player input.
     */
    @Override
    public void userInput(int x, int y) {

    }


    public int getSIZE() {
        return SIZE;
    }
}
