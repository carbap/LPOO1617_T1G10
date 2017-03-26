package dkeep.logic;

import java.util.Random;


/**
 * Guard extends the Character class and implements a few of its abstract methods.
 * One of the game's enemy types.
 * 
 */
public class Guard extends Character{

	private boolean asleep = false;

	private char asleepDisplayChar = 'g';

	/**
	 * Constructs a new Guard given its X and Y coordinates and also
	 * an integer value to indicate its movement strategy.
	 * A guardMov value of 1 means the guard's movement will be Rookie, 
	 * 2 means Drunken and 3 means Suspicious.
	 * 
	 * @param guardX guard's x coordinate
	 * @param guardY guards's y coordinate
	 * @param guardMov indicates the guard's movement strategy
	 */
	public Guard(int guardX, int guardY, int guardMov){
		this.X = guardX;
		this.Y = guardY;
		this.displayChar = 'G';
		if(guardMov == 1)
			this.mov = new RookieMovement();
		else if(guardMov == 2)
			this.mov = new DrunkenMovement();
		else if(guardMov == 3)
			this.mov = new SuspiciousMovement();
	}

	/**
	 * Gets the direction the guard should move in according to its
	 * movement strategy and updates its position using that direction.
	 * This function is also responsible for making the guard asleep.
	 * When this happens, the guard will remain in its place.
	 * 
	 * @param table char matrix that represents the level's table.
	 */
	public void move(char[][] table){
		int direction = mov.getDirection(table, X, Y);
		if(direction == 0){
			this.setAsleep(true);
		}
		else{
			this.setAsleep(false);
		}
		updatePosition(direction);
	}

	/**
	 * Returns true if the guard is asleep and false otherwise.
	 * 
	 * @return boolean value that indicates if the guard is asleep
	 */
	public boolean isAsleep() {
		return asleep;
	}

	/**
	 * Sets the guard's 'asleep' attribute to the parameter. 
	 * The 'asleep' attribute indicates if the guard is asleep.
	 * 
	 * @param asleep the boolean value that 'asleep' will be set to
	 */
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}

	/**
	 * Returns the char that is used to represent the guard.
	 * This char has two possible values because the guard may be awake or asleep.
	 * 
	 * @return the guards's display char
	 */
	public char getDisplayChar(){
		if(!asleep){
			return this.displayChar;
		}
		else{
			return this.asleepDisplayChar;
		}
	}

	/**
	 * Returns true if the Guard is adjacent to the (X,Y) position while 
	 * also being awake. Returns false otherwise. 
	 * 
	 * @return boolean value that indicates if the Guard is awake and adjacent to the (X,Y) position
	 */
	public boolean isAdjacent(int X, int Y){
		if(!this.asleep){
			if(this.X == X && (this.Y == Y+1 || this.Y == Y-1) ){
				return true;
			}

			if(this.Y == Y && (this.X == X+1 || this.X == X-1) ){
				return true;
			}
		}

		return false;
	}

	@Override
	public void setStunned(boolean value) {

	}

}
