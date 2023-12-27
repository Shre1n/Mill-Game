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
    public final int SIZE = 1000;


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
}
