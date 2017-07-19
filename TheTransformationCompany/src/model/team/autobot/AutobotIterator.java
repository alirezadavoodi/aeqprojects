package model.team.autobot;

import java.util.Iterator;
import java.util.List;
import model.TransformerFactory.Autobot;

/***
 * Iterator for the Autobot Team
 * @author Alireza
 *
 */
public class AutobotIterator implements Iterator<Autobot>{

	private List<Autobot> autobots;
	int counter = 0;

	
	public AutobotIterator(List<Autobot> autobots)
	{
		this.autobots = autobots;
		this.counter = 0;
	}
	@Override
	public boolean hasNext() {
		return this.autobots.size()>counter;
	}

	@Override
	public Autobot next() {

		if(hasNext())
		{
			Autobot autobot =  this.autobots.get(this.counter);
			this.counter++;					
			return autobot;	
		}
		return null;
	}

}

