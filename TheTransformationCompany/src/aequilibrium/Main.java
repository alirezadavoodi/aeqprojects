package aequilibrium;

import java.util.ArrayList;
import java.util.List;

import game.FightRule1;
import game.FightRule2;
import game.FightRule3;
import game.FightRule4;
import game.FightRules;
import game.Game;
import game.SpecialRule1;
import game.SpecialRule2;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.autobot.AutobotTeam;
import model.team.deception.DeceptionTeam;

public class Main {

	/**
	 * To demo follow the steps: (you can use the test units provided too)
	 * step1: specify the number of autobots in the team
	 * step2: specify the number of deceptions in the team
	 * step3: create an autobot team. Use the simulator to create a random team or define your own team
	 * step4: create a deception team. Use the simulator to create a random team or define your own team
	 * step5: create the game rules objects
	 * step6: start the game
	 * @param args
	 */
	public static void main(String[] args) {
	
		//step1: number of autobots in the team (at least one or will get an exception)
		int numberOfAutobotsToGenerate = 1;
		
		//step2: step1: number of deceptions in the team (at least one or will get an exception)
		int numberOfDeceptionsToGenerate = 3;
		
		//step3: using the simulator to create random autobot team (for demo only)
		AutobotTeam autobotTeam = null;
		try {
			autobotTeam = Simulator.createAutobotTeam(numberOfAutobotsToGenerate);
		} catch (TransformerTeamInvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step4: using the simulator to create random deception team (for demo only)
		DeceptionTeam deceptionTeam = null;
		try {
			deceptionTeam = Simulator.createDeceptionTeam(numberOfDeceptionsToGenerate);
		} catch (TransformerTeamInvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step5: generating the game rules objects
		List<FightRules<Autobot,Deception>> fightRules = new ArrayList<>();		
		fightRules.add(new FightRule1());
		fightRules.add(new FightRule2());
		fightRules.add(new FightRule3());
		fightRules.add(new FightRule4());
		fightRules.add(new SpecialRule1());
		fightRules.add(new SpecialRule2());
	
		//step6: starting the game using the generated teams
		new Game().start(autobotTeam, deceptionTeam,fightRules);
	}

}
