package dkeep.logic;

/**
 * This interface represents a movement strategy
 * for a character. The implementation of the methods in this interface
 * will determine the behavior of the characters movement.
 *
 */
public interface MovementStrategy {
	
	/**
	 * Returns the direction of movement.
	 * 
	 * @param table
	 * @param X
	 * @param Y
	 * @return the direction of movement
	 */
	public int getDirection(char[][] table, int X, int Y);
}
