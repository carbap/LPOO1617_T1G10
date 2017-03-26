package dkeep.logic;


/**
 * Hero extends the Character class and implements a few of its abstract methods
 * 
 */
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
