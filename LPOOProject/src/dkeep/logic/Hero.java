package dkeep.logic;

public class Hero extends Character
{	
	public Hero(int heroX, int heroY){
		this.X = heroX;
		this.Y = heroY;
		this.displayChar = 'H';
	}
		
	public void move(char[][] table){
		
	}
	
	public boolean isAdjacent(int X, int Y){
		return false;
	}
}
