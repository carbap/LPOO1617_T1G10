import java.util.Scanner;

public class GuardLevel extends Level{

	int guardX = 0;
	int guardY = 0;
	int guardMovIndex = 0;
	
	int[] guardMov;
	
	
	GuardLevel(int endLevelX[], int endLevelY[], boolean lastLevel, int heroX, int heroY, int guardX, int guardY, int[] guardMov){
		super(endLevelX, endLevelY, lastLevel, heroX, heroY);
		this.guardMov = guardMov;
		this.guardX = guardX;
		this.guardY = guardY;
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
		int direction;
		boolean keepRunning = true;
		Scanner s = new Scanner(System.in); 
		direction = s.nextInt();
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
	}
	
	
}
