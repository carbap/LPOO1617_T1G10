package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Level {
	protected char table[][];
	
	protected boolean keyEnabled = true;
	 
	protected int endLevelX[];
	protected int endLevelY[];
	
	protected ArrayList <Character> Enemies = new ArrayList<Character>();
	
	protected Hero hero = new Hero(0,0);
	
	protected int keyX;
	protected int keyY;
	
	public boolean isFree(int x, int y)
	{
		if(table[x][y] == ' ')
		{
			return true; 
		}
		else
		{
			return false;
		}
	}
	
	public Hero getHero()
	{
		return this.hero;
	}
	
	public void openDoors(){
		for(int i = 0; i < this.endLevelX.length; i++){
			this.table[ this.endLevelX[i]][ this.endLevelY[i] ] = 'S';
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
	
}
