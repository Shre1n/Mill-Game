/**
 * @author Robin Hahn
 * @since 2023-12-12
 * @version 1.0
 *
 * Provides the classes necessary to create and run the applet and the classes an applet uses to communicate
 * with the applet context.
 * The applet runs a Processing Sketch (see the {@link processing.core.PApplet} class).
 *
 * @see processing.core
 */


package Millgame.Main;

import Millgame.Controller.Controller;
import Millgame.Model.Model;
import Millgame.View.View;
import processing.core.PApplet;


/**
 * The main class to initialize and run the Mill game with in user interface.
 * It creates instances of the Model, Controller, and View classes, sets up the necessary
 * connections between them, and starts the processing application to display the game interface.
 */

public class Main {

    /**
     * The main method to start the Mill game.
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        var model = new Model();
        var controller = new Controller();
        var view = new View();

        controller.setSize(1000);

        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);

        // Starts the processing application with the view
        PApplet.runSketch(new String[]{"View"}, view);



    }


}
