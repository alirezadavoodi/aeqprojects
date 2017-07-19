package game;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;

/**
 * Rule1: If any fighter is down 4 or more points of courage and 3 or more points of strength
	compared to their opponent, the opponent automatically wins the face-off regardless of
	overall rating (opponent has ran away) 
 * @author Alireza
 * 
 */
public class FightRule1 implements FightRules<Autobot,Deception>{

	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(Autobot a, Deception d, GameStatus gameStatus) {
		
		if((a.getCourage()-d.getCourage()>=4) && (a.getStrength()-d.getStrength()>=3))
		{
			gameStatus.updateGameStatusWithDeceptionLoser(d);
			
			gameStatus.addAutobotSurvivor(a);
			return true;
		}		
		else if((d.getCourage()-a.getCourage()>=4) && (d.getStrength()-a.getStrength()>=3))
		{
			gameStatus.updateGameStatusWithAutobotLoser(a);			
			gameStatus.addDeceptionSurvivor(d);
			return true;
		}
		else
		{
			return false;
		}
	}
}