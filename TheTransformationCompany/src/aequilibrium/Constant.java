package aequilibrium;

public class Constant {

	public static final String OptimusPrime = "Optimus Prime";
	public static final String Predaking = "Predaking";
	
	
	public enum WinnerTeam
	{
		DeceptionsTeam ("Deception"),
		AutobotTeam("Autobot"),
		NONE("None");
		
		final String title;
		
		WinnerTeam(String title)
		{
			this.title = title;
		}
		
		public String title()
		{
			return title;
		}
	}
	
	public enum GameRules
	{
		RULE1,
		RULE2,
		RULE3,
		RULE4,
		SPECIAL_RULE1,
		SPECIAL_RULE2,
		NONE
	}
}
