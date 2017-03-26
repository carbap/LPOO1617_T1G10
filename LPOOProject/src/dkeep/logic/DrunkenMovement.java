package dkeep.logic;

import java.util.Random;


/**
 * Implements the movement strategy of a drunken guard.
 *
 */
public class DrunkenMovement implements MovementStrategy{
	private int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	private int movIndex = movPattern.length-1;
	private int turnsAsleep = 0;
	private boolean moveForward = true;

	/**
	 * If the guard is currently asleep, this method does nothing.
	 * Otherwise, has a chance of about 20% to make the guard 
	 * fall asleep for 3 turns. In this case, there will be a 50% chance
	 * of setting moveForward as true or false.
	 */
	public void RandFallsAsleep(){
		if(turnsAsleep == 0){
			Random rand = new Random();
			int fallAsleep = rand.nextInt(100);
			if(fallAsleep < 20){
				turnsAsleep = 3;
				if(fallAsleep % 2 == 0){
					moveForward = true;
				}
				else{
					moveForward = false;
				}
			}
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
	 * Returns the direction of movement if the guard is awake.
	 * If the guard is asleep decrements turnsAsleep by 1 and returns 0.
	 * 
	 * @return direction of movement if guard is awake and 0 otherwise
	 */
	public int getDirection(char[][] table, int X, int Y){
		RandFallsAsleep();
		if(turnsAsleep == 0){
			updateMovIndex();
			int direction = movPattern[movIndex];
			if(!moveForward){
				movIndex -=1;
				direction = invertMovement(direction);
			}				
			return direction;
		}
		turnsAsleep -= 1;
		return 0;
	}
}
