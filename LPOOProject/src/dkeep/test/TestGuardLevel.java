package dkeep.test;

import dkeep.logic.*;

public class TestGuardLevel extends Level
{
	char[][] map = 
		   {{'X','X','I','I','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	char map2[][] ={ 
			{'X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
			};

	public TestGuardLevel(){
		this.getHero().setX(1);
		this.getHero().setY(1);
		this.setTable(map);
		this.setEndLevelX(new int[]{0,0});
		this.setEndLevelY(new int[]{2,3});
		
		Guard guard = new Guard(3,1);
		
		this.Enemies.add(guard);
		
		this.setKeyX(1);
		this.setKeyY(3);
	}
	
	public TestGuardLevel(int i){
		this.getHero().setX(1);
		this.getHero().setY(1);
		
		this.setTable(map2);
		
		this.setKeyX(1);
		this.setKeyY(3);
		
		this.setEndLevelX(new int[]{8,8});
		this.setEndLevelY(new int[]{6,7});
		
		Guard guard = new Guard(7,1);
		
		this.Enemies.add(guard);
		
			
	}
}
