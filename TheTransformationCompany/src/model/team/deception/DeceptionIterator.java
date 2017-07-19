package model.team.deception;

import java.util.Iterator;
import java.util.List;

import model.TransformerFactory.Deception;

/**
 * Iterator for the Autobot Team
 * @author Alireza
 *
 */
public class DeceptionIterator implements Iterator<Deception>{

	private List<Deception> deceptions;
	int counter = 0;
	
	public DeceptionIterator(List<Deception> deceptions)
	{
		this.deceptions = deceptions;
		this.counter = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.deceptions.size()>counter;
	}

	@Override
	public Deception next() {

		if(hasNext())
		{
			Deception deception =  this.deceptions.get(this.counter);
			this.counter++;					
			return deception;	
		}
		return null;
	}
}

