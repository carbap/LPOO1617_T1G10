package projectpokemon.logic;

import java.util.ArrayList;
import java.util.Collections;

public class Trainer {
	private ArrayList<Pokemon> party = new ArrayList<Pokemon>();
	
	
	public void setParty(ArrayList<Pokemon> newParty){
		party = newParty;
	}
	
	public Pokemon getPartyLeader()
	{
		return party.get(0);
	}
	
	public Pokemon getPokemonAt(int index){
		return party.get(index);
	}
	
	public void swapPokemonAt(int index1, int index2){
		Collections.swap(party, index1, index2);
	}
	
	public void swapPokemonWith(int index, Pokemon newPokemon){
		party.add(newPokemon);
		int lastIndex = party.size()-1;
		Collections.swap(party, index, lastIndex);
		party.remove(lastIndex);
	}
	
	public boolean addPokemon(Pokemon newPokemon){
		if(party.size() < 6){
			party.add(newPokemon);
			return true;
		}
		return false;
	}
	
	public boolean removePokemon(int index){
		if(party.size() > 1){
			party.remove(index);
			return true;
		}
		return false;
	}
	
}
