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
		updatePosition(direction);
		direction = mov.getDirection(table, X, Y);
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
	
	public boolean isAdjacent(int X, int Y){
		if(this.X == X && (this.Y == Y+1 || this.Y == Y-1) ){
			return true;
		}
			
		if(this.Y == Y && (this.X == X+1 || this.X == X-1) ){
			return true;
		}
		
		if(this.weaponX == X && (this.weaponY == Y+1 || this.weaponY == Y-1) ){
			return true;
		}
			
		if(this.weaponY == Y && (this.weaponX == X+1 || this.weaponX == X-1) ){
			return true;
		}
		
		return false;
	}
	
}
