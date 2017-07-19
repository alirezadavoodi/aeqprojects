package game;

import java.util.List;

import aequilibrium.Constant;
import model.TransformerFactory.Autobot;
import model.TransformerFactory.Deception;

/***
 *A model for a battle between a autobot and a deception 
 * @author Alireza
 *
 */
public class Battle {

	private Autobot autobot;
	private Deception deception;
	
	private Constant.GameRules applicableRule;
	
	public Battle(Autobot autobot, Deception deception)
	{
		this.autobot = autobot;
		this.deception = deception;
		
		this.autobot.setHasCompetitor(true);
		this.deception.setHasCompetitor(true);
	}
	
	public static final int RULE1 = 0;
	public static final int RULE2 = 1;
	public static final int RULE3 = 2;
	public static final int RULE4 = 3;
	public static final int SPECIAL_RULE1 = 4;
	public static final int SPECIAL_RULE2 = 5;
	
	/***
	 * Apply the game rules on the fight between an autobot and deception and updates the game status
	 * @param rules
	 * @param gameStatus
	 * @return The rule applied to determine the winner
	 */
	public Constant.GameRules applyRules(List<FightRules<Autobot,Deception>> rules, GameStatus gameStatus)
	{	
		Constant.GameRules applicableRule = Constant.GameRules.NONE;
		
		//applies the game rules based on their priorities. For instance the 
		//special rule2 has the top power. If not applicable, then other rules 
		//will be applied in order.
		
		FightRules<Autobot,Deception> specialRule2 = rules.get(SPECIAL_RULE2);		
		boolean applicable = specialRule2.apply(this.autobot, this.deception,gameStatus);
		
		if(!applicable)//first if
		{
			FightRules<Autobot,Deception> specialRule1 = rules.get(SPECIAL_RULE1);
			applicable = specialRule1.apply(this.autobot, this.deception, gameStatus);
			
			if(!applicable)//second if
			{
				FightRules<Autobot,Deception> rule1 = rules.get(RULE1);
				applicable = rule1.apply(this.autobot, this.deception, gameStatus);
				
				if(!applicable)//third if
				{
					FightRules<Autobot,Deception> rule2 = rules.get(RULE2);
					applicable = rule2.apply(this.autobot, this.deception, gameStatus);
					
					if(!applicable)//forth if
					{
						FightRules<Autobot,Deception> rule3 = rules.get(RULE3);
						applicable = rule3.apply(this.autobot, this.deception, gameStatus);
						
						if(!applicable)//fifth if
						{
							FightRules<Autobot,Deception> rule4 = rules.get(RULE4);
							applicable = rule4.apply(this.autobot, this.deception, gameStatus);
							if(applicable)
							{
								applicableRule = Constant.GameRules.RULE4;
							}
							else
							{
								applicableRule = Constant.GameRules.NONE;
							}
						}//fifth if
						else
						{
							applicableRule = Constant.GameRules.RULE3;
						}
					}//forth if
					else
					{
						applicableRule = Constant.GameRules.RULE2;
					}
				}//third if
				else
				{
					applicableRule = Constant.GameRules.RULE1;
				}
			}//second if
			else
			{
				applicableRule = Constant.GameRules.SPECIAL_RULE1;
			}
		}//first if
		else
		{
			applicableRule = Constant.GameRules.SPECIAL_RULE2;
		}
		
		this.applicableRule = applicableRule;
		return applicableRule;
	}
	
	@Override
	public String toString()
	{
		String s = "Battle:\n";
		s = s + this.autobot.toString();
		s = s + this.deception.toString();
		s = s + "Winning Rule:"+this.applicableRule;
		return s;
	}
}
