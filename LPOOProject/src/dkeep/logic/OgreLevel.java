package dkeep.logic;
import java.util.Random;

public class OgreLevel extends Level{
	/*
	{   {'X','X','X','X','X','X','X','X','X'},
		{'I',' ',' ',' ','O',' ',' ',' ','X'},
		{'X',' ',' ',' ','*',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X','H',' ',' ',' ',' ',' ',' ','X'},
		{'X','X','X','X','X','X','X','X','X'}*/
	
	Ogre ogre;
	
	OgreLevel(){
		char temp[][] ={
				{'X','I','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}
				};
				
				table = temp;	
	}

	public void printTable(){
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				if(keyEnabled == true){
					if((keyX != ogre.X || keyY != ogre.Y) && (keyX != ogre.clubX || keyY != ogre.clubY) ){
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

	public void OgreMovement(){
		Random rand = new Random();
		boolean valid = false;
		while(!valid){
			int direction = (rand.nextInt(4) + 1)*2;
			if(direction == 2){
				if(table[ogre.Y+1][ogre.X] != 'X' && table[ogre.Y+1][ogre.X] != 'I'){
					ogre.Y += 1;
					table[ogre.Y-1][ogre.X] = ' ';
					table[ogre.Y][ogre.X] = 'O';
					valid = true;
				}

			}
			else if(direction == 4){
				if(table[ogre.Y][ogre.X-1] != 'X' && table[ogre.Y][ogre.X-1] != 'I'){
					ogre.X -= 1;
					table[ogre.Y][ogre.X+1] = ' ';
					table[ogre.Y][ogre.X] = 'O';
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[ogre.Y][ogre.X+1] != 'X' && table[ogre.Y][ogre.X+1] != 'I'){
					ogre.X += 1;
					table[ogre.Y][ogre.X-1] = ' ';
					table[ogre.Y][ogre.X] = 'O';
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[ogre.Y-1][ogre.X] != 'X' && table[ogre.Y-1][ogre.X] != 'I'){
					ogre.Y -= 1;
					table[ogre.Y+1][ogre.X] = ' ';
					table[ogre.Y][ogre.X] = 'O';
					valid = true;
				}
			}
		}
		//return checkPosition();
	}

	public void ClubMovement(){
		Random rand = new Random();
		boolean valid = false;
		while(!valid){
			int direction = (rand.nextInt(4) + 1)*2;

			if(direction == 2){
				if(table[ogre.Y+1][ogre.X] != 'X' && table[ogre.Y+1][ogre.X] != 'I' && table[ogre.Y+1][ogre.X] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubY][ogre.clubX] = ' ';	
					}
					ogre.clubY= ogre.Y + 1;
					ogre.clubX = ogre.X;
					table[ogre.clubY][ogre.clubX] = '*';
					valid = true;
				}

			}
			else if(direction == 4){
				if(table[ogre.Y][ogre.X-1] != 'X' && table[ogre.Y][ogre.X-1] != 'I' && table[ogre.Y+1][ogre.X] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubY][ogre.clubX] = ' ';	
					}
					ogre.clubX= ogre.X - 1;
					ogre.clubY = ogre.Y;
					table[ogre.clubY][ogre.clubX] = '*';
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[ogre.Y][ogre.X+1] != 'X' && table[ogre.Y][ogre.X+1] != 'I' && table[ogre.Y+1][ogre.X] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubY][ogre.clubX] = ' ';	
					}
					ogre.clubX= ogre.X + 1;
					ogre.clubY = ogre.Y;
					table[ogre.clubY][ogre.clubX] = '*';
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[ogre.Y-1][ogre.X] != 'X' && table[ogre.Y-1][ogre.X] != 'I' && table[ogre.Y+1][ogre.X] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubY][ogre.clubX] = ' ';	
					}
					ogre.clubY= ogre.Y - 1;
					ogre.clubX = ogre.X;
					table[ogre.clubY][ogre.clubX] = '*';
					valid = true;
				}
			}
		}

		//return checkPosition();
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
				System.out.println("Invalid Input. Choose a direction: 2, 4, 6 or 8.");
			}
		}
		keepRunning = checkMovement(direction);
		if(!keepRunning){
			printTable();
			return false;
		}
		keepRunning = OgreMovement();
		keepRunning = ClubMovement();
		printTable();
		return keepRunning;
	}
	*/

}
