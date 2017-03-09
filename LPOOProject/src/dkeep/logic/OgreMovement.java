package dkeep.logic;

import java.util.Random;

public class OgreMovement implements MovementStrategy{
	
	
	public int getDirection(char[][] table, int X, int Y){
		Random rand = new Random();
		boolean valid = false;
		int direction = 0;
		while(!valid){
			direction = (rand.nextInt(4) + 1)*2;
			if(direction == 2){
				if(table[X][Y+1] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
					Y += 1;
					valid = true;
				}

			}
			else if(direction == 4){
				if(table[X-1][Y] != 'X' && table[X-1][Y] != 'I' && table[X-1][Y] != 'S'){
					X -= 1;
					valid = true;
				}
			}
			else if (direction == 6){
				if(table[X+1][Y] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
					X += 1;
					valid = true;
				}
			}
			else if(direction == 8){
				if(table[X][Y-1] != 'X' && table[X][Y-1] != 'I' && table[X][Y-1] != 'S'){
					Y -= 1;
					valid = true;
				}
			}
		}
		return direction;
	}
	
	
	
}
