package projectpokemon.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	/**
	 * Chart that contains the effectiveness of each type on another.
	 */
	private double[][] typeChart = new double[17][17];
	
	/**
	 * Variable that holds the Trainer to be considered as "Player 1"
	 */
	private Trainer player1;
	
	/**
	 * Variable that holds the Trainer to be considered as "Player 2"
	 */
	private Trainer player2;
	
	/**
	 * It holds information about this Battle to be shown meanwhile the game is played.
	 */
	private BattleVariables variables = new BattleVariables();
	
	/**
	 * Constructor of Battle.
	 * Sets each Trainer variable and loads the type chart.
	 * 
	 * @param player1 Trainer to be considered as "Player 1"
	 * @param player2 Trainer to be considered as "Player 2"
	 */
	public Battle(Trainer player1, Trainer player2){
		this.setPlayer1(player1);
		this.setPlayer2(player2);
		loadTypeChart();
	}

	/**
	 * Returns the type chart.
	 * 
	 * @return the type chart.
	 */
	public double[][] getTypeChart() {
		return typeChart;
	}

	/**
	 * Loads the type chart from a file to the typeChart variable.
	 */
	public void loadTypeChart(){
        try {
        	//File a = new File(Battle.class.getResource("../data/pokemon_types.txt").getFile());
        	File a = new File("data/pokemon_types.txt");
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			for(int i = 0; i < 17; i++){
				for(int j = 0; j < 17; j++){
					typeChart[i][j] = scanner.nextDouble();
				}
			}
			scanner.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
	}
	
	/**
	 * Calculates which of the two pokemons currently in battle (the party leaders
	 * of each trainer) should attack first and sets the firstToAttack attribute in
	 * the BattleVariables.
	 * 
	 * The pokemon that attacks first is the one with the greater Speed stat.
	 * If these stats are equal, the pokemon to attack first is chosen at random.
	 */
	public void whoAttacksFirst()
	{
		Random r = new Random();
		Pokemon p1 = getPlayer1().getPartyLeader();
		Pokemon p2 = getPlayer2().getPartyLeader();
		
		if(p1.getSPD() > p2.getSPD())
			variables.setFirstToAttack(1);
		else if(p1.getSPD() < p2.getSPD())
			variables.setFirstToAttack(2);
		else{
			
			if(r.nextDouble() < 0.5)
				variables.setFirstToAttack(1);
			else
				variables.setFirstToAttack(2);
		}
	}
	
	/**
	 * Sets the BattleVariables depending on if the move hits, is critical or not,
	 * its effectiveness on the foe and the StatStages it may apply to both the attacking
	 * and defending pokemon.
	 * 
	 * Besides setting this information to the BattleVariables, the function actually applies
	 * the eventual StatStages to each pokemon and the calculated damage to the defending pokemon.
	 * 
	 * @param attacker the pokemon that is attacking
	 * @param defender the pokemon that will be attacked
	 * @param movIndex the index of the move that the attacking pokemon is going to use
	 * @param who the Trainer of the attacking pokemon
	 * @return the damage the defending pokemon will receive
	 */
	public int attackPokemon(Pokemon attacker, Pokemon defender, int movIndex, Trainer who)
	{
		double damage;
		boolean moveHit;
		boolean critical;
		
		setSwitch(false, who);
		
		Move chosenMove = attacker.getMoveSet().get(movIndex);
		
		setMove(movIndex, who);
		
		moveHit = moveHits(attacker, defender, chosenMove);
		
		if(!setMoveHit(moveHit, who))
				return 0;	
		
		setEffectiveness(chosenMove, defender, who);
		
		setStages(chosenMove.getSelfStatStages(), chosenMove.getEnemyStatStages(), who);
		applyStages(chosenMove, attacker, defender);
		
		critical = isCritical(chosenMove);
		setCritical(critical,who);
		
		double modifier = calculateModifier(attacker, defender, chosenMove, critical);
		
		damage = calculateDamage(attacker, defender, chosenMove, modifier, critical);
		
		defender.addDamageTaken((int) Math.floor(damage));
		
		return (int) Math.floor(damage);
	}
	
	/**
	 * Swaps the pokemon at the given index with the one at the first
	 * position of the party (the party leader).
	 * It also resets each pokemons StatStages, so that changes to stats
	 * are only kept meanwhile the pokemon are in battle.
	 * 
	 * @param index the index of the Pokemon to be swapped in to battle
	 * @param who the Trainer that is switching pokemons
	 */
	public void switchPokemon(int index, Trainer who)
	{
		StatStages s = new StatStages(0,0,0,0,0,0,0);
		who.getPokemonAt(0).setStatStages(s);
		who.getPokemonAt(index).setStatStages(s);
		who.swapPokemonAt(0, index);
		setSwitch(true, who);
	}
	
	/**
	 * Sets the corresponding moveIndex attribute in the BattleVariables to the
	 * one passed as a parameter according to the given trainer.
	 * 
	 * @param moveIndex index of the move
	 * @param who Trainer whose move index will bet set.
	 */
	public void setMove(int moveIndex, Trainer who)
	{
			if(who == player1)
				variables.setPlayer1MoveIndex(moveIndex);
			else
				variables.setPlayer2MoveIndex(moveIndex);
	}
	
	/**
	 * Sets both player's stages attribute in the BattleVariables according to
	 * the given Trainer. So if the 'who' parameter is Player 1 then
	 * the player1Stages will be 'self'. On the other hand, if 'who' is Player 2
	 * then 'self' will be set to the Player2Stages.
	 * 
	 * @param self the self StatStages of a move
	 * @param enemy the enemy StatStages of a move
	 * @param who Trainer who used the move
	 */
	public void setStages(StatStages self, StatStages enemy, Trainer who)
	{
		if(who == player1)
		{
			variables.setPlayer1Stages(self);
			variables.setPlayer2Stages(enemy);
		}
		else
		{
			variables.setPlayer2Stages(self);
			variables.setPlayer1Stages(enemy);
		}
	}
	
	/**
	 * Applies the chosenMove's StatStages to both the attacking pokemon, meaning it will
	 * receive the SelfStatStages, and the defending pokemon, that will receive
	 * the EnemyStatStages.
	 * If the chosenMove's StatStages are all zero there is no need to apply them.
	 * 
	 * @param chosenMove the move used by the attacking pokemon
	 * @param attacker the pokemon that is attacking
	 * @param defender the pokemon that will be attacked
	 */
	public void applyStages(Move chosenMove, Pokemon attacker, Pokemon defender)
	{
		if(!chosenMove.getSelfStatStages().allStagesZero())
		{
			attacker.getStatStages().addAtk(chosenMove.getSelfStatStages().getAtkStage());
			attacker.getStatStages().addDef(chosenMove.getSelfStatStages().getDefStage());
			attacker.getStatStages().addSpatk(chosenMove.getSelfStatStages().getSpatkStage());
			attacker.getStatStages().addSpdef(chosenMove.getSelfStatStages().getSpdefStage());
			attacker.getStatStages().addSpd(chosenMove.getSelfStatStages().getSpdStage());
			attacker.getStatStages().addAccuracy(chosenMove.getSelfStatStages().getAccuracyStage());
			attacker.getStatStages().addEvasion(chosenMove.getSelfStatStages().getEvasionStage());
		}
		if(!chosenMove.getEnemyStatStages().allStagesZero())
		{
			defender.getStatStages().addAtk(chosenMove.getEnemyStatStages().getAtkStage());
			defender.getStatStages().addDef(chosenMove.getEnemyStatStages().getDefStage());
			defender.getStatStages().addSpatk(chosenMove.getEnemyStatStages().getSpatkStage());
			defender.getStatStages().addSpdef(chosenMove.getEnemyStatStages().getSpdefStage());
			defender.getStatStages().addSpd(chosenMove.getEnemyStatStages().getSpdStage());
			defender.getStatStages().addAccuracy(chosenMove.getEnemyStatStages().getAccuracyStage());
			defender.getStatStages().addEvasion(chosenMove.getEnemyStatStages().getEvasionStage());
		}
	}
	
	/**
	 * Sets the Switch attributes in the BattleVariables according to if
	 * the given Trainer has switched pokemons.
	 * 
	 * @param switched boolean that indicates if a Trainer has switched pokemons
	 * @param who the Trainer that may or may not have switched pokemons
	 */
	public void setSwitch(boolean switched, Trainer who)
	{
		if(switched == true)
		{
			if(who == player1)
				variables.setPlayer1Switch(true);
			else
				variables.setPlayer2Switch(true);
		}
		else
		{
			if(who == player1)
				variables.setPlayer1Switch(false);
			else
				variables.setPlayer2Switch(false);
		}
	}
	
	/**
	 * Sets the Hits attributes in the BattleVariables according to if the given
	 * Trainer's pokemon has successfully hit the foe.
	 * 
	 * @param moveHit boolean that indicates if the Trainer's pokemon has succesfully hit the foe.
	 * @param who the Trainer whose pokemon is attacking.
	 * @return true if the move hits, false if the move fails to hit
	 */
	public boolean setMoveHit(boolean moveHit, Trainer who)
	{
		if(moveHit)
		{
			if(who == player1)
				variables.setPlayer1Hits(true);
			else
				variables.setPlayer2Hits(true);
			return true;
		}
		else
		{
			if(who == player1)
				variables.setPlayer1Hits(false);
			else
				variables.setPlayer2Hits(false);
			
			return false;
		}
	}
	
	/**
	 * Sets the Critical attributes in the BattleVariables according to if the
	 * given Trainer's pokemon critically struck the foe.
	 * 
	 * @param critical boolean that indicates if the Trainer's pokemon has critically struck the foe.
	 * @param who the Trainer whose pokemon is attacking.
	 */
	public void setCritical(boolean critical, Trainer who)
	{
		if(critical == true)
		{
			if(who == player1)
				variables.setPlayer1Critical(true);
			else
				variables.setPlayer2Critical(true);
		}
		else
		{
			if(who == player1)
				variables.setPlayer1Critical(false);
			else
				variables.setPlayer2Critical(false);
		}
	}
	
	/**
	 * Sets the Effectiveness attributes in the BattleVariables according to the effectiveness
	 * of the move used by the given Trainer's pokemon on the foe.
	 * The possible values of these attributes and their meaning are the following:
	 * 0 does not affect
	 * 1 not very effective
	 * 2 normal effectiveness
	 * 3 super effective
	 * 
	 * @param move the move used by the given Trainer's pokemon
	 * @param target the defending pokemon
	 * @param who the Trainer whose pokemon is attacking
	 */
	public void setEffectiveness(Move move, Pokemon target, Trainer who)
	{
		if(move.getPower() == 0)
		{
			if(who == player1)
				variables.setPlayer1Effectiveness(2);
			else
				variables.setPlayer2Effectiveness(2);
			return;
		}
		
		double modifier = modifierAgainst(move, target);
		
		if(modifier == 0)//does not affect
		{
			if(who == player1)
				variables.setPlayer1Effectiveness(0);
			else
				variables.setPlayer2Effectiveness(0);
		}
		else if(modifier == 0.25 || modifier == 0.5)//not very effective
		{
			if(who == player1)
				variables.setPlayer1Effectiveness(1);
			else
				variables.setPlayer2Effectiveness(1);
		}
		else if(modifier == 1)//normal effectiveness
		{
			if(who == player1)
				variables.setPlayer1Effectiveness(2);
			else
				variables.setPlayer2Effectiveness(2);
		}
		else if(modifier == 2 || modifier == 4)//super effective
		{
			if(who == player1)
				variables.setPlayer1Effectiveness(3);
			else
				variables.setPlayer2Effectiveness(3);
		}
	}
	
	/**
	 * Based on the move's chance to be critical returns if the
	 * move will critically strike or not.
	 * 
	 * @param move the move to test if it will critically strike
	 * @return true if the move is critical, false otherwise
	 */
	public boolean isCritical(Move move)
	{
		Random r = new Random();
		double critChance = 0.0625;
		if(move.getHighCritChance())
			critChance *= 2.0;
		
		if(r.nextDouble() < critChance && move.getPower() != 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates if the move is going to hit or not based on the move's accuracy,
	 * the attacking pokemon's accuracy modifier and the defending pokemon's evasion modifier.
	 * 
	 * @param attacker the pokemon that is attacking
	 * @param defender the pokemon that is defending
	 * @param chosenMove the attacking pokemon's chosen move
	 * @return true if the move hits, false otherwise
	 */
	public boolean moveHits(Pokemon attacker, Pokemon defender, Move chosenMove)
	{
		Random r = new Random();
		double hitChance;
		
		hitChance = (chosenMove.getAccuracy()/100.0) * attacker.getStatStages().getAccuracyModifier()/defender.getStatStages().getEvasionModifier();
	
		if(r.nextDouble() < hitChance)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates and returns the modifier that will be further used to calculate
	 * the damage that the defending pokemon will take.
	 * This modifier takes into account the effectiveness of the move
	 * on the defender, a small random factor that will range from 0.85 to 1.0
	 * and also if the move is STAB and/or critical.
	 * 
	 * @param attacker the pokemon that is attacking
	 * @param defender the pokemon that will be attacked
	 * @param chosenMove the move used by the attacker
	 * @param critical true if the move is critical and false otherwise
	 * @return the calculated modifier
	 */
	public double calculateModifier(Pokemon attacker, Pokemon defender, Move chosenMove, boolean critical){
		Random r = new Random();
		double modifier = 1;
		
		if(isSTAB(chosenMove, attacker))
			modifier *= 1.5;

		modifier *= 1 - r.nextInt(16)/100.0;
		
		if(critical == true)
			modifier *=2;
		
		modifier *= modifierAgainst(chosenMove, defender);
		
		return modifier;
	}
	
	/**
	 * Calculates and returns the damage caused by the attacking pokemon's move on the defender.
	 * The damage is calculated through a formula that takes into account the attackers level,
	 * the move's power, the attacker's Attack/Special Attack and the defender's
	 * Defense/Special Defense stats depending if the move is physical or special respectively.
	 * 
	 * @param attacker the pokemon that is attacking
	 * @param defender the pokemon that will be attacked
	 * @param chosenMove the move used by the attacker
	 * @param modifier the modifier to use in the damage formula
	 * @param critical true if the move is critical, false otherwise
	 * @return the damage the defending pokemon will receive
	 */
	public double calculateDamage(Pokemon attacker, Pokemon defender, Move chosenMove, double modifier, boolean critical)
	{
		double damage;
		
		if(chosenMove.isSpecial()){
			damage = ((2.0 * attacker.getLevel() / 5.0 + 2.0) * chosenMove.getPower() * attacker.getSPATK() / defender.getSPDEF(critical) / 50.0 + 2.0) * modifier;
		}
		else{
			damage = ((2.0 * attacker.getLevel() / 5.0 + 2.0) * chosenMove.getPower() * attacker.getATK() / defender.getDEF(critical) / 50.0 + 2.0) * modifier;
		}
		
		return damage;
	}
	
	/**
	 * Returns true if the given move is STAB and false otherwise.
	 * A move is considered STAB (Same Type Attack Bonus) if its type
	 * is the same as one of the types of the pokemon that used it. ~
	 * 
	 * @param move the move to test 
	 * @param self the pokemon that used the move
	 * @return true if the move is STAB, returns false otherwise
	 */
	public boolean isSTAB(Move move, Pokemon self)
	{
		if(self.getType1() == move.getType()){
			return true;
		}
		else if(self.hasTwoTypes() && self.getType2() == move.getType()){
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates and returns the modifier of a move against a target pokemon,
	 * based on the move's type and the pokemon's type(s).
	 * 
	 * @param move move to test
	 * @param target the target pokemon
	 * @return the modifier of a move against a target pokemon
	 */
	public double modifierAgainst(Move move, Pokemon target)
	{
		double mult = getTypeChart()[move.getType()][target.getType1()];
		if(target.hasTwoTypes())
			mult *= getTypeChart()[move.getType()][target.getType2()];
		
		return mult;
	}
	
	/**
	 * Returns the Trainer considered to be Player 1.
	 * 
	 * @return the Trainer considered to be Player 1
	 */
	public Trainer getPlayer1() {
		return player1;
	}

	/**
	 * Returns the Trainer considered to be Player 2.
	 * 
	 * @return the Trainer considered to be Player 2
	 */
	public Trainer getPlayer2() {
		return player2;
	}
	
	/**
	 * Sets player1 to the given Trainer
	 * 
	 * @param player1 the trainer to set player1 to
	 */
	public void setPlayer1(Trainer player1) {
		this.player1 = player1;
	}

	/**
	 * Sets player2 to the given Trainer
	 * 
	 * @param player12 the trainer to set player2 to
	 */
	public void setPlayer2(Trainer player2) {
		this.player2 = player2;
	}

	/**
	 * Returns this battle's BattleVariables
	 * 
	 * @return this battle's BattleVariables
	 */
	public BattleVariables getVariables() {
		return variables;
	}
}
