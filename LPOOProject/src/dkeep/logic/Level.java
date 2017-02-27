package dkeep.logic;

public abstract class Level {
	char table[][];
	
	Hero hero = new Hero();
	
	boolean keyEnabled = true;
	 
	int endLevelX[];
	int endLevelY[];
	
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
	
	public abstract void npc(); 
	
	public char[][] getTable() {
		return table;
	}
	public void setTable(char[][] table) {
		this.table = table;
	}
	public int[] getEndLevelX() {
		return endLevelX;
	}
	public void setEndLevelX(int[] endLevelX) {
		this.endLevelX = endLevelX;
	}
	public int[] getEndLevelY() {
		return endLevelY;
	}
	public void setEndLevelY(int[] endLevelY) {
		this.endLevelY = endLevelY;
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
