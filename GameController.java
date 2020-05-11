/*
 * Erik Ibarra Hurtado
 * Samuel Bryant
 * Leonardo Loureiro
 * 
 * Sunday, May 10, 2020
 * 
 * GameController.java
 * 
 * 
 * */

package application;

import java.util.Random;
import java.io.IOException;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {
	private PlayerModel playerA;
	private PlayerModel playerB;
	private GameModel gameModel;
	
	int dieNumber;
	
	int turnCounter;
	
	int maxAmountOfRolls = 3;
	
	
	@FXML
    private Label playerAPoints;

    @FXML
    private Label numberToMatch;

    @FXML
    private Label playerBPoints;

    @FXML
    private Button rollButton;

    @FXML
    private Button doneButton;

    @FXML
    private Label promptLabel;

    @FXML
    private Label roundNumber;

    @FXML
    private ToggleButton die3ToggleButton;

    @FXML
    private ImageView die3;

    @FXML
    private ToggleButton die2ToggleButton;

    @FXML
    private ImageView die2;

    @FXML
    private ToggleButton die1ToggleButton;

    @FXML
    private ImageView die1;
    
    @FXML 
    private Label currentFirstPlayerLabel;

    @FXML
    void doneOnClick(ActionEvent event) throws InterruptedException {
    
    	// every turn we gotta enable the toggle buttons.
    	enableToggleButtons();
    	
    	// the player can only be done(click done) after one round has been played
    	if(gameModel.getNumberOfRoll() == 1) {
    		System.out.println("You need to roll at least once");
    		
    		
    	}  
    	else {

//	    	System.out.printf("%s\n", Arrays.toString(gameModel.getPlayerRolling().getDiceRolled()));
//	    	System.out.printf("%d%n",gameModel.getCurrentFirstPlayerRolling().getPlayerId());
//	    	System.out.printf("%d%n",gameModel.getCurrentSecondPlayerRolling().getPlayerId());
//	    	System.out.printf("%d%n",gameModel.getPlayerRolling().getPlayerId());
//	    	// there is an offset here that needs to get fixed
//	    	System.out.printf("%d%n",gameModel.getNumberOfRoll());
//	    	
//	    	
	    	// whenever done is clicked, the playerRolling gets changed.
	    	if(turnCounter == 1) {
	    		// here we choose who goes first again

//	    		if(gameModel.getPlayerRolling() == playerA) {
//		    		gameModel.setPlayerRolling(playerB);
//		    		
//		    	}
//		    	else if (gameModel.getPlayerRolling() == playerB) {
//		    		gameModel.setPlayerRolling(playerA);
//		    	}
//	    		if(gameModel.getPlayerRolling() == gameModel.getCurrentFirstPlayerRolling()) {
//		    		gameModel.setPlayerRolling(gameModel.getCurrentSecondPlayerRolling());
//		    		System.out.println(gameModel.getPlayerRolling());
//		    	}
//		    	else if (gameModel.getPlayerRolling() == gameModel.getCurrentSecondPlayerRolling()) {
//		    		gameModel.setPlayerRolling(gameModel.getCurrentFirstPlayerRolling());
//		    		System.out.println(gameModel.getPlayerRolling());
//		    	}
	    		System.out.printf("Enter round %d, first player is %s, %nsecond is %s%n", gameModel.getRound(), gameModel.getCurrentFirstPlayerRolling().getName(),
	    				gameModel.getCurrentSecondPlayerRolling().getName());
	    		if(playerA == gameModel.getCurrentFirstPlayerRolling()) {
		    		gameModel.setPlayerRolling(playerB);
		    		gameModel.setCurrentSecondPlayerRolling(playerB);
		    		
		    		
		    		
		    	}
		    	else if (playerB == gameModel.getCurrentFirstPlayerRolling()) {
		    		gameModel.setPlayerRolling(playerA);
		    		gameModel.setCurrentSecondPlayerRolling(playerA);
		    		System.out.println(gameModel.getPlayerRolling());
		    	}
	    		
	    		System.out.println(gameModel.getPlayerRolling().getName());
		    	// all labels should be cleared since the user is playing correctly
	    		promptLabel.setText(String.format("It's %s's turn now.", gameModel.getPlayerRolling().getName() ));
		    	
	    	
	    		
	    		// works just fine!
         		maxAmountOfRolls = gameModel.getNumberOfRoll() - 1;
	    		
	    		
	    		// reseting number of Roll in each round
	    		gameModel.setNumberOfRoll(1);
	    		
	    		
	    		// this passes the turn to player two of the current round
	    		turnCounter++;

	    		
	    		// trying to see if the information gets updated after one round is finished.
	    		System.out.printf("%d\n", gameModel.getCurrentFirstPlayerRolling().getPlayerId());
	    		System.out.printf("%d\n", gameModel.getRound());
	    		System.out.printf("%d\n", turnCounter);
	    		
	    	}
	    	else{
	    		// comparing results to see who is the winner
	    		// comparing results to see who goes first next round
	    		// comparing results to see who goes goes second next round
	    		// here we need to update the following
	
	    		//    		private PlayerModel currentFirstPlayerRolling;
	
	    		//    		private PlayerModel currentSecondPlayerRolling;
	
	    		//    		private PlayerModel playerRolling;
	    		// in this part we call the method that determines which player rolled the best and wins the round
	    		// roundWinner();
	    		
//	    		System.out.println(gameModel.getCurrentFirstPlayerRolling().getName() + " is rolling first");
//	    		System.out.println(gameModel.getCurrentSecondPlayerRolling().getName() + " is rolling first");
//	    		
//	    		int playerADiceRolled[] = {1,2,3};
//	        	int playerBDiceRolled[] = {5,4,6};
//	        	
//	        	
//	    		PlayerModel player1 = new PlayerModel("Erik Ibarra", 1, 0, playerADiceRolled);
//	    		PlayerModel player2 = new PlayerModel("Leonardo Loureiro", 1, 0, playerBDiceRolled);
//	    		
//	    		
//	    		PlayerModel winner = roundWinner(player1, player2);
	    		
	    		//System.out.printf("%s is first Player%n%s is second%n",gameModel.getCurrentFirstPlayerRolling().getName(),gameModel.getCurrentSecondPlayerRolling().getName());
 	    		PlayerModel winner = roundWinner(gameModel.getCurrentFirstPlayerRolling(), gameModel.getCurrentSecondPlayerRolling());
	    		
	    		System.out.println("REACHED");
	    		System.out.println(winner);
	    		System.out.println(winner.getName() + " Won the round");
	    		
	    		if (winner.equals(gameModel.getCurrentFirstPlayerRolling())) {
	    			gameModel.getCurrentFirstPlayerRolling().setPoints(gameModel.getCurrentFirstPlayerRolling().getPoints() + 1);
	    			System.out.println(winner.getName());
	    			if (winner.equals(playerA)) {
	    				gameModel.setCurrentFirstPlayerRolling(playerA);
	    				gameModel.setCurrentSecondPlayerRolling(playerB);
	    				//gameModel.setPlayerRolling(playerA);
	    			}
//	    			gameModel.setCurrentFirstPlayerRolling(winner);	
	    			else {
	    				gameModel.setCurrentFirstPlayerRolling(playerB);
	    				gameModel.setCurrentSecondPlayerRolling(playerA);
	    				//gameModel.setPlayerRolling(playerB);
	    			}
	    			
//	    			gameModel.setCurrentSecondPlayerRolling("");
	    		}
	    		else {
	    			gameModel.getCurrentSecondPlayerRolling().setPoints(gameModel.getCurrentSecondPlayerRolling().getPoints() + 1);
	    			System.out.println(winner.getName());
	    			gameModel.setCurrentFirstPlayerRolling(gameModel.getCurrentSecondPlayerRolling());
	    			gameModel.setCurrentSecondPlayerRolling(gameModel.getCurrentFirstPlayerRolling());
	    			
	    			//gameModel.setPlayerRolling(winner);
	    			
	    		}
	    		// by this point of time we have determined the winner of the round by calling roundWinner
	    		
	    		playerAPoints.setText(Integer.toString(playerA.getPoints()));
	    		playerBPoints.setText(Integer.toString(playerB.getPoints()));
	    		
	    		
	    		
	    		
	    		
	    		
	    		// DUMMY variable for now, whoever wins, is the first player
	    		currentFirstPlayerLabel.setText(String.format("%s", gameModel.getCurrentFirstPlayerRolling().getName()));
	    		
	    		promptLabel.setText(String.format("It's %s's turn now.", winner.getName() ));
	    		
	    		// round goes up by one every time a round is played
	    		gameModel.setRound(gameModel.getRound() + 1);
		    
	    		
	    		
		    	roundNumber.setText(String.format("Round %d", gameModel.getRound()));
		    	turnCounter = 1;	
		    	
		    	// the maxAmountOfrolls goes back to 3 after a round has been played
         		maxAmountOfRolls = 3;
		    	
		    	// reseting number of Roll in each round
	    		gameModel.setNumberOfRoll(1);
	    		
	    		
	    		if (gameModel.getRound() == 12) {
	    			endGame();
	    		}
	    		
	    		
	    	}
    	
    	
    	}
    	
    }
 
    @FXML
    void rollOnClick(ActionEvent event) {
    	
    	// maxAmountOfRolls varies every round
    	if(gameModel.getNumberOfRoll() > maxAmountOfRolls) {
    		promptLabel.setText("You already rolled the maximun amount of times, hit done." + maxAmountOfRolls);
    		System.out.println("You already rolled the maximun amount of times");
    	} 
    	else {   		
    		
    		// during the first roll of each player, all three buttons need to be selected.
    		if(gameModel.getNumberOfRoll() == 1  && !(die1ToggleButton.isSelected() && die2ToggleButton.isSelected() && die3ToggleButton.isSelected())) {
    			promptLabel.setText("It's the first roll. You need to select/roll all three buttons!");
    			System.out.println("You need to select all three buttons!");
    			
    		} 
    		// at least one die has to be selected if you want to roll again.
    		else if(!die1ToggleButton.isSelected() && !die2ToggleButton.isSelected() && !die3ToggleButton.isSelected()) {
	    		System.out.print("You need to select at least one die\n");
	    		
	    	}
	    	else {
	    		// all labels should be cleared since the use is playing correctly
	    		promptLabel.setText("");
		    	
	    		// disable buttons that the user did not choose
		    	disableToggleButtons();
		    	if(die1ToggleButton.isSelected()) {
		    		String dieString = rollDie();	
		    		// calling setImages to set the images to the new values
		            setImages(dieString, die1);
		            // updating gameModel
		            int array[]= gameModel.getPlayerRolling().getDiceRolled();
		            
		            array[0] = dieNumber;
		            
		            
		    	}
		    	if(die2ToggleButton.isSelected()) {
		    		String dieString = rollDie();	
		    		// calling setImages to set the images to the new values
		            setImages(dieString, die2);
		            
		            int array[]= gameModel.getPlayerRolling().getDiceRolled();
		            
		            array[1] = dieNumber;
		    	}
		    	if(die3ToggleButton.isSelected()) {
		    		String dieString = rollDie();	
		    		// calling setImages to set the images to the new values
		            setImages(dieString, die3);
		            
	            	int array[]= gameModel.getPlayerRolling().getDiceRolled();
		            
		            array[2] = dieNumber;
		    	}
		    	// updating round
		    	gameModel.setNumberOfRoll(gameModel.getNumberOfRoll() + 1);
		    	
		    	System.out.println(gameModel.getNumberOfRoll());
		    	
//		    	System.out.println(gameModel.getPlayerRolling().getName());

	    	}
	    	
    		
	    	
    	}
    	
    	
    }
    	
    
    @FXML
    public void initialize() {
    	// creating default images
    	Image die1Image = new Image("images/one.png", 60, 60 , true, true);
    	Image die2Image = new Image("images/one.png", 60, 60 , true, true);
    	Image die3Image = new Image("images/one.png", 60, 60 , true, true);
    	
    	// default images of one.png
    	die1.setImage(die1Image);
    	die2.setImage(die2Image);
    	die3.setImage(die3Image);
    
    	// each player will contain three dice 
    	int playerADiceRolled[] = new int[3];
    	int playerBDiceRolled[] = new int[3];
    	
    	// initialize playerA with (name, playerId, 0 points by default, int[] diceRolled) 
    	playerA = new PlayerModel("Player A", 1, 0, playerADiceRolled);
    	
    	// initialize playerB with (name, playerId, 0 points by default, int[] diceRolled) 
    	playerB = new PlayerModel("Player B", 2, 0, playerBDiceRolled);
    	
    	
    	// initialize game model
    	//(int round, int numberOfRoll, PlayerModel currentFirstPlayerRollingprivate, PlayerModel currentSecondPlayerRolling, private PlayerModel playerRolling;)
    	gameModel = new GameModel(1, 1, playerA, playerB, playerA);
    	
    	// initialize turn counter to one, 
    	// turn counter only has two states, 1, and 2.
    	// if counter == 1, then currentFirstPlayerRollingprivate is rolling,
    	// if counter == 2, then currentSecondPlayerRolling
    	turnCounter = 1;
    	
    }
    
    
    public String rollDie() {
    	Random random = new Random();
    	
    	dieNumber = random.nextInt(6) + 1;
    	
    	System.out.println(dieNumber);
    	String dieString = "one";
     

    	switch(dieNumber) {
	        case 1:
	           dieString = "one";	            
	            break;
	        case 2:
	            dieString = "two";
	            break;
	        case 3:
	            dieString = "three";
	            break;
	        case 4:
	            dieString = "four";
	            break;
	        case 5:
	        	dieString = "five";
	            break;
	        default:
	        	dieString = "six";
	            break;
    	} 
    	
    	return dieString;
    	
    
 
    }
    public void setImages(String dieString, ImageView dieImageView) {
        
        Image dieImage = new Image("images/" + dieString + ".png", 60, 60 , true, true);
        
        dieImageView.setImage(dieImage);
       
    }
    
    // this method disables the togglebuttons the user did not roll again
    public void disableToggleButtons() {
    	
    	boolean die1State = die1ToggleButton.isSelected();
    	boolean die2State = die2ToggleButton.isSelected();
    	boolean die3State = die3ToggleButton.isSelected();
    	

    	if(die1State == true && die2State == true && die3State == false ) {
    		System.out.print("toggle is selected\n");
    		die3ToggleButton.setDisable(true);
    			
    	}
    	if(die1State == true && die2State == false && die3State == false) {
    		
    		System.out.print("toggle is selected\n");
    		die2ToggleButton.setDisable(true);
    		die3ToggleButton.setDisable(true);
    	}
    	if(die1State == false && die2State == true && die3State == false) {
    		
    		System.out.print("toggle is selected\n");
    		die1ToggleButton.setDisable(true);
    		die3ToggleButton.setDisable(true);
    	}
    	if(die1State == false && die2State == true && die3State == true) {
    		
    		System.out.print("toggle is selected\n");
    		die1ToggleButton.setDisable(true);

    	}
    	if(die1State == false && die2State == false && die3State == true) {
    		
    		System.out.print("toggle is selected\n");
    		die1ToggleButton.setDisable(true);
    		die2ToggleButton.setDisable(true);

    	}
    	if(die1State == true && die2State == false && die3State == true) {
    		
    		System.out.print("toggle is selected\n");
    		die2ToggleButton.setDisable(true);

    	}
    	
    	
    	
    	
    }
    public void enableToggleButtons(){
    	die1ToggleButton.setDisable(false);
    	die2ToggleButton.setDisable(false);
    	die3ToggleButton.setDisable(false);
    	
    }

    // this method will choose the player the wins the round
    public PlayerModel roundWinner(PlayerModel firstPlayer, PlayerModel secondPlayer) {
    	
//    	int firstPlayerArray[] = firstPlayer.getDiceRolled();
//    	int secondPlayerArray[] = secondPlayer.getDiceRolled();
//    	
//    	// sorting array from lowest to highest
//    	Arrays.sort(firstPlayerArray);
//    	Arrays.sort(secondPlayerArray);
//    	System.out.printf("%s\n%s\n", Arrays.toString(gameModel.getCurrentFirstPlayerRolling().getDiceRolled()), Arrays.toString(gameModel.getCurrentSecondPlayerRolling().getDiceRolled()));
//    	
//    	// first we check if arrays are equal, if so, first player wins
//    	if(Arrays.equals(firstPlayerArray, secondPlayerArray)) {
//    		System.out.print("First player wins (oversimplification)");
//    		// here we select a winner,
//    		// we might even choose to return it.
//    		
//    	}
    	// rest of the rules are labeled here
//    	else if() {
//    		
//    		
//    	}
    	
    	int firstPlayerArray[] = firstPlayer.getDiceRolled();
		int secondPlayerArray[] = secondPlayer.getDiceRolled();
		int HighestScore[] = {1, 2, 4};

		// sorting array from lowest to highest
		Arrays.sort(firstPlayerArray);
		//System.out.printf("%d%d%d",firstPlayerArray[0],firstPlayerArray[1],firstPlayerArray[2]);
		Arrays.sort(secondPlayerArray);
		
		int playerOneRollOne = firstPlayerArray[0];
		int playerOneRollTwo = firstPlayerArray[1];
		int playerOneRollThree = firstPlayerArray[2];

		int playerTwoRollOne = secondPlayerArray[0];
		int playerTwoRollTwo = secondPlayerArray[1];
		int playerTwoRollThree = secondPlayerArray[2];

//		firstPlayer.setPoints(0);
//		firstPlayer.setPoints(0);

//		String player1ScoreType = "";
//		String player2ScoreType = "";
//		String temporalScore1 = "";
//		String temporalScore2 = "";
		
		PlayerModel winner = null;
		

		//System.out.printf("%s\n%s\n", Arrays.toString(gameModel.getCurrentFirstPlayerRolling().getDiceRolled()), Arrays.toString(gameModel.getCurrentSecondPlayerRolling().getDiceRolled()));
		System.out.printf("%s\n%s\n", Arrays.toString(firstPlayer.getDiceRolled()), Arrays.toString(secondPlayer.getDiceRolled()));
		
//		// first we check if arrays are equal, if so, first player wins
		if(Arrays.equals(firstPlayerArray, secondPlayerArray)) {
			System.out.print("First player wins (oversimplification)");
			winner = firstPlayer;
			//return firstPlayer;
		}
		else if( Arrays.equals(HighestScore, firstPlayerArray)) {
			System.out.println("Entered");
			winner = firstPlayer;
			//return firstPlayer;
		}
		else if( Arrays.equals(HighestScore, secondPlayerArray)) {
			winner = secondPlayer;
			//return secondPlayer;
		}
		// check if they're both triple
//		else if (((playerOneRollOne ==  playerOneRollTwo) &&  (playerOneRollOne ==  playerOneRollThree)) && 
//				((playerTwoRollOne ==  playerTwoRollTwo) &&  (playerTwoRollOne ==  playerTwoRollThree))) {
//			if(playerOneRollOne > playerTwoRollOne) {
//				System.out.printf("%d, %d",playerOneRollOne, playerTwoRollOne);
//				System.out.println("First Player won");
//				winner = firstPlayer;
//				//return firstPlayer;
//			}
//			else {
//				System.out.printf("%d, %d",playerOneRollOne, playerTwoRollOne);
//				System.out.println("Second Player won!!!");
//				winner = secondPlayer;
//				//return secondPlayer;
//			}
//					
//		}		
		// check they are not triples
		else if( !((playerOneRollOne ==  playerOneRollTwo) &&  (playerOneRollOne ==  playerOneRollThree)) && 
				 !((playerTwoRollOne ==  playerTwoRollTwo) &&  (playerTwoRollOne ==  playerTwoRollThree)) ) 
		{
		
				
			
			// check if they are not doubles
			if ( !((playerOneRollOne ==  playerOneRollTwo) || (playerOneRollOne ==  playerOneRollThree) || (playerOneRollTwo == playerOneRollThree)) &&
				 !((playerTwoRollOne ==  playerTwoRollTwo) || (playerTwoRollOne ==  playerTwoRollThree) || (playerTwoRollTwo == playerTwoRollThree))) 
			{
				
				
				
				if(playerOneRollThree > playerTwoRollThree) {
					winner = firstPlayer;
					//return firstPlayer;
				}
				else if(playerOneRollThree == playerTwoRollThree) {
					if(playerOneRollTwo > playerTwoRollTwo) {
						winner = firstPlayer;
						//return firstPlayer;
					}
					else if (playerOneRollTwo == playerTwoRollTwo) {
						if (playerOneRollOne > playerTwoRollOne) {
							winner = firstPlayer;
							
						}
						else {
							//System.out.print("NOTHING WAS ENTERED");
							winner = secondPlayer;
						}
						
					}
					else {
						//System.out.print("NOTHING WAS ENTERED");
						winner = secondPlayer;
						//return secondPlayer;
					}
					
					
				}
				else {
					
					winner = secondPlayer;
					//return secondPlayer;
				}
				
				
			}
			// checks for two doubles
			else if(((playerOneRollOne ==  playerOneRollTwo) || (playerOneRollOne ==  playerOneRollThree) || (playerOneRollTwo == playerOneRollThree))
					&& ((playerTwoRollOne ==  playerTwoRollTwo) || (playerTwoRollOne ==  playerTwoRollThree) || (playerTwoRollTwo == playerTwoRollThree))) {
				//System.out.println("Entered doublues");
				if(playerOneRollThree > playerTwoRollThree) {
					System.out.println("It worked");
					winner = firstPlayer;
					//return firstPlayer;
				}
				else if(playerOneRollThree == playerTwoRollThree) {
					if(playerOneRollTwo == playerTwoRollTwo) {
						if(playerOneRollOne > playerTwoRollOne) {
							winner = firstPlayer;
							//return firstPlayer;
						}
						else {
							winner = secondPlayer;
							//return secondPlayer;
						}
				
					}
					else if(playerOneRollTwo > playerTwoRollTwo) {
						winner = firstPlayer;
						//return firstPlayer;
					}
					else {
						winner = secondPlayer;
						//return secondPlayer;
					}
					
					
					
				}
				else {
					
					winner = secondPlayer;
					//return secondPlayer;
				}
				
			}// checking if this is a double and the other one is not
			// HERE WE ADD
			else if (((playerOneRollOne ==  playerOneRollTwo) || (playerOneRollOne ==  playerOneRollThree) || (playerOneRollTwo == playerOneRollThree)) &&
					((playerTwoRollThree >  playerTwoRollTwo) && (playerTwoRollTwo >  playerTwoRollOne) )){
				System.out.print("ENTERRRRREED");
				winner = firstPlayer;
				//return secondPlayer;
			}
			else {
				winner = secondPlayer;
			}
			System.out.print("NOTHING WAS ENTERED");
			
		}
		// check if they're both triple
		else if (((playerOneRollOne ==  playerOneRollTwo) &&  (playerOneRollOne ==  playerOneRollThree)) && 
				 ((playerTwoRollOne ==  playerTwoRollTwo) &&  (playerTwoRollOne ==  playerTwoRollThree))) {
			if(playerOneRollOne > playerTwoRollOne) {
				winner = firstPlayer;
				//return firstPlayer;
			}
			else {
				winner = secondPlayer;
				//return secondPlayer;
			}
			
		}
		else if (((playerOneRollOne ==  playerOneRollTwo) &&  (playerOneRollOne ==  playerOneRollThree)) && 
				 !((playerTwoRollOne ==  playerTwoRollTwo) &&  (playerTwoRollOne ==  playerTwoRollThree))) {
			
			winner = firstPlayer;
			//return firstPlayer;
			
			
		}
		else if (!((playerOneRollOne ==  playerOneRollTwo) &&  (playerOneRollOne ==  playerOneRollThree)) && 
				 ((playerTwoRollOne ==  playerTwoRollTwo) &&  (playerTwoRollOne ==  playerTwoRollThree))) {
			winner = secondPlayer;
			
		}
//		// checks for two doubles
//		else if(((playerOneRollOne ==  playerOneRollTwo) || (playerOneRollOne ==  playerOneRollThree) || (playerOneRollTwo == playerOneRollThree))
//				&& ((playerTwoRollOne ==  playerTwoRollTwo) || (playerTwoRollOne ==  playerTwoRollThree) || (playerTwoRollTwo == playerTwoRollThree))) {
//			//System.out.println("Entered doublues");
//			if(playerOneRollThree > playerTwoRollThree) {
//				System.out.println("Entered doublues");
//				winner = firstPlayer;
//				//return firstPlayer;
//			}
//			else if(playerOneRollThree == playerTwoRollThree) {
//				if(playerOneRollTwo == playerTwoRollTwo) {
//					if(playerOneRollOne > playerTwoRollOne) {
//						winner = firstPlayer;
//						//return firstPlayer;
//					}
//					else {
//						winner = secondPlayer;
//						//return secondPlayer;
//					}
//			
//				}
//				else if(playerOneRollTwo > playerTwoRollTwo) {
//					winner = firstPlayer;
//					//return firstPlayer;
//				}
//				else {
//					winner = secondPlayer;
//					//return secondPlayer;
//				}
//				
//				
//				
//			}
//			else {
//				
//				winner = secondPlayer;
//				//return secondPlayer;
//			}
//			
//		}// checking if this is a double and the other one is not
//		else {
//			System.out.println("LASE ELSE ENTRED");
//		}
//		if(1==1) {
//			System.out.println("1 == 1");
//		}
//		else if(true){
//			System.out.print("ENTERRRRREED TRUE");
//			//winner = firstPlayer;
//			//return firstPlayer;
//			
//		} 
//		else if (!((playerOneRollOne ==  playerOneRollTwo) || (playerOneRollOne ==  playerOneRollThree) || (playerOneRollTwo == playerOneRollThree)) &&
//				((playerTwoRollOne ==  playerTwoRollTwo) || (playerTwoRollOne ==  playerTwoRollThree) || (playerTwoRollTwo == playerTwoRollThree))){
//			System.out.print("ENTERRRRREED");
//			winner = secondPlayer;
//			//return secondPlayer;
//		}
//		System.out.print("ENTERRRRREED TRUE");
//		
//		
		return winner;
		
		
		
		
//		if (winner == null) {
//			System.out.println(winner);
//		}
		
		//System.out.printf("%s\n%s\n", Arrays.toString(gameModel.getCurrentFirstPlayerRolling().getDiceRolled()), Arrays.toString(gameModel.getCurrentSecondPlayerRolling().getDiceRolled()));
		//System.out.printf("%d%d%d", winner.getDiceRolled()[0], winner.getDiceRolled()[1], winner.getDiceRolled()[2]);
		
		//return winner;
		//See if player one has double or triple
		
    	
    	
    	

    	
    }
    
    public void endGame() throws InterruptedException  {
    	die1ToggleButton.setDisable(true);
    	die2ToggleButton.setDisable(true);
    	die3ToggleButton.setDisable(true);
    	rollButton.setDisable(true);
    	doneButton.setDisable(true);
    	
    	currentFirstPlayerLabel.setText("");
    	roundNumber.setText("");
    	
    	if (playerA.getPoints() > playerB.getPoints()) {
    		promptLabel.setText(String.format("The winner is %s",playerA.getName())  );
    	}
    	else {
    		promptLabel.setText(String.format("The winner is %s",playerB.getName())  );
    	}
    	
//    	Thread.sleep(5000);
    	//Platform.exit();
    	
    	
    }
	

    
    
}
