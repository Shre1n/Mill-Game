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
     * Declare x-axis of input via mousePressed
     */
    private int x;

    /**
     * Declare y-axis of input via mousePressed
     */
    private int y;

    /**
     * Image storage of player White
     */
    private PImage player1;

    /**
     * Image storage of player Black
     */
    private PImage player2;


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

    /**
     * User information about current player and their state
     */

    public void writeTurn() {
        //to fix with calculation
        update(350, 350, 300, 300);
    }

    /**
     * update information with rectangle draw
     * @param x1 start x-axis of rectangle
     * @param y1 start y-axis of rectangle
     * @param x2 end x-axis of rectangle
     * @param y2 end y-axis of rectangle
     */

    private void update(int x1, int y1, int x2,int y2){
        fill(255);
        noStroke();
        rect(x1,y1,x2,y2);

        fill(0);
        textAlign(CENTER,CENTER);
        textSize((float) controller.getSIZE() /20);
        if (controller.playerTurn().equals("WHITE")) {
            text(controller.playerTurn() + " : " + controller.getPlayer1(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        } else {
            text(controller.playerTurn() + " : " + controller.getPlayer2(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        }

    }

    /**
     * load white player image at pressed x and y value
     */

    public void loadImgPlayer1() {
        image(player1,this.getX()-50,this.getY()-50,100,100);
    }

    /**
     * load black player image at pressed x and y value
     */

    public void loadImgPlayer2() {
       image(player2,this.getX()-50,this.getY()-50,100,100);
    }

    /**
     * {@inheritDoc}
     *
     * @param color to identify the winner
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
        background(255);
        noFill();
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
        controller.userInput(this.getX(),this.getY());
        redraw();
    }



    @Override
    public void keyPressed() {
        if (key == ' ')controller.nextFrame();
    }


    /**
     * Handle given x-axis position given from input
     * @return pressed X
     */
    public int getX() {
        return x;
    }

    /**
     * Handle given y-axis position given from input
     * @return pressed Y
     */

    public int getY() {
        return y;
    }
}
