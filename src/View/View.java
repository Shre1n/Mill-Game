package View;

import processing.core.PApplet;
import Controller.Controller;

/**
 * Visualize the Application and draw game
 */


public class View extends PApplet implements IView {

    /**
     * Declare Controller instance
     */
    private Controller controller;

    /**
     * Setting the Controller of View
     * @param controller sets Controller of Application
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }


    /**
     * {@inheritDoc}
     * Set Size of Application given from Controller
     */
    @Override
    public void settings() {
        size(controller.SIZE, controller.SIZE);
    }

    /**
     * {@inheritDoc}
     * load Player images to Application
     */
    @Override
    public void setup() {

    }

    /**
     * {@inheritDoc}
     * draw the next frame given from Controller
     */
    @Override
    public void draw() {
        background(255);
        drawField();
    }




    /**
     * {@inheritDoc}
     * @param number
     */
    @Override
    public void drawGG(int number) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawField() {
        line(100,100, 900, 100);
    }
}
