/*
 * CSCV335 
 * Final Capstone
 * 
 * Erik Ibarra Hurtado
 * Samuel Bryant
 * Leonardo Loureiro
 * 
 * Sunday, May 10, 2020
 * 
 * GameController.java
 * 
 * This is the game controller for the capstone. The logic of the game is determined in this file.
 * One of the most important functions of this file is roundWinner(), which contains the algorithm 
 * that determines the winner of each round.
 * 
 * */

package application;

import java.util.Random;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {
	// creating game control objects of dice game
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
		if (gameModel.getNumberOfRoll() == 1) {
			System.out.println("You need to roll at least once");
		} 
		else {
			// whenever done is clicked, the playerRolling gets changed.
			if (turnCounter == 1) {
				
				// here we choose who goes first again
				System.out.printf("Enter round %d, first player is %s, %nsecond is %s%n", gameModel.getRound(),
						gameModel.getCurrentFirstPlayerRolling().getName(),
						gameModel.getCurrentSecondPlayerRolling().getName());
				if (playerA == gameModel.getCurrentFirstPlayerRolling()) {
					gameModel.setPlayerRolling(playerB);
					gameModel.setCurrentSecondPlayerRolling(playerB);

				} else if (playerB == gameModel.getCurrentFirstPlayerRolling()) {
					gameModel.setPlayerRolling(playerA);
					gameModel.setCurrentSecondPlayerRolling(playerA);
					System.out.println(gameModel.getPlayerRolling());
				}

				System.out.println(gameModel.getPlayerRolling().getName());
				// all labels should be cleared since the user is playing correctly
				promptLabel.setText(String.format("It's %s's turn now.", gameModel.getPlayerRolling().getName()));

				maxAmountOfRolls = gameModel.getNumberOfRoll() - 1;

				// reseting number of Roll in each round
				gameModel.setNumberOfRoll(1);

				// this passes the turn to player two of the current round
				turnCounter++;

			} else {

				// here we call roundWinner() to determine the winner of each round.
				PlayerModel winner = roundWinner(gameModel.getCurrentFirstPlayerRolling(),
						gameModel.getCurrentSecondPlayerRolling());

				if (winner.equals(gameModel.getCurrentFirstPlayerRolling())) {
					gameModel.getCurrentFirstPlayerRolling()
							.setPoints(gameModel.getCurrentFirstPlayerRolling().getPoints() + 1);
				
					// this is the logic for determining who becomes the first player for each round.
					if (winner.equals(playerA)) {
						gameModel.setCurrentFirstPlayerRolling(playerA);
						gameModel.setCurrentSecondPlayerRolling(playerB);
					}
					else {
						gameModel.setCurrentFirstPlayerRolling(playerB);
						gameModel.setCurrentSecondPlayerRolling(playerA);
					}

				} else {
					gameModel.getCurrentSecondPlayerRolling()
							.setPoints(gameModel.getCurrentSecondPlayerRolling().getPoints() + 1);
					System.out.println(winner.getName());
					gameModel.setCurrentFirstPlayerRolling(gameModel.getCurrentSecondPlayerRolling());
					gameModel.setCurrentSecondPlayerRolling(gameModel.getCurrentFirstPlayerRolling());
				}
				// by this point of time we have determined the winner of the round by calling
				// roundWinner

				// Here we update the GUI for each round.
				playerAPoints.setText(Integer.toString(playerA.getPoints()));
				playerBPoints.setText(Integer.toString(playerB.getPoints()));

				// whoever wins becomes the first player
				currentFirstPlayerLabel
						.setText(String.format("%s", gameModel.getCurrentFirstPlayerRolling().getName()));

				promptLabel.setText(String.format("It's %s's turn now.", winner.getName()));

				// round goes up by one every time a round is played
				gameModel.setRound(gameModel.getRound() + 1);

				roundNumber.setText(String.format("Round %d", gameModel.getRound()));
				
				// turn counter passes the turn to the first player in the next round.
				turnCounter = 1;

				// the maxAmountOfrolls goes back to 3 after a round has been played
				maxAmountOfRolls = 3;

				// reseting number of Roll in each round
				gameModel.setNumberOfRoll(1);

				// this section ends the game once 12 rounds have been reached.
				if (gameModel.getRound() == 12) {
					endGame();
				}

			}

		}

	}

	@FXML
	void rollOnClick(ActionEvent event) {

		// maxAmountOfRolls varies every round
		if (gameModel.getNumberOfRoll() > maxAmountOfRolls) {
			promptLabel.setText(String.format("You already rolled the maximun amount of times (%d times), click done.", maxAmountOfRolls));
			System.out.println("You already rolled the maximun amount of times");
		} else {

			// during the first roll of each player, all three buttons need to be selected.
			if (gameModel.getNumberOfRoll() == 1 && !(die1ToggleButton.isSelected() && die2ToggleButton.isSelected()
					&& die3ToggleButton.isSelected())) {
				promptLabel.setText("It's the first roll. You need to select/roll all three buttons!");
				System.out.println("You need to select all three buttons!");

			}
			// at least one die has to be selected if you want to roll again.
			else if (!die1ToggleButton.isSelected() && !die2ToggleButton.isSelected()
					&& !die3ToggleButton.isSelected()) {
				System.out.print("You need to select at least one die\n");

			} else {
				// all labels should be cleared since the user is playing correctly
				promptLabel.setText("");

				// disable buttons that the user did not choose
				disableToggleButtons();
				if (die1ToggleButton.isSelected()) {
					String dieString = rollDie();
					// calling setImages to set the images to the new values
					setImages(dieString, die1);
					// updating gameModel
					int array[] = gameModel.getPlayerRolling().getDiceRolled();

					array[0] = dieNumber;

				}
				if (die2ToggleButton.isSelected()) {
					String dieString = rollDie();
					// calling setImages to set the images to the new values
					setImages(dieString, die2);
					// updating gameModel
					int array[] = gameModel.getPlayerRolling().getDiceRolled();

					array[1] = dieNumber;
				}
				if (die3ToggleButton.isSelected()) {
					String dieString = rollDie();
					// calling setImages to set the images to the new values
					setImages(dieString, die3);
					// updating gameModel
					int array[] = gameModel.getPlayerRolling().getDiceRolled();

					array[2] = dieNumber;
				}
				// updating round
				gameModel.setNumberOfRoll(gameModel.getNumberOfRoll() + 1);

			}

		}

	}

	@FXML
	public void initialize() {
		// creating default images
		Image die1Image = new Image("images/one.png", 60, 60, true, true);
		Image die2Image = new Image("images/one.png", 60, 60, true, true);
		Image die3Image = new Image("images/one.png", 60, 60, true, true);

		// default images of one.png
		die1.setImage(die1Image);
		die2.setImage(die2Image);
		die3.setImage(die3Image);

		// each player will contain three dice
		int playerADiceRolled[] = new int[3];
		int playerBDiceRolled[] = new int[3];

		// initialize playerA with (name, playerId, 0 points by default, int[]
		// diceRolled)
		playerA = new PlayerModel("Player A", 1, 0, playerADiceRolled);

		// initialize playerB with (name, playerId, 0 points by default, int[]
		// diceRolled)
		playerB = new PlayerModel("Player B", 2, 0, playerBDiceRolled);

		// initialize game model
		// (int round, int numberOfRoll, PlayerModel currentFirstPlayerRollingprivate,
		// PlayerModel currentSecondPlayerRolling, private PlayerModel playerRolling;)
		gameModel = new GameModel(1, 1, playerA, playerB, playerA);

		// initialize turn counter to one,
		// turn counter only has two states, 1, and 2.
		// if counter == 1, then currentFirstPlayerRollingprivate is rolling,
		// if counter == 2, then currentSecondPlayerRolling
		turnCounter = 1;

	}
	// This function returns the string number of the die.
	public String rollDie() {
		Random random = new Random();

		dieNumber = random.nextInt(6) + 1;

		String dieString = "one";

		switch (dieNumber) {
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

	// this function sets the images of the dice to the dice rolled.
	public void setImages(String dieString, ImageView dieImageView) {

		Image dieImage = new Image("images/" + dieString + ".png", 60, 60, true, true);

		dieImageView.setImage(dieImage);

	}

	// this method disables the togglebuttons the user did not roll again
	public void disableToggleButtons() {

		boolean die1State = die1ToggleButton.isSelected();
		boolean die2State = die2ToggleButton.isSelected();
		boolean die3State = die3ToggleButton.isSelected();

		if (die1State == true && die2State == true && die3State == false) {
			die3ToggleButton.setDisable(true);
		}
		if (die1State == true && die2State == false && die3State == false) {
			die2ToggleButton.setDisable(true);
			die3ToggleButton.setDisable(true);
		}
		if (die1State == false && die2State == true && die3State == false) {
			die1ToggleButton.setDisable(true);
			die3ToggleButton.setDisable(true);
		}
		if (die1State == false && die2State == true && die3State == true) {
			die1ToggleButton.setDisable(true);
		}
		if (die1State == false && die2State == false && die3State == true) {
			die1ToggleButton.setDisable(true);
			die2ToggleButton.setDisable(true);
		}
		if (die1State == true && die2State == false && die3State == true) {
			die2ToggleButton.setDisable(true);
		}

	}

	// this function enables the toggle buttons
	public void enableToggleButtons() {
		die1ToggleButton.setDisable(false);
		die2ToggleButton.setDisable(false);
		die3ToggleButton.setDisable(false);

	}

	// this method will determine the winner of each round.
	public PlayerModel roundWinner(PlayerModel firstPlayer, PlayerModel secondPlayer) {

		// we extract the player's rolls into int[] arrays.
		int firstPlayerArray[] = firstPlayer.getDiceRolled();
		int secondPlayerArray[] = secondPlayer.getDiceRolled();
		int HighestScore[] = { 1, 2, 4 };

		// sorting array from lowest to highest
		Arrays.sort(firstPlayerArray);
		Arrays.sort(secondPlayerArray);

		int playerOneRollOne = firstPlayerArray[0];
		int playerOneRollTwo = firstPlayerArray[1];
		int playerOneRollThree = firstPlayerArray[2];

		int playerTwoRollOne = secondPlayerArray[0];
		int playerTwoRollTwo = secondPlayerArray[1];
		int playerTwoRollThree = secondPlayerArray[2];


		// creating the winner variable 
		PlayerModel winner = null;

		// first we check if arrays are equal, if so, first player wins
		if (Arrays.equals(firstPlayerArray, secondPlayerArray)) {
			winner = firstPlayer;
		} else if (Arrays.equals(HighestScore, firstPlayerArray)) {
			winner = firstPlayer;
		} else if (Arrays.equals(HighestScore, secondPlayerArray)) {
			winner = secondPlayer;
		}
		// check they are not triples
		else if (!((playerOneRollOne == playerOneRollTwo) && (playerOneRollOne == playerOneRollThree))
				&& !((playerTwoRollOne == playerTwoRollTwo) && (playerTwoRollOne == playerTwoRollThree))) {

			// check if they are not doubles
			if (!((playerOneRollOne == playerOneRollTwo) || (playerOneRollOne == playerOneRollThree)
					|| (playerOneRollTwo == playerOneRollThree))
					&& !((playerTwoRollOne == playerTwoRollTwo) || (playerTwoRollOne == playerTwoRollThree)
							|| (playerTwoRollTwo == playerTwoRollThree))) {
				if (playerOneRollThree > playerTwoRollThree) {
					winner = firstPlayer;
				} else if (playerOneRollThree == playerTwoRollThree) {
					if (playerOneRollTwo > playerTwoRollTwo) {
						winner = firstPlayer;
					} else if (playerOneRollTwo == playerTwoRollTwo) {
						if (playerOneRollOne > playerTwoRollOne) {
							winner = firstPlayer;
						} else {
							winner = secondPlayer;
						}
					} else {
						winner = secondPlayer;
					}

				} else {
					winner = secondPlayer;
				}
			}
			// checks for two doubles
			else if (((playerOneRollOne == playerOneRollTwo) || (playerOneRollOne == playerOneRollThree)
					|| (playerOneRollTwo == playerOneRollThree))
					&& ((playerTwoRollOne == playerTwoRollTwo) || (playerTwoRollOne == playerTwoRollThree)
							|| (playerTwoRollTwo == playerTwoRollThree))) {
				if (playerOneRollThree > playerTwoRollThree) {
					winner = firstPlayer;
				} else if (playerOneRollThree == playerTwoRollThree) {
					if (playerOneRollTwo == playerTwoRollTwo) {
						if (playerOneRollOne > playerTwoRollOne) {
							winner = firstPlayer;
						} else {
							winner = secondPlayer;
						}

					} else if (playerOneRollTwo > playerTwoRollTwo) {
						winner = firstPlayer;
					} else {
						winner = secondPlayer;
					}
				} else {
					winner = secondPlayer;
				}

			} // checking if this is a double and the other one is not
			else if (((playerOneRollOne == playerOneRollTwo) || (playerOneRollOne == playerOneRollThree)
					|| (playerOneRollTwo == playerOneRollThree))
					&& ((playerTwoRollThree > playerTwoRollTwo) && (playerTwoRollTwo > playerTwoRollOne))) {
				winner = firstPlayer;
			} else {
				winner = secondPlayer;
			}

		}
		// check if they're both triple
		else if (((playerOneRollOne == playerOneRollTwo) && (playerOneRollOne == playerOneRollThree))
				&& ((playerTwoRollOne == playerTwoRollTwo) && (playerTwoRollOne == playerTwoRollThree))) {
			if (playerOneRollOne > playerTwoRollOne) {
				winner = firstPlayer;
			} else {
				winner = secondPlayer;
			}
		// check if one is a triple and the other one is not.
		} else if (((playerOneRollOne == playerOneRollTwo) && (playerOneRollOne == playerOneRollThree))
				&& !((playerTwoRollOne == playerTwoRollTwo) && (playerTwoRollOne == playerTwoRollThree))) {
			winner = firstPlayer;
		// check if one is a triple and the other is is not. Flipped else if from the past one.
		} else if (!((playerOneRollOne == playerOneRollTwo) && (playerOneRollOne == playerOneRollThree))
				&& ((playerTwoRollOne == playerTwoRollTwo) && (playerTwoRollOne == playerTwoRollThree))) {
			winner = secondPlayer;
		}
 
		// at this point we have chosen the correct winner of the round.
		return winner;

	}

	// this function displays the winner and ends the game.
	public void endGame() throws InterruptedException {
		die1ToggleButton.setDisable(true);
		die2ToggleButton.setDisable(true);
		die3ToggleButton.setDisable(true);
		rollButton.setDisable(true);
		doneButton.setDisable(true);

		currentFirstPlayerLabel.setText("");
		roundNumber.setText("");

		// The player with more points wins.
		if (playerA.getPoints() > playerB.getPoints()) {
			promptLabel.setText(String.format("The winner is %s", playerA.getName()));
		} else {
			promptLabel.setText(String.format("The winner is %s", playerB.getName()));
		}

	}

}
