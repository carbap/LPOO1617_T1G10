package dkeep.logic;

import java.util.Random;

public class OgreMovement implements MovementStrategy{

	public boolean direction2(char[][] table, int X, int Y, boolean valid){
		if(table[X][Y+1] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
			Y += 1;
			return true;
		}
		return false;
	}

	public boolean direction4(char[][] table, int X, int Y, boolean valid){
		if(table[X-1][Y] != 'X' && table[X-1][Y] != 'I' && table[X-1][Y] != 'S'){
			X -= 1;
			return true;
		}
		return false;
	}

	public boolean direction6(char[][] table, int X, int Y, boolean valid){
		if(table[X+1][Y] != 'X' && table[X][Y+1] != 'I' && table[X][Y+1] != 'S'){
			X += 1;
			return true;
		}
		return false;
	}

	public boolean direction8(char[][] table, int X, int Y, boolean valid){
		if(table[X][Y-1] != 'X' && table[X][Y-1] != 'I' && table[X][Y-1] != 'S'){
			Y -= 1;
			return true;
		}
		return false;
	}


	public int getDirection(char[][] table, int X, int Y){
		Random rand = new Random();
		boolean valid = false;
		int direction = 0;
		while(!valid){
			direction = (rand.nextInt(4) + 1)*2;
			if(direction == 2){
				valid = direction2(table,X,Y,valid);
			}
			else if(direction == 4){
				valid = direction4(table,X,Y,valid);
			}
			else if (direction == 6){
				valid = direction6(table,X,Y,valid);
			}
			else if(direction == 8){
				valid = direction8(table,X,Y,valid);
			}
		}
		return direction;
	}



}
