import java.util.Scanner;

public class HelloWorld {

	
	
	
	public static int heroX = 1;
	public static int heroY = 1;
	
	public static int guardX = 8;
	public static int guardY = 1;
	public static int guardMovIndex = 0;
	public static int[] guardMov = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	
	public static char table[][] = new char[10][10];
	
	public static void loadTable(){
		table[0] = new char[] {'X','X','X','X','X','X','X','X','X','X'};
		table[1] = new char[] {'X','H',' ',' ','I',' ','X',' ','G','X'};
		table[2] = new char[] {'X','X','X',' ','X','X','X',' ',' ','X'};
		table[3] = new char[] {'X',' ','I',' ','I',' ','X',' ',' ','X'};
		table[4] = new char[] {'X','X','X',' ','X','X','X',' ',' ','X'};
		table[5] = new char[] {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'};
		table[6] = new char[] {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'};
		table[7] = new char[] {'X','X','X',' ','X','X','X','X',' ','X'};
		table[8] = new char[] {'X',' ','I',' ','I',' ','X','k',' ','X'};
		table[9] = new char[] {'X','X','X','X','X','X','X','X','X','X'};
	}
	
	public static void printTable(){
		for(char[] bloc : table)
		{
			for(char i : bloc)
			{
				System.out.print(i + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void openDoors(){
		table[5][0] = 'S';
		table[6][0] = 'S';
	}
	
	
	public static boolean checkPosition(){
		boolean return_value = true;
		if(table[heroY][heroX] == 'S'){
			System.out.println("You Win!");
			return_value =  false;
		}
		if(table[heroY][heroX] == 'k'){
			openDoors();
		}
		if(table[heroY+1][heroX] == 'G' || table[heroY-1][heroX] == 'G' || table[heroY][heroX+1] == 'G' || table[heroY][heroX-1] == 'G'){
			System.out.println("GameOver");
			return_value =  false;
		}
		return return_value;
	}
	
	public static void GuardMovement(){
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
		checkPosition();
	}
	
	public static boolean checkMovement(int direction){
		boolean keep_running = true;
		if(direction == 2){
			if(table[heroY+1][heroX] == 'X' || table[heroY+1][heroX] == 'I'){
				return false;
			}
			else{
				heroY += 1;
				keep_running = checkPosition();
				table[heroY-1][heroX] = ' ';
				table[heroY][heroX] = 'H';
				if(!keep_running){
					printTable();
					System.exit(0);
				}
				return true;
			}
		}
		else if(direction == 4){
			if(table[heroY][heroX-1] == 'X' || table[heroY][heroX-1] == 'I'){
				return false;
			}
			else{
				heroX -= 1;
				keep_running = checkPosition();
				table[heroY][heroX+1] = ' ';
				table[heroY][heroX] = 'H';
				if(!keep_running){
					printTable();
					System.exit(0);
				}
				return true;
			}
		}
		else if (direction == 6){
			if(table[heroY][heroX+1] == 'X' || table[heroY][heroX+1] == 'I'){
				return false;
			}
			else{
				heroX += 1;
				keep_running = checkPosition();
				table[heroY][heroX-1] = ' ';
				table[heroY][heroX] = 'H';
				if(!keep_running){
					printTable();
					System.exit(0);
				}
				return true;
			}
		}
		else if(direction == 8){
			if(table[heroY-1][heroX] == 'X' || table[heroY-1][heroX] == 'I'){
				return false;
			}
			else{
				heroY -= 1;
				keep_running = checkPosition();
				table[heroY+1][heroX] = ' ';
				table[heroY][heroX] = 'H';
				if(!keep_running){
					printTable();
					System.exit(0);
				}
				return true;
			}
		}
		
		return false;
	}
	
	public static void input(){
		int direction;
		Scanner s = new Scanner(System.in); 
		direction = s.nextInt();
		checkMovement(direction);
		GuardMovement();
		printTable();
	}
	
	public static void main(String[] args)
	{
		//char table[][] = new char[10][10];
		loadTable();
		printTable();
		while(true){
			input();
			
		}

	}

}
