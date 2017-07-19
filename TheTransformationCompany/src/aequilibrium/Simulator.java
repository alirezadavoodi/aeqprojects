package aequilibrium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.TranTech;
import model.TransformerFactory;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.autobot.AutobotTeam;
import model.team.deception.DeceptionTeam;


public class Simulator {

	//each transformer has 8 tech specifications
	private final static int numberOfSkills = 8;
	
	/**
	 * create a random team of autobots for the game
	 * @param memberNumber
	 * @return AutobotTeam of autobots (randomly generated)
	 */
	public static AutobotTeam createAutobotTeam(int memberNumber) throws TransformerTeamInvalidNumberException
	{
		if(memberNumber<1)
		{
			throw new TransformerTeamInvalidNumberException("The number of a member in a team should be at least one");
		}
		else
		{
			List<Autobot> autobots = new LinkedList<>();		
			for(int i=0; i<memberNumber; i++)
			{
				int[] skills =  generateRandomTechSpecForTransformer(numberOfSkills);
				String name = getARandomNameForTransformers();			
				Map<TranTech, Integer> techTable = convertToTechSpecTable(skills);			
				Autobot a = TransformerFactory.createAutobot(name, techTable);
				
				autobots.add(a);
			}
			
			AutobotTeam autobotTeam = new AutobotTeam(autobots);
			return autobotTeam;
		}

	}
		
	/***
	 * create a random team of deceptions for the game
	 * @param memberNumber
	 * @return DeceptionTeam of deceptions (randomly generated)
	 */
	public static DeceptionTeam createDeceptionTeam(int memberNumber) throws TransformerTeamInvalidNumberException
	{
		if(memberNumber<1)
		{
			throw new TransformerTeamInvalidNumberException("The number of a member in a team should be at least one");
		}
		else
		{
			List<Deception> deceptions = new LinkedList<>();		
			for(int i=0; i<memberNumber; i++)
			{
				int[] skills =  generateRandomTechSpecForTransformer(numberOfSkills);
				String name = getARandomNameForTransformers();			
				Map<TranTech, Integer> techTable = convertToTechSpecTable(skills);			
				Deception d = TransformerFactory.createDeception(name,techTable);
				
				deceptions.add(d);
			}
			
			DeceptionTeam deceptionTeam = new DeceptionTeam(deceptions);
			return deceptionTeam;
		}
	}
	
	/***
	 * create a list of tech specs for each transformer. (randomly generated) 
	 * @param numberOfSpecItem
	 * @return array of tech specs of the transformer
	 */
	private static int[] generateRandomTechSpecForTransformer(int numberOfSpecItem)
	{
		int max = 10;
		int min = 1;
		
		int[] specs = new int[numberOfSpecItem];
		
		Random random = new Random();
		
		for(int i=0; i<numberOfSpecItem; i++)
		{
			int rand = random.nextInt(max-min+1) + min;
			specs[i] = rand;
		}
		
		return specs;
	}
	
	/***
	 * Generates a random name for the transformer from a list of predefined names
	 * @return a random name for the generated transformer
	 */
	private static String getARandomNameForTransformers()
	{
		String[] randomNames = {"Pounce","Predaking","Ramjet","Rampage","Ransack","Ratbat","Ravage","Razorclaw","Reflector","Rippersnapper","Rumble","Fireflight","First Aid",
		"Fortress Maximus","Freeway","Gears","Goldbug","Grapple","Grimlock","Groove","Hardhead","Optimus Prime","Predaking"};
		
		
		int max = randomNames.length-1;
		int min = 0;
		
		Random random = new Random();
		int rand = random.nextInt(max-min+1) + min;
		
		return randomNames[rand];
		
	}
	
	/***
	 * Maps the array of tech specs to a map
	 * @param skillRates
	 * @return a map between skills and their scores
	 */
	private static Map<TranTech, Integer> convertToTechSpecTable(int[] skillRates)
	{
		Map<TranTech, Integer> skillRatesMapping = new HashMap<>();
		
		skillRatesMapping.put(TranTech.STRENGTH, skillRates[0]);
		skillRatesMapping.put(TranTech.INTELLIGENCE, skillRates[1]);
		skillRatesMapping.put(TranTech.SPEED, skillRates[2]);
		skillRatesMapping.put(TranTech.ENDURANCE, skillRates[3]);
		skillRatesMapping.put(TranTech.RANK, skillRates[4]);
		skillRatesMapping.put(TranTech.COURAGE, skillRates[5]);
		skillRatesMapping.put(TranTech.FIREPOWER, skillRates[6]);
		skillRatesMapping.put(TranTech.SKILL, skillRates[7]);
		
		return skillRatesMapping;

	}
}
