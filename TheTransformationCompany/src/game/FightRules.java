package game;

@FunctionalInterface
public interface FightRules<T,E> {

	/***
	 * A generic class for defining the game rules and applying them
	 * @param t
	 * @param e
	 * @param gameStatus
	 * @return
	 */
	boolean apply(T t, E e, GameStatus gameStatus);
}
