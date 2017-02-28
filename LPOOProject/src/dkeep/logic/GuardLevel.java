package dkeep.logic;

public class GuardLevel extends Level {

	/*
			{'X','X','X','X','X','X','X','X','X','X'};
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};*/

	Guard guard = new Guard();

	public GuardLevel(){
		char temp[][] ={
				{'X','X','X','X','X','I','I','X','X','X'},
				{'X',' ','X',' ','X',' ',' ','X',' ','X'},
				{'X',' ','X','I','X',' ',' ','X','I','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','I','X','I','X',' ',' ','X','I','X'},
				{'X',' ','X',' ','X',' ',' ','X',' ','X'},
				{'X','X','X','X','X',' ',' ','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};

		table = temp;

		guard.setX(8);
		guard.setY(1);

		keyX=7;
		keyY=8;

		hero.setX(1);
		hero.setY(1); 

		int endLevelX_temp[] = {0,0};
		endLevelX = endLevelX_temp;
		
		int endLevelY_temp[] = {5,6};
		endLevelY = endLevelY_temp;
		
		guard.movIndex = 0;
		
		int[] movPattern_temp = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
		guard.movPattern = movPattern_temp;
		
		table[guard.X][guard.Y] = 'G';	
	}  

	public void printTable(){ 
		for(int j = 0; j < table[0].length; j++)
		{
			for(int i = 0; i < table.length; i++) 
			{
				if(i == keyX && j == keyY && i == hero.X && j== hero.Y)
				{
					System.out.print("$ ");
				}
			    else if(i == keyX && j == keyY && keyEnabled == true)
				{
					System.out.print("k ");
				}
				else if(i == hero.X && j== hero.Y) 
				{
					System.out.print("H ");
				}
				else
				{
					System.out.print(table[i][j] + " ");
				}
			} 
			System.out.print("\n");
		}
	}

	public void npc()
	{
		guardMovement();
	}

	public void guardMovement(){
		int direction = guard.movPattern[guard.movIndex];
		guard.movIndex +=1; 
		guard.movIndex %= 24;
		if(direction == 2){
			guard.Y += 1;
			table[guard.X][guard.Y-1] = ' ';
			table[guard.X][guard.Y] = 'G';	
		}
		else if(direction == 4){
			guard.X -= 1;
			table[guard.X+1][guard.Y] = ' ';
			table[guard.X][guard.Y] = 'G';
		}
		else if (direction == 6){
			guard.X += 1; 
			table[guard.X-1][guard.Y] = ' ';
			table[guard.X][guard.Y] = 'G';
		}
		else if(direction == 8){
			guard.Y -= 1;
			table[guard.X][guard.Y+1] = ' ';
			table[guard.X][guard.Y] = 'G';
		}
		//return checkGameOver();
	}
	/*
	public boolean input(){
		int direction = 0;
		boolean keepRunning = true;
		boolean validInput = false;
		Scanner s = new Scanner(System.in);
		while(!validInput){
			direction = s.nextInt();
			if(direction == 2 || direction == 4 || direction == 6 || direction == 8){
				validInput = true;
			}
			else{
				System.out.println("Invalid Input. Choose a direction: 2, 4, 6 or 8.\n");
			}
		}
		keepRunning = checkMovement(direction);
		if(!keepRunning){
			printTable();
			return false;
		}
		keepRunning = GuardMovement();
		if(!keepRunning){
			printTable();
			return false;
		}
		printTable();
		return keepRunning;
	}
	 */


}
