package dkeep.cli;

import dkeep.logic.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainGameLoop
{

	static public ArrayList <Level> gameLevels = new ArrayList<Level>();
	
	public static void main(String[] args)
	{
		loadGameLevels();
		int levelIndex = 0;
		Game g = new Game(gameLevels.get(levelIndex));
		levelIndex++;
		int direction;
		boolean validDirection = false;
		while(!g.isGameOver())
		{ 
			g.draw();
			direction = input();
			validDirection = g.updateHero(direction);
			
			if(validDirection == true){
				if(!g.isGameOver()){
					g.updateGame();
					if(g.isEndLevel() ){
						if(levelIndex == gameLevels.size()){
							g.setGameOver(true);
						}
						else{
							g.changeLevel(gameLevels.get(levelIndex));
							levelIndex++;
						}
					}
				}
				
			}
			else{
				System.out.println("Invalid Input. Choose a new direction(2, 4, 6 or 8).");
			}
			
		}
		g.draw();
		
	}

	public static int input()
	{
		int direction = 0;
		Scanner s = new Scanner(System.in); 
		direction = s.nextInt();
		return direction;
	}
	
	public static void loadGameLevels(){
		Level level0 = new GuardLevel();
		Level level1 = new OgreLevel();
		gameLevels.add(level0);
		gameLevels.add(level1);
		
	}
}
