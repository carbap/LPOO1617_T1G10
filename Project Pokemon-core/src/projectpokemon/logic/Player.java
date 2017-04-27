package projectpokemon.logic;

public class Player extends Trainer{
	
	public Move chooseMove(int index)
	{
		return getPartyLeader().getMoveSet().get(index);
	}
}
