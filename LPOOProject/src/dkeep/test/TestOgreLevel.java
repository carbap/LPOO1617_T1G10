package dkeep.test;

import dkeep.logic.*;

public class TestOgreLevel extends Level
{
	char[][] map = {{'X','X','I','I','X'},
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

	public TestOgreLevel(){
		this.hero.X = 1;
		this.hero.Y = 1;
		this.setTable(map);
		this.setEndLevelX(new int[]{0,0});
		this.setEndLevelY(new int[]{2,3});
		
		Ogre ogre = new Ogre(3,1,3,2);
		
		this.Enemies.add(ogre);
		
		this.setKeyX(1);
		this.setKeyY(3);	
	}
	
	public TestOgreLevel(int i){
		this.setTable(map2);	
		Ogre ogre = new Ogre(4,4,4,5);
		
		this.Enemies.add(ogre);
	}
}
