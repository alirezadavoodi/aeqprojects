package aequilibrium;

import castel.BuildingCastle;
import castel.CastlesBuilderFunction;

public class Main {

	/**
	 * To demo follow the steps: (you can use the test units provided too)
	 * step1: define the array of hights
	 * step2: build castles
	 * Assumptions: In addition to the assumptions defined in the project's description
	 * One more assumption is made which is it is possible to create a castle at the end of the land.
	 * For instance for the input [1,2,1] you can build 3 castles: 2 valleys and 1 peak
	 * @param args
	 */
	public static void main(String[] args) {
		
		//step1: define the array of heights
		//Integer[] stretchOfLand = {1,2,3,4,1,1,5,6,7,8,9};
		//Integer[] stretchOfLand = {2,2,2,2,2,2,2,2,2,2,1,5,1,2,2,2,2,2,2,2,2,2,2,2};
		//Integer[] stretchOfLand = {2,2,1,5,1,2,2};
		//Integer[] stretchOfLand = {8,0,0,0,1,1,2,2,2,2,1,1,1,3,3,3,3,90};
		Integer[] stretchOfLand = {1,2,1};
		
		//step2: build castles
		BuildingCastle buildingCastle = new BuildingCastle();		
		int totalCastles = buildingCastle.buildCastles(
				stretchOfLand,
				(landStretch) -> { 					
					CastlesBuilderFunction cbf = new CastlesBuilderFunction();
					int counter = cbf.apply(landStretch);
					return counter;
				});
		
		System.out.println("YaY! You can build "+totalCastles+" awesome castle(s)");
		
	}
}
