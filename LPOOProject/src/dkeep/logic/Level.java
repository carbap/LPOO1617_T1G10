package dkeep.logic;

public abstract class Level {
	char table[][];
	
	int endLevelX[];
	int endLevelY[];
	
	int keyX = 0;
	int keyY = 0;
	
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
