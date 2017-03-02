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

		this.hero.setPosition(1, 1);
		
		Guard guard = new Guard(8,1);
		this.Enemies.add(guard);

		keyX=7;
		keyY=8;

		int endLevelX_temp[] = {0,0};
		endLevelX = endLevelX_temp;
		
		int endLevelY_temp[] = {5,6};
		endLevelY = endLevelY_temp;
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

/*
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
	*/


}
