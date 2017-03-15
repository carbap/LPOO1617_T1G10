package dkeep.logic;

import java.util.Random;

public class Guard extends Character{
	
	private boolean asleep = false;

	private char asleepDisplayChar = 'g';
	
	//constructor
	public Guard(int guardX, int guardY, int guardMov){
		this.X = guardX;
		this.Y = guardY;
		this.displayChar = 'G';
		//Random rand = new Random();
		//int movtype = rand.nextInt(3) + 1;
		switch(guardMov){
		case 1:
			this.mov = new RookieMovement();
			break;
		case 2:
			this.mov = new DrunkenMovement();
			break;
		case 3:
			this.mov = new SuspiciousMovement();
			break;
		}		
		
	}
	
	//move guard
	public void move(char[][] table){
		int direction = mov.getDirection(table, X, Y);
		if(direction == 0){
			this.setAsleep(true);
		}
		else{
			this.setAsleep(false);
		}
		updatePosition(direction);
	}

	//return if guard is asleep or not
	public boolean isAsleep() {
		return asleep;
	}

	//sets guard asleep/awake
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	
	//return the char to be displayed
	public char getDisplayChar(){
		if(!asleep){
			return this.displayChar;
		}
		else{
			return this.asleepDisplayChar;
		}
	}
	
	public boolean isAdjacent(int X, int Y){
		if(!this.asleep){
			if(this.X == X && (this.Y == Y+1 || this.Y == Y-1) ){
				return true;
			}
				
			if(this.Y == Y && (this.X == X+1 || this.X == X-1) ){
				return true;
			}
		}
			
		return false;
	}

	@Override
	public void setStunned(boolean value) {
		// TODO Auto-generated method stub
		
	}
	
}
