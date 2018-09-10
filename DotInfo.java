// Author: Bryan Battershill
// Student number: 300014415
// Course: ITI 1121-B
// Assignment: 2

/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE
	
	private int x;
	private int y;
	private boolean mined;
	private boolean covered;
	private int neighbooringMines;
	private boolean wasClicked;
	
    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){

    // ADD YOU CODE HERE
		this.x = x;
		this.y = y;
		mined = false;
		covered = true;
		neighbooringMines = 0;
		wasClicked = false;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){

    // ADD YOU CODE HERE
		return x;

    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

    // ADD YOU CODE HERE
		return y;

    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

    // ADD YOU CODE HERE
		mined = true;

    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

    // ADD YOU CODE HERE
		return mined;
    }


    /**
     * Setter for covered
     */
    public void uncover() {

    // ADD YOU CODE HERE
		covered = false;

    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
		return covered;

    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE
		wasClicked = true;

    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

    // ADD YOU CODE HERE
		return wasClicked;

    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighbooring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE
		this.neighbooringMines = neighbooringMines;

    }

    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {

    // ADD YOU CODE HERE
		return neighbooringMines;

    }

 }
