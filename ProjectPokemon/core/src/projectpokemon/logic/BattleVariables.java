package projectpokemon.logic;

public class BattleVariables
{
	/**
	 * The index of the move used by player1
	 */
	private int player1MoveIndex;
	/**
	 * The index of the move used by player2
	 */
	private int player2MoveIndex;
	/**
	 * The StatStages applied to player1
	 */
	private StatStages player1Stages = new StatStages();
	/**
	 * The StatStages applied to player2
	 */
	private StatStages player2Stages = new StatStages();
	/**
	 * True if player1 has switched
	 */
	private boolean player1Switch;
	/**
	 * True if player2 has switched
	 */
	private boolean player2Switch;
	/**
	 * True if the moved used by player1 hit
	 */
	private boolean player1Hits;
	/**
	 * True if the moved used by player2 hit
	 */
	private boolean player2Hits;
	/**
	 * True if the move used by player1 was a critical hit
	 */
	private boolean player1Critical;
	/**
	 * True if the move used by player2 was a critical hit
	 */
	private boolean player2Critical;
	/** 
	 * Effectiveness of the move used by player1 on the foe
	 * 0 does not affect
	 * 1 not very effective
	 * 2 normal effectiveness
	 * 3 super effective
	 */
	private int player1Effectiveness;
	/** 
	 * Effectiveness of the move used by player2 on the foe
	 * 0 does not affect
	 * 1 not very effective
	 * 2 normal effectiveness
	 * 3 super effective
	 */
	private int player2Effectiveness;
	
	/**
	 * Holds info about which player attacks first
	 * 1 - player 1 attacks first
	 * 2 - player 2 attacks first
	 */
	private int firstToAttack;
	/**
	 * Returns the index of the move used by player1
	 * 
	 * @return the index of the move used by player1
	 */
	public int getPlayer1MoveIndex() {
		return player1MoveIndex;
	}
	/**
	 * Sets the player1MoveIndex to the given int value
	 * 
	 * @param player1MoveIndex the index of a move
	 */
	public void setPlayer1MoveIndex(int player1MoveIndex) {
		this.player1MoveIndex = player1MoveIndex;
	}
	/**
	 * Returns the index of the move used by player2
	 * 
	 * @return the index of the move used by player2
	 */
	public int getPlayer2MoveIndex() {
		return player2MoveIndex;
	}
	/**
	 * Sets the player2MoveIndex to the given int value
	 * 
	 * @param player2MoveIndex the index of a move
	 */
	public void setPlayer2MoveIndex(int player2MoveIndex) {
		this.player2MoveIndex = player2MoveIndex;
	}
	/**
	 * Returns the player1Stages
	 * 
	 * @return the player1Stages
	 */
	public StatStages getPlayer1Stages() {
		return player1Stages;
	}
	/**
	 * Sets the player1Stages to the given StatStages
	 * 
	 * @param player1Stages the StatStages to set player1Stages to
	 */
	public void setPlayer1Stages(StatStages player1Stages) {
		this.player1Stages = player1Stages;
	}
	/**
	 * Returns the player2Stages
	 * 
	 * @return the player2Stages
	 */
	public StatStages getPlayer2Stages() {
		return player2Stages;
	}
	/**
	 * Sets the player2Stages to the given StatStages
	 * 
	 * @param player2Stages the StatStages to set player2Stages to
	 */
	public void setPlayer2Stages(StatStages player2Stages) {
		this.player2Stages = player2Stages;
	}
	/**
	 * Returns the player1Switch attribute
	 * 
	 * @return the player1Switch attribute
	 */
	public boolean isPlayer1Switch() {
		return player1Switch;
	}
	/**
	 * Sets the player1Switch attribute to the given boolean value
	 * 
	 * @param player1Switch the boolean value to set player1Switch to
	 */
	public void setPlayer1Switch(boolean player1Switch) {
		this.player1Switch = player1Switch;
	}
	/**
	 * Returns the player2Switch attribute
	 * 
	 * @return the player2Switch attribute
	 */
	public boolean isPlayer2Switch() {
		return player2Switch;
	}
	/**
	 * Sets the player2Switch attribute to the given boolean value
	 * 
	 * @param player2Switch the boolean value to set player2Switch to
	 */
	public void setPlayer2Switch(boolean player2Switch) {
		this.player2Switch = player2Switch;
	}
	/**
	 * Returns the player1Hits attribute
	 * 
	 * @return the player1Hits attribute
	 */
	public boolean isPlayer1Hits() {
		return player1Hits;
	}
	/**
	 * Sets the player1Hits attribute to the given boolean value
	 * 
	 * @param player1Hits the boolean value to set player1Hits to
	 */
	public void setPlayer1Hits(boolean player1Hits) {
		this.player1Hits = player1Hits;
	}
	/**
	 * Returns the player2Hits attribute
	 * 
	 * @return the player2Hits attribute
	 */
	public boolean isPlayer2Hits() {
		return player2Hits;
	}
	/**
	 * Sets the player2Hits attribute to the given boolean value
	 * 
	 * @param player2Hits the boolean value to set player2Hits to
	 */
	public void setPlayer2Hits(boolean player2Hits) {
		this.player2Hits = player2Hits;
	}
	
	/**
	 * Returns the player1Critical attribute
	 * 
	 * @return the player1Critical attribute
	 */
	public boolean isPlayer1Critical() {
		return player1Critical;
	}
	
	/**
	 * Sets the player1Critical attribute to the given boolean value
	 * 
	 * @param player1Critical the boolean value to set player1Critical to
	 */
	public void setPlayer1Critical(boolean player1Critical) {
		this.player1Critical = player1Critical;
	}
	
	/**
	 * Returns the player2Critical attribute
	 * 
	 * @return the player2Critical attribute
	 */
	public boolean isPlayer2Critical() {
		return player2Critical;
	}
	
	/**
	 * Sets the player2Critical attribute to the given boolean value
	 * 
	 * @param player2Critical the boolean value to set player2Critical to
	 */
	public void setPlayer2Critical(boolean player2Critical) {
		this.player2Critical = player2Critical;
	}
	
	/**
	 * Returns the player1Effectiveness attribute
	 * 
	 * @return the player1Effectiveness attribute
	 */
	public int getPlayer1Effectiveness() {
		return player1Effectiveness;
	}
	
	/**
	 * Sets the player1Effectiveness attribute to the given int value
	 * 
	 * @param player1Effectiveness the int value to set player1Effectiveness to
	 */
	public void setPlayer1Effectiveness(int player1Effectiveness) {
		this.player1Effectiveness = player1Effectiveness;
	}
	
	/**
	 * Returns the player2Effectiveness attribute
	 * 
	 * @return the player2Effectiveness attribute
	 */
	public int getPlayer2Effectiveness() {
		return player2Effectiveness;
	}
	
	/**
	 * Sets the player2Effectiveness attribute to the given int value
	 * 
	 * @param player2Effectiveness the int value to set player2Effectiveness to
	 */
	public void setPlayer2Effectiveness(int player2Effectiveness) {
		this.player2Effectiveness = player2Effectiveness;
	}
	
	/**
	 * Returns the firstToAttack attribute
	 * 
	 * @return the firstToAttack attribute
	 */
	public int getFirstToAttack() {
		return firstToAttack;
	}
	
	/**
	 * Sets the firstToAttack attribute to the given int value
	 * 
	 * @param firstToAttack the int value to set firstToAttack to
	 */
	public void setFirstToAttack(int firstToAttack) {
		this.firstToAttack = firstToAttack;
	}
	
}
