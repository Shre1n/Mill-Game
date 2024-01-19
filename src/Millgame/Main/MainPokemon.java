package Millgame.Main;

import Millgame.Controller.Controller;
import Millgame.Model.Model;
import Millgame.View.ViewPokemon;
import processing.core.PApplet;

public class MainPokemon {
    public static void main(String[] args) {
        var model = new Model();
        var controller = new Controller();
        var view = new ViewPokemon();

        controller.setSize(1000);

        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);

        // Starts the processing application with the view
        PApplet.runSketch(new String[]{"ViewPokemon"}, view);



    }
}
