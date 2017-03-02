package dkeep.logic;

public class Game {	 

	public Level level; 
	public boolean lastLevel = false;
	public boolean gameOver = false;

	public char[][] workTable;

	public Game(Level startingLevel){
		this.level = startingLevel;
		//workTable = startingLevel.getTable();
	}

	public Level getLevel()
	{
		return level;
	}

	public void changeLevel(Level newLevel){
		this.level = newLevel;
		//workTable = newLevel.getTable();
	}

	public boolean isGameOver()
	{
		return gameOver;
	}

	public void draw()
	{
		updateWorkTable();
		for(int i = 0; i < workTable.length; i++){
			for(int j = 0; j < workTable[i].length; j++){
				System.out.print( workTable[j][i] + " " );
			}
			System.out.print("\n");
		}
	}
	
	public void updateWorkTable()
	{
		//reset table
		workTable = level.getTableCopy();
		
		//draw key
		if(level.keyEnabled)
		{
			workTable[level.keyX][level.keyY] = 'k';
		}
		
		//draw hero
		if(workTable[level.hero.getX()][level.hero.getY()] == ' ' || workTable[level.hero.getX()][level.hero.getY()] == 'S'){
			workTable[level.hero.getX()][level.hero.getY()] = level.hero.getDisplayChar();
		}
		else{
			workTable[level.hero.getX()][level.hero.getY()] = '$';
		}
			
		//draw enemies and weapons
		for(int i = 0; i < level.Enemies.size(); i++){
			//draw enemies
			if(workTable[level.Enemies.get(i).getX()][level.Enemies.get(i).getY()] == ' '){
				workTable[level.Enemies.get(i).getX()][level.Enemies.get(i).getY()] = level.Enemies.get(i).getDisplayChar();
			}
			else if(workTable[level.Enemies.get(i).getX()][level.Enemies.get(i).getY()] == 'k'){
				workTable[level.Enemies.get(i).getX()][level.Enemies.get(i).getY()] = '$';
			}
			
			//draw respective weapons if they exist
			if(level.Enemies.get(i).hasWeapon()){
				if(workTable[level.Enemies.get(i).getWeaponX()][level.Enemies.get(i).getWeaponY()] == ' '){
					workTable[level.Enemies.get(i).getWeaponX()][level.Enemies.get(i).getWeaponY()] = level.Enemies.get(i).getWeaponDisplayChar();
				}
				else if(workTable[level.Enemies.get(i).getWeaponX()][level.Enemies.get(i).getWeaponY()] == 'k'){
					workTable[level.Enemies.get(i).getWeaponX()][level.Enemies.get(i).getWeaponY()] = '$';
				}
			}
		}

	}
	

	public void update(int direction) 
	{ 
		
		if(direction == 2){
			if(level.table[level.hero.X][level.hero.Y+1] != 'X' && level.table[level.hero.X][level.hero.Y+1] != 'I'){
				this.level.hero.updatePosition(direction);
			}
		} 
		else if(direction == 4){
			if(level.table[level.hero.X-1][level.hero.Y] != 'X' && level.table[level.hero.X-1][level.hero.Y] != 'I'){
				this.level.hero.updatePosition(direction);
			}
		}
		else if (direction == 6){
			if(level.table[level.hero.X+1][level.hero.Y] != 'X' && level.table[level.hero.X+1][level.hero.Y] != 'I'){
				this.level.hero.updatePosition(direction);
			}
		}
		else if(direction == 8){
			if(level.table[level.hero.X][level.hero.Y-1] != 'X' && level.table[level.hero.X][level.hero.Y-1] != 'I'){
				this.level.hero.updatePosition(direction);
			}
		}

		checkKey();
		if(checkHero()){
			System.out.println("Lose0");
		}
		
		level.npc();
		
		if(checkHero()){
			System.out.println("Lose0");
		}

	}

	public void setGameOver(boolean state){
		this.gameOver = state;
	}
	
	
	public boolean isEndLevel(){
		boolean flag = false;
		for(int i = 0; i < this.level.endLevelX.length; i++){
			if(this.level.hero.getX() == this.level.endLevelX[i] && this.level.hero.getY() == this.level.endLevelY[i]){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public boolean checkHero(){
		boolean flag = false;
		for(int i = 0; i < level.Enemies.size(); i++){
			if( level.Enemies.get(i).isAdjacent(level.hero.getX(), level.hero.getY()) ){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void checkKey(){
		if(level.hero.getX() == level.keyX && level.hero.getY() == level.keyY){
			level.keyEnabled = false;
			level.openDoors();
		}
	}
	
	
	
	public void checkGameOver(){  
		if(level.table[level.hero.X][level.hero.Y] == 'S'){
			if(lastLevel == true){
				gameOver =  true;
				System.out.println("You Win!");
				return;
			}
		}
		else{
			if(level.hero.X == level.keyX && level.hero.Y == level.keyY){
				level.keyEnabled = false;
				level.openDoors();
			}
			if(level.table[level.hero.X][level.hero.Y] == 'G')
			{
				System.out.println("GameOver");
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y] == 'O')
			{
				System.out.println("GameOver");
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y] == '*')
			{
				System.out.println("GameOver"); 
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == 'G' || level.table[level.hero.X][level.hero.Y-1] == 'G' || level.table[level.hero.X+1][level.hero.Y] == 'G' || level.table[level.hero.X-1][level.hero.Y] == 'G'){
				System.out.println("GameOver");
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == 'O' || level.table[level.hero.X][level.hero.Y-1] == 'O' || level.table[level.hero.X+1][level.hero.Y] == 'O' || level.table[level.hero.X-1][level.hero.Y] == 'O'){
				System.out.println("GameOver");
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == '*' || level.table[level.hero.X][level.hero.Y-1] == '*' || level.table[level.hero.X+1][level.hero.Y] == '*' || level.table[level.hero.X-1][level.hero.Y] == '*'){
				System.out.println("GameOver");
				gameOver =  true;
				return;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == '$' || level.table[level.hero.X][level.hero.Y-1] == '$' || level.table[level.hero.X+1][level.hero.Y] == '$' || level.table[level.hero.X-1][level.hero.Y] == '$'){
				System.out.println("GameOver");
				gameOver =  true;
				return; 
			}
		}
	}




}
