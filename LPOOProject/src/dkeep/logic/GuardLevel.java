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
		
	}  
	
	public void printTable(){
		for(int j = 0; j < table[0].length; j++)
		{
			for(int i = 0; i < table.length; i++)
			{
				if(keyEnabled == true){
					if(keyX != guard.X || keyY != guard.Y ){
						if(i == keyY && j == keyX){
							System.out.print("k ");
						}
						else{
							System.out.print(table[i][j] + " ");
						}
					}
					else{
						if( i == keyY && j == keyX){
							System.out.print("$ ");
						}
						else{
							System.out.print(table[i][j] + " ");
						}
					}
				}
				else{
					System.out.print(table[i][j] + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	
	public void GuardMovement(){
		int direction = guard.guardMov[guard.guardMovIndex];
		guard.guardMovIndex +=1; 
		guard.guardMovIndex %= 24;
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
