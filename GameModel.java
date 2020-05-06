package application;

public class GameModel {
	private int round;
	private int numberOfRoll;
	private PlayerModel currentFirstPlayerRolling;
	private PlayerModel currentSecondPlayerRolling;
	private PlayerModel playerRolling;
	public GameModel(int round, int numberOfRoll, PlayerModel currentFirstPlayerRolling, PlayerModel currentSecondPlayerRolling, PlayerModel playerRolling){
		
		setRound(round);
		setNumberOfRoll(numberOfRoll);
		setCurrentFirstPlayerRolling(currentFirstPlayerRolling);
		setCurrentSecondPlayerRolling(currentSecondPlayerRolling);
		setPlayerRolling(playerRolling);
		
	}
	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getNumberOfRoll() {
		return numberOfRoll;
	}
	public void setNumberOfRoll(int numberOfRoll) {
		this.numberOfRoll = numberOfRoll;
	}
	public PlayerModel getCurrentFirstPlayerRolling() {
		return currentFirstPlayerRolling;
	}
	public void setCurrentFirstPlayerRolling(PlayerModel currentFirstPlayerRolling) {
		this.currentFirstPlayerRolling = currentFirstPlayerRolling;
	}

	public PlayerModel getCurrentSecondPlayerRolling() {
		return currentSecondPlayerRolling;
	}

	public void setCurrentSecondPlayerRolling(PlayerModel currentSecondPlayerRolling) {
		this.currentSecondPlayerRolling = currentSecondPlayerRolling;
	}

	public PlayerModel getPlayerRolling() {
		return playerRolling;
	}

	public void setPlayerRolling(PlayerModel playerRolling) {
		this.playerRolling = playerRolling;
	}
	
	
	
}
