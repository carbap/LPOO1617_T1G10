package dkeep.logic;


/**
 * This class has basic information about the in-game characters 
 * such as the hero, guards and ogres.
 * 
 * @see Hero
 * @see Guard
 * @see Ogre
 */
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
	/**
	 * Returns the X value of the character's weapon
	 * 
	 * @return X value of the character's weapon
	 */
	public int getWeaponX() {
		return weaponX;
	}
	/**
	 * Returns the Y value of the character's weapon
	 * 
	 * @return Y value of the character's weapon
	 */
	public int getWeaponY() {
		return weaponY;
	}
	/**
	 * Returns the char that is used to represent the
	 * character's weapon
	 * 
	 * @return the weapon's display char
	 */
	public char getWeaponDisplayChar() {
		return weaponDisplayChar;
	}
	/**
	 * Returns a boolean value that is true if the character owns 
	 * a weapon and false otherwise.
	 * 
	 * @return boolean value that indicates if the character owns a weapon
	 */
	public boolean hasWeapon() {
		return weapon;
	}
	/**
	 * Sets the character's 'weapon' attribute to 'value'. 
	 * The 'weapon' attribute indicates if the character owns a weapon.
	 * 
	 * @param value the boolean value that 'weapon' will be set to
	 */
	public void setWeapon(boolean value){
		this.weapon = value;
	}
	/**
	 * Returns the character's X value.
	 * 
	 * @return the character's X value
	 */
	public int getX() {
		return X;
	}
	/**
	 * Sets the character's X value to the x parameter.
	 * 
	 * @param x value to set X to
	 */
	public void setX(int x) {
		X = x;
	}
	/**
	 * Returns the character's Y value.
	 * 
	 * @return the character's Y value
	 */
	public int getY() {
		return Y;
	}
	/**
	 * Sets the character's Y value to the y parameter.
	 * 
	 * @param y value to set Y to
	 */
	public void setY(int y) {
		Y = y;
	}
	/**
	 * Returns the char that is used to represent the character.
	 * 
	 * @return the character's display char
	 */
	public char getDisplayChar(){
		return displayChar;
	}
	/**
	 * Sets the character's display char to the value parameter.
	 * 
	 * @param value the char that will be set as the character's display char
	 */
	public void setDisplayChar(char value){
		this.displayChar = value;
	}
	
	/**
	 * Abstract method that is responsible for moving a character in the level's table.
	 * 
	 * @param table char matrix that represents the level's table.
	 * @see Level
	 * @see Guard
	 * @see Ogre
	 */
	public abstract void move(char[][] table);
	
	/**
	 * Method to update the character's position.
	 * The direction parameter has four possible values:
	 * 2 means down, 4 means left, 6 means right and 8 means up.
	 * 
	 * 
	 * @param direction integer value that indicates where the character will move to
	 */
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
	/**
	 * Sets the character's position to the X and Y parameters.
	 * 
	 * @param X value to set the character's X to
	 * @param Y value to set the character's Y to
	 */
	public void setPosition(int X, int Y){
		this.X = X;
		this.Y = Y;
	}

	/**
	 * Abstract method that returns true if the character's position 
	 * is adjacent to the (X,Y) position and returns false otherwise.
	 * This is an abstract method because this function's behavior is
	 * different for hero, guards and ogres.
	 * 
	 * @param X x value of the desired position
	 * @param Y y value of the desired position
	 * @see Hero
	 * @see Guard
	 * @see Ogre
	 */
	public abstract boolean isAdjacent(int X, int Y);
	/**
	 * Returns a boolean value that is true if the character is stunned
	 * and false otherwise.
	 * 
	 * @return boolean value that indicates if the character is stunned
	 */
	public boolean isStunned() {
		return stunned;
	}
	/**
	 * Abstract method that sets the character's 'stunned' attribute to 'value'. 
	 * The 'stunned' attribute indicates if the character is stunned.
	 * 
	 * @param value the boolean value that 'stunned' will be set to
	 * @see Ogre
	 */
	public abstract void setStunned(boolean value);
	/**
	 * Returns the numbers of turns the character is stunned for.
	 * 
	 * @return numbers of turns the character is stunned for
	 */
	public int getTurnsStunned() {
		return turnsStunned;
	}
	/**
	 * Sets the numbers of turns the character will be stunned for.
	 * 
	 * @param turnsStunned value to set the numbers of turns the character will be stunned for
	 */
	public void setTurnsStunned(int turnsStunned) {
		this.turnsStunned = turnsStunned;
	}
	/**
	 * Sets the x value of the character's weapon to the weaponX parameter.
	 * 
	 * @param weaponX value to set the weapon's x coordinate to
	 */
	public void setWeaponX(int weaponX) {
		this.weaponX = weaponX;
	}
	/**
	 * Sets the y value of the character's weapon to the weaponY parameter.
	 * 
	 * @param weaponY value to set the weapon's y coordinate to
	 */
	public void setWeaponY(int weaponY) {
		this.weaponY = weaponY;
	}
	/**
	 * Returns the character's movement strategy
	 * 
	 * @return the character's movement strategy
	 */
	public MovementStrategy getMov() {
		return mov;
	}
	/**
	 * Sets the character's movement strategy to the mov parameter
	 * 
	 * @param mov value to set the character's movement strategy to
	 */
	public void setMov(MovementStrategy mov) {
		this.mov = mov;
	}
		
}
