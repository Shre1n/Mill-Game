package View;

import Model.PlayerTurn;
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
     *
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
        size(controller.getSIZE(), controller.getSIZE());
    }

    /**
     * {@inheritDoc}
     * load Player images to Application
     */
    @Override
    public void setup() {
        if (controller.playerTurn().equals("White")) loadImgPlayer1();
        else loadImgPlayer2();
    }

    /**
     * {@inheritDoc}
     * draw the next frame given from Controller
     */
    @Override
    public void draw() {
        controller.nextFrame();
    }

    public void writeTurn() {
        fill(0);
        textSize((float) controller.getSIZE() / 20);
        textAlign(CENTER,BOTTOM);
        text("Turn: " + controller.playerTurn(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
    }

    private void loadImgPlayer1() {
        loadImage("SETWhiteStone.png");
    }

    private void loadImgPlayer2() {
        loadImage("SETBlackStone.png");
    }

    /**
     * {@inheritDoc}
     *
     * @param color
     */
    @Override
    public void drawGG(String color) {
        background(0);
        textSize((float) controller.getSIZE() / 15);
        text("Game Over! " + color + " has won!", (float) controller.getSIZE() / 10, (float) controller.getSIZE() / 2);
    }

    /**
     * {@inheritDoc}
     * Draws the game field with squares
     */
    @Override
    public void drawField() {
        // Thickness of Squares
        strokeWeight((float) controller.getSIZE() / 100);

        // Calculate square parameters
        float one_hundred = (float) controller.getSIZE() / 10;
        // shrink squares for squares in square
        float shrinkSqaure = 200;

        // Define Squares with params
        // for better understanding -- foreach square as inner square multiply shrink value by the number of this inner square
        // e.g. square 1 is outta square, next is square inner outta square then multiply by 2. That shifts the x and y-Axis of this square.
        square(one_hundred, one_hundred, controller.getSIZE() - shrinkSqaure);
        square(one_hundred + one_hundred, one_hundred + one_hundred, controller.getSIZE() - (shrinkSqaure * 2));
        square(one_hundred + shrinkSqaure, one_hundred + shrinkSqaure, controller.getSIZE() - (shrinkSqaure * 3));

        //Define Lines with params

        line(one_hundred, (float) controller.getSIZE() / 2, one_hundred + shrinkSqaure, (float) controller.getSIZE() / 2); // left line
        line((float) controller.getSIZE() - one_hundred, (float) controller.getSIZE() / 2, one_hundred + (shrinkSqaure * 3), (float) controller.getSIZE() / 2); //right line
        line((float) controller.getSIZE() / 2, one_hundred, (float) controller.getSIZE() / 2, one_hundred + shrinkSqaure); //upper line
        line((float) controller.getSIZE() / 2, controller.getSIZE() - one_hundred, (float) controller.getSIZE() / 2, one_hundred + (shrinkSqaure * 3));


    }

}
