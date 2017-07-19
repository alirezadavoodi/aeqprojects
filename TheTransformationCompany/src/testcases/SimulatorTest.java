package testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aequilibrium.Simulator;
import aequilibrium.TransformerTeamInvalidNumberException;
import model.team.autobot.AutobotTeam;

public class SimulatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test(expected = TransformerTeamInvalidNumberException.class)
	public void testCreateAutobotTeamWithNoMember() throws TransformerTeamInvalidNumberException {
		
		Simulator.createAutobotTeam(0);
	}
	
	@Test(expected = TransformerTeamInvalidNumberException.class)
	public void testCreateAutobotTeamWithInvalidNumberOfMember() throws TransformerTeamInvalidNumberException {
		
		Simulator.createDeceptionTeam(-1);
	}
	
	@Test
	public void testCreateAutobotTeam()
	{
		try {
			AutobotTeam team = Simulator.createAutobotTeam(3);			
			assertEquals("Number of autobot team members: ", 3, team.size());
		} catch (TransformerTeamInvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
