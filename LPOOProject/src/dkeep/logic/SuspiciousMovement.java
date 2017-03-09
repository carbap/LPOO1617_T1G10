package dkeep.logic;

import java.util.Random;

public class SuspiciousMovement implements MovementStrategy{
	int[] movPattern = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	int movIndex = movPattern.length-1;
	boolean moveForward = true;


	public int getDirection(char[][] table, int X, int Y){

		Random rand = new Random();
		int moveDirection = rand.nextInt(100);
		if(moveDirection < 75){
			moveForward = true;
		}
		else{
			moveForward = false;
		}						

		int direction;
		
		if(moveForward){
			movIndex +=1;
			movIndex %= 24;
			direction = movPattern[movIndex];
		}
		else{
			//movIndex -=1;
			if(movIndex < 0){
				movIndex += 24;
			}
			else{
				movIndex %= 24;
			}
			
			direction = movPattern[movIndex];
			movIndex -=1;
			
			if(direction == 2){
				direction = 8;
			}
			else if(direction == 4){
				direction = 6;
			}
			else if(direction == 6){
				direction = 4;
			}
			else if(direction == 8){
				direction = 2;
			}
		}
		return direction;
	}
}

