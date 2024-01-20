package Millgame.View;

import processing.core.PApplet;
import processing.core.PImage;
import Millgame.Controller.IController;

/**
 * An abstract class which is for the standard difference between a specific view Mode.
 * Provides all necessary methods to allow multiple views in the Project with different images.
 * All view classes must inherit this class in the purpose to load different images.
 */
public abstract class BaseView extends PApplet {


    protected IController controller;

    protected PImage bg;

    protected PImage ts;


    protected PImage player1;

    protected PImage player2;

    /**
     * Setting the Controller of View.
     *
     * @param controller class instance for passing controller
     */

    public void setController(IController controller) {
        this.controller = controller;
    }

    /**
     * Set Size of Application given from Controller.
     */

    public void settings() {
        pixelDensity(2);
        size(controller.getSIZE(), controller.getSIZE());
    }

    /**
     * Sets the default setup for Application.
     */

    public void setup() {
        frameRate(60);
        surface.setTitle("The Mill Game");
        surface.setResizable(false);
        bg = loadImage(getBackgroundImage());
        ts = loadImage(getTitleScreenImage());
        player1 = loadImage(getPlayer1Image());
        player2 = loadImage(getPlayer2Image());
        nullableImage();
        bg.resize(controller.getSIZE(), controller.getSIZE());
        ts.resize(controller.getSIZE(), controller.getSIZE());
        player1.resize(100, 100);
        player2.resize(100, 100);
    }

    private void nullableImage() {
        if (bg == null || ts == null || player1 == null || player2 == null) {
            System.out.println("Image has not been found.");
            exitActual();
        }
    }

    /**
     * Draws the title Screen with background and introductions.
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

    abstract String getBackgroundImage();

    abstract String getTitleScreenImage();

    abstract String getPlayer1Image();

    abstract String getPlayer2Image();

}
