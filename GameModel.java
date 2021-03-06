// Author: Bryan Battershill
// Student number: 300014415
// Course: ITI 1121-B
// Assignment: 2


import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
	
	private Random generator;
	private int widthOfGame;
	private int heigthOfGame;
	private int numberOfMines;
	private int numberOfSteps;
	private int numberUncovered;
	private DotInfo[][] model;
	
	
    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE
		widthOfGame = width;
		heigthOfGame = heigth;
		this.numberOfMines = numberOfMines;
		generator = new Random();
		numberOfSteps = 0;
		numberUncovered = 0;
		model = new DotInfo[width][heigth];
		setBoard();
    }
	/**
	 * Sets the positions of the mines and initialises the board elements
	 *
	 */
	private void setBoard(){
		
		for (int a = 0; a<widthOfGame; a++){
			for (int b = 0; b<heigthOfGame; b++){
				model[a][b] = new DotInfo(a,b);
			}
		}
		
		int c = 0;
		while (c<numberOfMines){
			int i = generator.nextInt(widthOfGame);
			int j = generator.nextInt(heigthOfGame);
			if (model[i][j].isMined() == false){
				model[i][j].setMined();
				if (i != 0 && j!=0){
					model[i-1][j-1].setNeighbooringMines(model[i-1][j-1].getNeighbooringMines()+1);
				}
				if (j != 0){
					model[i][j-1].setNeighbooringMines(model[i][j-1].getNeighbooringMines()+1);
				}
				if (i!= widthOfGame-1 && j!= 0){
					model[i+1][j-1].setNeighbooringMines(model[i+1][j-1].getNeighbooringMines()+1);
				}
				if (i != 0){
					model[i-1][j].setNeighbooringMines(model[i-1][j].getNeighbooringMines()+1);
				}
				if (i!=0 && j!= heigthOfGame-1){
					model[i-1][j+1].setNeighbooringMines(model[i-1][j+1].getNeighbooringMines()+1);
				}
				if (i!= widthOfGame-1 && j!= heigthOfGame-1){
					model[i+1][j+1].setNeighbooringMines(model[i+1][j+1].getNeighbooringMines()+1);
				}
				if (j!= heigthOfGame-1){
					model[i][j+1].setNeighbooringMines(model[i][j+1].getNeighbooringMines()+1);
				}
				if (i!= widthOfGame-1){
					model[i+1][j].setNeighbooringMines(model[i+1][j].getNeighbooringMines()+1);
				}
				
				c++;
			}
		}
		
	}
 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        
    // ADD YOU CODE HERE
		numberOfSteps = 0;
		numberUncovered = 0;
		model = new DotInfo[widthOfGame][heigthOfGame];
		setBoard();
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE
		return heigthOfGame;
    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE
		return widthOfGame;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE
		return model[i][j].isMined();

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
		return model[i][j].hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE
		return model[i][j].getNeighbooringMines() == 0;

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
		return model[i][j].isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
		return model[i][j].getNeighbooringMines();

    }
	/**
	 * returns the number of mines on the board
	 *
	 * @return the number of mines on the board
	 */
	public int getNumberOfMines(){
		return numberOfMines;
	}
    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
		model[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
		model[i][j].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
		for (int a = 0; a<widthOfGame; a++){
			for (int b = 0; b<heigthOfGame; b++){
				model[a][b].uncover();
			}
		}

    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
		return numberOfSteps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
		return model[i][j];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE
		numberOfSteps++;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
		for (int a = 0; a<widthOfGame; a++){
			for (int b = 0; b<heigthOfGame; b++){
				if (model[a][b].isMined() == false && model[a][b].isCovered() == true){
					return false;
				} 
			}
		}
		return true;

    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
		return "Width: " + widthOfGame + " Height: " + heigthOfGame + " Number of Mines: " + numberOfMines + " Number of Steps: " + numberOfSteps + " Number Uncovered: " + numberUncovered;

    }
}
