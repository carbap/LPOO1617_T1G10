package dkeep.logic;

public class OgreLevel extends Level{
	/**
	 * Constructs a default OgreLevel that will be used for
	 * the game's second and last level.
	 * The OgreNr parameter will indicate the number of ogres in the level.
	 * 
	 * @param OgreNr
	 */
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
			Ogre ogre0 = new Ogre(4, 1, 4, 1);
			this.Enemies.add(ogre0);
		}


		keyX = 7;
		keyY = 1;


		int endLevelX_temp[] = {0};
		this.endLevelX = endLevelX_temp;

		int endLevelY_temp[] = {1};
		this.endLevelY = endLevelY_temp;
	}
	
	/**
	 * Constructs an OgreLevel that will be used for
	 * the game's level editor functionality.
	 * The table's dimensions will be set using the parameters
	 * width and height. The OgreNr parameter will indicate the amount
	 * of ogres and their initial position will be set using ogreX and ogreY.
	 * 
	 * @param OgreNr
	 * @param ogreX
	 * @param ogreY
	 * @param width
	 * @param height
	 */
	public OgreLevel(int OgreNr, int ogreX, int ogreY, int width, int height){
		char temp[][] = new char[width][height];
		
		for(int i = 0; i < temp.length;i++)
		{
			for(int j = 0; j < temp[i].length;j++)
			{
				if(i == 0 || i == (width-1) || j == 0 || j == (height-1))
				{
					temp[i][j] = 'X';
				}
				else
				{
					temp[i][j] = ' ';
				}
			}
		}

		table = temp;
		
		for(int i = 0; i < OgreNr; i++){
			Ogre ogre0 = new Ogre(ogreX, ogreY, ogreX, ogreY);
			this.Enemies.add(ogre0);
		}
		
		int endLevelX_temp[] = {0};
		this.endLevelX = endLevelX_temp;

		int endLevelY_temp[] = {1};
		this.endLevelY = endLevelY_temp;
		
		this.hero.setWeapon(true);
		this.hero.setDisplayChar('A');
		
	}

}
