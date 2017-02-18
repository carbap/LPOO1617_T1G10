import java.util.Scanner;
import java.util.Random;

public class OgreLevel extends Level{

	int ogreX = 0;
	int ogreY = 0;
	int clubX = 0;
	int clubY = 0;
	
	
	
	OgreLevel(int endLevelX[], int endLevelY[], boolean lastLevel, int heroX, int heroY, int ogreX, int ogreY, int clubX, int clubY){
		super(endLevelX, endLevelY, lastLevel, heroX, heroY);
		this.ogreX = ogreX;
		this.ogreY = ogreY;
		this.clubX = clubX;
		this.clubY = clubY;
	}
	
	public boolean OgreMovement(){
		Random rand = new Random();
		int direction = (rand.nextInt(4) + 1)*2;
		if(direction == 2){
			if(table[ogreY+1][ogreX] != 'X' && table[ogreY+1][ogreX] != 'I'){
				ogreY += 1;
				table[ogreY-1][ogreX] = ' ';
				table[ogreY][ogreX] = 'O';	
			}
			
		}
		else if(direction == 4){
			if(table[ogreY][ogreX-1] != 'X' && table[ogreY][ogreX-1] != 'I'){
				ogreX -= 1;
				table[ogreY][ogreX+1] = ' ';
				table[ogreY][ogreX] = 'O';
			}
		}
		else if (direction == 6){
			if(table[ogreY][ogreX+1] != 'X' && table[ogreY][ogreX+1] != 'I'){
				ogreX += 1;
				table[ogreY][ogreX-1] = ' ';
				table[ogreY][ogreX] = 'O';
			}
		}
		else if(direction == 8){
			if(table[ogreY-1][ogreX] != 'X' && table[ogreY-1][ogreX] != 'I'){
				ogreY -= 1;
				table[ogreY+1][ogreX] = ' ';
				table[ogreY][ogreX] = 'O';
			}
		}
		return checkPosition();
	}
	
	public boolean ClubMovement(){
		Random rand = new Random();
		boolean valid = false;
		while(!valid){
			int direction = (rand.nextInt(4) + 1)*2;
			
			if(direction == 2){
				if(table[ogreY+1][ogreX] != 'X' && table[ogreY+1][ogreX] != 'I'){
					if(clubX != ogreX || clubY != ogreY){
						table[clubY][clubX] = ' ';	
					}
					clubY= ogreY + 1;
					clubX = ogreX;
					table[clubY][clubX] = '*';
					valid = true;
				}
				
			}
			else if(direction == 4){
				if(table[ogreY][ogreX-1] != 'X' && table[ogreY][ogreX-1] != 'I'){
					if(clubX != ogreX || clubY != ogreY){
						table[clubY][clubX] = ' ';	
					}
					clubX= ogreX - 1;
					clubY = ogreY;
					table[clubY][clubX] = '*';
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[ogreY][ogreX+1] != 'X' && table[ogreY][ogreX+1] != 'I'){
					if(clubX != ogreX || clubY != ogreY){
						table[clubY][clubX] = ' ';	
					}
					clubX= ogreX + 1;
					clubY = ogreY;
					table[clubY][clubX] = '*';
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[ogreY-1][ogreX] != 'X' && table[ogreY-1][ogreX] != 'I'){
					if(clubX != ogreX || clubY != ogreY){
						table[clubY][clubX] = ' ';	
					}
					clubY= ogreY - 1;
					clubX = ogreX;
					table[clubY][clubX] = '*';
					valid = true;
				}
			}
			
		}
		
		
		return checkPosition();
	}
	
	
	
	public boolean input(){
		int direction;
		boolean keepRunning = true;
		Scanner s = new Scanner(System.in); 
		direction = s.nextInt();
		keepRunning = checkMovement(direction);
		if(!keepRunning){
			printTable();
			return false;
		}
		keepRunning = OgreMovement();
		if(!keepRunning){
			printTable();
			return false;
		}
		keepRunning = ClubMovement();
		if(!keepRunning){
			printTable();
			return false;
		}
		printTable();
		return keepRunning;
	}
	
}
