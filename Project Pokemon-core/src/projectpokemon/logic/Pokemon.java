package projectpokemon.logic;

import java.util.ArrayList;

public class Pokemon
{
	private String name;
	private boolean male;
	private short id;
	private short level;
	private short type1;
	private short type2;
	private boolean twoTypes;
	private boolean shiny;
	private StatStages statStages = new StatStages();
	
	ArrayList<Move> moveSet = new ArrayList<Move>();
	
	private short damageTaken;
	
	private short evHP, evATK, evDEF, evSPATK, evSPDEF, evSPD;
	private short ivHP, ivATK, ivDEF, ivSPATK, ivSPDEF, ivSPD;
	private short baseHP, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPD;
	
	
	public StatStages getStatStages()
	{
		return statStages;
	}
	
	public ArrayList<Move> getMoveSet()
	{
		return moveSet;
	}
	
	public short getLevel()
	{
		return level;
	}
	
	public short getType1(){
		return type1;
	}
	
	public short getType2(){
		return type2;
	}
	
	public boolean hasTwoTypes(){
		return twoTypes;
	}

	/*
	* Calculates and returns current HP
	*
	* @return short with current HP
	*/
	public short getCurrentHP()
	{
		return (short) (getHP() - damageTaken);
	}
	
	/*
	* Calculates max HP based on baseHP and returns it
	*
	* @return short with max HP
	*/
	public short getHP()
	{
		return (short) Math.floor((2 * baseHP + ivHP + Math.floor((evHP / 4.0))) * level / 100.0 + level + 10);
	}
	
	/*
	* Calculates attack stat based on baseATK and attack modifiers and returns it
	*
	* @return short with attack stat
	*/
	public short getATK(){
		return (short) Math.floor( ((2 * baseATK + ivATK + Math.floor((evATK / 4.0))) * level / 100.0 + 5) * statStages.getAtkModifier());
	}
	
	/*
	* Calculates defense stat based on baseDEF
	* Defense modifiers are not taken into account if the parameter ignoreModifier is set to true
	*
	* @param ignoreModifier boolean that dictates if defense modifiers are ignored or not
	* @return short with defense stat
	*/
	public short getDEF(boolean ignoreModifier){
		double returnValue = ((2 * baseDEF + ivDEF + Math.floor((evDEF / 4.0))) * level / 100.0 + 5);
		if(!ignoreModifier || statStages.getDefStage() < 0)
		{
			returnValue *= statStages.getDefModifier();
		}	
		return (short) Math.floor(returnValue);
	}
	
	/*
	* Calculates special attack stat based on baseSPATK and special attack modifiers and returns it
	*
	* @return short with special attack stat
	*/
	public short getSPATK(){
		return (short) Math.floor( ((2 * baseSPATK + ivSPATK + Math.floor((evSPATK / 4.0))) * level / 100.0 + 5) * statStages.getSpatkModifier());
	}
	
	/*
	* Calculates special defense stat based on baseSPDEF
	* Special defense modifiers are not taken into account if the parameter ignoreModifier is set to true
	*
	* @param ignoreModifier boolean that dictates if special defense modifiers are ignored or not
	* @return short with special defense stat
	*/
	public short getSPDEF(boolean ignoreModifier){
		double returnValue = ((2 * baseSPDEF + ivSPDEF + Math.floor((evSPDEF / 4.0))) * level / 100.0 + 5);
		if(!ignoreModifier || statStages.getSpdefStage() < 0)
		{
			returnValue *= statStages.getSpdefModifier();
		}	
		return (short) Math.floor(returnValue);
	}
	
	/*
	* Calculates speed stat based on baseSPD and speed modifiers and returns it
	*
	* @return short with speed stat
	*/
	public short getSPD(){
		return (short) Math.floor( ((2 * baseSPD + ivSPD + Math.floor((evSPD / 4.0))) * level / 100.0 + 5) * statStages.getSpdModifier());
	}
	
}
