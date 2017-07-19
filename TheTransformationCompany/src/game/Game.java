package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import aequilibrium.Constant;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.autobot.AutobotTeam;
import model.team.deception.DeceptionTeam;

/**
 * A model to represent the Game entity. The Game class uses Observer/Observable pattern with the GameStatus class
 * @author Alireza
 *
 */
public class Game implements Observer{

	/***
	 * Starts the game with two inputs teams and the game rules
	 * @param autobotTeam
	 * @param deceptionTeam
	 * @param fightRules
	 */
	public void start(AutobotTeam autobotTeam, DeceptionTeam deceptionTeam, List<FightRules<Autobot,Deception>> fightRules)
	{		
		//sort both teams based on the rank score of their transformers
		autobotTeam.sortRank();
		deceptionTeam.sortRank();
		
	
		//creating a game status to track the status of the game.
		GameStatus gameStatus = new GameStatus(true, autobotTeam, deceptionTeam);		
		int numberOfBattles = gameStatus.expectedNumberOfBattles();
		gameStatus.addObserver(this);

		
		//creating battles for corresponding transformers
		for(int i=0; i<numberOfBattles && gameStatus.isGoingOn(); i++)
		{
			Autobot autobotFighter = autobotTeam.getAtIndex(i);
			Deception deceptionFighter = deceptionTeam.getAtIndex(i);
			
			//creating a battle
			Battle battle = new Battle(autobotFighter, deceptionFighter);
			gameStatus.addBattle(battle);
			
			//applying the rules on the battle
			battle.applyRules(fightRules,gameStatus);	
			
			//print the battle
			System.out.println(battle.toString());
		}
				
		//printing the teams after the game is over
		System.out.println(autobotTeam);		
		System.out.println(deceptionTeam);
		
		//printing the final game results
		gameStatus.printStatus();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(o instanceof GameStatus)
		{
			System.out.println("Game is Terminated!\n");
		}
	}

	
			
}
