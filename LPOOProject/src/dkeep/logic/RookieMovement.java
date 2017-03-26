package dkeep.logic;

/**
 * Implements the movement strategy of a rookie guard.
 *
 */
public class RookieMovement implements MovementStrategy{
	private int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	private int movIndex = -1;
	
	/**
	 * Gets the direction to move in by updating 
	 * the movIndex in the movPattern array.
	 */
	public int getDirection(char[][] table, int X, int Y){
		movIndex +=1; 
		movIndex %= 24;
		int direction = movPattern[movIndex];
		
		return direction;
	}
}
