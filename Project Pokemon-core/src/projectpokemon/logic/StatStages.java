package projectpokemon.logic;

public class StatStages
{
	private short atk;
	private short def;
	private short spatk;
	private short spdef;
	private short spd;
	private short accuracy;
	private short evasion;
	
	/*
	* Constructor initializing all stat stages to the respective given values
	*/
	public StatStages(short atk, short def, short spatk, short spdef, short spd, short accuracy, short evasion) {
		this.atk = atk;
		this.def = def;
		this.spatk = spatk;
		this.spdef = spdef;
		this.spd = spd;
		this.accuracy = accuracy;
		this.evasion = evasion;
	}
	
	/*
	* Default constructor, initializes all stat stages to 0
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

	/*
	* Returns attack stat stage
	*
	* @return short with attack stat stage value
	*/
	public short getAtkStage() {
		return atk;
	}

	/*
	* Returns defense stat stage
	*
	* @return short with defense stat stage value
	*/
	public short getDefStage() {
		return def;
	}

	/*
	* Returns special attack stat stage
	*
	* @return short with special attack stat stage value
	*/
	public short getSpatkStage() {
		return spatk;
	}

	/*
	* Returns special defense stat stage
	*
	* @return short with special defense stat stage value
	*/
	public short getSpdefStage() {
		return spdef;
	}

	/*
	* Returns speed stat stage
	*
	* @return short with speed stat stage value
	*/
	public short getSpdStage() {
		return spd;
	}

	/*
	* Returns accuracy stat stage
	*
	* @return short with accuracy stat stage value
	*/
	public short getAccuracyStage() {
		return accuracy;
	}

	/*
	* Returns evasion stat stage
	*
	* @return short with evasion stat stage value
	*/
	public short getEvasionStage() {
		return evasion;
	}
	
	/*
	* Calculates and returns attack stat modifier (%)
	*
	* @return double with attack stat modifier percentage
	*/
	public double getAtkModifier() {
		if(atk >= 0){
			return (2+atk)/2.0;
		}
		return 2.0/(2-atk);
	}

	/*
	* Calculates and returns defense stat modifier (%)
	*
	* @return double with defense stat modifier percentage
	*/
	public double getDefModifier() {
		if(def >= 0){
			return (2+def)/2.0;
		}
		return 2.0/(2-def);
	}

	/*
	* Calculates and returns special attack stat modifier (%)
	*
	* @return double with special attack stat modifier percentage
	*/
	public double getSpatkModifier() {
		if(spatk >= 0){
			return (2+spatk)/2.0;
		}
		return 2.0/(2-spatk);
	}

	/*
	* Calculates and returns special defense stat modifier (%)
	*
	* @return double with special defense stat modifier percentage
	*/
	public double getSpdefModifier() {
		if(spdef >= 0){
			return (2+spdef)/2.0;
		}
		return 2.0/(2-spdef);
	}

	/*
	* Calculates and returns speed stat modifier (%)
	*
	* @return double with speed stat modifier percentage
	*/
	public double getSpdModifier() {
		if(spd >= 0){
			return (2+spd)/2.0;
		}
		return 2.0/(2-spd);
	}

	/*
	* Calculates and returns accuracy stat modifier (%)
	*
	* @return double with accuracy stat modifier percentage
	*/
	public double getAccuracyModifier() {
		if(accuracy >= 0){
			return (3+accuracy)/3.0;
		}
		return 3.0/(3-accuracy);
	}

	/*
	* Calculates and returns evasion stat modifier (%)
	*
	* @return double with evasion stat modifier percentage
	*/
	public double getEvasionModifier() {
		if(evasion >= 0){
			return (3+evasion)/3.0;
		}
		return 3.0/(3-evasion);
	}
	
}
