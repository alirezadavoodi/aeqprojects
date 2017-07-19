package model;

import java.util.Map;

/***
 * A class to model to represent the Autobot and Deception models designed based on the restrictions defined in the project's description
 * @author Alireza
 *
 */
public final class TransformerFactory {

	public enum TransformerType
	{
		AUTOBOT,
		DECEPTION
	}
	
	/***
	 * A abstract class to act as the super class of Autobot and Deception. It implements the Comparable pattern to help with sorting the transformers based on their rank
	 * @author Alireza
	 *
	 */
	private static abstract class Transformer implements Comparable<Transformer>
	{

		protected int strength;
		protected int intelligence;
		protected int speed;		
		protected int endurance;
		protected int rank;
		protected int courage;
		protected int firepower;
		protected int skill;		
		protected String name;
		protected TransformerType type;
		protected boolean eliminated;
		
		protected boolean hasCompetitor;
		
		public int getStrength() {
			return strength;
		}

		public void setStrength(int strength) {
			this.strength = strength;
		}

		public int getIntelligence() {
			return intelligence;
		}

		public void setIntelligence(int intelligence) {
			this.intelligence = intelligence;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}

		public int getEndurance() {
			return endurance;
		}

		public void setEndurance(int endurance) {
			this.endurance = endurance;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getCourage() {
			return courage;
		}

		public void setCourage(int courage) {
			this.courage = courage;
		}

		public int getFirepower() {
			return firepower;
		}

		public void setFirepower(int firepower) {
			this.firepower = firepower;
		}

		public int getSkill() {
			return skill;
		}

		public void setSkill(int skill) {
			this.skill = skill;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public TransformerType getType() {
			return type;
		}

		public void setType(TransformerType type) {
			this.type = type;
		}
		
		public boolean isEliminated() {
			return eliminated;
		}

		public void setEliminated(boolean eliminated) {
			this.eliminated = eliminated;
		}
		
		public boolean isHasCompetitor() {
			return hasCompetitor;
		}

		public void setHasCompetitor(boolean hasCompetitor) {
			this.hasCompetitor = hasCompetitor;
		}

		public int compareTo(Transformer t1)
		{
			 int t1Rank = t1.rank;
			 int t2Rank = this.rank;

			 if(t1Rank>t2Rank)
			 {
				 return 1;
			 }
			 else if(t1Rank<t2Rank)
			 {
				 return -1;
			 }
			 else
			 {
				 return 0;
			 }
		}

		/***
		 * Calculates the overal rating of a transformer
		 * @return
		 */
		public int overalRating()
		{
			return strength+intelligence+speed+endurance+firepower;
		}
		
		@Override
		public String toString()
		{
			String s = "";
			
			s = s+name;
			s = s+" ";
			
			if(type==TransformerType.DECEPTION)
				s = s+"D";
			else if(type==TransformerType.AUTOBOT)
				s = s+"A";
			
			s = s+"[";
			
			s = s+strength+",";
			s = s+intelligence+",";
			s = s+speed+",";
			s = s+endurance+",";
			s = s+rank+",";
			s = s+courage+",";
			s = s+firepower+",";
			s = s+skill;
			
			s = s+"]";
			s = s+" ,Rate[:"+overalRating()+"]";
			s = s+" ,Rank[:"+rank+"]";
			s = s+" ,Competitor:"+hasCompetitor+" ";
			s = s+" ,Eliminated:"+eliminated+"\n";
			
			
			return s;
		}
	}
	
	/***
	 * A mode to represent an Autobot. A subclass of Transformer.
	 * @author Alireza
	 *
	 */
	public static class Autobot extends Transformer
	{
		public int overalRating()
		{
			return super.overalRating();
		}
		
	}
	

	/***
	 * A mode to represent a Deception. A subclass of Transformer.
	 * @author Alireza
	 *
	 */
	public static class Deception extends Transformer
	{
		public int overalRating()
		{
			return super.overalRating();
		}
	}
	
	/***
	 * A builder class for Transformer. It uses the Builder design pattern
	 * @author Alireza
	 *
	 */
	private static abstract class TransformerBuilder
	{
		protected Transformer transformer;
		
		protected abstract Transformer createTransformer();
		
		final protected void buildStrength(int strength)
		{
			if(strength>=1 && strength<=10){
				transformer.strength = strength;
			}
		}
		
		final protected void buildIntelligence(final int intelligence)
		{
			if(intelligence>=1 && intelligence<=10){
				transformer.intelligence = intelligence;
			}
		}
		
		final protected void buildSpeed(int speed)
		{
			if(speed>=1 && speed<=10){
				transformer.speed = speed;
			}
		}

		
		final protected void buildEndurance(int endurance)
		{
			if(endurance>=1 && endurance<=10){
				transformer.endurance = endurance;
			}
		}
		
		final protected void buildRank(int rank)
		{
			if(rank>=1 && rank<=10){
				transformer.rank = rank;
			}
		}
		
		final protected void buildCourage(int courage)
		{
			if(courage>=1 && courage<=10){
				transformer.courage = courage;
			}
		}
		
		final protected void buildFirepower(int firepower)
		{
			if(firepower>=1 && firepower<=10){
				transformer.firepower = firepower;
			}
		}
		
		final void buildSkill(int skill)
		{
			if(skill>=1 && skill<=10){
				transformer.skill = skill;
			}			
		}
		
		final void buildName(String name)
		{
			transformer.name = name;
		}
		
		final void buildEliminated(boolean eliminated)
		{
			transformer.eliminated = eliminated;
		}
		
		
		final void buildHasCompetitor(boolean hasCompetitor)
		{
			transformer.hasCompetitor = hasCompetitor;
		}
		
		abstract void buildTransformerType(TransformerType type);

		
	}
	
	/***
	 * The builder class for an Autobot. It uses the Builder design pattern
	 * @author Alireza
	 *
	 */
	private static class AutobotBuilder extends TransformerBuilder
	{
		//public AutobotBuilder(){}
		@Override
		public Autobot createTransformer() {
			// TODO Auto-generated method stub
			transformer = new Autobot();
			return (Autobot) transformer;
		}
		
		@Override
		void buildTransformerType(TransformerType type)
		{
			transformer.type = TransformerType.AUTOBOT;
		}
	}
	
	/***
	 * The builder class for a Deception. It uses the Builder design pattern
	 * @author Alireza
	 *
	 */
	private static class DeceptionBuilder extends TransformerBuilder
	{
		
		//protected DeceptionBuilder(){}
		@Override
		public Deception createTransformer() {
			// TODO Auto-generated method stubs
			transformer = new Deception();
			return (Deception) transformer;
		}
		
		@Override
		void buildTransformerType(TransformerType type)
		{
			transformer.type = TransformerType.DECEPTION;
		}
	}
	
	/**
	 * The public API of the top class to create autobot. It uses the builder to create one Autobot
	 * @param name
	 * @param techTable
	 * @return an Autobot object
	 */
	public static Autobot createAutobot(String name, Map<TranTech, Integer> techTable)
	{
		AutobotBuilder ab = new AutobotBuilder();
		ab.createTransformer();
		
		
		if(techTable.get(TranTech.STRENGTH)!=null) ab.buildStrength((Integer)(techTable.get(TranTech.STRENGTH)));
		
		if(techTable.get(TranTech.INTELLIGENCE)!=null) ab.buildIntelligence((Integer)(techTable.get(TranTech.INTELLIGENCE)));
		
		if(techTable.get(TranTech.SPEED)!=null) ab.buildSpeed((Integer)(techTable.get(TranTech.SPEED)));
		
		if(techTable.get(TranTech.ENDURANCE)!=null) ab.buildEndurance((Integer)(techTable.get(TranTech.ENDURANCE)));
		
		if(techTable.get(TranTech.RANK)!=null) ab.buildRank((Integer)(techTable.get(TranTech.RANK)));
		
		if(techTable.get(TranTech.COURAGE)!=null) ab.buildCourage((Integer)(techTable.get(TranTech.COURAGE)));
		
		if(techTable.get(TranTech.FIREPOWER)!=null) ab.buildFirepower((Integer)(techTable.get(TranTech.FIREPOWER)));
		
		if(techTable.get(TranTech.SKILL)!=null) ab.buildSkill((Integer)(techTable.get(TranTech.SKILL)));
		
		ab.buildTransformerType(TransformerType.AUTOBOT);
		ab.buildName(name);		
		ab.buildEliminated(false);
		ab.buildHasCompetitor(false);
		
		return (Autobot) ab.transformer;
	}

	/***
	 * The public API of the top class to create a Deception. It uses the builder to create one Deception
	 * @param name
	 * @param techTable
	 * @return a Deception object
	 */
	public static Deception createDeception(String name, Map<TranTech, Integer> techTable)
	{
		DeceptionBuilder db = new DeceptionBuilder();		
		db.createTransformer();

		if(techTable.get(TranTech.STRENGTH)!=null) db.buildStrength((Integer)(techTable.get(TranTech.STRENGTH)));
		
		if(techTable.get(TranTech.INTELLIGENCE)!=null) db.buildIntelligence((Integer)(techTable.get(TranTech.INTELLIGENCE)));
		
		if(techTable.get(TranTech.SPEED)!=null) db.buildSpeed((Integer)(techTable.get(TranTech.SPEED)));
		
		if(techTable.get(TranTech.ENDURANCE)!=null) db.buildEndurance((Integer)(techTable.get(TranTech.ENDURANCE)));
		
		if(techTable.get(TranTech.RANK)!=null) db.buildRank((Integer)(techTable.get(TranTech.RANK)));
		
		if(techTable.get(TranTech.COURAGE)!=null) db.buildCourage((Integer)(techTable.get(TranTech.COURAGE)));
		
		if(techTable.get(TranTech.FIREPOWER)!=null) db.buildFirepower((Integer)(techTable.get(TranTech.FIREPOWER)));
		
		if(techTable.get(TranTech.SKILL)!=null) db.buildSkill((Integer)(techTable.get(TranTech.SKILL)));
		
		db.buildTransformerType(TransformerType.DECEPTION);
		db.buildName(name);
		db.buildEliminated(false);
		db.buildHasCompetitor(false);
		
		return (Deception) db.transformer;
	}
}
