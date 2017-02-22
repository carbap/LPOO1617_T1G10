import java.util.Scanner;

public class GuardLevel extends Level{

	int guardX = 0;
	int guardY = 0;
	int guardMovIndex = 0;
	
	int[] guardMov;
	
	
	GuardLevel(int endLevelX[], int endLevelY[], boolean lastLevel, int heroX, int heroY, int guardX, int guardY, int[] guardMov, int keyX, int keyY){
		super(endLevelX, endLevelY, lastLevel, heroX, heroY, keyX, keyY);
		this.guardMov = guardMov;
		this.guardX = guardX;
		this.guardY = guardY;
	}
	
	/*public void printTable(){
		for(char[] bloc : table)
		{
			for(char i : bloc)
			{
				System.out.print(i + " ");
			}
			System.out.print("\n");
		}
	}*/
	
	public void printTable(){
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				if(keyEnabled == true){
					if(keyX != guardX || keyY != guardY ){
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
