package castel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A model to represent the BuildingCastle action
 * @author Alireza
 *
 */
public class BuildingCastle {
	
	/**
	 * Uses a functional interface (functional programming) to create the castles. The Function
	 * object can define any castle building algorithm.
	 * @param stretchOfLand: Heights of the land
	 * @param castlesBuilderFunction: The algorithm
	 * @return number of possible castles. 
	 */
	public int buildCastles(Integer[] stretchOfLand, Function<List<Integer>,Integer> castlesBuilderFunction)
	{
		if(stretchOfLand==null) return 0;
		
		//use the apply method of the CastlesBuildingFunction class to run the algorithm
		int counter = castlesBuilderFunction.apply(Arrays.asList(stretchOfLand));
		return counter;
	}
}
