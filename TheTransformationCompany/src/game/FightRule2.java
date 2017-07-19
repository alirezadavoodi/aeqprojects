package game;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;

/** 
 * Rule2: If one of the fighters is 3 or more points of skill above their opponent, they win
the fight regardless of overall rating
 * @author Alireza
 */
public class FightRule2 implements FightRules<Autobot,Deception>{

	/***
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(Autobot a, Deception d, GameStatus gameStatus) {
		
		if((a.getSkill()-d.getSkill())>=3)
		{
			gameStatus.updateGameStatusWithDeceptionLoser(d);
			gameStatus.addAutobotSurvivor(a);			
			return true;
		}
		else if((d.getSkill()-a.getSkill())>=3)
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
