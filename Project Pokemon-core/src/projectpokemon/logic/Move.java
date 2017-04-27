package projectpokemon.logic;


public class Move
{
	private short type;
	private boolean special;
	private String name;
	private short accuracy;
	private short power;
	private short id;
	private short basePP;
	private short currentPP;
	private StatStages selfStatStages;
	private StatStages enemyStatStages;
	private boolean highCritChance;
	private boolean canMiss;
	
	public boolean getCanMiss()
	{
		return canMiss;
	}
	
	public short getType() {
		return type;
	}
	
	public boolean getHighCritChance()
	{
		return highCritChance;
	}
	
	/*
	 * Returns true if Move is special, false if physical.
	 * 
	 * @return boolean that indicates if attack is special or not(physical)
	 */
	public boolean isSpecial() {
		return special;
	}
	
	/*
	 * Returns Move's name.
	 * 
	 * @return String containing Move's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Returns Move's accuracy
	 * 
	 * @return short with Move's accuracy
	 */
	public short getAccuracy() {
		return accuracy;
	}
	
	/*
	* Returns Move's power
	*
	* @return short with Move's power
	*/
	public short getPower() {
		return power;
	}
	
	/*
	* Returns Move's id
	*
	* @return short with Move's id
	*/
	public short getId() {
		return id;
	}
	
	/*
	* Returns Move's base PP
	*
	* @return short with Move's base PP
	*/
	public short getBasePP() {
		return basePP;
	}
	
	/*
	* Returns Move's current PP
	*
	* @return short with Move's current PP
	*/
	public short getCurrentPP() {
		return currentPP;
	}
	
	/*
	* Reset's Move's current PP to base values;
	*/
	void resetPP(){
		currentPP = basePP;
	}

	/*
	* Decreases Move's current PP
	*/
	void decreasePP(){
		currentPP--;
	}

}
