package dkeep.logic;

import java.util.Random;

public class SuspiciousMovement implements MovementStrategy{
	private int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	private int movIndex = movPattern.length-1;
	private boolean moveForward = true;

	public void changeDirection(){
		Random rand = new Random();
		int moveDirection = rand.nextInt(100);
		if(moveDirection < 75){
			moveForward = true;
		}
		else{
			moveForward = false;
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
			if(movIndex < 0)
				movIndex += 24;
			else
				movIndex %= 24;
		}
	}
	
	public int getDirection(char[][] table, int X, int Y){

		changeDirection();
		updateMovIndex();
		int direction = movPattern[movIndex];
		if(!moveForward){
			movIndex -=1;
			direction = invertMovement(direction);
		}				
		return direction;
	}
}

