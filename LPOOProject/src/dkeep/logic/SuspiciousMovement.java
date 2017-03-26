package dkeep.logic;

import java.util.Random;

/**
 * Implements the movement strategy of a suspicious guard.
 *
 */
public class SuspiciousMovement implements MovementStrategy{
	private int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	private int movIndex = movPattern.length-1;
	private boolean moveForward = true;

	/**
	 * Sets the moveForward attribute to true or false.
	 * This method has about 75% chance to set moveForward
	 * as true and 25% to set it as false.
	 */
	public void changeDirection(){
		Random rand = new Random();
		int moveDirection = rand.nextInt(100);
		if(moveDirection < 75){
			moveForward = true;
		}
		else{
			moveForward = false;
		}	
	}
	
	/**
	 * Given the 'direction' parameter, returns the opposite direction.
	 * 
	 * @param direction
	 * @return the opposite direction of the parameter
	 */
	public int invertMovement(int direction){
		int dir = 0;
		if(direction == 2){
			dir = 8;
		}
		else if(direction == 4){
			dir = 6;
		}
		else if(direction == 6){
			dir = 4;
		}
		else if(direction == 8){
			dir = 2;
		}
		return dir;
	}
	
	/**
	 * Updates the movIndex according to the moveForward value.
	 */
	public void updateMovIndex(){
		if(moveForward){
			movIndex +=1;
			movIndex %= 24;
		}
		else{
			if(movIndex < 0)
				movIndex += 24;
			else
				movIndex %= 24;
		}
	}
	
	/**
	 * Returns the direction of movement, taking into account 
	 * that the moveForward variable may change.
	 */
	public int getDirection(char[][] table, int X, int Y){

		changeDirection();
		updateMovIndex();
		int direction = movPattern[movIndex];
		if(!moveForward){
			movIndex -=1;
			direction = invertMovement(direction);
		}				
		return direction;
	}
}

