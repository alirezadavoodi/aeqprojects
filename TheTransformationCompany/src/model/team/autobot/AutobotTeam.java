package model.team.autobot;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.TransformerFactory.Autobot;
import model.team.TransformerTeam;

/**
 * Represents the Autobot team
 * @author Alireza
 *
 */
public class AutobotTeam extends TransformerTeam<Autobot>{

	private List<Autobot> autobots;
	
	public AutobotTeam(List<Autobot> autobots)
	{
		this.autobots = autobots;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Autobot> iterator() {
		return new AutobotIterator(this.autobots);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sortRank()
	{
		Collections.sort(autobots);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size()
	{
		return this.autobots.size();
	}
	
	/**
	 * {@inheritDoc}
	 * @param i
	 * @return
	 */
	public Autobot getAtIndex(int i)
	{
		if(i<autobots.size())
		{
			return autobots.get(i);
		}
		
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @param autobot
	 */
	public void eliminateFromAutobotTeam(Autobot autobot)
	{
		if(autobots!=null){
			
			int index = autobots.indexOf(autobot);
			if(index!=-1)
			{
				autobots.get(index).setEliminated(true);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminateAllCompetitors()
	{
		for(Autobot a : this)
		{
			if(a.isHasCompetitor())
			{
				a.setEliminated(true);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfEliminated()
	{
		int counter = 0;
		
		for(Autobot a : this)
		{
			counter = a.isEliminated()?  counter+1 : counter;			
		}
		return counter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printSurvivorNames()
	{
		String s = "";
		
		for(Autobot a : this)
		{
			if(!(a.isEliminated()) && a.isHasCompetitor())
			{
				s = s+ a.getName()+", ";
			}
		}
		
		if(s.length()==0)
		{
			System.out.println("None!");
		}
		else
		{
			System.out.println(s);
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printEliminatedNames()
	{
		String s = "";
		for(Autobot a : this)
		{
			if(a.isEliminated())
			{
				s = s+ a.getName()+" ";
			}
		}
		
		System.out.println(s);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		String s = "\n====Team AUTOBOT====\n";
		for(Autobot a: this)
		{
			s += a.toString();
		}
		
		return s;
	}
}
