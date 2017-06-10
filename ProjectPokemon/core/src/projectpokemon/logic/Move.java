package projectpokemon.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Move
{
	/**
	 * The type of the move. The moves range from 0 to 16.
	 */
	private int type;
	/**
	 * True if the move is special, false if the move is physical.
	 */
	private boolean special;
	/**
	 * The name of the move.
	 */
	private String name;
	/**
	 * The move's accuracy.
	 */
	private int accuracy;
	/**
	 * The move's power.
	 */
	private int power;
	/**
	 * The ID of the move.
	 */
	private int id;
	/**
	 * The StatStages that the move will apply to the pokemon that used it.
	 */
	private StatStages selfStatStages;
	/**
	 * The StatStages that the move will apply to the enemy.
	 */
	private StatStages enemyStatStages;
	/**
	 * True if the move has greater chance of critical hitting.
	 */
	private boolean highCritChance;
	
	/**
	 * Constructor of Move.
	 * The move's info will be loaded from the moves.txt file.
	 * 
	 * @param i the id of the move to construct
	 */
	public Move(int i){
		String[] info = new String[1];
		
		try {
        	//File a = new File(Move.class.getResource("../data/moves.txt").getFile());
			File a = new File("data/moves.txt");
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			String line;
			
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				if(line.substring(0, 1).equals("/"))
					continue;
				info= line.split(",");
				if(Integer.parseInt(info[0]) == i)
					break;
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.id = i;
		this.type = Integer.parseInt(info[1]);
		if(Integer.parseInt(info[2]) == 1)
			this.special = true;
		else
			this.special = false;
		if(Integer.parseInt(info[3]) == 1)
			this.highCritChance = true;
		else
			this.highCritChance = false;
		this.name = info[4];
		this.power = Integer.parseInt(info[5]);
		this.accuracy = Integer.parseInt(info[6]);
		this.selfStatStages = new StatStages(Integer.parseInt(info[7]),
				Integer.parseInt(info[8]),
				Integer.parseInt(info[9]),
				Integer.parseInt(info[10]),
				Integer.parseInt(info[11]),
				Integer.parseInt(info[12]),
				Integer.parseInt(info[13]));
		this.enemyStatStages = new StatStages(Integer.parseInt(info[14]),
				Integer.parseInt(info[15]),
				Integer.parseInt(info[16]),
				Integer.parseInt(info[17]),
				Integer.parseInt(info[18]),
				Integer.parseInt(info[19]),
				Integer.parseInt(info[20]));
	}
	
	/**
	 * Returns the move's type.
	 * 
	 * @return the move's type.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Returns the highCritChance attribute.
	 * 
	 * @return the hightCritChance attribute
	 */
	public boolean getHighCritChance()
	{
		return highCritChance;
	}
	
	/**
	 * Returns true if Move is special, false if physical.
	 * 
	 * @return boolean that indicates if attack is special or not(physical)
	 */
	public boolean isSpecial() {
		return special;
	}
	
	/**
	 * Returns the move's name.
	 * 
	 * @return String containing the move's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the move's accuracy
	 * 
	 * @return int with the move's accuracy
	 */
	public int getAccuracy() {
		return accuracy;
	}
	
	/**
	* Returns the move's power
	*
	* @return int with the move's power
	*/
	public int getPower() {
		return power;
	}
	
	/**
	* Returns the move's id
	*
	* @return int with the move's id
	*/
	public int getId() {
		return id;
	}

	/**
	 * Returns the move's selfStatStages
	 * 
	 * @return the move's selfStatStages
	 */
	public StatStages getSelfStatStages() {
		return selfStatStages;
	}

	/**
	 * Sets the selfStatStages to the given StatStages.
	 * 
	 * @param selfStatStages the StatStages that the selfStatStages will be set to
	 */
	public void setSelfStatStages(StatStages selfStatStages) {
		this.selfStatStages = selfStatStages;
	}

	/**
	 * Returns the move's enemyStatStages
	 * 
	 * @return the move's enemyStatStages
	 */
	public StatStages getEnemyStatStages() {
		return enemyStatStages;
	}

	/**
	 * Sets the enemyStatStages to the given StatStages.
	 * 
	 * @param enemyStatStages the StatStages that the enemyStatStages will be set to
	 */
	public void setEnemyStatStages(StatStages enemyStatStages) {
		this.enemyStatStages = enemyStatStages;
	}
	
	/**
	 * Returns the number of moves from the given file
	 * @param fileName the move's file name (should include ".txt")
	 * @return the number of moves from the given file
	 */
	public static int getNumberOfMoves(String fileName){
		String path = "data/";
		path += fileName;
		int numMoves = 0;
		try {
        	File a = new File(path);
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			String line;
			
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				if(line.substring(0, 1).equals("/"))
					continue;
				else
					numMoves++;
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return numMoves;
	}
}
