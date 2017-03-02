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
		while(!g.isGameOver())
		{ 
			g.draw();
			direction = input();
			g.update(direction);
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
		if(g.isGameOver())
		{
			g.draw();   
		}
	} 

	public static int input()
	{
		int direction = 0;
		boolean validInput = false;
		Scanner s = new Scanner(System.in); 
		while(!validInput){
			direction = s.nextInt();
			if(direction == 2 || direction == 4 || direction == 6 || direction == 8){
				validInput = true;
			}
			else{
				System.out.println("Invalid Input. Choose a direction: 2, 4, 6 or 8.");
			}
		}
		return direction;
	}
	
	public static void loadGameLevels(){
		Level level0 = new GuardLevel();
		Level level1 = new OgreLevel();
		gameLevels.add(level0);
		gameLevels.add(level1);
		
	}
}
