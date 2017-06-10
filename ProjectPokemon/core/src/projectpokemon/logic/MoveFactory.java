package projectpokemon.logic;

import java.util.ArrayList;

import projectpokemon.logic.Move;

public class MoveFactory {
	/**
	 * Instance of move factory
	 */
	private static MoveFactory instance = new MoveFactory();
	
	/**
	 * ArrayList of all the available moves
	 */
	private ArrayList<Move> moveList= new ArrayList<Move>();
	
	/**
	 * Constructor of MoveFactory.
	 * It initializes the array of the available moves
	 */
	private MoveFactory(){
		for(int i = 0; i <= Move.getNumberOfMoves("moves.txt"); i++){
			Move m = new Move(i);
			moveList.add(m);
		}
	}
	
	/**
	 * Updates the move list.
	 */
	public void update()
	{
		moveList.removeAll(moveList);
		this.instance = new MoveFactory();	
	}
	
	/**
	 * Returns the instance of MoveFactory.
	 * 
	 * @return the instance of MoveFactory
	 */
	public static MoveFactory getInstance(){
	      return instance;
	}
	
	/**
	 * Returns the move with the given ID
	 * 
	 * @param id the ID of the wanted move
	 * @return the move with the given ID
	 */
	public Move getMove(int id){
		return moveList.get(id);
	}
}
