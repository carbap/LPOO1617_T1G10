package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.gui.GameWindow;
import dkeep.logic.*;

public class TestGame
{
	
	
	@Test
	public void testMoveHeroIntoFreeCell()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(2);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		g.updateHero(8);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(1, g.getLevel().getHero().getY());
	}
	
	@Test
	public void testMoveHeroIntoWall()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(4);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(1, g.getLevel().getHero().getY());
		
	}
	
	@Test
	public void testMoveHeroIntoGuard()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(6);
		assertEquals(true, g.isGameOver());	
	}
	
	@Test
	public void testMoveHeroIntoClosedDoor()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(2);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		g.updateHero(4);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		
	}
	
	@Test
	public void testOpenDoors()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(2);
		g.updateHero(2);
		assertEquals(false, g.getLevel().isKeyEnabled());
	}
	
	@Test
	public void testFinishLevel()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(2);
		g.updateHero(2);
		g.updateHero(4);
		assertEquals(true , g.isEndLevel() );
	}
	
	//task2
	@Test
	public void testMoveHeroIntoOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(6);
		assertEquals(true, g.isGameOver());	
	}
	
	@Test
	public void testMoveHeroIntoKey()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(2);
		g.updateHero(2);
		assertEquals(false, g.getLevel().isKeyEnabled());
		assertEquals('K',g.getLevel().getHero().getDisplayChar());
	}
	
	@Test
	public void testMoveHeroIntoClosedDoorOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(2);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		g.updateHero(4);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		
	}
	
	@Test
	public void testOpenDoorsOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(2);
		g.updateHero(2);
		assertEquals(false, g.getLevel().isKeyEnabled());
	}
	
	@Test
	public void testFinishOgreLevel()
	{
		Game g = new Game(new TestGuardLevel());
		g.updateHero(2);
		g.updateHero(2);
		g.updateHero(4);
		assertEquals(true , g.isEndLevel() );
	}
	
	@Test
	public void testStunOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.getLevel().getHero().setWeapon(true);
		g.updateHero(6);
		assertEquals(false, g.isGameOver());
		assertEquals('8', g.getLevel().getEnemies().get(0).getDisplayChar());
	}
	
	@Test
	public void testKilledByStunnedOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.getLevel().getHero().setWeapon(true);
		g.updateHero(6);
		assertEquals(false, g.isGameOver());
		assertEquals('8', g.getLevel().getEnemies().get(0).getDisplayChar());
		g.getLevel().getEnemies().get(0).setWeaponX(2);
		g.getLevel().getEnemies().get(0).setWeaponY(1);
		g.setGameOver(g.checkEnemyTurn());
		assertEquals(true, g.isGameOver());
	}
	
	@Test(timeout = 1000)
	public void testOgreMovement()
	{
		Game g = new Game(new TestOgreLevel(1));
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false, outcome5 = false, outcome6 = false, outcome7 = false, outcome8 = false, outcome9 = false, outcome10 = false, outcome11 = false, outcome12 = false, outcome13 = false, outcome14 = false, outcome15 = false, outcome16 = false;
		boolean valid = false;
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4 || !outcome5 || !outcome6 || !outcome7 || !outcome8 || !outcome9 || !outcome10 || !outcome11 || !outcome12 || !outcome13 || !outcome14 || !outcome15 || !outcome16)
		{
			g.getLevel().npc();
			if(g.getLevel().getEnemies().get(0).getX() == 4 && g.getLevel().getEnemies().get(0).getY() == 3){
				if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 2){
					outcome1 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 5 && g.getLevel().getEnemies().get(0).getWeaponY() == 3){
					outcome2 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome3 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 3 && g.getLevel().getEnemies().get(0).getWeaponY() == 3){
					outcome4 = true;
				}
			}
			
			if(g.getLevel().getEnemies().get(0).getX() == 5 && g.getLevel().getEnemies().get(0).getY() == 4){
				if(g.getLevel().getEnemies().get(0).getWeaponX() == 5 && g.getLevel().getEnemies().get(0).getWeaponY() == 3){
					outcome5 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 6 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome6 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 5 && g.getLevel().getEnemies().get(0).getWeaponY() == 5){
					outcome7 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome8 = true;
				}
			}
			
			if(g.getLevel().getEnemies().get(0).getX() == 4 && g.getLevel().getEnemies().get(0).getY() == 5){
				if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome9 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 5 && g.getLevel().getEnemies().get(0).getWeaponY() == 5){
					outcome10 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 6){
					outcome11 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 3 && g.getLevel().getEnemies().get(0).getWeaponY() == 5){
					outcome12 = true;
				}
			}
			
			if(g.getLevel().getEnemies().get(0).getX() == 3 && g.getLevel().getEnemies().get(0).getY() == 4){
				if(g.getLevel().getEnemies().get(0).getWeaponX() == 3 && g.getLevel().getEnemies().get(0).getWeaponY() == 3){
					outcome13 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 4 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome14 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 3 && g.getLevel().getEnemies().get(0).getWeaponY() == 5){
					outcome15 = true;
				}
				else if(g.getLevel().getEnemies().get(0).getWeaponX() == 2 && g.getLevel().getEnemies().get(0).getWeaponY() == 4){
					outcome16 = true;
				}
			}
			
			g.getLevel().getEnemies().get(0).setX(4);
			g.getLevel().getEnemies().get(0).setY(4);
			g.getLevel().getEnemies().get(0).setWeaponX(4);
			g.getLevel().getEnemies().get(0).setWeaponY(5);
			
		}
		valid = true;
		assertEquals(true, valid);
		
	}
	
	@Test
	public void testReturnMap()
	{
		char[][] map = {{'X','X','I','I','X'},
				{'X',' ',' ',' ','X'},
				{'X',' ',' ',' ','X'},
				{'X',' ',' ',' ','X'},
				{'X','X','X','X','X'}};
		
		Game g = new Game(new TestOgreLevel());
		assertEquals(map, g.getLevel().getTableCopy());
	}
	
	@Test
	public void testCharacter()
	{		
		Game g = new Game(new TestOgreLevel());
		g.getLevel().getHero().setY(2);
		assertEquals(1, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		g.getLevel().getHero().setX(3);
		assertEquals(3, g.getLevel().getHero().getX());
		assertEquals(2, g.getLevel().getHero().getY());
		
	}
	
	@Test
	public void testDoorChangestToS()
	{
		Game g = new Game(new TestOgreLevel());
		assertEquals('I',g.getLevel().getTableCopy()[0][2]);
		g.updateHero(2);
		g.updateHero(2);
		assertEquals('S',g.getLevel().getTableCopy()[0][2]);
	}
	
	@Test
	public void testRookie()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new RookieMovement());
		assertEquals(true, g.getLevel().getEnemies().get(0).getMov() instanceof RookieMovement);
	}
	
	@Test
	public void testDrunken()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new DrunkenMovement());
		assertEquals(true, g.getLevel().getEnemies().get(0).getMov() instanceof DrunkenMovement);
	}
	
	@Test
	public void testSuspicious()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new SuspiciousMovement());
		assertEquals(true, g.getLevel().getEnemies().get(0).getMov() instanceof SuspiciousMovement);
	}
	
	@Test
	public void testRookieMovement()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new RookieMovement());
		g.getLevel().npc();
		assertEquals(6,g.getLevel().getEnemies().get(0).getX()); 
		assertEquals(1,g.getLevel().getEnemies().get(0).getY());
		g.getLevel().npc();
		assertEquals(6,g.getLevel().getEnemies().get(0).getX());
		assertEquals(2,g.getLevel().getEnemies().get(0).getY());
		g.getLevel().npc();
		assertEquals(6,g.getLevel().getEnemies().get(0).getX());
		assertEquals(3,g.getLevel().getEnemies().get(0).getY());
	}
	
	@Test(timeout = 1000)
	public void testDrunkenMovement()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new DrunkenMovement());
		g.getLevel().getHero().setX(4);
		g.getLevel().getHero().setY(4);
		while(!g.isGameOver())
		{
			g.updateGame();
		}
		assertEquals(true, g.isGameOver());
		assertEquals(true, g.getLevel().getHero().isAdjacent(4, 5));
		assertEquals(4,g.getLevel().getEnemies().get(0).getX());
		assertEquals(5,g.getLevel().getEnemies().get(0).getY());
		
		g.getLevel().getHero().setX(7);
		g.getLevel().getHero().setY(7); 
		g.setGameOver(false);
		while(!g.isGameOver())
		{
			g.updateGame();
		}
		assertEquals(true, g.isGameOver());
		assertEquals(true, g.getLevel().getHero().isAdjacent(7, 6));
		assertEquals(7,g.getLevel().getEnemies().get(0).getX());
		assertEquals(6,g.getLevel().getEnemies().get(0).getY());	
	}
	
	@Test(timeout = 1000)
	public void testHasBeenDrunk()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new DrunkenMovement());
		int currentX, currentY;
		boolean slept = false;
		int nextX, nextY;
		while(!g.isGameOver())
		{
			currentX = g.getLevel().getEnemies().get(0).getX();
			currentY = g.getLevel().getEnemies().get(0).getY();
			g.updateGame();
			nextX = g.getLevel().getEnemies().get(0).getX();
			nextY = g.getLevel().getEnemies().get(0).getY();
			if(currentX == nextX && currentY == nextY)
			{
				slept = true;
			}
			if(slept = true)
			{
				break;
			}
		}
		assertEquals(true,slept);
	}
	
	@Test(timeout = 1000)
	public void testSuspiciousMovement()
	{
		Game g = new Game(new TestGuardLevel(1));
		g.getLevel().getEnemies().get(0).setMov(new SuspiciousMovement()); 
		
		g.getLevel().getHero().setX(4);
		g.getLevel().getHero().setY(4);
		while(!g.isGameOver())
		{
			g.updateGame(); 
		}
		assertEquals(true, g.isGameOver()); 
		assertEquals(true, g.getLevel().getHero().isAdjacent(4, 5));
		assertEquals(4,g.getLevel().getEnemies().get(0).getX());
		assertEquals(5,g.getLevel().getEnemies().get(0).getY());
		
		g.getLevel().getHero().setX(7);
		g.getLevel().getHero().setY(7);
		g.setGameOver(false);
		while(!g.isGameOver())
		{
			g.updateGame();
		}
		assertEquals(true, g.isGameOver());
		assertEquals(true, g.getLevel().getHero().isAdjacent(7, 6));
		assertEquals(7,g.getLevel().getEnemies().get(0).getX());
		assertEquals(6,g.getLevel().getEnemies().get(0).getY());	
	}  
	
	@Test
	public void testFirstLevel()
	{
		Game g = new Game(new GuardLevel(1));
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
				{'X','X','X','X','X','X','X','X','X','X'}};
		assertEquals(temp,g.getLevel().getTableCopy());
				
	}
	
	@Test
	public void testSecondLevel()
	{
		Game g = new Game(new OgreLevel(2));
		char temp[][] ={ 
				{'X','I','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}};
		assertEquals(temp,g.getLevel().getTableCopy());
				
	}
	
	@Test
	public void testWeaponCharAndStun()
	{
		Game g = new Game(new TestOgreLevel());
		assertEquals('*' , g.getLevel().getEnemies().get(0).getWeaponDisplayChar() );
		assertEquals(0 , g.getLevel().getEnemies().get(0).getTurnsStunned() );
		g.getLevel().getEnemies().get(0).setTurnsStunned(3);
		assertEquals(3 , g.getLevel().getEnemies().get(0).getTurnsStunned() );
	}
	
	@Test
	public void testLastLevel()
	{
		Game g = new Game(new TestOgreLevel());
		assertEquals(false, g.isLastLevel());
		g.setLastLevel(true);
		assertEquals(true, g.isLastLevel());
	}
	
	@Test
	public void testOgreLevel() 
	{
		int width = 8;
		int height = 8;
		int ogreNr = 3;
		OgreLevel o = new OgreLevel(ogreNr,7,7,width,height);
		char[][] table = o.getTableCopy();
		boolean flag = true;
		for(int i = 0; i < table.length;i++)
		{
			for(int j = 0;j < table[i].length;j++)
			{
				if(i == 0 || i == (8-1) || j == 0 || j == (8-1))
				{
					if(table[i][j] != 'X')
						flag = false;
				}
				else
				{
					if(table[i][j] != ' ')
						flag = false;

				}
			}
		}
		assertEquals(true,flag); 
		
		assertEquals(ogreNr, o.getEnemies().size());
		
		assertEquals(true,o.isKeyEnabled());
		
		assertEquals('X',o.getTableCopy()[5][0]);
		
		o.setTableChar(5, 0, 'I');
		assertEquals('I',o.getTableCopy()[5][0]);
		
		o.getHero().setX(5);
		o.getHero().setY(2);
		assertEquals(5,o.getHero().getX());
		assertEquals(2,o.getHero().getY());
		
		o.setKeyX(5);
		o.setKeyY(1); 
		assertEquals('k',o.getWorktable()[5][1]);
		
		o.openAllDoors();
		assertEquals('S',o.getTableCopy()[5][0]);
		 
	}
	
	
	
	
}
