package projectpokemon.logic;

public class InfoBattleAI {
	/**
	 * Array of the AI opponents
	 */
	private Trainer opponents[] = new Trainer[4];
	
	/**
	 * Indicates the current opponent index
	 */
	private int currentOpponent = 0;

	/**
	 * Constructor of InfoBattleAI
	 * It initializes the party of each opponent
	 */
	public InfoBattleAI(){
		initBlue();
		initLance();
		initSteven();
		initCynthia();
	};

	/**
	 * Returns the current opponent trainer
	 * 
	 * @return the current opponent trainer
	 */
	public Trainer getCurrentOpponent(){
		return opponents[currentOpponent];
	}

	/**
	 * Returns the current opponent index
	 * 
	 * @return the current opponent index
	 */
	public int getCurrentOpponentIndex(){
		return currentOpponent;
	}

	/**
	 * It increments the current opponent index and
	 * resets every opponents party
	 */
	public void nextOpponent(){
		initBlue();
		initLance();
		initSteven();
		initCynthia();
		currentOpponent++;
	}
	
	/**
	 * Resets the current opponent index to zero and
	 * also resets every opponents party
	 */
	public void reset(){
		initBlue();
		initLance();
		initSteven();
		initCynthia();
		currentOpponent = 0;
	}

	/**
	 * Initializes the first opponent's party
	 */
	void initBlue(){
		opponents[0] = new Trainer();
		opponents[0].swapPokemonWith(0, new Pokemon(18));
		opponents[0].swapPokemonWith(1, new Pokemon(65));
		opponents[0].swapPokemonWith(2, new Pokemon(112));
		opponents[0].swapPokemonWith(3, new Pokemon(103));
		opponents[0].swapPokemonWith(4, new Pokemon(59));
		opponents[0].swapPokemonWith(5, new Pokemon(9));
	}

	/**
	 * Initializes the second opponent's party
	 */
	void initLance(){
		opponents[1] = new Trainer();
		opponents[1].swapPokemonWith(0, new Pokemon(130));
		opponents[1].swapPokemonWith(1, new Pokemon(149));
		opponents[1].swapPokemonWith(2, new Pokemon(142));
		opponents[1].swapPokemonWith(3, new Pokemon(6));
		opponents[1].swapPokemonWith(4, new Pokemon(34));
		opponents[1].swapPokemonWith(5, new Pokemon(94));
	}

	/**
	 * Initializes the third opponent's party
	 */
	void initSteven(){
		opponents[2] = new Trainer();
		opponents[2].swapPokemonWith(0, new Pokemon(227));
		opponents[2].swapPokemonWith(1, new Pokemon(344));
		opponents[2].swapPokemonWith(2, new Pokemon(306));
		opponents[2].swapPokemonWith(3, new Pokemon(346));
		opponents[2].swapPokemonWith(4, new Pokemon(348));
		opponents[2].swapPokemonWith(5, new Pokemon(376));
	}

	/**
	 * Initializes the fourth opponent's party
	 */
	void initCynthia(){
		opponents[3] = new Trainer();
		opponents[3].swapPokemonWith(0, new Pokemon(442));
		opponents[3].swapPokemonWith(1, new Pokemon(445));
		opponents[3].swapPokemonWith(2, new Pokemon(423));
		opponents[3].swapPokemonWith(3, new Pokemon(350));
		opponents[3].swapPokemonWith(4, new Pokemon(407));
		opponents[3].swapPokemonWith(5, new Pokemon(448));
	}
}
