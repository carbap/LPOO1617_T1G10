package dkeep.logic;

public class OgreLevel extends Level{
	/*
	{   {'X','X','X','X','X','X','X','X','X'},
		{'I',' ',' ',' ','O',' ',' ',' ','X'},
		{'X',' ',' ',' ','*',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X','H',' ',' ',' ',' ',' ',' ','X'},
		{'X','X','X','X','X','X','X','X','X'}*/
	
	
	public OgreLevel(){
		char temp[][] ={ 
				{'X','I','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}
				};
				
				table = temp;
				this.hero.setPosition(1, 7);
				this.hero.setWeapon(true);
				this.hero.setDisplayChar('A');
				Ogre ogre0 = new Ogre(4, 1, 4, 2);
				//Ogre ogre1 = new Ogre(4, 1, 4, 2);
				//Ogre ogre2 = new Ogre(4, 1, 4, 2);
				this.Enemies.add(ogre0);
				//this.Enemies.add(ogre1);
				//this.Enemies.add(ogre2);
				
				keyX = 7;
				keyY = 1;
				
				/*ogre.setX(4);
				ogre.setY(1);
				
				ogre.clubX = 4;
				ogre.clubY = 2;
				
				
				hero.setX(1);
				hero.setY(7);
				*/
				int endLevelX_temp[] = {0};
				endLevelX = endLevelX_temp;
				
				int endLevelY_temp[] = {1};
				endLevelY = endLevelY_temp;
	}

}
