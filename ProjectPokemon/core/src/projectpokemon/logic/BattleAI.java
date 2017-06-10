package projectpokemon.logic;

import java.util.Random;

public class BattleAI extends Battle{
	/**
	 * The AI can only switch when this value is >= 3. It is incremented every turn
	 * the AI chooses to attack and resets to 0 when it switches pokemons.
	 */
	private int switchAI = 3;
	
	/**
	 * Constructor of Battle.
	 * Sets each Trainer variable and loads the type chart.
	 * 
	 * @param player1 Trainer to be considered as "Player 1"
	 * @param player2 Trainer to be considered as "Player 2"
	 */
	public BattleAI(Trainer player1, Trainer player2) {
		super(player1, player2);
	}
	
	/**
	 * This function is called whenever its the AI's turn to play.
	 * The AI will choose either to attack or switch pokemons.
	 */
	public void moveAI(){
		
		if(switchAI()){
			int index = chooseSwitch();
			this.getPlayer2().swapPokemonAt(0, index);
		}
		else{
			int chosenMovIndex = chooseMove();
			
			getVariables().setPlayer2MoveIndex(chosenMovIndex);
		}
	}

	/**
	 * This function calculates whether the AI should switch pokemons or not.
	 * The criteria to switch is one of the following:
	 * - to be in a type disadvantage while not having a super-effective move against the target
	 * - have less or equal than 25% of the pokemons maximum health.
	 * Some restrictions:
	 * The AI can only switch when the switchAI attribute is >= 3, so that
	 * the frequency of switches is reasonable.
	 * The AI cannot switch when its party leader is the only pokemon that
	 * is still alive (the other 5 have all fainted).
	 * The AI must switch when its party leader has fainted.
	 * 
	 * @return true if the AI should switch, false if the AI should attack
	 */
	public boolean switchAI()
	{
		if(this.getPlayer2().allFainted())
			return false;
		
		if(getPlayer2().getPartyLeader().getCurrentHP() <= 0)
			return true;

		float mult = 1.0f;
		float mult2 = 1.0f;
		Pokemon attacker = getPlayer1().getPartyLeader();
		Pokemon defender = getPlayer2().getPartyLeader();

		boolean resistent = isResistentTo(defender,attacker);
		boolean superEffective = hasSuperEffectiveAgainst(defender, attacker);
		
		if(!resistent && !superEffective && switchAI >= 3){
			switchAI = 0;
			return true;
		}
		
		if(defender.getCurrentHP() < 0.25 * defender.getHP() && switchAI >= 3){
			switchAI = 0;
			return true;
		}
		
		switchAI++;
		return false;
	}
	
	/**
	 * Calculates and returns the index of the best pokemon that the AI trainer can switch to.
	 * Each pokemon in the AI's party besides the party leader will have points based on how good
	 * of a choice they are. This includes checking their current percentage of HP, checking if 
	 * they have at least one super effective move against the target and if they are resistent to 
	 * the target.
	 * 
	 * @return the index of the best pokemon that the AI trainer can switch to
	 */
	public int chooseSwitch()
	{
		int[] points = new int[5];

		Trainer human = getPlayer1();
		Trainer ai = getPlayer2();
		
		Pokemon candidate;
		for(int i = 0; i < 5;i++)
		{
			candidate = ai.getParty().get(i+1);
			
			if(candidate.getCurrentHP() <= 0)
			{
				points[i] = -100;
				continue;
			}
				
			if(hasSuperEffectiveAgainst(candidate, human.getPartyLeader()))
				points[i] += 3;

			if(isResistentTo(candidate, human.getPartyLeader()))
				points[i] += 2;

			if(candidate.getCurrentHP() >= 0.25 * candidate.getHP())
				points[i] += 1;

			if(candidate.getCurrentHP() >= 0.5 * candidate.getHP())
				points[i] += 1;
		}
		
		int numInvalid = 0;
		int  maxIndice = 0;
		int maxValue = 0;
		for(int i = 0; i < 5;i++)
		{
			if(points[i] < 0)
				numInvalid++;
			if(points[i] > maxValue){
				maxValue = points[i];
				maxIndice = i;
			}
		}
		
		if(numInvalid == 5)
			return -1;
		
		return maxIndice+1;
	}
	/**
	 * Calculates and returns the index of the move that the AI will use.
	 * Each available move to the AI will have a score based on the factors that
	 * influence its effectiveness against the target.
	 * The chosen move has a 50% chance of being the move with the best score
	 * and the remaining chance of being chosen randomly.
	 * 
	 * @return the index of the chosen move
	 */
	public int chooseMove()
	{
		Random r = new Random();
		double[] points = new double[4];
		int chosenMove;
		
		for(int i = 0; i < 4; i++)
		{
			Move move = getPlayer2().getPartyLeader().getMoveSet().get(i);
			Pokemon self = getPlayer2().getPartyLeader();
			Pokemon target = getPlayer1().getPartyLeader();
			
			points[i] = calculateScore(move, self, target);
		}
		
		chosenMove = maxScoreIndex(points);
		
		if(r.nextDouble() < 0.50){
			chosenMove = (int) r.nextInt(4);
		}
		
		return chosenMove;	
	}
	
