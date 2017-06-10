package projectpokemon.logic;

public class StatStages
{
	private int atk;
	private int def;
	private int spatk;
	private int spdef;
	private int spd;
	private int accuracy;
	private int evasion;
	
	/**
	 * Constructor of StatStages.
	 * Initializes all stat stages to the respective given values
	 * 
	 * @param atk attack stage
	 * @param def defense stage
	 * @param spatk special attack stage
	 * @param spdef special defense stage
	 * @param spd speed stage
	 * @param accuracy accuracy stage
	 * @param evasion evasion stage
	 */
	public StatStages(int atk, int def, int spatk, int spdef, int spd, int accuracy, int evasion) {
		this.atk = atk;
		this.def = def;
		this.spatk = spatk;
		this.spdef = spdef;
		this.spd = spd;
		this.accuracy = accuracy;
		this.evasion = evasion;
	}
	
	/**
	* Default constructor of StatStages, initializes all stat stages to 0
	*/
	public StatStages()
	{
		this.atk = 0;
		this.def = 0;
		this.spatk = 0;
		this.spdef = 0;
		this.spd = 0;
		this.accuracy = 0;
		this.evasion = 0;
	}

	/**
	* Returns the Attack stage
	*
	* @return int with the Attack stage value
	*/
	public int getAtkStage() {
		return atk;
	}

	/**
	* Returns the Defense stage
	*
	* @return int with the Dfense stage value
	*/
	public int getDefStage() {
		return def;
	}

	/**
	* Returns the Special Attack stage
	*
	* @return int with the Special Attack stage value
	*/
	public int getSpatkStage() {
		return spatk;
	}

	/**
	* Returns the Special Defense stage
	*
	* @return int with the Special Defense stage value
	*/
	public int getSpdefStage() {
		return spdef;
	}

	/**
	* Returns the Speed stage
	*
	* @return int with the Speed stage value
	*/
	public int getSpdStage() {
		return spd;
	}

	/**
	* Returns the Accuracy stage
	*
	* @return int with the Accuracy stage value
	*/
	public int getAccuracyStage() {
		return accuracy;
	}

	/**
	* Returns the Evasion stage
	*
	* @return int with the Evasion stage value
	*/
	public int getEvasionStage() {
		return evasion;
	}
	
	/**
	 * Adds the given value to the pokemon's Attack stage
	 * The Attack stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param atk value to add
	 */
	public void addAtk(int atk) {
		if(this.atk + atk >= 6)
			this.atk = 6;
		else if(this.atk + atk <= -6)
			this.atk = -6;
		else
			this.atk += atk;
	}

	/**
	 * Adds the given value to the pokemon's Defense stage
	 * The Defense stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param def value to add
	 */
	public void addDef(int def) {
		if(this.def + def >= 6)
			this.def = 6;
		else if(this.def + def <= -6)
			this.def = -6;
		else
			this.def += def;
	}

	/**
	 * Adds the given value to the pokemon's Special Attack stage
	 * The Special Attack stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param spatk value to add
	 */
	public void addSpatk(int spatk) {
		if(this.spatk + spatk >= 6)
			this.spatk = 6;
		else if(this.spatk + spatk <= -6)
			this.spatk = -6;
		else
			this.spatk += spatk;
	}

	/**
	 * Adds the given value to the pokemon's Special Defense stage
	 * The Special Defense stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param spdef value to add
	 */
	public void addSpdef(int spdef) {
		if(this.spdef + spdef >= 6)
			this.spdef = 6;
		else if(this.spdef + spdef <= -6)
			this.spdef = -6;
		else
			this.spdef += spdef;
	}

	/**
	 * Adds the given value to the pokemon's Speed stage
	 * The Speed stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param spd value to add
	 */
	public void addSpd(int spd) {
		if(this.spd + spd >= 6)
			this.spd = 6;
		else if(this.spd + spd <= -6)
			this.spd = -6;
		else
			this.spd += spd;
	}

	/**
	 * Adds the given value to the pokemon's Accuracy stage
	 * The Accuracy stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param accuracy value to add
	 */
	public void addAccuracy(int accuracy) {
		if(this.accuracy + accuracy >= 6)
			this.accuracy = 6;
		else if(this.accuracy + accuracy <= -6)
			this.accuracy = -6;
		else
			this.accuracy += accuracy;
	}

	/**
	 * Adds the given value to the pokemon's Evasion stage
	 * The Evasion stage ranges from -6 to 6
	 * The value to be added can be negative
	 * 
	 * @param evasion value to add
	 */
	public void addEvasion(int evasion) {
		if(this.evasion + evasion >= 6)
			this.evasion = 6;
		else if(this.evasion + evasion <= -6)
			this.evasion = -6;
		else
			this.evasion += evasion;
	}

	/**
	* Calculates and returns the Attack stat modifier (%)
	*
	* @return double with the Attack stat modifier percentage
	*/
	public double getAtkModifier() {
		if(atk >= 0){
			return (2+atk)/2.0;
		}
		return 2.0/(2-atk);
	}

	/**
	* Calculates and returns the Defense stat modifier (%)
	*
	* @return double with the Defense stat modifier percentage
	*/
	public double getDefModifier() {
		if(def >= 0){
			return (2+def)/2.0;
		}
		return 2.0/(2-def);
	}

	/**
	* Calculates and returns the Special Attack stat modifier (%)
	*
	* @return double with the Special Attack stat modifier percentage
	*/
	public double getSpatkModifier() {
		if(spatk >= 0){
			return (2+spatk)/2.0;
		}
		return 2.0/(2-spatk);
	}

	/**
	* Calculates and returns the Special Defense stat modifier (%)
	*
	* @return double with the Special Defense stat modifier percentage
	*/
	public double getSpdefModifier() {
		if(spdef >= 0){
			return (2+spdef)/2.0;
		}
		return 2.0/(2-spdef);
	}

	/**
	* Calculates and returns the Speed stat modifier (%)
	*
	* @return double with the Speed stat modifier percentage
	*/
	public double getSpdModifier() {
		if(spd >= 0){
			return (2+spd)/2.0;
		}
		return 2.0/(2-spd);
	}

	/**
	* Calculates and returns the Accuracy stat modifier (%)
	*
	* @return double with the Accuracy stat modifier percentage
	*/
	public double getAccuracyModifier() {
		if(accuracy >= 0){
			return (3+accuracy)/3.0;
		}
		return 3.0/(3-accuracy);
	}

	/**
	* Calculates and returns the Evasion stat modifier (%)
	*
	* @return double with the Evasion stat modifier percentage
	*/
	public double getEvasionModifier() {
		if(evasion >= 0){
			return (3+evasion)/3.0;
		}
		return 3.0/(3-evasion);
	}
	
	/**
	 * Returns true if the pokemon's StatStages are all zero, false otherwise
	 * 
	 * @return true if the pokemon's StatStages are all zero, false otherwise
	 */
	public boolean allStagesZero()
	{
		if(atk != 0 || def != 0 || spatk != 0 || spdef != 0 || spd != 0 || accuracy != 0 || evasion != 0)
			return false;
		return true;
	}
}
