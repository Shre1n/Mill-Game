import View.View;
import Controller.Controller;
import Model.Model;
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

        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);

        // Starts the processing application with the view
        PApplet.runSketch(new String[]{"View"}, view);



    }


}
