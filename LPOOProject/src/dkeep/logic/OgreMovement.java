package dkeep.logic;

import java.util.Random;

/**
 * Implements the movement strategy of an ogre.
 *
 */
public class OgreMovement implements MovementStrategy{

	/**
	 * Tries to move down. If there are no walls or doors below the
	 * (X,Y) position then the movement is valid.
	 * Returns true if the movement is valid and false if not valid.
	 * 
	 * @param table
	 * @param X
	 * @param Y
	 * @param valid
	 * @return a boolean value indicating if the movement is valid
	 */
	public boolean direction2(char[][] table, int X, int Y, boolean valid){
		if(table[X][Y+1] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
			Y += 1;
			return true;
		}
		return false;
	}
	/**
	 * Tries to move left. If there are no walls or doors
	 * at the left of the (X,Y) position then the movement is valid.
	 * Returns true if the movement is valid and false if not valid.
	 * 
	 * @param table
	 * @param X
	 * @param Y
	 * @param valid
	 * @return a boolean value indicating if the movement is valid
	 */
	public boolean direction4(char[][] table, int X, int Y, boolean valid){
		if(table[X-1][Y] != 'X' && table[X-1][Y] != 'I' && table[X-1][Y] != 'S'){
			X -= 1;
			return true;
		}
		return false;
	}

	/**
	 * Tries to move right. If there are no walls or doors
	 * at the right of the (X,Y) position then the movement is valid.
	 * Returns true if the movement is valid and false if not valid.
	 * 
	 * @param table
	 * @param X
	 * @param Y
	 * @param valid
	 * @return a boolean value indicating if the movement is valid
	 */
	public boolean direction6(char[][] table, int X, int Y, boolean valid){
		if(table[X+1][Y] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
			X += 1;
			return true;
		}
		return false;
	}
	/**
	 * Tries to move up. If there are no walls or doors above the
	 * (X,Y) position then the movement is valid.
	 * Returns true if the movement is valid and false if not valid.
	 * 
	 * @param table
	 * @param X
	 * @param Y
	 * @param valid
	 * @return a boolean value indicating if the movement is valid
	 */
	public boolean direction8(char[][] table, int X, int Y, boolean valid){
		if(table[X][Y-1] != 'X' && table[X][Y-1] != 'I' && table[X][Y-1] != 'S'){
			Y -= 1;
			return true;
		}
		return false;
	}

	/**
	 * Keeps generating a random direction with the values 2, 4,
	 * 6 or 8 until a movement in that direction is considered valid.
	 * Returns that direction.
	 * 
	 * @return a valid direction to move in
	 */
	public int getDirection(char[][] table, int X, int Y){
		Random rand = new Random();
		boolean valid = false;
		int direction = 0;
		while(!valid){
			direction = (rand.nextInt(4) + 1)*2;
			if(direction == 2){
				valid = direction2(table,X,Y,valid);
			}
			else if(direction == 4){
				valid = direction4(table,X,Y,valid);
			}
			else if (direction == 6){
				valid = direction6(table,X,Y,valid);
			}
			else if(direction == 8){
				valid = direction8(table,X,Y,valid);
			}
		}
		return direction;
	}



}
