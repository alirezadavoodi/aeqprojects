package castel;

import java.util.function.Predicate;

/**
 * A predicate functional interface with determine whether a given coded string represent a land 
 * in which you can build a castle
 * @author Alireza
 *
 */
@FunctionalInterface
public interface CastlePatternPredicate extends Predicate<String>{

	boolean test(String s);
}
