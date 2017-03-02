package dkeep.logic;

public class RookieMovement implements MovementStrategy{
	int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	int movIndex = -1;
	
	public int getDirection(char[][] table, int X, int Y){
		movIndex +=1; 
		movIndex %= 24;
		int direction = movPattern[movIndex];
		
		return direction;
	}
}
