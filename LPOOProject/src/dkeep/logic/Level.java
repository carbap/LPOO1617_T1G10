package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Level {
	char table[][];
	
	public boolean keyEnabled = true;
	 
	public int endLevelX[];
	public int endLevelY[];
	
	public ArrayList <Character> Enemies = new ArrayList<Character>();
	
	public Hero hero = new Hero(0,0);
	
	int keyX;
	int keyY;
	
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
	
	public void openDoors(){
		for(int i = 0; i < this.endLevelX.length; i++){
			this.table[ this.endLevelX[i]][ this.endLevelY[i] ] = 'S';
		}
	}
	
	public abstract void printTable();
	
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
	
	
}
