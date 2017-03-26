package dkeep.logic;
/**
 * This class is a Level whose enemies are guards.
 *
 */
public class GuardLevel extends Level {
	/**
	 * Constructs a default GuardLevel that will be used for
	 * the game's first level.
	 * The guardMov parameter will indicate the guards movement strategy
	 * @param guardMov
	 */
	public GuardLevel(int guardMov){
		char temp[][] ={
				{'X','X','X','X','X','I','I','X','X','X'},
				{'X',' ','X',' ','X',' ',' ','X',' ','X'},
				{'X',' ','X','I','X',' ',' ','X','I','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','I','X','I','X',' ',' ','X','I','X'},
				{'X',' ','X',' ','X',' ',' ','X',' ','X'},
				{'X','X','X','X','X',' ',' ','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};

		table = temp;

		this.hero.setPosition(1, 1);
		
		Guard guard = new Guard(8,1, guardMov);
		this.Enemies.add(guard);

		keyX=7;
		keyY=8;

		int endLevelX_temp[] = {0,0};
		this.endLevelX = endLevelX_temp;
		
		int endLevelY_temp[] = {5,6};
		this.endLevelY = endLevelY_temp;
	}  

	
}
