package dkeep.logic;

public abstract class Character {
	protected MovementStrategy mov;
	protected int X;
	protected int Y;
	protected int weaponX;
	protected int weaponY;
	
	protected boolean stunned = false;
	protected int turnsStunned = 0; 
	
	
	protected char displayChar;
	protected char weaponDisplayChar;
	
	protected boolean weapon = false;
	
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
	public void setWeapon(boolean value){
		this.weapon = value;
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
	
	public void setDisplayChar(char value){
		this.displayChar = value;
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
	
	public boolean isStunned() {
		return stunned;
	}
	public abstract void setStunned(boolean value);
	
	public int getTurnsStunned() {
		return turnsStunned;
	}
	
	public void setTurnsStunned(int turnsStunned) {
		this.turnsStunned = turnsStunned;
	}
	
	public void setWeaponX(int weaponX) {
		this.weaponX = weaponX;
	}
	public void setWeaponY(int weaponY) {
		this.weaponY = weaponY;
	}
	public MovementStrategy getMov() {
		return mov;
	}
	public void setMov(MovementStrategy mov) {
		this.mov = mov;
	}
	
	
	
	
}