	/**
	 * Returns the index of the maximum value of an array of doubles.
	 * 
	 * @param points array of doubles
	 * @return the index of the maximum value in the array
	 */
	public int maxScoreIndex(double[] points)
	{
		int maxIndex = 0;
		double maxScore = 0;
		for(int i = 0; i < 4; i++)
		{
			if(points[i] > maxScore)
			{
				maxScore = points[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}

	/**
	 * Calculates and returns the score of a move, given the pokemon that
	 * will use it (self) and the one it will be used against (target).
	 * These scores pretend to simulate the expected damage that each move will deal to the target. 
	 * However, the factors used to determine the scores do not take to account inaccessible information
	 * about the target pokemon, such as their stats.
	 * 
	 * @param move move to use
	 * @param self pokemon that uses the move
	 * @param target pokemon which the move will be used against
	 * @return the score of the given move
	 */
	public double calculateScore(Move move, Pokemon self, Pokemon target)
	{
		double score, stab, critChance;
		int usedStat;
		
		if(isSTAB(move,self))
			stab = 1.5;
		else
			stab = 1.0;
		
		if(move.isSpecial())
			usedStat = self.getSPATK();
		else
			usedStat = self.getATK();
		
		if(move.getHighCritChance())
			critChance = 0.125;
		else
			critChance = 0.0625;
			
		score = move.getPower()*move.getAccuracy()*modifierAgainst(move, target)*stab*usedStat;
		score += score*critChance;
		
		return score;
	}
	
	/**
	 * Returns true if the given pokemon has  at least one super effectiv move
	 * against the target.
	 * 
	 * @param self the pokemon that may have a super effective move
	 * @param target the target pokemon
	 * @return true if the given pokemon has at least one super effective move against the target
	 */
	public boolean hasSuperEffectiveAgainst(Pokemon self, Pokemon target)
	{
		for(int i = 0; i < 4; i++)
		{
			if(isSuperEffectiveAgainst(self.getMoveSet().get(i), target))
				return true;
		}
		return false;
	}
	
	/**
	 * Return true if the given pokemon is resistent to the target pokemon.
	 * 
	 * @param self the pokemon that may be resistent to the target
	 * @param enemy the target pokemon
	 * @return true if the given pokemon is resistent to the target pokemon.
	 */
	public boolean isResistentTo(Pokemon self, Pokemon enemy)
	{
		double mult = 1.0;
		double mult2 = 1.0;

		mult *= getTypeChart()[enemy.getType1()][self.getType1()];
		
		if(self.hasTwoTypes()){
			mult *= getTypeChart()[enemy.getType1()][self.getType2()];
		}

		if(enemy.hasTwoTypes()){
			mult2 *= getTypeChart()[enemy.getType2()][self.getType1()];
			if(self.hasTwoTypes()){
				mult2 *= this.getTypeChart()[enemy.getType2()][self.getType2()];
			}
		}

		if(mult <= 1.0 && mult2 <= 1.0)
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if the given move is super effective against the target
	 * 
	 * @param move move to be tested
	 * @param target the target pokemon
	 * @return true if the given move is super effective against the target, false otherwise
	 */
	public boolean isSuperEffectiveAgainst(Move move, Pokemon target)
	{
		double mult = getTypeChart()[move.getType()][target.getType1()];
		if(target.hasTwoTypes())
			mult *= getTypeChart()[move.getType()][target.getType2()];

		if(mult > 1.0)
			return true;
		else
			return false;
	}
}
