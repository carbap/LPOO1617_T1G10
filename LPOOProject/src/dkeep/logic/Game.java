package dkeep.logic;
import java.util.Scanner;

public class Game {	 
	
	public Level level; 

	public boolean lastLevel = false;
	
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
	
	public void draw()
	{
		level.printTable();
		level.npc();
	}

	public void update(int direction) 
	{ 
			if(direction == 2){
				if(level.table[level.hero.X][level.hero.Y+1] == 'X' || level.table[level.hero.X][level.hero.Y+1] == 'I'){
					return;
				}
				else{
					level.hero.Y += 1;
					checkGameOver();
					level.table[level.hero.X][level.hero.Y-1] = ' ';
					level.table[level.hero.X][level.hero.Y] = 'H';
				}
			} 
			else if(direction == 4){
				if(level.table[level.hero.X-1][level.hero.Y] == 'X' || level.table[level.hero.X-1][level.hero.Y] == 'I'){
					return;
				}
				else{
					level.hero.X -= 1;
					checkGameOver();
					level.table[level.hero.X+1][level.hero.Y] = ' ';
					level.table[level.hero.X][level.hero.Y] = 'H';
				}
			}
			else if (direction == 6){
				if(level.table[level.hero.X+1][level.hero.Y] == 'X' || level.table[level.hero.X+1][level.hero.Y] == 'I'){
					return;
				}
				else{
					level.hero.X += 1;
					checkGameOver();
					level.table[level.hero.X-1][level.hero.Y] = ' ';
					level.table[level.hero.X][level.hero.Y] = 'H';
					
				}
			}
			else if(direction == 8){
				if(level.table[level.hero.X][level.hero.Y-1] == 'X' || level.table[level.hero.X][level.hero.Y-1] == 'I'){
					return;
				}
				else{
					level.hero.Y -= 1;
					checkGameOver();
					level.table[level.hero.X][level.hero.Y+1] = ' ';
					level.table[level.hero.X][level.hero.Y] = 'H';
				}
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