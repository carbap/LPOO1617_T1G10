package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestGame
{
	
	
	@Test
	public void testMoveHeroIntoFreeCell()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(2);
		assertEquals(1, g.level.hero.getX());
		assertEquals(2, g.level.hero.getY());
		
	}
	
	@Test
	public void testMoveHeroIntoWall()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(4);
		assertEquals(1, g.level.hero.getX());
		assertEquals(1, g.level.hero.getY());
		
	}
	
	@Test
	public void testMoveHeroIntoGuard()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(6);
		assertEquals(true, g.isGameOver());	
	}
	
	@Test
	public void testMoveHeroIntoClosedDoor()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(2);
		assertEquals(1, g.level.hero.getX());
		assertEquals(2, g.level.hero.getY());
		g.updateHero(4);
		assertEquals(1, g.level.hero.getX());
		assertEquals(2, g.level.hero.getY());
		
	}
	
	@Test
	public void testOpenDoors()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(2);
		g.updateHero(2);
		assertEquals(false, g.level.isKeyEnabled());
	}
	
	@Test
	public void testFinishLevel()
	{
		Game g = new Game(new TestLevel());
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
		assertEquals(false, g.level.isKeyEnabled());
		assertEquals('K',g.level.hero.getDisplayChar());
	}
	
	@Test
	public void testMoveHeroIntoClosedDoorOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(2);
		assertEquals(1, g.level.hero.getX());
		assertEquals(2, g.level.hero.getY());
		g.updateHero(4);
		assertEquals(1, g.level.hero.getX());
		assertEquals(2, g.level.hero.getY());
		
	}
	
	@Test
	public void testOpenDoorsOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.updateHero(2);
		g.updateHero(2);
		assertEquals(false, g.level.isKeyEnabled());
	}
	
	@Test
	public void testFinishOgreLevel()
	{
		Game g = new Game(new TestLevel());
		g.updateHero(2);
		g.updateHero(2);
		g.updateHero(4);
		assertEquals(true , g.isEndLevel() );
	}
	
	@Test
	public void testStunOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.level.hero.setWeapon(true);
		g.updateHero(6);
		assertEquals(false, g.isGameOver());
		assertEquals('8', g.level.Enemies.get(0).getDisplayChar());
	}
	
	@Test
	public void testKilledByStunnedOgre()
	{
		Game g = new Game(new TestOgreLevel());
		g.level.hero.setWeapon(true);
		g.updateHero(6);
		assertEquals(false, g.isGameOver());
		assertEquals('8', g.level.Enemies.get(0).getDisplayChar());
		g.level.Enemies.get(0).weaponX = 2;
		g.level.Enemies.get(0).weaponY = 1;
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
			g.level.npc();
			if(g.level.Enemies.get(0).getX() == 4 && g.level.Enemies.get(0).getY() == 3){
				if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 2){
					outcome1 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 5 && g.level.Enemies.get(0).getWeaponY() == 3){
					outcome2 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome3 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 3 && g.level.Enemies.get(0).getWeaponY() == 3){
					outcome4 = true;
				}
			}
			
			if(g.level.Enemies.get(0).getX() == 5 && g.level.Enemies.get(0).getY() == 4){
				if(g.level.Enemies.get(0).getWeaponX() == 5 && g.level.Enemies.get(0).getWeaponY() == 3){
					outcome5 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 6 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome6 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 5 && g.level.Enemies.get(0).getWeaponY() == 5){
					outcome7 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome8 = true;
				}
			}
			
			if(g.level.Enemies.get(0).getX() == 4 && g.level.Enemies.get(0).getY() == 5){
				if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome9 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 5 && g.level.Enemies.get(0).getWeaponY() == 5){
					outcome10 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 6){
					outcome11 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 3 && g.level.Enemies.get(0).getWeaponY() == 5){
					outcome12 = true;
				}
			}
			
			if(g.level.Enemies.get(0).getX() == 3 && g.level.Enemies.get(0).getY() == 4){
				if(g.level.Enemies.get(0).getWeaponX() == 3 && g.level.Enemies.get(0).getWeaponY() == 3){
					outcome13 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 4 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome14 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 3 && g.level.Enemies.get(0).getWeaponY() == 5){
					outcome15 = true;
				}
				else if(g.level.Enemies.get(0).getWeaponX() == 2 && g.level.Enemies.get(0).getWeaponY() == 4){
					outcome16 = true;
				}
			}
			
			g.level.Enemies.get(0).X = 4;
			g.level.Enemies.get(0).Y = 4;
			g.level.Enemies.get(0).weaponX = 4;
			g.level.Enemies.get(0).weaponY = 5;
			
		}
		valid = true;
		assertEquals(true, valid);
		
	}
	
	
}
