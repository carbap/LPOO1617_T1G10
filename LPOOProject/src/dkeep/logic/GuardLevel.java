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
	}
	
	/*public void printTable(){
		for(char[] bloc : table)
		{
			for(char i : bloc)
			{
				System.out.print(i + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void printTable(){
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				if(keyEnabled == true){
					if(keyX != guardX || keyY != guardY ){
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
	
	
	public boolean GuardMovement(){
		int direction = guardMov[guardMovIndex];
		guardMovIndex +=1;
		guardMovIndex %= 24;
		if(direction == 2){
			guardY += 1;
			table[guardY-1][guardX] = ' ';
			table[guardY][guardX] = 'G';	
		}
		else if(direction == 4){
			guardX -= 1;
			table[guardY][guardX+1] = ' ';
			table[guardY][guardX] = 'G';
		}
		else if (direction == 6){
			guardX += 1;
			table[guardY][guardX-1] = ' ';
			table[guardY][guardX] = 'G';
		}
		else if(direction == 8){
			guardY -= 1;
			table[guardY+1][guardX] = ' ';
			table[guardY][guardX] = 'G';
		}
		return checkPosition();
	}
	
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
	}*/
	
	
}
