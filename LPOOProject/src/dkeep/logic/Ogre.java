package dkeep.logic;

public class Ogre extends Character{
	
	/**
	 * Constructs a new Ogre given its X and Y coordinates and also
	 * its club's X and Y coordinates.
	 * 
	 * @param ogreX ogre's x coordinate
	 * @param ogreY ogre's y coordinate
	 * @param clubX club's x coordinate
	 * @param clubY club's y coordinate
	 */
	public Ogre(int ogreX, int ogreY, int clubX, int clubY){
		this.X = ogreX;
		this.Y = ogreY;
		this.weapon = true;
		this.weaponX = clubX;
		this.weaponY = clubY;
		this.mov = new OgreMovement();
		this.displayChar = 'O';
		this.weaponDisplayChar = '*';
	}
	
		
	/**
	 * Gets the direction the ogre should move in according to both its
	 * movement strategy and the level's table (ogre must move into a free tile).
	 * If the ogre is not stunned, its position is updated using the direction
	 * mentioned above. The ogre's club position is always updated (this means
	 * the ogre may swing its club even when stunned).
	 * 
	 * @param table char matrix that represents the level's table.
	 */
	public void move(char[][] table){
		int direction = mov.getDirection(table, X, Y);
		
		if(!this.isStunned()){
			updatePosition(direction);
			direction = mov.getDirection(table, X, Y);
		}
		else{
			this.turnsStunned--;
			if(this.turnsStunned == 0){
				this.setStunned(false);
			}
		}
		updateClubPosition(direction);
	}
	
	/**
	 * Method to update the clubs's position.
	 * The direction parameter has four possible values:
	 * 2 means down, 4 means left, 6 means right and 8 means up.
	 * 
	 * @param direction integer value that indicates where the club will move to
	 */
	public void updateClubPosition(int direction){
		if(direction == 2){
			weaponY = Y + 1;
			weaponX = X;
		}
		else if(direction == 4){
			weaponX = X - 1;
			weaponY = Y;
		}
		else if (direction == 6){
			weaponX = X + 1;
			weaponY = Y;
		}
		else if(direction == 8){
			weaponY = Y - 1;
			weaponX = X;
		}
	}
	/**
	 * Returns true if the ogre is not stunned and its 
	 * position is adjacent to the (X,Y) position. 
	 * Returns false otherwise.
	 * 
	 * @param X x value of the desired position
	 * @param Y y value of the desired position
	 */
	public boolean checkAdjacentOgre(int X, int Y){
		if(!stunned){
			if(this.X == X && (this.Y == Y+1 || this.Y == Y-1) ){
				return true;
			}	
			if(this.Y == Y && (this.X == X+1 || this.X == X-1) ){
				return true;
			}
		}
		return false;
	}
		
	/**
	 * Returns true if:
	 * - the ogre is not stunned and its position is adjacent to the (X,Y) position; 
	 * - the club's position is adjacent to the (X,Y) position;
	 * Returns false other wise.
	 * This method will be used to verify if the ogre kills the hero.
	 * 
	 * @param X x value of the desired position
	 * @param Y y value of the desired position
	 */
	public boolean isAdjacent(int X, int Y){
		boolean ret = false;
		ret = checkAdjacentOgre(X, Y);
		if(this.weaponX == X && (this.weaponY == Y+1 || this.weaponY == Y-1) )
			ret =  true;
			
		if(this.weaponY == Y && (this.weaponX == X+1 || this.weaponX == X-1) )
			ret =  true;
		
		if(this.weaponX == X && this.weaponY == Y)
			ret =  true;
		return ret;
	}
	/**
	 * Sets the character's 'stunned' attribute to 'value'. It also
	 * sets the ogre's display char according to 'value'.
	 * The 'stunned' attribute indicates if the character is stunned.
	 * 
	 * @param value the boolean value that 'stunned' will be set to
	 */
	public void setStunned(boolean value) {
		this.stunned = value;
		if(value == true){
			this.setDisplayChar('8');
		}
		else{
			this.setDisplayChar('O');
		} 
	}	
}
