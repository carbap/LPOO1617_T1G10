package dkeep.logic;
import java.util.Scanner;

public class Game {	
	
	Level level;

	boolean keyEnabled = true;

	boolean lastLevel = false;
	
	boolean gameOver = false;

	public Game(Level startingLevel){
		this.level = startingLevel;
	}
	
	public Level getLevel()
	{
		return level;
	}

	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public void openDoors(){
		for(int i = 0; i < level.endLevelX.length; i++){
			level.table[ level.endLevelY[i] ][ level.endLevelX[i] ] = 'S';
		}
	}
	
	public boolean checkPosition(){
		boolean return_value = true;
		if(table[heroY][heroX] == 'S'){
			if(lastLevel == true){
				System.out.println("You Win!");
			}
			return_value =  false;
		}
		else{
			/*if(table[heroY][heroX] == 'k'){
				openDoors();
			}*/
			if(heroX == keyX && heroY == keyY){
				keyEnabled = false;
				openDoors();
			}
			if(table[heroY+1][heroX] == 'G' || table[heroY-1][heroX] == 'G' || table[heroY][heroX+1] == 'G' || table[heroY][heroX-1] == 'G'){
				System.out.println("GameOver");
				return_value =  false;
			}
			if(table[heroY+1][heroX] == 'O' || table[heroY-1][heroX] == 'O' || table[heroY][heroX+1] == 'O' || table[heroY][heroX-1] == 'O'){
				System.out.println("GameOver");
				return_value =  false;
			}
			if(table[heroY+1][heroX] == '*' || table[heroY-1][heroX] == '*' || table[heroY][heroX+1] == '*' || table[heroY][heroX-1] == '*'){
				System.out.println("GameOver");
				return_value =  false;
			}
			if(table[heroY+1][heroX] == '$' || table[heroY-1][heroX] == '$' || table[heroY][heroX+1] == '$' || table[heroY][heroX-1] == '$'){
				System.out.println("GameOver");
				return_value =  false;
			}
		}
		return return_value;
	}

	
	public boolean checkMovement(int direction){
		boolean keep_running = true;
		if(direction == 2){
			if(table[heroY+1][heroX] == 'X' || table[heroY+1][heroX] == 'I'){
				return keep_running;
			}
			else{
				heroY += 1;
				keep_running = checkPosition();
				table[heroY-1][heroX] = ' ';
				table[heroY][heroX] = 'H';
				return keep_running;
			}
		}
		else if(direction == 4){
			if(table[heroY][heroX-1] == 'X' || table[heroY][heroX-1] == 'I'){
				return keep_running;
			}
			else{
				heroX -= 1;
				keep_running = checkPosition();
				table[heroY][heroX+1] = ' ';
				table[heroY][heroX] = 'H';
				return keep_running;
			}
		}
		else if (direction == 6){
			if(table[heroY][heroX+1] == 'X' || table[heroY][heroX+1] == 'I'){
				return keep_running;
			}
			else{
				heroX += 1;
				keep_running = checkPosition();
				table[heroY][heroX-1] = ' ';
				table[heroY][heroX] = 'H';
				return keep_running;
			}
		}
		else if(direction == 8){
			if(table[heroY-1][heroX] == 'X' || table[heroY-1][heroX] == 'I'){
				return keep_running;
			}
			else{
				heroY -= 1;
				keep_running = checkPosition();
				table[heroY+1][heroX] = ' ';
				table[heroY][heroX] = 'H';
				return keep_running;
			}
		}
		
		return keep_running;
	}
	
}
