package dkeep.logic;

/**
 * This class has information about the game,
 * most importantly its level.
 *
 */
public class Game {	 

	private  Level level; 
	private  boolean lastLevel = false;
	private  boolean gameOver = false;

	/**
	 * Constructs a new game, setting its level to the parameter.
	 * 
	 * @param startingLevel the level that the game's level will be set to
	 */
	public Game(Level startingLevel){
		this.level = startingLevel;
	}

	/**
	 * Returns true if the game is over and false otherwise.
	 * 
	 * @return the boolean value that indicates if the game is over
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
	/**
	 * Prints the worktable of the game's level in the console.
	 * 
	 * @see Level
	 */
	public void draw()
	{
		char[][] workTable = getWorktable();
		for(int i = 0; i < workTable.length; i++){
			for(int j = 0; j < workTable[i].length; j++){
				System.out.print( workTable[j][i] + " " );
			}
			System.out.print("\n");
		}
	}

	/**
	 * Returns the worktable of the game's level.
	 * 
	 * @return the worktable of the game's level
	 */
	public char[][] getWorktable(){
		return level.getWorktable();
	}
	/**
	 * Moves the hero down if possible. The hero can not move down
	 * if that position is occupied by a wall, a closed door
	 * or a stunned ogre.
	 * Returns true if the movement was successful and false otherwise.
	 * 
	 * @return a boolean value that indicates if the hero's position was updated
	 */
	public boolean updateHero2(){
		if(level.table[level.getHero().X][level.getHero().Y+1] != 'X' && level.table[level.getHero().X][level.getHero().Y+1] != 'I' && level.table[level.getHero().X][level.getHero().Y+1] != '8'){
			this.level.getHero().updatePosition(2);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Moves the hero to the left if possible. The hero can not move left
	 * if that position is occupied by a wall, a closed door
	 * or a stunned ogre.
	 * Returns true if the movement was successful and false otherwise.
	 * 
	 * @return a boolean value that indicates if the hero's position was updated
	 */
	public boolean updateHero4(){
		if(level.table[level.getHero().X-1][level.getHero().Y] != 'X' && level.table[level.getHero().X-1][level.getHero().Y] != 'I' && level.table[level.getHero().X-1][level.getHero().Y] != '8'){
			this.level.getHero().updatePosition(4);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Moves the hero to the right if possible. The hero can not move right
	 * if that position is occupied by a wall, a closed door
	 * or a stunned ogre.
	 * Returns true if the movement was successful and false otherwise.
	 * 
	 * @return a boolean value that indicates if the hero's position was updated
	 */
	public boolean updateHero6(){
		if(level.table[level.getHero().X+1][level.getHero().Y] != 'X' && level.table[level.getHero().X+1][level.getHero().Y] != 'I' && level.table[level.getHero().X+1][level.getHero().Y] != '8'){
			this.level.getHero().updatePosition(6);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Moves the hero up if possible. The hero can not move up
	 * if that position is occupied by a wall, a closed door
	 * or a stunned ogre.
	 * Returns true if the movement was successful and false otherwise.
	 * 
	 * @return a boolean value that indicates if the hero's position was updated
	 */
	public boolean updateHero8(){
		if(level.table[level.getHero().X][level.getHero().Y-1] != 'X' && level.table[level.getHero().X][level.getHero().Y-1] != 'I' && level.table[level.getHero().X][level.getHero().Y-1] != '8'){
			this.level.getHero().updatePosition(8);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Checks if the key was picked up and if so opens the doors
	 * and changes the hero's display char. It also checks if the 
	 * hero was killed by an enemie, if so setting the gameOver flag.
	 */
	public void EndOfTurnCheckin(){
		checkKey();
		if(checkHeroTurn()){
			System.out.println("Lose");
			this.setGameOver(true);
		}
	}
	/**
	 * If possible updates the hero position according to the parameter.
	 * If the movement was successful checks if the key was picked up or
	 * if the hero was killed, handling those events in case they occur.
	 * Returns true if the movement was successful and false otherwise.
	 * 
	 * @param direction integer value indicating where the character will attempt to move to
	 * @return boolean value indicating if the movement was successful
	 */
	public boolean updateHero(int direction) 
	{ 
		boolean valid = false;

		if(direction == 2)
			valid = updateHero2();
		else if(direction == 4)
			valid = updateHero4();
		else if (direction == 6)
			valid = updateHero6();
		else if(direction == 8)
			valid = updateHero8();

		if(valid)
			EndOfTurnCheckin();		
		return valid;
	}
	/**
	 * Moves all enemies in the game's level and checks
	 * if they killed the hero, if so ending the game
	 * 
	 * @see Level
	 */
	public void updateGame(){
		level.npc();

		if(checkEnemyTurn()){
			System.out.println("Lose");
			this.setGameOver(true);
		}
	}


	/**
	 * Sets the gameOver flag to the parameter.
	 * 
	 * @param state boolean value to set gameOver to
	 */
	public void setGameOver(boolean state){
		this.gameOver = state;
	}

	/**
	 * Returns true if the hero is positioned on a open door.
	 * Return false otherwise.
	 * 
	 * @return boolean value indicating if the hero is positioned on a open door
	 */
	public boolean isEndLevel(){
		char[][] tablecopy = level.getTableCopy();
		return (tablecopy[level.getHero().getX()][level.getHero().getY()] == 'S');

	}
	/**
	 * On the hero's turn, checks if the hero stunned an enemie and
	 * checks if an enemie killed the hero.
	 * Returns false if the hero lived after his turn or returns true if he died.
	 * 
	 * @return boolean value indicating if the hero lived after his turn
	 */
	public boolean checkHeroTurn(){
		boolean flag = false;
		for(int i = 0; i < level.Enemies.size(); i++){
			if(level.getHero().isAdjacent(level.Enemies.get(i).getX(), level.Enemies.get(i).getY()) ){
				if(!level.Enemies.get(i).isStunned() ){
					if(level.getHero().hasWeapon() ){
						level.Enemies.get(i).setTurnsStunned(3);
						level.Enemies.get(i).setStunned(true);
					}					
				}

			}

			if(level.Enemies.get(i).isAdjacent(level.getHero().getX(), level.getHero().getY()) ){
				if(!level.Enemies.get(i).isStunned() ){
					flag = true;
				}

			}

		}
		return flag;
	}

	/**
	 * On the enemies' turn, checks if an enemie killed the hero.
	 * Returns false if the hero lived after the enemies' turn
	 * or returns true if he died.
	 * 
	 * @return boolean value indicating if the hero lived after the enemies' turn
	 */
	public boolean checkEnemyTurn(){
		boolean flag = false;
		for(int i = 0; i < level.Enemies.size(); i++){
			if(level.Enemies.get(i).isAdjacent(level.getHero().getX(), level.getHero().getY()) ){
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * Checks if the hero picked up the key, if so opens the doors
	 * and changes the hero's display char.
	 */
	public void checkKey(){
		if(level.getHero().getX() == level.keyX && level.getHero().getY() == level.keyY){
			level.keyEnabled = false;
			if(lastLevel)
				level.openAllDoors();
			else
				level.openDoors();
			this.level.getHero().setDisplayChar('K');
		}
	}
	/**
	 * Returns true if the game's current level is the
	 * last level of the game. Returns false otherwise.
	 * 
	 * @return boolean value indicating if the current level is the game's last level.
	 */
	public boolean isLastLevel() {
		return lastLevel;
	}
	/**
	 * Sets the 'lastLevel' attribute to the parameter.
	 * 
	 * @param lastLevel boolean value to set the 'lastLevel' attribute to
	 */
	public void setLastLevel(boolean lastLevel) {
		this.lastLevel = lastLevel;
	}
	/**
	 * Returns the game's current level.
	 * 
	 * @return the game's current level.
	 */
	public Level getLevel()
	{
		return level;
	}
	/**
	 * Sets the game's level to the parameter.
	 * 
	 * @param newLevel level to set the game's level to
	 */
	public void changeLevel(Level newLevel){
		this.level = newLevel;
	}

}
