package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.autobot.AutobotTeam;
import model.team.deception.DeceptionTeam;
import aequilibrium.Constant;
import aequilibrium.Constant.WinnerTeam;

/***
 * A Model class to represent the status of the game
 * @author Alireza
 *
 */
public class GameStatus extends Observable{

	private boolean isGoingOn;
	private AutobotTeam autobotTeam;
	private DeceptionTeam deceptionTeam;
	
	private List<Battle> battles;
	private List<Autobot> autobotSurvivors;
	private List<Deception> deceptionSurvivors;
	private List<Observer> observers;

	public GameStatus(boolean isGoingOn, AutobotTeam autobotTeam, DeceptionTeam deceptionTeam)
	{
		this.isGoingOn = isGoingOn;
		this.autobotTeam = autobotTeam;
		this.deceptionTeam = deceptionTeam;
		
		this.observers = new ArrayList<>();
		
		this.autobotSurvivors = new ArrayList<>();
		this.deceptionSurvivors = new ArrayList<>();
		this.battles = new ArrayList<>();
	}
	
	public boolean isGoingOn() {
		return isGoingOn;
	}
	
	public void setGoingOn(boolean isGoingOn) {
		this.isGoingOn = isGoingOn;		
		this.notifyObservers();
	}

	public AutobotTeam getAutobotTeam() {
		return autobotTeam;
	}

	public void setAutobotTeam(AutobotTeam autobotTeam) {
		this.autobotTeam = autobotTeam;
	}

	public DeceptionTeam getDeceptionTeam() {
		return deceptionTeam;
	}

	public void setDeceptionTeam(DeceptionTeam deceptionTeam) {
		this.deceptionTeam = deceptionTeam;
	}

	public List<Battle> getBattles() {
		return battles;
	}

	public void setBattles(List<Battle> battles) {
		this.battles = battles;
	}
	
	public void addBattle(Battle battle)
	{
		if(this.battles==null) this.battles = new ArrayList<>();

		this.battles.add(battle);
	}

	public List<Autobot> getAutobotSurvivors() {
		
		return autobotSurvivors;
	}

	public void setAutobotSurvivors(List<Autobot> autobotSurvivors) {
		this.autobotSurvivors = autobotSurvivors;
	}

	/***
	 * Adds an autobot when the autobot is the survivor of a fight
	 * @param autobot
	 */
	public void addAutobotSurvivor(Autobot autobot)
	{
		if(this.autobotSurvivors==null) this.autobotSurvivors = new ArrayList<>();
		this.autobotSurvivors.add(autobot);
	}
	
	public List<Deception> getDeceptionSurvivors() {
		return deceptionSurvivors;
	}

	public void setDeceptionSurvivors(List<Deception> deceptionSurvivors) {
		this.deceptionSurvivors = deceptionSurvivors;
		
	}
	
	/***
	 * Adds a deception when the decption is the survivor of a fight
	 * @param deception
	 */
	public void addDeceptionSurvivor(Deception deception)
	{
		if(this.deceptionSurvivors==null) this.deceptionSurvivors = new ArrayList<>();
		
		this.deceptionSurvivors.add(deception);
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		observers.add(o);
    }
	
	@Override
	public synchronized void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	/***
	 * Updates the observer (which is game) about termination/continuation of the game
	 */
	public void notifyObservers() {
        
		for(Observer game : observers)
		{
			game.update(this, null);
		}
    }
	
