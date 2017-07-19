package model.team.deception;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import model.team.TransformerTeam;

/**
 * Represents the Autobot team
 * @author Alireza
 *
 */
public class DeceptionTeam extends TransformerTeam<Deception>{

	private List<Deception> deceptions;
	
	public DeceptionTeam(List<Deception> deceptions)
	{
		this.deceptions = deceptions;
	}
	
	@Override
	public Iterator<Deception> iterator() {
		// TODO Auto-generated method stub
		return new DeceptionIterator(deceptions);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sortRank()
	{
		Collections.sort(deceptions);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size()
	{
		return this.deceptions.size();
	}

	/**
	 * {@inheritDoc}
	 * @param i
	 * @return
	 */
	public Deception getAtIndex(int i)
	{
		if(i<deceptions.size())
		{
			return deceptions.get(i);
		}
		
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * @param deception
	 */
	public void eliminateFromDeceptionTeam(Deception deception)
	{
		if(deceptions!=null){
			
			int index = deceptions.indexOf(deception);
			if(index!=-1)
			{
				deceptions.get(index).setEliminated(true);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminateAllCompetitors()
	{
		for(Deception d : this)
		{
			if(d.isHasCompetitor())
			{
				d.setEliminated(true);
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
		
		for(Deception d : this)
		{
			counter = d.isEliminated()?  counter+1 : counter;			
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
		
		for(Deception d : this)
		{
			if(!(d.isEliminated()) && d.isHasCompetitor())
			{
				s = s+ d.getName()+", ";
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
		for(Deception d : this)
		{
			if(d.isEliminated())
			{
				s = s+ d.getName()+" ";
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
		String s = "\n====Team DECEPTION====\n";
		for(Deception d: this)
		{
			s += d.toString();
		}
		
		return s;
	}
	
}
