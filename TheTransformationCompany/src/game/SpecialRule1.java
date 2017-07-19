package game;

import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;
import aequilibrium.Constant;

/**
 * Special Rule1: Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of
any other criteria 
 * @author Alireza
 * 
 */
public class SpecialRule1 implements FightRules<Autobot,Deception>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(Autobot a, Deception d, GameStatus gameStatus) {
		
		if(a.getName().equals(Constant.OptimusPrime) && 
				!(d.getName().equals(Constant.OptimusPrime)) || (d.getName().equals(Constant.Predaking)))
		{
			
			gameStatus.updateGameStatusWithDeceptionLoser(d);			
			gameStatus.addAutobotSurvivor(a);
			
			return true;
		}
		else if(a.getName().equals(Constant.Predaking) && 
				!(d.getName().equals(Constant.OptimusPrime)) || (d.getName().equals(Constant.Predaking)))
		{
			gameStatus.updateGameStatusWithDeceptionLoser(d);
			gameStatus.addAutobotSurvivor(a);
						
			return true;
		}
		else if(d.getName().equals(Constant.OptimusPrime) && 
				!(a.getName().equals(Constant.OptimusPrime)) || (a.getName().equals(Constant.Predaking)))
		{
			gameStatus.updateGameStatusWithAutobotLoser(a);
			gameStatus.addDeceptionSurvivor(d);
			return true;
		}
		else if(d.getName().equals(Constant.Predaking) && 
				!(a.getName().equals(Constant.OptimusPrime)) || (a.getName().equals(Constant.Predaking)))
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

