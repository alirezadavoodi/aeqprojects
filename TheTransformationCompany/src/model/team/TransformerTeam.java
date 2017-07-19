package model.team;
import java.util.Iterator;

/***
 * A model class to represent a team of Transformers. Uses Iterable/Iterator pattern.
 * @author Alireza
 *
 * @param <E>
 */
public abstract class TransformerTeam<E> implements Iterable<E>{

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Rank the transformers based on their ranks
	 */
	public abstract void sortRank();
	
	/**
	 * 
	 * @return the size of the team
	 */
	public abstract int size();
	
	/**
	 * Sets all competitors (transformers with a fighter/competitor) as eliminated
	 */
	public abstract void eliminateAllCompetitors();
	
	/**
	 * 
	 * @return number of eliminated transformer in the team
	 */
	public abstract int getNumberOfEliminated();
	
	/**
	 * Prints the name of the surviving transformers after the game ends
	 */
	public abstract void printSurvivorNames();
	
	/**
	 * Prints the name of the eliminated transformers after the game ends.
	 */
	public abstract void printEliminatedNames();
	
}
