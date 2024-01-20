package Millgame.View;

import Millgame.Controller.IController;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Visualize the Application and draw game.
 * Must contain all public classes from View interface to use them.
 * This Class and its Methods are intended to draw the graphical interface and handle how are states are drawn.
 */


public class View extends BaseView implements IView {
    /**
     * Declare x-axis of input via mousePressed
     */
    private int x;

    /**
     * Declare y-axis of input via mousePressed
     */
    private int y;

    /**
     * Default value of new x value
     */
    private int xnew;

    /**
     * Default value of new y value
     */
    private int ynew;

    /**
     * Default value to handle the Thread for image loading.
     * This value has been set to avoid the while true Thread runner.
     */
    private boolean active = false;


    protected String getBackgroundImage() {
        return "backgroundGame.jpg";
    }

    protected String getTitleScreenImage() {
        return "background.png";
    }

    protected String getPlayer1Image() {
        return "WhiteStone.png";
    }

    protected String getPlayer2Image() {
        return "BlackStone.png";
    }

    /**
     * draw the next frame given from Controller
     */
    @Override
    public void draw() {
        controller.nextFrame();
    }

    /**
     * {@inheritDoc}
     */

    public void drawTitleScreen() {
        background(ts);


        image(player1, (float) controller.getSIZE() / 2 - 100, (float) controller.getSIZE() / 2 - 150);
        image(player2, (float) controller.getSIZE() / 2 + 20, (float) controller.getSIZE() / 2 - 150);


        fill(0);
        textAlign(CENTER, CENTER);
        textSize((float) controller.getSIZE() / 25);
        text("The Mill Game", (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        fill(255);
        textAlign(CENTER, TOP);
        text("Press 'R' to Start and Reset the Game", (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2 + 400);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawGG(String message) {
        background(0);
        textSize((float) controller.getSIZE() / 15);
        textAlign(CENTER, CENTER);
        fill(255);
        text(message, (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        textSize((float) controller.getSIZE() / 30);
        text("Press 'r' to reset!", (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 3 * 2);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void drawField() {
        background(bg);

        noFill();
        // Thickness of Squares
        strokeWeight((float) controller.getSIZE() / 100);


        // Calculate square parameters
        float start = (float) controller.getSIZE() / 10;
        // shrink squares for squares in square
        float shrinkSqaure = (float) controller.getSIZE() / 5;

        // Define Squares with params
        // for better understanding -- foreach square as inner square multiply shrink value by the number of this inner square
        // e.g. square 1 is outer square, next is square inner outer square then multiply by 2. That shifts the x and y-Axis of this square.
        square(start, start, controller.getSIZE() - shrinkSqaure);
        square(start + start, start + start, controller.getSIZE() - (shrinkSqaure * 2));
        square(start + shrinkSqaure, start + shrinkSqaure, controller.getSIZE() - (shrinkSqaure * 3));

        //Define Lines with params
        line(start, (float) controller.getSIZE() / 2, start + shrinkSqaure, (float) controller.getSIZE() / 2); // left line
        line((float) controller.getSIZE() - start, (float) controller.getSIZE() / 2, start + (shrinkSqaure * 3), (float) controller.getSIZE() / 2); //right line
        line((float) controller.getSIZE() / 2, start, (float) controller.getSIZE() / 2, start + shrinkSqaure); //upper line
        line((float) controller.getSIZE() / 2, controller.getSIZE() - start, (float) controller.getSIZE() / 2, start + (shrinkSqaure * 3));

        fill(0);
        textAlign(CENTER, CENTER);
        textSize((float) controller.getSIZE() / 20);
        if (controller.playerTurn().equals("WHITE")) {
            text(controller.playerTurn() + " : " + controller.getPlayer1State(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        } else {
            text(controller.playerTurn() + " : " + controller.getPlayer2State(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        }
    }


    /**
     * Send input from user in Controller to calculate Field and index of board
     */
    @Override
    public void mousePressed() {
        this.x = mouseX;
        this.y = mouseY;
        controller.userInput(true);
    }


    /**
     * Sends input as a drag and drop x and y-axis to controllers userinput
     */
    @Override
    public void mouseReleased() {
        xnew = mouseX;
        ynew = mouseY;

        controller.userInput(false);
    }

    /**
     * {@inheritDoc}
     */
    public int getXnew() {
        return xnew;
    }


    /**
     * {@inheritDoc}
     */
    public int getYnew() {
        return ynew;
    }


    /**
     * {@inheritDoc}
     */
    public void exceptionRunner(String message) {
        fill(0);
        textAlign(CENTER, BOTTOM);
        textSize((float) controller.getSIZE() / 25);
        text(message, (float) controller.getSIZE() / 2, 50);
    }

    /**
     * {@inheritDoc}
     */
    public void activateThread() {
        active = true;
        new Thread (()->runThread()).start(); //running lambda in as Thread
    }

    private void runThread(){
        while (active) {
            char[] board = controller.getBoard();
            float start = (float) controller.getSIZE() / 10;
            float x = 0, y = 0;
            float square = 0; // je nachdem, in welchem Square
            float change = (float) controller.getSIZE() / 2; // für die Abstände der Steine im Square
            for (int i = 0; i <= 23; i++) {
                if (i % 8 == 0) {
                    square += start;
                    x = 0 + square;
                    y = 0 + square;
                    change -= start;
                }
                if (board[i] == controller.getPlayer_1()) image(player1, x - 50, y - 50, 100, 100);
                else if (board[i] == controller.getPlayer_2()) image(player2, x - 50, y - 50, 100, 100);
                if (i % 8 <= 1) x += change;
                else if (i % 8 <= 3) y += change;
                else if (i % 8 <= 5) x -= change;
                else if (i % 8 == 6) y -= change;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deactivateThread() {
        active = false;
    }

    /**
     * checks if user pressed the restart or start button
     */
    @Override
    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            controller.setRestartGame();
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */

    public int getY() {
        return y;
    }
}
