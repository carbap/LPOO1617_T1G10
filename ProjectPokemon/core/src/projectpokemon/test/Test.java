package projectpokemon.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import projectpokemon.logic.*;

public class Test {

	@org.junit.Test
	public void testPokemonConstructor()
	{		
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();

		assertEquals("Pidgeot",ai.getPartyLeader().getName());
		assertEquals(18,ai.getPartyLeader().getID());
		assertEquals(true,ai.getPartyLeader().hasTwoTypes());
		assertEquals(0,ai.getPartyLeader().getType1());
		assertEquals(2,ai.getPartyLeader().getType2());

		assertEquals("Alakazam",ai.getParty().get(1).getName());
		assertEquals(65,ai.getParty().get(1).getID());
		assertEquals(false,ai.getParty().get(1).hasTwoTypes());
		assertEquals(13,ai.getParty().get(1).getType1());
	}

	@org.junit.Test
	public void testMoveConstructor()
	{		
		Move move = new Move(4);

		assertEquals(4,move.getId());
		assertEquals(0,move.getType());
		assertEquals(true, move.isSpecial());
		assertEquals(false,move.getHighCritChance());
		assertEquals("Shell Smash",move.getName());
		assertEquals(0,move.getPower());
		assertEquals(95,move.getAccuracy());
	}

	@org.junit.Test
	public void testSelfStatStages()
	{
		Move move = new Move(4);

		StatStages test = new StatStages(2,-1,2,-1,2,0,0);
		move.setSelfStatStages(test);

		assertEquals(2,move.getSelfStatStages().getAtkStage());
		assertEquals(-1,move.getSelfStatStages().getDefStage());
		assertEquals(2,move.getSelfStatStages().getSpatkStage());
		assertEquals(-1,move.getSelfStatStages().getSpdefStage());
		assertEquals(2,move.getSelfStatStages().getSpdStage());
		assertEquals(0,move.getSelfStatStages().getAccuracyStage());
		assertEquals(0,move.getSelfStatStages().getEvasionStage());

		assertEquals(2.0,move.getSelfStatStages().getAtkModifier(),0.001);
		assertEquals(0.667,move.getSelfStatStages().getDefModifier(),0.001);
		assertEquals(2.0,move.getSelfStatStages().getSpatkModifier(),0.001);
		assertEquals(0.667,move.getSelfStatStages().getSpdefModifier(),0.001);
		assertEquals(2,move.getSelfStatStages().getSpdModifier(),0.001);
		assertEquals(1.0,move.getSelfStatStages().getAccuracyModifier(),0.001);
		assertEquals(1.0,move.getSelfStatStages().getEvasionModifier(),0.001);
	}

	@org.junit.Test
	public void testEnemyStatStages()
	{
		Move move = new Move(4);
		
		StatStages test = new StatStages(-1,0,-1,0,-1,-1,-1);
		move.setEnemyStatStages(test);

		assertEquals(-1,move.getEnemyStatStages().getAtkStage());
		assertEquals(0,move.getEnemyStatStages().getDefStage());
		assertEquals(-1,move.getEnemyStatStages().getSpatkStage());
		assertEquals(0,move.getEnemyStatStages().getSpdefStage());
		assertEquals(-1,move.getEnemyStatStages().getSpdStage());
		assertEquals(-1,move.getEnemyStatStages().getAccuracyStage());
		assertEquals(-1,move.getEnemyStatStages().getEvasionStage());

		assertEquals(0.667,move.getEnemyStatStages().getAtkModifier(),0.001);
		assertEquals(1.0,move.getEnemyStatStages().getDefModifier(),0.001);
		assertEquals(0.667,move.getEnemyStatStages().getSpatkModifier(),0.001);
		assertEquals(1.0,move.getEnemyStatStages().getSpdefModifier(),0.001);
		assertEquals(0.667,move.getEnemyStatStages().getSpdModifier(),0.001);
		assertEquals(0.75,move.getEnemyStatStages().getAccuracyModifier(),0.001);
		assertEquals(0.75,move.getEnemyStatStages().getEvasionModifier(),0.001);
	}
	
	@org.junit.Test
	public void testMoveEffectiveness()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(human, ai);

		Move surf = new Move(11);
		Pokemon rhydon = ai.getParty().get(2);
		Pokemon exeggutor  = ai.getParty().get(3);

