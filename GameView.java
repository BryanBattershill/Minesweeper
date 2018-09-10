// Author: Bryan Battershill
// Student number: 300014415
// Course: ITI 1121-B
// Assignment: 2


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
	private DotButton[][] board;
	private GameModel gameModel;
	private JLabel nbreOfStepsLabel;
	private JLabel nbreOfMines;
	private int flagged;
    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */
	@SuppressWarnings("deprecation")
    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
	
		super("MineSweeper ITI1121");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.gameModel = gameModel;
		flagged = 0;
		MouseListener mouseListener = new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				int modifiers = mouseEvent.getModifiers();
				if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
					DotButton temp = (DotButton) mouseEvent.getSource();
					if (temp.isEnabled()){
						temp.setEnabled(false);
						flagged++;
					}
					else{
						temp.setEnabled(true);
						flagged--;
					}
					nbreOfMines.setText("Mines remaining: " + (gameModel.getNumberOfMines()-flagged));
				}
			}
        };
		board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()];
		JPanel buttonPanel = new JPanel(new GridLayout(0, gameModel.getWidth()));
		for (int b = 0; b < gameModel.getHeigth(); b++){
			for (int a = 0; a < gameModel.getWidth(); a++){
				//System.out.println(a + " " + b);
				//DotButton temp = new DotButton(a,b,11);
				board[a][b] = new DotButton(a,b,11);
				board[a][b].setBorder(null);
				board[a][b].setActionCommand(a+"_"+b);
				board[a][b].addActionListener(gameController);
				board[a][b].addMouseListener(mouseListener);
				buttonPanel.add(board[a][b]);
				
			}
		}
		buttonPanel.setBorder(new EmptyBorder(28, 28, 28, 28));
		add(buttonPanel);
		JButton reset = new JButton("Reset");
		reset.addActionListener(gameController);
		JButton quit = new JButton("Quit");
		quit.addActionListener(gameController);
		nbreOfStepsLabel = new JLabel("Number of steps: 0");
		nbreOfMines = new JLabel("Mines remaining: " + gameModel.getNumberOfMines());
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.WHITE);
		controlPanel.add(nbreOfStepsLabel);
		controlPanel.add(reset);
		controlPanel.add(quit);
		controlPanel.add(nbreOfMines);
		add(controlPanel, BorderLayout.SOUTH);
		setResizable(false);
		
		pack();

    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
		for (int a = 0; a<gameModel.getWidth(); a++){
			for (int b = 0; b < gameModel.getHeigth(); b++){
				if (gameModel.isCovered(a,b) == false){
					if(gameModel.isMined(a,b) == true){
						board[a][b].setIconNumber(9);
						if(gameModel.hasBeenClicked(a,b) == true){
							board[a][b].setIconNumber(10);
						}
					}else{
						board[a][b].setIconNumber(gameModel.getNeighbooringMines(a,b));
					}
				}else{
					board[a][b].setIconNumber(11);
				}
			}
		}
		nbreOfStepsLabel.setText("Number of steps: " + gameModel.getNumberOfSteps());
		if (gameModel.isFinished() == true){
			int n = JOptionPane.showConfirmDialog(null, "Play again", "Play again?", JOptionPane.YES_NO_OPTION);
			if (n == 0){
				this.reset();
				gameModel.reset();
				update();
			}else{
				System.exit(0);
			}
		}
    }
	
	/**
	 * Resets the flags on the board and re-enables the tiles
	 *
	 */
	public void reset(){
		flagged = 0;
		nbreOfMines.setText("Mines remaining: " + gameModel.getNumberOfMines());
		for (int a = 0; a<gameModel.getWidth(); a++){
			for (int b = 0; b<gameModel.getHeigth(); b++){
				board[a][b].setEnabled(true);
			}
		}
	}
    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE
		return board[i][j].getIconNumber();

    }
	

}
