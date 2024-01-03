/**
 * @author Robin Hahn
 * @version 1.0
 * @since 2023-12-12
 */

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
    private int xnew;
    private int ynew;

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
        frameRate(60);
        surface.setTitle("The Mill Game");
        surface.setResizable(false);

    }

    /**
     * {@inheritDoc}
     * draw the next frame given from Controller
     */
    @Override
    public void draw() {
        controller.nextFrame();
    }

    public void drawTitleScreen() {
        PImage bi = loadImage("background.jpg");
        bi.resize(controller.getSIZE(), controller.getSIZE());
        background(bi);
        fill(31,21,1);
        textAlign(CENTER, CENTER);
        textSize((float) controller.getSIZE() / 20);
        text("The Mill Game", (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
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
        textAlign(CENTER, CENTER);
        fill(255);
        text("Game Over! " + color + " has won!", (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
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
            text(controller.playerTurn() + " : " + controller.getPlayer1(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        } else {
            text(controller.playerTurn() + " : " + controller.getPlayer2(), (float) controller.getSIZE() / 2, (float) controller.getSIZE() / 2);
        }

        noFill();
        char[] board = controller.getBoard();
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


    /**
     * Send input from user in Controller to calculate Field and index of board
     */
    @Override
    public void mousePressed() {
        this.x = mouseX;
        this.y = mouseY;
        controller.userInput(true);
    }

    @Override
    public void mouseReleased() {
        xnew = mouseX;
        ynew = mouseY;

        controller.userInput(false);
    }

    public int getXnew() {
        return xnew;
    }

    public int getYnew() {
        return ynew;
    }

    public void exceptionRunner(String message) {
        fill(0);
        textAlign(CENTER, BOTTOM);
        textSize((float) controller.getSIZE() / 20);
        text(message, (float) controller.getSIZE() / 2, 50);
    }

    @Override
    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            controller.setRestartGame();
        }
    }

    /**
     * Handle given x-axis position given from input
     *
     * @return pressed X
     */
    public int getX() {
        return x;
    }

    /**
     * Handle given y-axis position given from input
     *
     * @return pressed Y
     */

    public int getY() {
        return y;
    }
}
