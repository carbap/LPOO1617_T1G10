import java.util.Scanner;

public class HelloWorld {

	static char table1[][] = { {'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	static char table2[][] = { {'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ','O',' ',' ','k','X'},
			{'X',' ',' ',' ','*',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','H',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
	};
	
	public static int level1endx[] = {0,0};
	public static int level1endy[] = {5,6};
	
	public static int heroX = 1;
	public static int heroY = 1;
	
	public static int guardX = 8;
	public static int guardY = 1;
	public static int[] guardMov = {4,2,2,2,2,4,4,4,4,4,4,2,6,6,6,6,6,6,6,8,8,8,8,8};
	
	public static int ogreX = 4;
	public static int ogreY = 1;
	public static int clubX = 4;
	public static int clubY = 2;
	
	public static int level2endx[] = {0};
	public static int level2endy[] = {1};
	
	
	
	

	
	public static void main(String[] args)
	{
		GuardLevel level1 = new GuardLevel(level1endx, level1endy, false, heroX, heroY, guardX, guardY, guardMov);
		level1.loadTable(table1);
		level1.printTable();
		while(level1.input() == true);
		
		heroX = 1;
		heroY = 7;
		
		OgreLevel level2 = new OgreLevel(level2endx, level2endy, true, heroX, heroY, ogreX, ogreY, clubX, clubY);
		level2.loadTable(table2);
		level2.printTable();
		while(level2.input() == true);
		
		System.exit(0);
	}

}
