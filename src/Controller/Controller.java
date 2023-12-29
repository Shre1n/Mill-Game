package Controller;

import Model.*;
import View.IView;

public class Controller implements IController {

    /**
     * Declare Model for knowledge of Controller
     */
    private Model millModel;

    private Player player;

    /**
     * Blueprint of view
     */
    private IView view;

    /**
     * Sets the size of the Application
     */
    private int size;

    private boolean gameBoardDrawn = false;


    /**
     * Refer model to Controller and set this model
     *
     * @param model as Mill Model
     */

    public void setModel(Model model) {
        this.millModel = model;
    }


    /**
     * Refer view to Controller and set this view
     *
     * @param view as Mill Model
     */

    public void setView(IView view) {
        this.view = view;
    }


    /**
     * Draw Game with dependencies of given game state
     */
    @Override
    public void nextFrame() {
        if (millModel.isGameOver()) {
            if (millModel.hasPlayer1Won()) view.drawGG("White");
            else view.drawGG("Black");
        }

        if (!gameBoardDrawn) {
            millModel.newGame();
            view.drawField();
            view.writeTurn();
            gameBoardDrawn = true;
        }


    }

    public GameState getPlayer1(){
        return millModel.getPlayer1();
    }


    /**
     * Sets the position of Players input with given parameters.
     * Checks if Player has made a correct Move.
     *
     * @param x takes the x-axis of player input.
     * @param y takes the y-axis of player input.
     */
    @Override
    public void userInput(int x, int y) {
        int posClicked = calculatePosClicked(x,y);
        millModel.setPlayer(posClicked);
    }


    private int calculatePosClicked(int x, int y) {

        x = view.getX();
        y = view.getY();

        // Calculate square parameters
        float start = (float) this.getSIZE() / 10;
        //Gap is 10 by default -- currently depends on size / start value
        float gap = start/10;


        //first square
        if ((x >= start - gap && x <= start + gap) && (y >= start - gap && y <= start + gap))
            return 0;
        if ((x >= ((start / 2) + ((float) this.getSIZE() - start) / 2) - gap && x <= ((start / 2) + ((float) this.getSIZE() - start) / 2) + gap) && (y >= start - gap && y <= start + gap))
            return 1;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= start - gap && y <= start + gap))
            return 2;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= (((start / 2) + (this.getSIZE() - start)/2)) - gap && y <= (((start / 2) + (this.getSIZE() - start)/2)) + gap))
            return 3;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 4;
        if ((x >= (((this.getSIZE() - start) / 2) + (start / 2)) - gap && x <= (((this.getSIZE() - start) / 2) + (start / 2)) + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 5;
        if ((x >= start - gap && x <= start + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 6;
        if ((x >= start - gap && x <= start + gap) && (            y >= (this.getSIZE()/2) - gap                    && y <= (this.getSIZE()/2) + gap))
            return 7;

        //second square
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 8;
        if ((x >= start + ((this.getSIZE() - (start * 2)) / 2) - gap && x <= start + ((this.getSIZE() - (start * 2)) / 2) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 9;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 10;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= (this.getSIZE() / 2) - gap && y <= (this.getSIZE() / 2) + gap))
            return 11;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 12;
        if ((x >= (this.getSIZE()/2) - gap && x <= (this.getSIZE()/2) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 13;
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 14;
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= (this.getSIZE() / 2) - gap && y <= (this.getSIZE() / 2) + gap))
            return 15;

        //third square
        if ((x >= (start * 3) - gap && x <= (start * 3) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 16;
        if ((x >= (((start * 3) / 2) + ((this.getSIZE() - (start * 3)) / 2)) - gap && x <= (((start * 3) / 2) + ((this.getSIZE() - (start * 3)) / 2)) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 17;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 18;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= (this.getSIZE() / 2) - gap && y <= (this.getSIZE() / 2) + gap))
            return 19;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 20;
        if ((x >= (this.getSIZE()/2) - gap && x <= (this.getSIZE()/2) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 21;
        if ((x >= (start * 3) - gap && x <= (start * 3) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 22;

        //fehler
        if ((x >= (start*3) - gap && x <= (start*3) + gap && (y >= (this.getSIZE() / 2) - gap && y <= (this.getSIZE() / 2) + gap)))
            return 23;


        return -1;
    }


    /**
     * @return color of player
     */

    public String playerTurn() {
        return millModel.getTurn();
    }

    /**
     * Sets the Size for Application
     *
     * @param SIZE to change
     */

    public void setSize(int SIZE) {
        this.size = SIZE;
    }

    /**
     * @return the Size of Application
     */

    public int getSIZE() {
        return size;
    }

}
