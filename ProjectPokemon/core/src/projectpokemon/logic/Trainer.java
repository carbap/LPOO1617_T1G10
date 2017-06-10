package projectpokemon.logic;

import java.util.ArrayList;
import java.util.Collections;

public class Trainer {
	/**
	 * This trainer's pokemon party
	 */
	private ArrayList<Pokemon> party = new ArrayList<Pokemon>();
	
	/**
	 * Constructor of Trainer.
	 * It initializes the players party with some pokemons.
	 * This will be the player's default pokemons.
	 */
	public Trainer(){
		party.add(new Pokemon(130));
		party.add(new Pokemon(65));
		party.add(new Pokemon(149));
		party.add(new Pokemon(94));
		party.add(new Pokemon(306));
		party.add(new Pokemon(376));
	}
	
	/**
	 * Sets the trainer's party to the given ArrayList of pokemon
	 * 
	 * @param newParty ArrayList of pokemon
	 */
	public void setParty(ArrayList<Pokemon> newParty){
		party = newParty;
	}
	
	/**
	 * Returns the trainer's party leader (the first pokemon
	 * in the party, meaning the one that will be in battle)
	 * @return
	 */
	public Pokemon getPartyLeader()
	{
		return party.get(0);
	}
	
	/**
	 * Retuns the pokemon at the given index of the party
	 * 
	 * @param index index of the wanted pokemon
	 * @return the pokemon at the given index of the party
	 */
	public Pokemon getPokemonAt(int index){
		return party.get(index);
	}
	
	/**
	 * Swaps the position of the pokemons at the given indexes of the party
	 * 
	 * @param index1 index of a pokemon to swap positions
	 * @param index2 index of a pokemon to swap positions
	 */
	public void swapPokemonAt(int index1, int index2){
		Collections.swap(party, index1, index2);
	}
	
	/**
	 * Replaces the pokemon at the given index of the party
	 * with the given pokemon
	 * 
	 * @param index index of pokemon to replace
	 * @param newPokemon the pokemon to swap in
	 */
	public void swapPokemonWith(int index, Pokemon newPokemon){
		party.add(newPokemon);
		int lastIndex = party.size()-1;
		Collections.swap(party, index, lastIndex);
		party.remove(lastIndex);
	}
	
	/**
	 * Adds the given pokemon to the trainer's party
	 * Returns true if the pokemon could be added, false otherwise.
	 * 
	 * @param newPokemon pokemon to be added
	 * @return true if the addition was successful, false otherwise
	 */
	public boolean addPokemon(Pokemon newPokemon){
		if(party.size() < 6){
			party.add(newPokemon);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the pokemon at the given index from the party
	 * Returns true if the pokemon could be removed, false otherwise.
	 * 
	 * @param index index of the pokemon to be removed
	 * @return true if the pokemon was removed, false otherwise.
	 */ 
	public boolean removePokemon(int index){
		if(party.size() > 1){
			party.remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Returns the trainer's party
	 * 
	 * @return the trainer's party
	 */
	public ArrayList<Pokemon> getParty() {
		return party;
	}

	/**
	 * Returns true if all the pokemon besides the party leader have
	 * no remaining health, and returns false otherwise.
	 * 
	 * @return true if all the pokemon besides the party leader fainted, false otherwise
	 */
	public boolean allFainted(){
		for(int i = 1; i < getParty().size(); i++)
		{
			if(getParty().get(i).getCurrentHP() != 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Heals all the pokemon in the party and also resets
	 * each pokemon's StatStages.
	 */
	public void healParty(){
		StatStages s = new StatStages(0,0,0,0,0,0,0);
		for(int i = 0; i < getParty().size(); i++){
			getParty().get(i).setStatStages(s);
			getParty().get(i).setDamageTaken(0);
		}
			
	}
	
}
