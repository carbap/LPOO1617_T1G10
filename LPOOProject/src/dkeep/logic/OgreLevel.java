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
	
	Ogre ogre = new Ogre();
	
	public OgreLevel(){
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
				
				ogre.setX(4);
				ogre.setY(1);
				
				ogre.clubX = 4;
				ogre.clubY = 2;
				
				keyX=7;
				keyY=1;
				
				hero.setX(1);
				hero.setY(7);
				
				int endLevelX_temp[] = {0};
				endLevelX = endLevelX_temp;
				
				int endLevelY_temp[] = {1};
				endLevelY = endLevelY_temp;
				
				table[ogre.X][ogre.Y] = 'O';
				table[ogre.clubX][ogre.clubY] = '*'; 
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
				else if(i == keyX && j == keyY && i == ogre.X && j== ogre.Y)
				{
					System.out.print("$ ");
				}
				else if(i == keyX && j == keyY && i == ogre.clubX && j== ogre.clubY)
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
		ogreMovement();
		clubMovement();
	}

	public void ogreMovement(){
		Random rand = new Random();
		boolean valid = false;
		while(!valid){
			int direction = (rand.nextInt(4) + 1)*2;
			if(direction == 2){
				if(table[ogre.X][ogre.Y+1] != 'X' && table[ogre.X][ogre.Y+1] != 'I'){
					ogre.Y += 1;
					table[ogre.X][ogre.Y-1] = ' ';
					table[ogre.X][ogre.Y] = 'O';  
					valid = true;
				}

			}
			else if(direction == 4){
				if(table[ogre.X-1][ogre.Y] != 'X' && table[ogre.X-1][ogre.Y] != 'I'){
					ogre.X -= 1;
					table[ogre.X+1][ogre.Y] = ' ';
					table[ogre.X][ogre.Y] = 'O';
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[ogre.X+1][ogre.Y] != 'X' && table[ogre.X][ogre.Y+1] != 'I'){
					ogre.X += 1;
					table[ogre.X-1][ogre.Y] = ' ';
					table[ogre.X][ogre.Y] = 'O';
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[ogre.X][ogre.Y-1] != 'X' && table[ogre.X][ogre.Y-1] != 'I'){
					ogre.Y -= 1;
					table[ogre.X][ogre.Y+1] = ' ';
					table[ogre.X][ogre.Y] = 'O';
					valid = true;
				}
			}
		}
		//return checkPosition();
	}

	public void clubMovement(){
		Random rand = new Random();
		boolean valid = false;
		while(!valid){
			int direction = (rand.nextInt(4) + 1)*2;

			if(direction == 2){
				if(table[ogre.X][ogre.Y+1] != 'X' && table[ogre.X][ogre.Y+1] != 'I' && table[ogre.X][ogre.Y+1] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubX][ogre.clubY] = ' ';	
					}
					ogre.clubY= ogre.Y + 1;
					ogre.clubX = ogre.X;
					table[ogre.clubX][ogre.clubY] = '*';
					valid = true; 
				}

			}
			else if(direction == 4){
				if(table[ogre.X-1][ogre.Y] != 'X' && table[ogre.X-1][ogre.Y] != 'I' && table[ogre.X][ogre.Y+1] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubX][ogre.clubY] = ' ';	
					}
					ogre.clubX= ogre.X - 1;
					ogre.clubY = ogre.Y;
					table[ogre.clubX][ogre.clubY] = '*';
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[ogre.X+1][ogre.Y] != 'X' && table[ogre.X+1][ogre.Y] != 'I' && table[ogre.X][ogre.Y+1] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubX][ogre.clubY] = ' ';	
					}
					ogre.clubX= ogre.X + 1;
					ogre.clubY = ogre.Y;
					table[ogre.clubX][ogre.clubY] = '*';
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[ogre.X][ogre.Y-1] != 'X' && table[ogre.X][ogre.Y-1] != 'I' && table[ogre.X][ogre.Y+1] != 'H'){
					if(ogre.clubX != ogre.X || ogre.clubY != ogre.Y){
						table[ogre.clubX][ogre.clubY] = ' ';	
					}
					ogre.clubY= ogre.Y - 1;
					ogre.clubX = ogre.X;
					table[ogre.clubX][ogre.clubY] = '*';
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
