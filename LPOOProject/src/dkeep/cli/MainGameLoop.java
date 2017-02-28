package dkeep.cli;

import dkeep.logic.*;

import java.util.Scanner;

public class MainGameLoop
{

	public static void main(String[] args)
	{
		Game g = new Game(new GuardLevel());
		int direction;
		while(!g.isGameOver())
		{ 
			g.draw();
			direction = input();
			g.update(direction);
			if(     g.lastLevel==false
					&&
					(
					(g.level.hero.X == g.level.endLevelX[0] && g.level.hero.Y == g.level.endLevelY[0]) ||
					(g.level.hero.X == g.level.endLevelX[1] && g.level.hero.Y == g.level.endLevelY[1])
					)
					&&
					g.level.keyEnabled == false
			  )
			{ 
				g = new Game(new OgreLevel());
				g.lastLevel = true;
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
}
