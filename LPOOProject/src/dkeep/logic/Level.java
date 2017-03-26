package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class has information about a game's level, 
 * such as their table and worktable, the hero, enemies
 * and position of the key.
 *
 */
public abstract class Level {
	protected char table[][];
	private  char[][] workTable;


	protected boolean keyEnabled = true;

	protected int endLevelX[];
	protected int endLevelY[];

	protected ArrayList <Character> Enemies = new ArrayList<Character>();

	protected Hero hero = new Hero(1,1);

	protected int keyX = 2;
	protected int keyY = 2;
	/**
	 * Returns the level's hero.
	 * 
	 * @return the level's hero
	 */
	public Hero getHero()
	{
		return this.hero;
	}

	/**
	 * Opens the desired doors indicated by their coordinates in
	 * the endLevelX and endLevelY arrays.
	 * This function is used in the first level, so that not all
	 * but only the desired doors open.
	 */
	public void openDoors(){
		for(int i = 0; i < this.endLevelX.length; i++){
			this.table[ this.endLevelX[i]][ this.endLevelY[i] ] = 'S';
		}
	}
	/**
	 * Opens all doors in the level's table.
	 */
	public void openAllDoors(){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				if(table[i][j] == 'I'){
					table[i][j] = 'S';
				}
			}
		}
	}
	/**
	 * Moves all enemies in the level.
	 */
	public void npc(){
		for(int i = 0; i < this.Enemies.size(); i++){
			Enemies.get(i).move(table);
		}
	}
	/**
	 * Returns a copy of the level's table.
	 * @return a copy of the level's table
	 */
	public char[][] getTableCopy() {
		char[][] map = new char[table.length][];
		for (int i = 0; i < map.length ; i++)
			map[i] = Arrays.copyOf(table[i], table[i].length);
		return map;
	}
	/**
	 * Sets the level's table to the parameter.
	 * 
	 * @param table the char matrix to set the level's table to
	 */
	public void setTable(char[][] table) {
		this.table = table;
	}

	/**
	 * Sets the char in the table's (X,Y) position to the 'c' parameter.
	 * 
	 * @param X
	 * @param Y
	 * @param c
	 */
	public void setTableChar(int X, int Y, char c){
		table[X][Y] = c;
	}

	/**
	 * Returns the level's endLevelX array
	 * 
	 * @return the level's endLevelX array
	 */
	public int[] getEndLevelX() {
		return endLevelX;
	}

	/**
	 * Returns the level's endLevelY array
	 * 
	 * @return the level's endLevelY array
	 */
	public int[] getEndLevelY() {
		return endLevelY;
	}

	/**
	 * Returns the key's x coordinate.
	 * 
	 * @return the key's x coordinate
	 */
	public int getKeyX() {
		return keyX;
	}
	/**
	 * Sets the 'keyX' attribute to the parameter.
	 * 
	 * @param keyX the x coordinate to set 'keyX' to
	 */
	public void setKeyX(int keyX) {
		this.keyX = keyX;
	}
	/**
	 * Returns the key's y coordinate.
	 * 
	 * @return the key's y coordinate
	 */
	public int getKeyY() {
		return keyY;
	}
	/**
	 * Sets the 'keyY' attribute to the parameter.
	 * 
	 * @param keyY the y coordinate to set 'keyY' to
	 */
	public void setKeyY(int keyY) {
		this.keyY = keyY;
	}

	/**
	 * Sets the 'endLevelX' attribute to the parameter.
	 * 
	 * @param endLevelX array of integers to set endLevelX to
	 */
	public void setEndLevelX(int[] endLevelX) {
		this.endLevelX = endLevelX;
	}

	/**
	 * Sets the 'endLevelY' attribute to the parameter.
	 * 
	 * @param endLevelY array of integers to set endLevelY to
	 */
	public void setEndLevelY(int[] endLevelY) {
		this.endLevelY = endLevelY;
	}

	/**
	 * Returns true if the key has not been picked up yet.
	 * Returns false otherwise.
	 * 
	 * @return boolean value to indicate if the key has been picked up
	 */
	public boolean isKeyEnabled() {
		return keyEnabled;
	}

	/**
	 * Sets the 'keyEnabled' attribute to the parameter. 
	 * 
	 * @param keyEnabled boolean value to set keyEnabled to
	 */
	public void setKeyEnabled(boolean keyEnabled) {
		this.keyEnabled = keyEnabled;
	}

	/**
	 * Returns the array list of Enemies
	 * 
	 * @return the Enemies array list
	 */
	public ArrayList<Character> getEnemies() {
		return Enemies;
	}
	/**
	 * Draws the key in its position on the workTable.
	 */
	public void drawKey(){
		if(keyEnabled)
		{
			workTable[keyX][keyY] = 'k';
		}
	}
	/**
	 * Draws the hero in his position on the workTable
	 */
	public void drawHero(){
		if(workTable[hero.getX()][hero.getY()] == ' ' || workTable[hero.getX()][hero.getY()] == 'S'){
			workTable[hero.getX()][hero.getY()] = hero.getDisplayChar();
		}
		else{
			workTable[hero.getX()][hero.getY()] = '$';
		}
	}
	/**
	 * Draws the enemies in their position on the workTable
	 */
	public void drawEnemies(){
		for(int i = 0; i < Enemies.size(); i++){
			if(workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] == ' '){
				workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] = Enemies.get(i).getDisplayChar();
			}
			else if(workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] == 'k'){
				workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] = '$';
			}
		}
	}
	/**
	 * Draws the enemies' weapons in their position on the workTable
	 */
	public void drawWeapons(){
		for(int i = 0; i < Enemies.size(); i++){
			if(Enemies.get(i).hasWeapon()){
				if(workTable[Enemies.get(i).getWeaponX()][Enemies.get(i).getWeaponY()] == ' '){
					workTable[Enemies.get(i).getWeaponX()][Enemies.get(i).getWeaponY()] = Enemies.get(i).getWeaponDisplayChar();
				}
				else if(workTable[Enemies.get(i).getWeaponX()][Enemies.get(i).getWeaponY()] == 'k'){
					workTable[Enemies.get(i).getWeaponX()][Enemies.get(i).getWeaponY()] = '$';
				}
			}
		}
	}
	
	/**
	 * Updates the level's workTable, drawing the key, hero, enemies and weapons on it.
	 */
	public void updateWorkTable()
	{
		workTable = this.getTableCopy();
		drawKey();
		drawHero();
		drawEnemies();
		drawWeapons();
	}
	
	/**
	 * Updates the level's workTable and then returns it.
	 * 
	 * @return the updated level's workTable
	 */
	public char[][] getWorktable(){
		this.updateWorkTable();
		return this.workTable;
	}

	/** 
	 * Creates a copy of itself (new object OgreLevel) with the specified number of ogres and returns it
	 * @param ogreNr
	 * @return a copy of the OgreLevel
	 */
	public OgreLevel getOgreLevelCopy(int ogreNr){
		OgreLevel a = new OgreLevel(ogreNr, Enemies.get(0).getX(), Enemies.get(0).getY(), table.length, table[0].length);
		a.hero.setPosition(hero.getX(), hero.getY());
		a.setKeyX(keyX);
		a.setKeyY(keyY);
		a.setEndLevelX(endLevelX);
		a.setEndLevelY(endLevelY);
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				a.setTableChar(i, j, this.table[i][j]);
			}
		}
		return a;
	}

}