	/***
	 * Prints the status of the game to output: Example:
	    1 battle
		Winning team (Decepticons): Soundwave
		Survivors from the losing team (Autobots): Hubcap
	 */
	public void printStatus()
	{
		System.out.println("\n****************************************************\n");
		if(expectedNumberOfBattles()==1)
			System.out.println(expectedNumberOfBattles()+ "battle");
		else
			System.out.println(expectedNumberOfBattles()+ "battles");
		
		
		String winnerTitle = getWinnerTeam().title();
		if(getWinnerTeam()==WinnerTeam.NONE)  //Tie
		{
			System.out.println("====No Winner!====");
			
			System.out.print("Survivors from the Autobot Team:");
			autobotTeam.printSurvivorNames();
			
			System.out.print("Survivors from the Deception Team:");
			deceptionTeam.printSurvivorNames();
		}
		else
		{
			System.out.print("Winning team ("+winnerTitle+"):");
			
			if(getWinnerTeam()==Constant.WinnerTeam.DeceptionsTeam)
			{
				deceptionTeam.printSurvivorNames();
				
				System.out.print("\nSurvivors from the losing team ("+Constant.WinnerTeam.AutobotTeam.title()+"):");			
				autobotTeam.printSurvivorNames();
			}
			else
			{
				autobotTeam.printSurvivorNames();
				
				System.out.print("\nSurvivors from the losing team ("+Constant.WinnerTeam.DeceptionsTeam.title()+"):");			
				deceptionTeam.printSurvivorNames();
			}
		}
		
		
		System.out.println("\n****************************************************\n");
	}
	
	/***
	 * The method updates game status based on an autobot is a loser.
	 * It will set the corresponding autobot in the team as eliminated
	 * @param autobot
	 */
	public void updateGameStatusWithAutobotLoser(Autobot autobot)
	{
		autobotTeam.eliminateFromAutobotTeam(autobot);
		updateGameTerminationStatus();
	}
	
	/***
	 * The method updates game status based on a deception is a loser.
	 * It will set the corresponding deception in the team as eliminated
	 * @param deception
	 */
	public void updateGameStatusWithDeceptionLoser(Deception deception)
	{
		deceptionTeam.eliminateFromDeceptionTeam(deception);
		updateGameTerminationStatus();
	}
	
	/***
	 * Destroys all competitors and terminate the game
	 */
	public void updateGameStatusWithDestroyingAllTransformers()
	{
		autobotTeam.eliminateAllCompetitors();
		deceptionTeam.eliminateAllCompetitors();
		
		updateGameTerminationStatus();
	}
	
	/***
	 * Checks whether the game should be terminated and update the isGoingOn property
	 * Situation 1: If SpecialRule2 happens. In this case all competitors will eliminate
	 * Situation 2: If all battles end
	 */
	public void updateGameTerminationStatus()
	{
		int expected = expectedNumberOfBattles();
		
		if(autobotTeam.getNumberOfEliminated()==expected)
		{
			this.setGoingOn(false);
		}
		else if(deceptionTeam.getNumberOfEliminated()==expected)
		{
			this.setGoingOn(false);
		}
		else if((this.autobotSurvivors.size() + this.deceptionSurvivors.size())==expected)
		{
			this.setGoingOn(false);
		}
		else if(this.battles.size()==expected)
		{
			this.setGoingOn(false);
		}
	}
	
	/***
	 * 
	 * @return expected number of battles between the two teams
	 */
	public int expectedNumberOfBattles()
	{
		//calculating the number of possible battles
		int autobotTeamSize = autobotTeam.size();
		int deceptionTeamSize = deceptionTeam.size();
		
		int numberOfBattles = (autobotTeamSize>deceptionTeamSize)? deceptionTeamSize:autobotTeamSize;
		
		return numberOfBattles;

	}
	
	/***
	 * Determines the winner team based on: The team who eliminated the largest number of the opposing team is the winner
	 * @return the winner team enum
	 */
	public Constant.WinnerTeam getWinnerTeam()
	{
		if(this.autobotSurvivors.size()>this.deceptionSurvivors.size())
		{
			return Constant.WinnerTeam.AutobotTeam;
		}
		else if(this.autobotSurvivors.size()<this.deceptionSurvivors.size())
		{
			return Constant.WinnerTeam.DeceptionsTeam;
		}
		else
		{
			return Constant.WinnerTeam.NONE;
		}
	}
}
