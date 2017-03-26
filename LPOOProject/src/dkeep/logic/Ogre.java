package dkeep.logic;

public class Ogre extends Character{
	
	
	
	
	//constructor
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
	
		
	//move ogre
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
	
	//update club position
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
	
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean value) {
		this.stunned = value;
		if(value == true){
			this.setDisplayChar('8');
		}
		else{
			this.setDisplayChar('O');
		}
	}
	public int getTurnsStunned() {
		return turnsStunned;
	}
	public void setTurnsStunned(int turnsStunned) {
		this.turnsStunned = turnsStunned;
	}
	
	
	
}
