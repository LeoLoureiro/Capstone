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
 * PlayerModel.java
 * 
 * PlayerModel contains each individual player's data as well as the dice that the player rolled.
 * It also contains the points that the player has accumulated so far.
 * Each player has a name, a playerId, points, and dice rolled.
 * 
 */
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
