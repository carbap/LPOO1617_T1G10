package dkeep.logic;

public class Game {	 

	private  Level level; 
	private  boolean lastLevel = false;
	private  boolean gameOver = false;

	

	public Game(Level startingLevel){
		this.level = startingLevel;
	}

	public boolean isGameOver()
	{
		return gameOver;
	}

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
	
	
	public char[][] getWorktable(){
		return level.getWorktable();
	}
	
	
	public boolean updateHero(int direction) 
	{ 
		boolean valid = false;
		
		if(direction == 2){
			if(level.table[level.getHero().X][level.getHero().Y+1] != 'X' && level.table[level.getHero().X][level.getHero().Y+1] != 'I' && level.table[level.getHero().X][level.getHero().Y+1] != '8'){
				this.level.getHero().updatePosition(direction);
				valid = true;
			}
			else{
				valid = false;
			}
		} 
		else if(direction == 4){
			if(level.table[level.getHero().X-1][level.getHero().Y] != 'X' && level.table[level.getHero().X-1][level.getHero().Y] != 'I' && level.table[level.getHero().X-1][level.getHero().Y] != '8'){
				this.level.getHero().updatePosition(direction);
				valid = true;
			}
			else{
				valid = false;
			}
		}
		else if (direction == 6){
			if(level.table[level.getHero().X+1][level.getHero().Y] != 'X' && level.table[level.getHero().X+1][level.getHero().Y] != 'I' && level.table[level.getHero().X+1][level.getHero().Y] != '8'){
				this.level.getHero().updatePosition(direction);
				valid = true;
			}
			else{
				valid = false;
			}
		}
		else if(direction == 8){
			if(level.table[level.getHero().X][level.getHero().Y-1] != 'X' && level.table[level.getHero().X][level.getHero().Y-1] != 'I' && level.table[level.getHero().X][level.getHero().Y-1] != '8'){
				this.level.getHero().updatePosition(direction);
				valid = true;
			}
			else{
				valid = false;
			}
		}
		
		if(valid){
			checkKey();
			if(checkHeroTurn()){
				System.out.println("Lose");
				this.setGameOver(true);
			}
		}
		
		return valid;
	}

	
	
	public void updateGame(){
		level.npc();
		
		if(checkEnemyTurn()){
			System.out.println("Lose");
			this.setGameOver(true);
		}
	}
	
	
	
	public void setGameOver(boolean state){
		this.gameOver = state;
	}
	
	
	public boolean isEndLevel(){
		char[][] tablecopy = level.getTableCopy();
		return (tablecopy[level.getHero().getX()][level.getHero().getY()] == 'S');
		
	}
	
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

	public boolean isLastLevel() {
		return lastLevel;
	}

	public void setLastLevel(boolean lastLevel) {
		this.lastLevel = lastLevel;
	}
	
	public Level getLevel()
	{
		return level;
	}

	public void changeLevel(Level newLevel){
		this.level = newLevel;
	}
	
}
