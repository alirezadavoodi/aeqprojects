package game;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;

/**
 * Rule4: If any fighter is down 4 or more points of courage and 3 or more points of strength
	compared to their opponent, the opponent automatically wins the face-off regardless of
	overall rating (opponent has ran away) 
 * @author Alireza
 * 
 */

public class FightRule4 implements FightRules<Autobot,Deception>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(Autobot a, Deception d, GameStatus gameStatus) {
		
		if(a.overalRating()==d.overalRating())
		{
			gameStatus.updateGameStatusWithAutobotLoser(a);
			gameStatus.updateGameStatusWithDeceptionLoser(d);
			
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
