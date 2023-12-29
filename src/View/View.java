package View;

import processing.core.PApplet;
import Controller.Controller;
import processing.core.PImage;

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


    private int x;
    private int y;

    private PImage player1;
    private PImage player2;
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
        player1 = loadImage("WhiteStone.png");
        player2 = loadImage("BlackStone.png");
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
        textAlign(CENTER,CENTER);
        text("Turn: " + controller.playerTurn(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        textSize((float) controller.getSIZE() /20);
        textAlign(CENTER,TOP);
        text("Current player state: " + controller.getPlayer1(), (float) controller.getSIZE() /2,20);
    }

    public void loadImgPlayer1() {
        image(player1,this.getX()-50,this.getY()-50,100,100);
    }

    public void loadImgPlayer2() {
       image(player2,this.getX()-50,this.getY()-50,100,100);
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
        float start = (float) controller.getSIZE() / 10;
        // shrink squares for squares in square
        float shrinkSqaure = 200;

        // Define Squares with params
        // for better understanding -- foreach square as inner square multiply shrink value by the number of this inner square
        // e.g. square 1 is outta square, next is square inner outta square then multiply by 2. That shifts the x and y-Axis of this square.
        square(start, start, controller.getSIZE() - shrinkSqaure);
        square(start + start, start + start, controller.getSIZE() - (shrinkSqaure * 2));
        square(start + shrinkSqaure, start + shrinkSqaure, controller.getSIZE() - (shrinkSqaure * 3));

        //Define Lines with params
        line(start, (float) controller.getSIZE() / 2, start + shrinkSqaure, (float) controller.getSIZE() / 2); // left line
        line((float) controller.getSIZE() - start, (float) controller.getSIZE() / 2, start + (shrinkSqaure * 3), (float) controller.getSIZE() / 2); //right line
        line((float) controller.getSIZE() / 2, start, (float) controller.getSIZE() / 2, start + shrinkSqaure); //upper line
        line((float) controller.getSIZE() / 2, controller.getSIZE() - start, (float) controller.getSIZE() / 2, start + (shrinkSqaure * 3));

    }


    /**
     * Send input from user in Controller to calculate Field and index of board
     */
    @Override
    public void mousePressed() {
        this.x = mouseX;
        this.y = mouseY;
        if (controller.playerTurn().equals("WHITE")) loadImgPlayer1();
        else loadImgPlayer2();
        System.out.println("X: " + this.getX() + " Y: " + this.getY());
        controller.userInput(this.getX(),this.getY());

    }



    @Override
    public void keyPressed() {
        if (key == ' ')controller.nextFrame();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
