package dkeep.logic;


/**
 * Hero extends the Character class and implements a few of its abstract methods
 * 
 */
public class Hero extends Character
{	
	
	/**
	 * Constructs a new Hero given its X and Y coordinates.
	 * 
	 * @param heroX hero's x coordinate
	 * @param heroY hero's y coordinate
	 */
	public Hero(int heroX, int heroY){
		this.X = heroX;
		this.Y = heroY;
		this.displayChar = 'H';
	}

	public void move(char[][] table){

	} 

	/**
	 * Returns true if the Hero is adjacent to the (X,Y) position
	 * and returns false otherwise. 
	 * 
	 * @return boolean value that indicates if the Hero is adjacent to the (X,Y) position
	 */
	public boolean isAdjacent(int X, int Y){
		if(this.X == X && (this.Y == Y+1 || this.Y == Y-1) ){
			return true; 
		}

		if(this.Y == Y && (this.X == X+1 || this.X == X-1) ){
			return true;
		}
		return false;
	}

	@Override
	public void setStunned(boolean value) {
				
	}
}
