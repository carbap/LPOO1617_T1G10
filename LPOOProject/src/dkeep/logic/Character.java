package dkeep.logic;

public abstract class Character {
	MovementStrategy mov;
	public int X;
	public int Y;
	public int weaponX;
	public int weaponY;
	
	public char displayChar;
	char weaponDisplayChar;
	
	public boolean weapon = false;
	
	public int getWeaponX() {
		return weaponX;
	}
	public int getWeaponY() {
		return weaponY;
	}
	public char getWeaponDisplayChar() {
		return weaponDisplayChar;
	}
	public boolean hasWeapon() {
		return weapon;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public char getDisplayChar(){
		return displayChar;
	}
	
	public abstract void move(char[][] table);
	
	public void updatePosition(int direction){
		if(direction == 2){
			Y += 1;
		}
		else if(direction == 4){
			X -= 1;
		}
		else if (direction == 6){
			X += 1;
		}
		else if(direction == 8){
			Y -= 1;
		}
	}
	
	public void setPosition(int X, int Y){
		this.X = X;
		this.Y = Y;
	}

	public abstract boolean isAdjacent(int X, int Y);
	
}
