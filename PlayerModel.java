package application;

public class PlayerModel {
	private String name;
	private int playerId;
	private int points;
	private int[] diceRolled;
	
	
	
	public PlayerModel(String name, int playerId, int points, int[] diceRolled) {
		setName(name);
		setPlayerId(playerId);
		setPoints(points);
		setDiceRolled(diceRolled);
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int[] getDiceRolled() {
		return diceRolled;
	}
	public void setDiceRolled(int[] diceRolled) {
		this.diceRolled = diceRolled;
	}
	
	
	
}
