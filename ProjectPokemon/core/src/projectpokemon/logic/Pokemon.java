package projectpokemon.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Pokemon
{
	/**
	 * The pokemon's name
	 */
	private String name;
	/**
	 * The pokemon's id
	 */
	private int id;
	
	/**
	 * The pokemon's level
	 */
	private int level;
	
	/**
	 * The pokemons first type
	 */
	private int type1;
	
	/**
	 * The pokemon's second type (it might not have a second type)
	 */
	private int type2;
	
	/**
	 * Returns true if the pokemon has two types
	 */
	private boolean twoTypes;
	
	/**
	 * The pokemon's StatStages
	 */
	private StatStages statStages = new StatStages();
	
	/**
	 * The pokemon's set of moves
	 */
	ArrayList<Move> moveSet = new ArrayList<Move>();
	
	/**
	 * The damage that this pokemon has taken
	 */
	private int damageTaken;
	
	/**
	 * A needed value for calculating the pokemon's stats
	 */
	private final int ev = 85;
	
	/**
	 *  A needed value for calculating the pokemon's stats
	 */
	private final int  iv = 15;
	
	/**
	 * The base stats of this pokemon
	 */
	private int baseHP, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPD;
	
	/**
	 * Constructor of Pokemon.
	 * The pokemon's info will be loaded from the pokemons.txt file.
	 * 
	 * @param id the ID of the pokemon to construct
	 */
	public Pokemon(int id){
		String[] info = new String[1];
		
		try {
        	File a = new File("data/pokemon.txt");
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			String line;
			
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				if(line.substring(0, 1).equals("/"))
					continue;
				info= line.split(",");
				if(Integer.parseInt(info[0]) == id)
					break;
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.id = id;
		this.level = 100;
		this.name = info[1];
		if(Integer.parseInt(info[2]) == 2)
			this.twoTypes = true;
		else
			this.twoTypes = false;
		this.type1 = Integer.parseInt(info[3]);
		this.type2 = Integer.parseInt(info[4]);
		this.baseHP = Integer.parseInt(info[5]);
		this.baseATK = Integer.parseInt(info[6]);
		this.baseDEF = Integer.parseInt(info[7]);
		this.baseSPATK = Integer.parseInt(info[8]);
		this.baseSPDEF = Integer.parseInt(info[9]);
		this.baseSPD = Integer.parseInt(info[10]);
		
		MoveFactory moves = MoveFactory.getInstance();
		for(int i = 11; i < 15; i++){
			this.moveSet.add(moves.getMove(Integer.parseInt(info[i])));
		}
	}
	
	/**
	 * Returns the pokemon's ID
	 * 
	 * @return the pokemon's ID
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Returns the pokemon's name
	 * 
	 * @return the pokemon's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the pokemon's StatStages
	 * 
	 * @return the pokemon's StatStages
	 */
	public StatStages getStatStages()
	{
		return statStages;
	}
	
	/**
	 * Sets the pokemon's StatStages to the given StatStages
	 * 
	 * @param statStages the StatStages to set
	 */
	public void setStatStages(StatStages statStages) {
		this.statStages = statStages;
	}

	/**
	 * Returns the pokemon's moveset
	 * 
	 * @return the pokemon's moveset
	 */
	public ArrayList<Move> getMoveSet()
	{
		return moveSet;
	}
	
	/**
	 * Returns the pokemon's level
	 * 
	 * @return the pokemon's level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Returns the pokemon's first type
	 * 
	 * @return the pokemon's first type
	 */
	public int getType1(){
		return type1;
	}
	
	/**
	 * Returns the pokemon's second type
	 * 
	 * @return the pokemon's second type
	 */
	public int getType2(){
		return type2;
	}
	
	/**
	 * Returns true if the pokemon has two types, false otherwise
	 * 
	 * @return true if the pokemon has two types, false otherwise
	 */
	public boolean hasTwoTypes(){
		return twoTypes;
	}

	/**
	* Calculates and returns the current HP
	*
	* @return int with the current HP
	*/
	public int getCurrentHP()
	{
		return (int) (getHP() - damageTaken);
	}
	
	/**
	* Calculates the max HP based on baseHP and returns it
	*
	* @return int with max HP
	*/
	public int getHP()
	{
		return (int) Math.floor((2 * baseHP + iv + Math.floor((ev / 4.0))) * level / 100.0 + level + 10);
	}
	
	/**
	* Calculates and returns the current percentage of HP ranging from 0.0f to 1.0
	*
	* @return float with the percentage of HP
	*/
	public float getPercentageHP()
	{
		return (float) ((getCurrentHP()*1.0)/(getHP()*1.0));
	}
	
	/**
	* Calculates the attack stat based on baseATK and attack modifiers and returns it
	*
	* @return int with attack stat
	*/
	public int getATK(){
		return (int) Math.floor( ((2 * baseATK + iv + Math.floor((ev / 4.0))) * level / 100.0 + 5) * statStages.getAtkModifier());
	}
	
	/**
	* Calculates defense stat based on baseDEF
	* Defense modifiers are not taken into account if the parameter ignoreModifier is set to true
	*
	* @param ignoreModifier boolean that dictates if defense modifiers are ignored or not
	* @return int with defense stat
	*/
	public int getDEF(boolean ignoreModifier){
		double returnValue = ((2 * baseDEF + iv + Math.floor((ev / 4.0))) * level / 100.0 + 5);
		if(!ignoreModifier || statStages.getDefStage() < 0)
		{
			returnValue *= statStages.getDefModifier();
		}	
		return (int) Math.floor(returnValue);
	}
	
	/**
	* Calculates special attack stat based on baseSPATK and special attack modifiers and returns it
	*
	* @return int with special attack stat
	*/
	public int getSPATK(){
		return (int) Math.floor( ((2 * baseSPATK + iv + Math.floor((ev / 4.0))) * level / 100.0 + 5) * statStages.getSpatkModifier());
	}
	
	/**
	* Calculates special defense stat based on baseSPDEF
	* Special defense modifiers are not taken into account if the parameter ignoreModifier is set to true
	*
	* @param ignoreModifier boolean that dictates if special defense modifiers are ignored or not
	* @return int with special defense stat
	*/
	public int getSPDEF(boolean ignoreModifier){
		double returnValue = ((2 * baseSPDEF + iv + Math.floor((ev / 4.0))) * level / 100.0 + 5);
		if(!ignoreModifier || statStages.getSpdefStage() < 0)
		{
			returnValue *= statStages.getSpdefModifier();
		}	
		return (int) Math.floor(returnValue);
	}
	
	/**
	* Calculates speed stat based on baseSPD and speed modifiers and returns it
	*
	* @return int with speed stat
	*/
	public int getSPD(){
		return (int) Math.floor( ((2 * baseSPD + iv + Math.floor((ev / 4.0))) * level / 100.0 + 5) * statStages.getSpdModifier());
	}

	/**
	 * It adds the given value to the pokemon's damageTaken.
	 * The pokemon's damageTaken has a maximum value equal to the pokemon's maximum HP.
	 * 
	 * @param damage the damage value to add
	 */
	public void addDamageTaken(int damage) {
		if(damageTaken+damage <= getHP())
			damageTaken += damage;
		else
			damageTaken = getHP();
	}

	/**
	 * Sets the pokemon's damageTaken to the given value
	 * 
	 * @param damageTaken the value to set damageTaken to
	 */
	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	/**
	 * Returns an ArrayList with all the pokemon's IDs from the given file.
	 * 
	 * @param fileName the pokemon's file name (should include ".txt")
	 * @return ArrayList with all the pokemon's IDs from the given file.
	 */
	public static ArrayList<Integer> getAllPokemonIDs(String fileName){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		String path = "data/";
		path += fileName;
		try {
        	File a = new File(path);
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			String line;
			
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				if(line.substring(0, 1).equals("/"))
					continue;
				int virgula = line.indexOf(',');
				String id = line.substring(0, virgula);
				ret.add(Integer.parseInt(id));
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
}
