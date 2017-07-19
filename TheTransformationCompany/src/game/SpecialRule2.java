package game;

import aequilibrium.Constant;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;

/**
 * Specifla Rule2: If any fighter is down 4 or more points of courage and 3 or more points of strength
	compared to their opponent, the opponent automatically wins the face-off regardless of
	overall rating (opponent has ran away) 
 * @author Alireza
 * 
 */
public class SpecialRule2 implements  FightRules<Autobot,Deception>{

	@Override
	public boolean apply(Autobot a, Deception d, GameStatus gameStatus) {
		
		if((a.getName().equals(Constant.OptimusPrime) || a.getName().equals(Constant.Predaking)) &&
				(d.getName().equals(Constant.OptimusPrime) || d.getName().equals(Constant.Predaking)))
		{
			gameStatus.updateGameStatusWithDestroyingAllTransformers();
			return true;
		}
		else
		{
			return false;
		}
	}

}
