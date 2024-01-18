

package Millgame.Controller;

import Millgame.Model.GameState;
import Millgame.Model.IModel;
import Millgame.View.IView;

/**
 * Controller has knowledge of Model and View.
 * Methods and Variables should only be referenced by the Model.
 * Accessibility is given by Mill Model. Variables and Methods should only be referenced or called by Value.
 * This Class and its Methods are typically used in the View component to handle what should be drawn at a specific time.
 */

public class Controller implements IController {

    /**
     * Declare Model for knowledge of Controller
     */
    private IModel millModel;

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

    public void setModel(IModel model) {
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
     * Specifies the order in which the Application should be updated.
     * First of all display the title screen.
     * If the Model is game over then display the Winning player.
     * If the game is a draw then display the draw screen.
     * Then Start a game by pressing 'r'.
     *
     */
    @Override
    public void nextFrame() {
        if (millModel.isGameOver()) {
            view.deactivateThread();
            if (millModel.hasPlayer1Won()) view.drawGG("Game Over! White has won!");
            else view.drawGG("Game Over! Black has won!");
        }

        if (millModel.isDraw()) {
            view.deactivateThread();
            view.drawGG("Game is a draw! Sadge :( ");
        }

        if (!gameBoardDrawn && !titleScreen) {
            millModel.newGame();
            view.drawField();
            view.activateThread();
            gameBoardDrawn = true;
        }

        if (titleScreen) {
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
     */
    public void setRestartGame() {
        if (titleScreen) {
            titleScreen = false;
        }
        restartGame = true;
    }

    /**
     * {@inheritDoc}
     * @return state
     */
    public GameState getPlayer1State() {
        return millModel.getPlayer1State();
    }

    /**
     * {@inheritDoc}
     *
     * @return state of player 2
     */

    public GameState getPlayer2State() {
        return millModel.getPlayer2State();
    }

    /**
     * {@inheritDoc}
     * @return char
     */
    public char getPlayer_1() {
        return millModel.getPLAYER_1();
    }

    /**
     * {@inheritDoc}
     * @return char
     */
    public char getPlayer_2() {
        return millModel.getPLAYER_2();
    }


    /**
     * {@inheritDoc}
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
        if (!titleScreen) {
            int x = view.getX();
            int y = view.getY();
            int xnew = view.getXnew();
            int ynew = view.getYnew();
            int posClicked = calculatePosClicked(x, y);
            int posDragged = calculatePosClicked(xnew, ynew);
            if (millModel.getTurn().equals("WHITE")) {
                try {
                    if (millModel.getPlayer1State() == GameState.STEAL && clicked) {
                        try {
                            millModel.steal(posClicked);
                            stolen = true;
                            view.drawField();
                        } catch (RuntimeException e) {
                            view.drawField();
                            view.exceptionRunner(e.getMessage());
                        }

                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner(e.getMessage());
                }
                try {
                    if ((millModel.getPlayer1State() == GameState.MOVE || millModel.getPlayer1State() == GameState.JUMP) && millModel.getBoard()[posDragged] == millModel.getEMPTY() && !clicked) {
                        if (!stolen) {
                            try {
                                millModel.move(posClicked, posDragged);
                                view.drawField();
                            } catch (RuntimeException e) {
                                view.drawField();
                                view.exceptionRunner(e.getMessage());
                            }
                        } else stolen = false;
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("You must choose a valid field to move to.");
                }
                if (millModel.getPlayer1State() == GameState.SET && clicked) {
                    try {
                        millModel.setPlayer(posClicked);
                        view.drawField();
                    } catch (RuntimeException e) {
                        view.drawField();
                        view.exceptionRunner("Please choose an empty field.");
                    }
                }
            } else {
                try {
                    if (millModel.getPlayer2State() == GameState.STEAL && clicked) {
                        try {
                            millModel.steal(posClicked);
                            stolen = true;
                            view.drawField();
                        } catch (RuntimeException e) {
                            view.drawField();
                            view.exceptionRunner(e.getMessage());
                        }
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner(e.getMessage());
                }
                try {
                    if ((millModel.getPlayer2State() == GameState.MOVE || millModel.getPlayer2State() == GameState.JUMP) && millModel.getBoard()[posDragged] == millModel.getEMPTY() && !clicked) {
                        if (!stolen) {
                            try {
                                millModel.move(posClicked, posDragged);
                                view.drawField();
                            } catch (RuntimeException e) {
                                view.drawField();
                                view.exceptionRunner(e.getMessage());
                            }
                        } else stolen = false;
                    }
                } catch (RuntimeException e) {
                    view.drawField();
                    view.exceptionRunner("You must choose a valid field to move to.");
                }
                if (millModel.getPlayer2State() == GameState.SET && clicked) {
                    try {
                        millModel.setPlayer(posClicked);
                        view.drawField();
                    } catch (RuntimeException e) {
                        view.drawField();
                        view.exceptionRunner("Please choose an empty field.");
                    }
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
     * Sets the Size of Application
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
