package testcases;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aequilibrium.Simulator;
import game.FightRule1;
import game.FightRule2;
import game.FightRule3;
import game.FightRule4;
import game.GameStatus;
import game.SpecialRule1;
import game.SpecialRule2;
import model.TranTech;
import model.TransformerFactory;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.autobot.AutobotTeam;
import model.team.deception.DeceptionTeam;

public class FightRulesTest {

	private GameStatus gameStatus;
	private Autobot autobot;
	private Deception deception;
	private AutobotTeam autobotTeam;
	private DeceptionTeam deceptionTeam;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		this.autobotTeam = Simulator.createAutobotTeam(4);		
		this.autobot = this.autobotTeam.getAtIndex(0);
		
		this.deceptionTeam = Simulator.createDeceptionTeam(2);
		this.deception = Simulator.createDeceptionTeam(1).getAtIndex(0);
		
		this.gameStatus = new GameStatus(true, autobotTeam, deceptionTeam);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFightRules() {
		
		FightRule1 rule1 = new FightRule1();
		FightRule2 rule2 = new FightRule2();
		FightRule3 rule3 = new FightRule3();
		FightRule4 rule4 = new FightRule4();
		SpecialRule1 rule5 = new SpecialRule1();
		SpecialRule2 rule6 = new SpecialRule2();
		
		boolean rule1Applicable = rule1.apply(this.autobot, this.deception, this.gameStatus);
		boolean rule2Applicable = rule2.apply(this.autobot, this.deception, this.gameStatus);
		boolean rule3Applicable = rule3.apply(this.autobot, this.deception, this.gameStatus);
		boolean rule4Applicable = rule4.apply(this.autobot, this.deception, this.gameStatus);
		boolean rule5Applicable = rule5.apply(this.autobot, this.deception, this.gameStatus);
		boolean rule6Applicable = rule6.apply(this.autobot, this.deception, this.gameStatus);
		
		boolean canDetermineWinner = rule1Applicable|| rule2Applicable || rule3Applicable|| 
				rule4Applicable || rule5Applicable || rule6Applicable;
		
		assertTrue("At least one rule is applicable and can determine the winner",canDetermineWinner);
	}

}
