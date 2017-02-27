package dkeep.logic;
import java.util.Scanner;

public class Game {	 
	
	Level level; 

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
	
	public void draw()
	{
		/*
		char table[][] = this.getLevel().getTable();
		
		if()
		for(int i = 0; i < table[0].length;i++)
		{
			for(int j = 0; j < table.length;j++)
			{
				System.out.print(table[j][0]+ " ");
			}
			System.out.print("\n");
		}
		*/
		level.printTable();
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
			}
			else
			{
				lastLevel = true;
			}
		}
		else{
			/*if(table[heroY][level.hero.X] == 'k'){
				openDoors();
			}*/
			if(level.hero.X == level.keyX && level.hero.Y == level.keyY){
				level.keyEnabled = false;
				level.openDoors();
			}
			if(level.table[level.hero.X][level.hero.Y+1] == 'G' || level.table[level.hero.X][level.hero.Y-1] == 'G' || level.table[level.hero.X+1][level.hero.Y] == 'G' || level.table[level.hero.X-1][level.hero.Y] == 'G'){
				System.out.println("GameOver");
				gameOver =  true;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == 'O' || level.table[level.hero.X][level.hero.Y-1] == 'O' || level.table[level.hero.X+1][level.hero.Y] == 'O' || level.table[level.hero.X-1][level.hero.Y] == 'O'){
				System.out.println("GameOver");
				gameOver =  true;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == '*' || level.table[level.hero.X][level.hero.Y-1] == '*' || level.table[level.hero.X+1][level.hero.Y] == '*' || level.table[level.hero.X-1][level.hero.Y] == '*'){
				System.out.println("GameOver");
				gameOver =  true;
			}
			if(level.table[level.hero.X][level.hero.Y+1] == '$' || level.table[level.hero.X][level.hero.Y-1] == '$' || level.table[level.hero.X+1][level.hero.Y] == '$' || level.table[level.hero.X-1][level.hero.Y] == '$'){
				System.out.println("GameOver");
				gameOver =  true;
			}
		}
	}

	

	
}