		assertEquals(true, b.isSuperEffectiveAgainst(surf, rhydon));
		assertEquals(false, b.isSuperEffectiveAgainst(surf, exeggutor));

		assertEquals(4.0, b.modifierAgainst(surf, rhydon), 0.001);
		assertEquals(0.5, b.modifierAgainst(surf, exeggutor), 0.001);
	}

	@org.junit.Test
	public void testMoveIsSTAB()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(human, ai);

		Pokemon pidgeot = ai.getParty().get(0);
		Move strength = pidgeot.getMoveSet().get(0);

		assertTrue(b.isSTAB(strength, pidgeot));
	}

	@org.junit.Test
	public void testHasSuperEffectiveAgainst()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(human, ai);

		Pokemon pidgeot = ai.getParty().get(0);
		Pokemon gyarados = human.getPartyLeader();

		assertFalse(b.hasSuperEffectiveAgainst(pidgeot, gyarados));

		Move thunderbolt = new Move(14);
		pidgeot.getMoveSet().set(0, thunderbolt);

		assertTrue(b.hasSuperEffectiveAgainst(pidgeot, gyarados));
	}

	@org.junit.Test
	public void testIsResistentTo()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(human, ai);

		Pokemon exeggutor = ai.getParty().get(3);
		Pokemon arcanine = ai.getParty().get(4);
		Pokemon blastoise = ai.getParty().get(5);

		assertTrue(b.isResistentTo(blastoise, arcanine));
		assertFalse(b.isResistentTo(arcanine, blastoise));

		assertTrue(b.isResistentTo(exeggutor, blastoise));
		assertFalse(b.isResistentTo(blastoise, exeggutor));

		assertTrue(b.isResistentTo(arcanine, exeggutor));
		assertFalse(b.isResistentTo(exeggutor, arcanine));
	}

	@org.junit.Test
	public void testHitChance()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(human, ai);

		Move swordsDance = new Move(3);
		Pokemon gyarados = human.getPartyLeader();
		Pokemon rhydon = ai.getParty().get(2);

		int hit = 0;
		for(int i = 0; i < 10000; i++)
		{
			if(b.moveHits(gyarados,rhydon,swordsDance))
				hit++;
		}

		assertTrue(hit >= 9000);
		assertTrue(hit <= 10000);
	}

	@org.junit.Test
	public void testDamageBoundaries()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();
		Trainer human = new Trainer();

		BattleAI b = new BattleAI(ai, human);

		Pokemon attacker = ai.getPartyLeader();
		Pokemon defender = human.getPartyLeader();
		Move chosenMove = attacker.getMoveSet().get(0);

		int minDamage =  (int) Math.floor(b.calculateDamage(attacker, defender, chosenMove, 1.275, false));
		int maxDamage =  (int) Math.floor(b.calculateDamage(attacker, defender, chosenMove, 3, true));

		int damage;
		boolean flag = false;
		for(int i = 0; i < 10; i++)
		{
			damage = b.attackPokemon(attacker, defender,  0, ai);
			assertTrue(damage >= minDamage);
			assertTrue(damage <= maxDamage);
		}

		assertEquals(false,flag);
	}

	@org.junit.Test
	public void testHitAndCritical()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);

		Pokemon attacker = p1.getPokemonAt(4);//Arcanine
		Pokemon defender = p2.getPokemonAt(3);//Charizard
		attacker.getMoveSet().set(3, new Move(15));//thunder
		defender.getMoveSet().set(0, new Move(6));//blazekick

		int hit1 = 0;
		int hit2 = 0;
		int crit1 = 0;
		int crit2 = 0;
		for(int i = 0; i < 10000; i++)
		{
			b.attackPokemon(attacker, defender,  3, p1);
			b.attackPokemon(defender, attacker,  0, p2);
			if(b.getVariables().isPlayer1Hits())
				hit1++;
			if(b.getVariables().isPlayer2Hits())
				hit2++;
			if(b.getVariables().isPlayer1Critical())
				crit1++;
			if(b.getVariables().isPlayer2Critical())
				crit2++;	
		}
		
		assertTrue(hit1 >= 7400 && hit1 <= 8600);
		assertTrue(hit2 >= 8400 && hit2 <= 9600);
		assertTrue(crit1 >= 425 && crit1 <= 825);
		assertTrue(crit2 >= 950 && crit2 <= 1550);
	}
	
	@org.junit.Test
	public void testIndexAndEffectiveness()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = new Trainer();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);
		
		p1.swapPokemonWith(0, new Pokemon(34));
		Pokemon attacker = p1.getPokemonAt(0);//Nidoking
		Pokemon defender = p2.getPokemonAt(3);//Charizard
		attacker.getMoveSet().set(0, new Move(35));//earthquake
		attacker.getMoveSet().set(1, new Move(26));//close combat
		attacker.getMoveSet().set(2, new Move(22));//ice punch
		attacker.getMoveSet().set(3, new Move(14));//thunderbolt
		
		defender.getMoveSet().set(0, new Move(14));//thunderbolt
		defender.getMoveSet().set(1, new Move(26));//close combat
		defender.getMoveSet().set(2, new Move(7));//flamethrower
		defender.getMoveSet().set(3, new Move(11));//surf
		
		b.attackPokemon(attacker, defender,  0, p1);
		assertEquals(0,b.getVariables().getPlayer1MoveIndex());
		assertEquals(0,b.getVariables().getPlayer1Effectiveness());
		
		b.attackPokemon(attacker, defender,  1, p1);
		assertEquals(1,b.getVariables().getPlayer1MoveIndex());
		assertEquals(1,b.getVariables().getPlayer1Effectiveness());
		
		b.attackPokemon(attacker, defender,  2, p1);
		assertEquals(2,b.getVariables().getPlayer1MoveIndex());
		assertEquals(2,b.getVariables().getPlayer1Effectiveness());

		b.attackPokemon(attacker, defender,  3, p1);
		assertEquals(3,b.getVariables().getPlayer1MoveIndex());
		assertEquals(3,b.getVariables().getPlayer1Effectiveness());
		
		b.attackPokemon(defender, attacker,  0, p2);
		assertEquals(0,b.getVariables().getPlayer2MoveIndex());
		assertEquals(0,b.getVariables().getPlayer2Effectiveness());

		b.attackPokemon(defender, attacker,  1, p2);
		assertEquals(1,b.getVariables().getPlayer2MoveIndex());
		assertEquals(1,b.getVariables().getPlayer2Effectiveness());

		b.attackPokemon(defender, attacker,  2, p2);
		assertEquals(2,b.getVariables().getPlayer2MoveIndex());
		assertEquals(2,b.getVariables().getPlayer2Effectiveness());

		b.attackPokemon(defender, attacker,  3, p2);
		assertEquals(3,b.getVariables().getPlayer2MoveIndex());
		assertEquals(3,b.getVariables().getPlayer2Effectiveness());
		
		attacker.getMoveSet().set(0, new Move(3));//swords dance
		b.attackPokemon(attacker, defender,  0, p1);
		if(b.getVariables().isPlayer1Hits())
			assertEquals(2,b.getVariables().getPlayer1Effectiveness());
		
	}
	
	@org.junit.Test
	public void testSetStages()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = new Trainer();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);
		
		b.attackPokemon(p1.getPartyLeader(), p2.getPartyLeader(), 0, p1);
		assertEquals(0,b.getVariables().getPlayer1Stages().getAtkStage());
		
		b.attackPokemon(p2.getPartyLeader(), p1.getPartyLeader(), 2, p2);
		if(b.getVariables().isPlayer2Hits())
			assertEquals(1,b.getVariables().getPlayer2Stages().getAtkStage());
	}
	
	@org.junit.Test
	public void testSwitchPokemon()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);

		assertFalse(b.getVariables().isPlayer1Switch());
		assertEquals("Pidgeot",p1.getPartyLeader().getName());
		b.switchPokemon(5, p1);
		assertEquals("Blastoise",p1.getPartyLeader().getName());
		assertTrue(b.getVariables().isPlayer1Switch());
		b.attackPokemon(p1.getPartyLeader(), p2.getPartyLeader(),  0, p1);
		assertFalse(b.getVariables().isPlayer1Switch());

		assertFalse(b.getVariables().isPlayer2Switch());
		assertEquals("Gyarados",p2.getPartyLeader().getName());
		b.switchPokemon(5, p2);
		assertEquals("Gengar",p2.getPartyLeader().getName());
		assertTrue(b.getVariables().isPlayer2Switch());
		b.attackPokemon(p2.getPartyLeader(), p1.getPartyLeader(),  0, p2);
		assertFalse(b.getVariables().isPlayer2Switch());
	}

	@org.junit.Test
	public void testSwapPokemonWith()
	{
		Trainer human = new Trainer();

		assertEquals("Metagross",human.getParty().get(5).getName());

		Pokemon charizard = new Pokemon(6);
		human.swapPokemonWith(5, charizard);

		assertEquals("Charizard",human.getParty().get(5).getName());
	}

	@org.junit.Test
	public void testSwapPokemonAt()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer ai = info.getCurrentOpponent();

		assertEquals("Arcanine",ai.getParty().get(4).getName());
		assertEquals("Blastoise",ai.getParty().get(5).getName());

		ai.swapPokemonAt(4, 5);

		assertEquals("Blastoise",ai.getParty().get(4).getName());
		assertEquals("Arcanine",ai.getParty().get(5).getName());
	}

	@org.junit.Test
	public void testPartyOperations()
	{
		Trainer human = new Trainer();

		assertFalse(human.allFainted());
		assertEquals(6,human.getParty().size());

		assertFalse(human.addPokemon(new Pokemon(149)));
		
		for(int i = 0; i < 5; i++)
			human.removePokemon(0);
		
		assertFalse(human.removePokemon(0));
		
		ArrayList<Pokemon> p = new ArrayList<Pokemon>();
		for(int i = 0; i < 2; i++)
			p.add(new Pokemon(149));
		human.setParty(p);
		
		human.addPokemon(new Pokemon(149));
		
		for(int i = 0; i < 3; i++)
		{
			human.getPokemonAt(i).addDamageTaken(1000);
		}
		
		assertTrue(human.allFainted());
	}

	@org.junit.Test
	public void testSwitchAI()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);

		assertFalse(b.switchAI());//switchCount 3 -> 4
		for(int i = 1; i < 6; i++)
			p2.getParty().get(i).addDamageTaken(1000);
		assertFalse(b.switchAI());
		for(int i = 1; i < 6; i++)
			p2.getParty().get(i).setDamageTaken(0);;

		p1.swapPokemonAt(0, 2);
		for(int i = 0; i <= 3; i++)
			p2.getPartyLeader().getMoveSet().set(i, new Move(0));
		
		assertTrue(b.switchAI());//switchCount 4 -> 0;
		assertFalse(b.switchAI());//switchCount 0 -> 1;
		assertFalse(b.switchAI());//switchCount 1 -> 2;
		assertFalse(b.switchAI());//switchCount 2 -> 3;
		
		Move waterfall = new Move(9);
		p2.getPartyLeader().getMoveSet().set(2, waterfall);
		assertFalse(b.switchAI());//switchCount 3 -> 4;
		p2.getPartyLeader().addDamageTaken(300);
		assertTrue(b.switchAI());//switchCount 4 -> 0;
		assertFalse(b.switchAI());//switchCount 0 -> 1;
		assertFalse(b.switchAI());//switchCount 1 -> 2;
		assertFalse(b.switchAI());//switchCount 2 -> 3;
		p2.getPartyLeader().addDamageTaken(300);
		assertTrue(b.switchAI());//switchCount 4 -> 0;
		
	}
	
	@org.junit.Test
	public void testChooseSwitch()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);
		
		p1.swapPokemonAt(0, 3);
		
		for(int i = 0; i <= 5; i++)
		{
			for(int j = 0; j <= 3; j++)
				p2.getPokemonAt(i).getMoveSet().set(j, new Move(0));
		}
		
		Move flamethrower = new Move(7);
		p2.getPokemonAt(3).getMoveSet().set(2, flamethrower);
	
		assertEquals(3,b.chooseSwitch());
		
		for(int i = 1; i <= 5; i++)
			p2.getPokemonAt(i).addDamageTaken(1000);
		
		assertEquals(-1,b.chooseSwitch());
		
		p2.getPokemonAt(4).setDamageTaken(0);
		
		assertEquals(4,b.chooseSwitch());
	}
	
	@org.junit.Test
	public void testCalculateScore()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();
		
		BattleAI b = new BattleAI(p1, p2);
		
		Pokemon pidgeot = p1.getPartyLeader();
		Pokemon gyarados = p2.getPartyLeader();

		Move move = pidgeot.getMoveSet().get(0);
		double score = b.calculateScore(move, pidgeot, gyarados);
		assertEquals(2562750, score, 0.001);
		
		move = pidgeot.getMoveSet().get(1);
		score = b.calculateScore(move, pidgeot, gyarados);
		assertEquals(2374312.5, score, 0.001);
		
		move = pidgeot.getMoveSet().get(1);
		score = b.calculateScore(move, pidgeot, gyarados);
		assertEquals(2374312.5, score, 0.001);
		
		move = new Move(29);
		score = b.calculateScore(move, pidgeot, gyarados);
		assertEquals(0, score, 0.001);
	}
	
	@org.junit.Test
	public void testMaxScoreIndex()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();
		
		BattleAI b = new BattleAI(p1, p2);
		
		double[] points = {1.0, 5.0, 2.5, 3.5,2.0};
		
		assertEquals(1,b.maxScoreIndex(points));
	}
	
	@org.junit.Test
	public void testChooseMove()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer human = new Trainer();
		Trainer ai = info.getCurrentOpponent();
	
		BattleAI b = new BattleAI(human, ai);
		
		ai.swapPokemonAt(0, 1);
		Pokemon alakazam = ai.getPartyLeader();
		alakazam.getMoveSet().set(0, new Move(7));//flamethrower
		alakazam.getMoveSet().set(1, new Move(18));//leafblade
		alakazam.getMoveSet().set(2, new Move(14));//Thunderbolt
		alakazam.getMoveSet().set(3, new Move(15));//thunder
		
		int count = 0;
		for(int i = 0; i < 10000; i++)
		{
			if(alakazam.getMoveSet().get(b.chooseMove()).getId() == 14)
				count++;
		}
		
		assertTrue(count >= 5500 && count <= 7000);
	}
	
	@org.junit.Test
	public void testMoveAI()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer p1 = info.getCurrentOpponent();
		info.nextOpponent();
		Trainer p2 = info.getCurrentOpponent();

		BattleAI b = new BattleAI(p1, p2);
		
		for(int i = 0; i <= 3; i++)
			p2.getPartyLeader().getMoveSet().set(i, new Move(0));
		
		Move waterfall = new Move(9);
		p2.getPartyLeader().getMoveSet().set(2, waterfall);
		
		int count = 0;
		for(int i = 0; i < 10000; i++)
		{
			b.moveAI();
			if(b.getVariables().getPlayer2MoveIndex() == 2)
					count++;
		}
		
		assertTrue(count >= 5500 && count <= 7000);
		
		p1.swapPokemonAt(0, 5);//blastoise
		p2.swapPokemonAt(0, 3);//charizard
		
		p2.getPokemonAt(5).getMoveSet().set(3, new Move(15));
		
		assertEquals("Charizard",p2.getPartyLeader().getName());
		b.moveAI();
		assertEquals("Gengar",p2.getPartyLeader().getName());
	}
	
	@org.junit.Test
	public void testWhoAttacksFirst()
	{
		InfoBattleAI info = new InfoBattleAI();
		Trainer human = new Trainer();
		Trainer ai = info.getCurrentOpponent();
		
		BattleAI b = new BattleAI(human, ai);
		
		b.whoAttacksFirst();
		assertEquals(2,b.getVariables().getFirstToAttack());
		
		ai.swapPokemonAt(0, 5);
		b.whoAttacksFirst();
		assertEquals(1,b.getVariables().getFirstToAttack());
		
		ai.swapPokemonWith(0, new Pokemon(350));
		int count = 0;
		for(int i = 0; i < 10000; i++)
		{
			b.whoAttacksFirst();
			if(b.getVariables().getFirstToAttack()==1)
				count++;
		}
		
		assertTrue(count >= 4200 && count <= 5800);
	}
	
	@org.junit.Test
	public void testPercentageHP()
	{
		Pokemon milotic = new Pokemon(350);
		
		assertEquals(1.0,milotic.getPercentageHP(),0.001);
		milotic.setDamageTaken(100);
		assertEquals(0.702,milotic.getPercentageHP(), 0.001);
	}
	
	@org.junit.Test
	public void testAddDamageTaken()
	{
		Pokemon milotic = new Pokemon(350);
		
		assertEquals(336,milotic.getCurrentHP());
		milotic.addDamageTaken(100);
		assertEquals(236,milotic.getCurrentHP());
		milotic.addDamageTaken(36);
		assertEquals(200,milotic.getCurrentHP());
		milotic.addDamageTaken(220);
		assertEquals(0,milotic.getCurrentHP());
	}
	
	@org.junit.Test
	public void testHealParty()
	{
		Trainer human = new Trainer();
		assertEquals(1.0,human.getPartyLeader().getPercentageHP(),0.001);
		
		human.getPartyLeader().addDamageTaken(20);
		
		human.healParty();
		
		for(int i = 0; i < human.getParty().size();i++)
			assertEquals(1.0,human.getPokemonAt(i).getPercentageHP(),0.001);
	}
	
	@org.junit.Test
	public void testCurrentOpponentIndex()
	{
		InfoBattleAI info = new InfoBattleAI();

		assertEquals(0,info.getCurrentOpponentIndex());
		info.nextOpponent();
		info.nextOpponent();
		assertEquals(2,info.getCurrentOpponentIndex());
		info.reset();
		assertEquals(0,info.getCurrentOpponentIndex());
	}
	
	@org.junit.Test
	public void testAddAtk()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addAtk(8);
		assertEquals(6,attacker.getStatStages().getAtkStage());
		attacker.getStatStages().addAtk(-20);
		assertEquals(-6,attacker.getStatStages().getAtkStage());
	}
	
	@org.junit.Test
	public void testAddDef()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addDef(8);
		assertEquals(6,attacker.getStatStages().getDefStage());
		attacker.getStatStages().addDef(-20);
		assertEquals(-6,attacker.getStatStages().getDefStage());
	}
	
	@org.junit.Test
	public void testAddSpatk()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addSpatk(8);
		assertEquals(6,attacker.getStatStages().getSpatkStage());
		attacker.getStatStages().addSpatk(-20);
		assertEquals(-6,attacker.getStatStages().getSpatkStage());
	}
	
	@org.junit.Test
	public void testAddSpdef()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addSpdef(8);
		assertEquals(6,attacker.getStatStages().getSpdefStage());
		attacker.getStatStages().addSpdef(-20);
		assertEquals(-6,attacker.getStatStages().getSpdefStage());
	}
	
	@org.junit.Test
	public void testAddSpd()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addSpd(8);
		assertEquals(6,attacker.getStatStages().getSpdStage());
		attacker.getStatStages().addSpd(-20);
		assertEquals(-6,attacker.getStatStages().getSpdStage());
	}
	
	@org.junit.Test
	public void testAddAccuracy()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addAccuracy(8);
		assertEquals(6,attacker.getStatStages().getAccuracyStage());
		attacker.getStatStages().addAccuracy(-20);
		assertEquals(-6,attacker.getStatStages().getAccuracyStage());
	}
	
	@org.junit.Test
	public void testAddEvasion()
	{
		Pokemon attacker = new Pokemon(65);
		attacker.getStatStages().addEvasion(8);
		assertEquals(6,attacker.getStatStages().getEvasionStage());
		attacker.getStatStages().addEvasion(-20);
		assertEquals(-6,attacker.getStatStages().getEvasionStage());
	}
	
	@org.junit.Test
	public void testApplyStages()
	{
		BattleAI b = new BattleAI(new Trainer(), new Trainer());
		
		Pokemon attacker = new Pokemon(65);
		Pokemon defender = new Pokemon(94);
		
		assertTrue(defender.getStatStages().allStagesZero());
		
		b.applyStages(new Move(44), attacker, defender);
		
		assertFalse((defender.getStatStages().allStagesZero()));
		assertEquals(-1,defender.getStatStages().getSpdefStage());
	}
	
	@org.junit.Test
	public void testGetAllPokemonIDs()
	{
		ArrayList<Integer> ret = Pokemon.getAllPokemonIDs("pokemon.txt");
		
		assertEquals(30,ret.size());
		assertEquals(18,(int)ret.get(0));
		assertEquals(130,(int)ret.get(6));
		assertEquals(448,(int)ret.get(23));
	}
	
	@org.junit.Test
	public void testGetNumberOfMoves()
	{
		int numMoves = Move.getNumberOfMoves("moves.txt");
		
		assertEquals(80,numMoves);
	}
	
	@org.junit.Test
	public void testUpdateMoveFactory()
	{
		assertEquals("Flamethrower",MoveFactory.getInstance().getMove(7).getName());
		MoveFactory.getInstance().update();
		assertEquals("Flamethrower",MoveFactory.getInstance().getMove(7).getName());
	}
}