import java.util.Scanner;

public class OgreLevel extends Level{

	int ogreX = 0;
	int ogreY = 0;
	int ogreMovDirection = 0;
	int ogreClubDirection = 0;
	
	int[] guardMov;
	
	
	OgreLevel(int endLevelX[], int endLevelY[], boolean lastLevel, int heroX, int heroY, int ogreX, int ogreY){
		super(endLevelX, endLevelY, lastLevel, heroX, heroY);
		this.guardMov = guardMov;
		this.ogreX = ogreX;
		this.ogreY = ogreY;
	}
	
	public boolean input(){
		int direction;
		Scanner s = new Scanner(System.in); 
		direction = s.nextInt();
		checkMovement(direction);
		//OgreMovement();
		printTable();
		return false;
	}
	
}
