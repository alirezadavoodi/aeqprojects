package testcaes;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import castel.BuildingCastle;
import castel.CastlesBuilderFunction;

public class BuildingCastleTest {

	private BuildingCastle buildingCastle;

	@Before
	public void setUp() throws Exception {
		this.buildingCastle = new BuildingCastle();
	}

	@After
	public void tearDown() throws Exception {
		this.buildingCastle = null;
	}

	@Test
	public void testBuildCastles() {
		int totalCastles = 0;
		
		Function<List<Integer>,Integer> castlesBuilderFunction = (landStretch) -> { 					
			CastlesBuilderFunction cbf = new CastlesBuilderFunction();
			int counter = cbf.apply(landStretch);
			return counter;
		};
		
		Integer[] sample1 = null;		
		totalCastles = buildingCastle.buildCastles(sample1,castlesBuilderFunction);
		assertEquals("Testing sample1: "+sample1+" ", 0,totalCastles);
		
		Integer[] sample2 = {};		
		totalCastles = buildingCastle.buildCastles(sample2,castlesBuilderFunction);
		assertEquals("Testing sample2: "+sample2+" ", 0,totalCastles);
		
		Integer[] sample3 = {8};	
		totalCastles = buildingCastle.buildCastles(sample3,castlesBuilderFunction);
		assertEquals("Testing sample3: "+sample3+" ", 1,totalCastles);
		
		Integer[] sample4 = {8,0,0,0,1,1,2,2,2,2,1,1,1,3,3,3,3,9};
		totalCastles = buildingCastle.buildCastles(sample4,castlesBuilderFunction);
		assertEquals("Testing sample4: "+sample4+" ", 5,totalCastles);
		
		Integer[] sample5 = {8,8,8,8,8,8,8,8};
		totalCastles = buildingCastle.buildCastles(sample5,castlesBuilderFunction);
		assertEquals("Testing sample5: "+sample5+" ", 1,totalCastles);
		
		Integer[] sample6 = {8,8,8,8,8,8,8,8,4,4};
		totalCastles = buildingCastle.buildCastles(sample6,castlesBuilderFunction);
		assertEquals("Testing sample6: "+sample6+" ", 2,totalCastles);
		
		Integer[] sample7 = {8,8,8,8,8,8,8,8,1};
		totalCastles = buildingCastle.buildCastles(sample7,castlesBuilderFunction);
		assertEquals("Testing sample7: "+sample7+" ", 2,totalCastles);
		
		Integer[] sample8 = {1,2,3,4,5,6};
		totalCastles = buildingCastle.buildCastles(sample8,castlesBuilderFunction);
		assertEquals("Testing sample8: "+sample8+" ", 2,totalCastles);
		
		Integer[] sample9 = {6,5,4,3,2,1};
		totalCastles = buildingCastle.buildCastles(sample9,castlesBuilderFunction);
		assertEquals("Testing sample9: "+sample9+" ", 2,totalCastles);
		
		Integer[] sample10 = {6,5,4,3,3,2,1};
		totalCastles = buildingCastle.buildCastles(sample10,castlesBuilderFunction);
		assertEquals("Testing sample10: "+sample10+" ", 2,totalCastles);
		
		Integer[] sample11 = {1,2,3,3};
		totalCastles = buildingCastle.buildCastles(sample11,castlesBuilderFunction);
		assertEquals("Testing sample11: "+sample11+" ", 2,totalCastles);
		
		Integer[] sample12 = {2,1,3,3};
		totalCastles = buildingCastle.buildCastles(sample12,castlesBuilderFunction);
		assertEquals("Testing sample12: "+sample12+" ", 3,totalCastles);
		
		Integer[] sample13 = {2,5,3,3};
		totalCastles = buildingCastle.buildCastles(sample13,castlesBuilderFunction);
		assertEquals("Testing sample13: "+sample13+" ", 3,totalCastles);
	}

}
