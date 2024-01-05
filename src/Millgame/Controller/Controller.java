/**
 * @author Robin Hahn
 * @version 1.0
 * @since 2023-12-12
 * Provides all necessary classes and interfaces for controller.
 * The applet controller has only one reference to {@link Millgame.Model.Model}
 * and one reference to {@link Millgame.View.IView}.
 *
 *
 */

package Millgame.Controller;

import Millgame.Model.GameState;
import Millgame.Model.Model;
import Millgame.View.IView;

/**
 * Controller has knowledge of Model and View.
 * Methods and Variables should only be referenced by the Model.
 * Accessibility is given by Mill Model. Variables and Methods should only be referenced or called by Value.
 */

public class Controller implements IController {

    /**
     * Declare Model for knowledge of Controller
     */
    private Model millModel;

    /**
     * Default restart value
     */
    private boolean restartGame;
    /**
     * If in the last turn a stone was stolen
     */
    private boolean stolen = false;

    /**
     * Blueprint of view
     */
    private IView view;

    /**
     * Sets the size of the Application
     */
    private int size;

    /**
     * Default draw game value
     */
    private boolean gameBoardDrawn = false;

    /**
     * default value of title screen
     */
    private boolean titleScreen = true;


    /**
     * Refer model to Controller and set model.
     * Typically used in Main.Main to set the Model for Controller.
     *
     * @param model as Mill Model
     */

    public void setModel(Model model) {
        this.millModel = model;
    }


    /**
     * Refer view to Controller and set this view.
     * Typically used in Main.Main to set View for Controller.
     *
     * @param view as Mill Model
     */

    public void setView(IView view) {
        this.view = view;
    }


    /**
     * {@inheritDoc}
     * Typically used to change the drawing state in view.
     */
    @Override
    public void nextFrame() {
        if (millModel.isGameOver()) {
            view.deactivateThread();
            if (millModel.hasPlayer1Won()) view.drawGG("Game Over! White has won!");
            else view.drawGG("Game Over! Black has won!");
        }

        if (millModel.isDraw()){
            view.deactivateThread();
            view.drawGG("Game is a draw! Sadge :( ");
        }

        if (!gameBoardDrawn && !titleScreen) {
            millModel.newGame();
            view.drawField();
            view.activateThread();
            gameBoardDrawn = true;
        }

        if (titleScreen){
            view.drawTitleScreen();
        }

        if (restartGame) {
            millModel.newGame();
            view.drawField();
            restartGame = false;
        }

    }


    /**
     * {@inheritDoc}
     * Typically used to restart the Game and to set the default value.
     */
    public void setRestartGame() {
        if(titleScreen){
            titleScreen = false;
        }
        restartGame = true;
    }

    /**
     * {@inheritDoc}
     * Typically used in View to compare gamestate with a specific value.
     *
     * @return state of player 1
     */
    public GameState getPlayer1() {
        return millModel.getPlayer1();
    }

    /**
     * {@inheritDoc}
     * Typically used in View to compare gamestate with a specific value.
     *
     * @return state of player 2
     */

    public GameState getPlayer2() {
        return millModel.getPlayer2();
    }

    /**
     * {@inheritDoc}
     * Typically used to check players character at position in View.
     *
     * @return char
     */
    public char getPlayer_1() {
        return millModel.getPLAYER_1();
    }

    /**
     * {@inheritDoc}
     * Typically used to check players character at position in View.
     *
     * @return char
     */
    public char getPlayer_2() {
        return millModel.getPLAYER_2();
    }


