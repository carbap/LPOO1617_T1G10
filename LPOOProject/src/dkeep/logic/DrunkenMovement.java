package dkeep.logic;

import java.util.Random;

public class DrunkenMovement implements MovementStrategy{
	private int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	private int movIndex = movPattern.length-1;
	private int turnsAsleep = 0;
	private boolean moveForward = true;

	
	public void RandFallsAsleep(){
		if(turnsAsleep == 0){
			Random rand = new Random();
			int fallAsleep = rand.nextInt(100);
			if(fallAsleep < 20){
				turnsAsleep = 3;
				if(fallAsleep % 2 == 0){
					moveForward = true;
				}
				else{
					moveForward = false;
				}
			}
		}
	}
	
	public int invertMovement(int direction){
		int dir = 0;
		if(direction == 2){
			dir = 8;
		}
		else if(direction == 4){
			dir = 6;
		}
		else if(direction == 6){
			dir = 4;
		}
		else if(direction == 8){
			dir = 2;
		}
		return dir;
	}
	
	public void updateMovIndex(){
		if(moveForward){
			movIndex +=1;
			movIndex %= 24;
		}
		else{
			
		}
	}
	
	public int getDirection(char[][] table, int X, int Y){
		if(turnsAsleep == 0){
			updateMovIndex();
			int direction = movPattern[movIndex];
			movIndex -=1;
			if(!moveForward)
				direction = invertMovement(direction);
			return direction;
	}
		else{
			turnsAsleep -= 1;
			return 0;
		}
	}
}
