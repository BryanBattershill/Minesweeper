// Author: Bryan Battershill
// Student number: 300014415
// Course: ITI 1121-B
// Assignment: 2


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
	private GameModel gameModel;
	private GameView gameView;
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
		gameModel = new GameModel(width, height, numberOfMines);
		gameView = new GameView(gameModel, this);
		gameView.setVisible(true);

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
		String command; 
		command = e.getActionCommand(); 
    // ADD YOU CODE HERE
		if (command.equals("Quit")){
			System.exit(0);
		}else if (command.equals("Reset")){
			reset();
		}else{
			String[] temp = command.split("_");
			play(Integer.valueOf(temp[0]),Integer.valueOf(temp[1]));
		}
		gameView.update();
    }
	

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE
		gameView.reset();
		gameModel.reset();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
		if (gameModel.hasBeenClicked(width,heigth) == false){
			gameModel.click(width,heigth);
			if (gameModel.isMined(width,heigth) == false){
				gameModel.uncover(width,heigth);
				if (gameModel.isBlank(width,heigth)){
					clearZone(gameModel.get(width,heigth));
				}
				if (gameModel.isFinished()){
					gameModel.uncoverAll();
					gameView.reset();
				}
				gameModel.step();
			}else{
				gameModel.uncoverAll();
				gameView.reset();
			}
		}

    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
	@SuppressWarnings("unchecked")
    private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE
		GenericArrayStack clear = new GenericArrayStack(gameModel.getHeigth()*gameModel.getWidth());
		clear.push(initialDot);
		while (clear.isEmpty() == false){
			DotInfo temp = (DotInfo) clear.pop();
			int x = temp.getX();
			int y = temp.getY();
			int lowerX = -1;
			int lowerY = -1;
			int upperX = 2;
			int upperY = 2;
			if (x == 0){
				lowerX=0;
			}else if (x==gameModel.getWidth()-1){
				upperX = 1;
			}
			if (y == 0){
				lowerY=0;
			}else if (y==gameModel.getHeigth()-1){
				upperY = 1;
			}
			for (int a = lowerX; a<upperX; a++){
				for (int b = lowerY; b<upperY; b++){
					if (a != x || b != y){
						//System.out.println(a+"/"+b);
						if (gameModel.isCovered(x+a,y+b) == true){
							gameModel.uncover(x+a,y+b);
							gameModel.click(x+a,y+b);
							if (gameModel.isBlank(x+a,y+b)){
								clear.push(gameModel.get(x+a,y+b));
							}
						}
					}
				}
			}
		}

    }



}