    /**
     * {@inheritDoc}
     * Typically used to check the current board setup.
     *
     * @return board
     */
    public char[] getBoard() {
        return millModel.getBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void userInput(boolean clicked) {
        if(!titleScreen) {
            int x = view.getX();
            int y = view.getY();
            int xnew = view.getXnew();
            int ynew = view.getYnew();
            int posClicked = calculatePosClicked(x, y);
            int posDragged = calculatePosClicked(xnew, ynew);
            System.out.println(posClicked + "= " + x + ", " + y + "\n" + posDragged + "= " + xnew + ", " + ynew);
            if (millModel.getTurn().equals("WHITE")) {
                if (millModel.getPlayer1() == GameState.SET && clicked) {
                    try {
                        millModel.setPlayer(posClicked);
                        view.drawField();
                    } catch (RuntimeException e) {
                        view.drawField();
                        view.exceptionRunner("No Valid Field. Choose another!");
                    }
                }
                try {
                    if ((millModel.getPlayer1() == GameState.MOVE || millModel.getPlayer1() == GameState.JUMP) && millModel.getBoard()[posDragged] == millModel.getEMPTY() && !clicked) {
                        if (!stolen) {
                            try {
                                millModel.move(posClicked, posDragged);
                                view.drawField();
                            } catch (RuntimeException e) {
                                view.drawField();
                                view.exceptionRunner("No valid move! Fields must be adjacent.");
                            }
                        } else stolen = false;
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("No valid adjacent field. Choose another one!");
                }


                try {
                    if (millModel.getPlayer1() == GameState.STEAL && millModel.getBoard()[posClicked] == millModel.getPLAYER_2() && clicked) {
                        try {
                            millModel.steal(posClicked);
                            stolen = true;
                            view.drawField();
                        } catch (RuntimeException e) {
                            view.drawField();
                            view.exceptionRunner("This is not a valid Field to steal!");
                        }

                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("This is not a valid field.");
                }

            } else {
                if (millModel.getPlayer2() == GameState.SET && clicked) {
                    try {
                        millModel.setPlayer(posClicked);
                        view.drawField();
                    } catch (RuntimeException e) {
                        view.drawField();
                        view.exceptionRunner("No Valid Field. Choose another!");
                    }
                }
                try {
                    if ((millModel.getPlayer2() == GameState.MOVE || millModel.getPlayer2() == GameState.JUMP) && millModel.getBoard()[posDragged] == millModel.getEMPTY() && !clicked) {
                        if (!stolen) {
                            try {
                                millModel.move(posClicked, posDragged);
                                view.drawField();
                            } catch (RuntimeException e) {
                                view.drawField();
                                view.exceptionRunner("No valid move! Fields must be adjacent");
                            }
                        } else stolen = false;
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("No valid adjacent field. Choose another one!");
                }
                try {
                    if (millModel.getPlayer2() == GameState.STEAL && millModel.getBoard()[posClicked] == millModel.getPLAYER_1() && clicked) {
                        try {
                            millModel.steal(posClicked);
                            stolen = true;
                            view.drawField();
                        } catch (RuntimeException e) {
                            view.drawField();
                            view.exceptionRunner("This is not a valid Field to steal!");
                        }
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("This is not a valid Field to steal!");
                }
            }
            System.out.println(millModel.toString());
        }
    }

    private int calculatePosClicked(int x, int y) {

        // Calculate square parameters
        float start = (float) this.getSIZE() / 10;
        //Gap is 10 by default -- currently depends on size / start value
        float gap = start / 10;


        //first square
        if ((x >= start - gap && x <= start + gap) && (y >= start - gap && y <= start + gap))
            return 0;
        if ((x >= ((start / 2) + ((float) this.getSIZE() - start) / 2) - gap && x <= ((start / 2) + ((float) this.getSIZE() - start) / 2) + gap) && (y >= start - gap && y <= start + gap))
            return 1;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= start - gap && y <= start + gap))
            return 2;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= (((start / 2) + (this.getSIZE() - start) / 2)) - gap && y <= (((start / 2) + (this.getSIZE() - start) / 2)) + gap))
            return 3;
        if ((x >= (this.getSIZE() - start) - gap && x <= (this.getSIZE() - start) + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 4;
        if ((x >= (((this.getSIZE() - start) / 2) + (start / 2)) - gap && x <= (((this.getSIZE() - start) / 2) + (start / 2)) + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 5;
        if ((x >= start - gap && x <= start + gap) && (y >= (this.getSIZE() - start) - gap && y <= (this.getSIZE() - start) + gap))
            return 6;
        if ((x >= start - gap && x <= start + gap) && (y >= ((float) this.getSIZE() / 2) - gap && y <= ((float) this.getSIZE() / 2) + gap))
            return 7;

        //second square
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 8;
        if ((x >= start + ((this.getSIZE() - (start * 2)) / 2) - gap && x <= start + ((this.getSIZE() - (start * 2)) / 2) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 9;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= (start * 2) - gap && y <= (start * 2) + gap))
            return 10;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= ((float) this.getSIZE() / 2) - gap && y <= ((float) this.getSIZE() / 2) + gap))
            return 11;
        if ((x >= (this.getSIZE() - (start * 2)) - gap && x <= (this.getSIZE() - (start * 2)) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 12;
        if ((x >= ((float) this.getSIZE() / 2) - gap && x <= ((float) this.getSIZE() / 2) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 13;
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= (this.getSIZE() - (start * 2)) - gap && y <= (this.getSIZE() - (start * 2)) + gap))
            return 14;
        if ((x >= (start * 2) - gap && x <= (start * 2) + gap) && (y >= ((float) this.getSIZE() / 2) - gap && y <= ((float) this.getSIZE() / 2) + gap))
            return 15;

        //third square
        if ((x >= (start * 3) - gap && x <= (start * 3) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 16;
        if ((x >= (((start * 3) / 2) + ((this.getSIZE() - (start * 3)) / 2)) - gap && x <= (((start * 3) / 2) + ((this.getSIZE() - (start * 3)) / 2)) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 17;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= (start * 3) - gap && y <= (start * 3) + gap))
            return 18;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= ((float) this.getSIZE() / 2) - gap && y <= ((float) this.getSIZE() / 2) + gap))
            return 19;
        if ((x >= (this.getSIZE() - (start * 3)) - gap && x <= (this.getSIZE() - (start * 3)) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 20;
        if ((x >= ((float) this.getSIZE() / 2) - gap && x <= ((float) this.getSIZE() / 2) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 21;
        if ((x >= (start * 3) - gap && x <= (start * 3) + gap) && (y >= (this.getSIZE() - (start * 3)) - gap && y <= (this.getSIZE() - (start * 3)) + gap))
            return 22;
        if ((x >= (start * 3) - gap && x <= (start * 3) + gap && (y >= ((float) this.getSIZE() / 2) - gap && y <= ((float) this.getSIZE() / 2) + gap)))
            return 23;
        return -1;
    }

     /**
     * {@inheritDoc}
     */

    public String playerTurn() {
        return millModel.getTurn();
    }

    /**
     * {@inheritDoc}
     */

    public void setSize(int SIZE) {
        this.size = SIZE;
    }

    /**
     * {@inheritDoc}
     */
    public int getSIZE() {
        return size;
    }



}
