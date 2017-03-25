package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;

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

	public Hero getHero()
	{
		return this.hero;
	}

	public void openDoors(){
		for(int i = 0; i < this.endLevelX.length; i++){
			this.table[ this.endLevelX[i]][ this.endLevelY[i] ] = 'S';
		}
	}
	
	public void openAllDoors(){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				if(table[i][j] == 'I'){
					table[i][j] = 'S';
				}
			}
		}
	}

	public void npc(){
		for(int i = 0; i < this.Enemies.size(); i++){
			Enemies.get(i).move(table);
		}
	}

	public char[][] getTableCopy() {
		char[][] map = new char[table.length][];
		for (int i = 0; i < map.length ; i++)
			map[i] = Arrays.copyOf(table[i], table[i].length);
		return map;
	}

	public void setTable(char[][] table) {
		this.table = table;
	}

	//adicionado agora
	public void setTableChar(int X, int Y, char c){
		table[X][Y] = c;
	}

	public int[] getEndLevelX() {
		return endLevelX;
	}

	public int[] getEndLevelY() {
		return endLevelY;
	}

	public int getKeyX() {
		return keyX;
	}
	public void setKeyX(int keyX) {
		this.keyX = keyX;
	}
	public int getKeyY() {
		return keyY;
	}
	public void setKeyY(int keyY) {
		this.keyY = keyY;
	}

	public void setEndLevelX(int[] endLevelX) {
		this.endLevelX = endLevelX;
	}

	public void setEndLevelY(int[] endLevelY) {
		this.endLevelY = endLevelY;
	}

	public boolean isKeyEnabled() {
		return keyEnabled;
	}

	public void setKeyEnabled(boolean keyEnabled) {
		this.keyEnabled = keyEnabled;
	}

	public ArrayList<Character> getEnemies() {
		return Enemies;
	}


	public void updateWorkTable()
	{
		//reset table
		workTable = this.getTableCopy();

		//draw key
		if(keyEnabled)
		{
			workTable[keyX][keyY] = 'k';
		}

		//draw hero
		if(workTable[hero.getX()][hero.getY()] == ' ' || workTable[hero.getX()][hero.getY()] == 'S'){
			workTable[hero.getX()][hero.getY()] = hero.getDisplayChar();
		}
		else{
			workTable[hero.getX()][hero.getY()] = '$';
		}

		//draw enemies and weapons
		for(int i = 0; i < Enemies.size(); i++){
			//draw enemies
			if(workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] == ' '){
				workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] = Enemies.get(i).getDisplayChar();
			}
			else if(workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] == 'k'){
				workTable[Enemies.get(i).getX()][Enemies.get(i).getY()] = '$';
			}

			//draw respective weapons if they exist
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

	public char[][] getWorktable(){
		this.updateWorkTable();
		return this.workTable;
	}

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
