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
	
	
	public OgreLevel(int OgreNr){
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
				for(int i = 0; i < OgreNr; i++){
					Ogre ogre0 = new Ogre(4, 1, 4, 2);
					this.Enemies.add(ogre0);
				}
				
				
				keyX = 7;
				keyY = 1;
				
				
				int endLevelX_temp[] = {0};
				this.endLevelX = endLevelX_temp;
				
				int endLevelY_temp[] = {1};
				this.endLevelY = endLevelY_temp;
	}

}
