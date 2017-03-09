package dkeep.test;

import dkeep.logic.*;

public class TestLevel extends Level
{
	char[][] map = {{'X','X','I','I','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	public TestLevel(){
		this.hero.X = 1;
		this.hero.Y = 1;
		this.setTable(map);
		this.setEndLevelX(new int[]{0,0});
		this.setEndLevelY(new int[]{2,3});
		
		Guard guard = new Guard(3,1);
		
		this.Enemies.add(guard);
		
		this.setKeyX(1);
		this.setKeyY(3);
		
		
	}
}
