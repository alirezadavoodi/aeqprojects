package castel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A class implementing the functional interface Function.
 * The class implements the apply method which define the algorithms for building castles
 * @author Alireza
 *
 */
public class CastlesBuilderFunction implements Function<List<Integer>,Integer>{

	/*each area of the land is given two segment relation which represent its 
	relative height with respect to its two neighbours. In case the area is
	at either end, it will be defined with a Border tag.
	*/
	enum SegmentRelation
	{
		UPPER,
		LOWER,
		LEVEL,
		BORDER,
		UNKNOWN
	};
	
	/**
	 * inner class to represent the the relationship between an area and its two right and left neighbours
	 * @author Alireza
	 *
	 */
	class LandSegmentRelation
	{
		SegmentRelation leftSide;
		SegmentRelation rightSide;
		
		LandSegmentRelation(SegmentRelation leftSide, SegmentRelation rightSide)
		{
			this.leftSide = leftSide;
			this.rightSide = rightSide;
		}
	}
	
	/**
	 * Defines the algorithm.
	 * Step1: Getting idea from Doubled Linked List, an object of LandSegmentRelation
	 * is build for every single height item. For instance for the input [1,4,3],
	 * a list of LandSegmentRelation is built as follow
	 * for 1: [Border, Upper]: the right neighbour of 1 is 4 which is bigger than 1
	 * for 4: [Lower, Lower]: the left neighbour of 4 is 1 and right neighbour of 4 is 3, and therefor it is both Lower and Lower  
	 * for 3: [Upper, Border]: the left  neighbour of 3 is 4 which is bigger, therefore the Upper is used.
	 * Step2: The result of step1 is a lits of LandSegmentRelation objects. In step2
	 * The list is converted into a sequence of characters. For instance for the above
	 * list the sequence will be BULLUB
	 * Step3: Used the sequence and applied Pattern Matching defined in the Predicate interface
	 * defined a local anonymous class. Look at the checkPattern function below.
	 * 
	 */
	@Override
	public Integer apply(List<Integer> hights) {
		
		
		//STEP 1: Building the list of items.
		List<LandSegmentRelation> segmentRelations = null;
		if(hights==null)
		{
			segmentRelations = null;
		}
		else if(hights.size()==0)
		{
			segmentRelations = null;			
		}
		else if(hights.size()==1)
		{
			//Lazy instantiation
			segmentRelations = (segmentRelations==null) ? segmentRelations=new ArrayList<>() : segmentRelations;			
			LandSegmentRelation segment = new LandSegmentRelation(SegmentRelation.BORDER,SegmentRelation.BORDER);			
			segmentRelations.add(segment);			
		}
		else if(hights.size()==2)
		{
			//Lazy instantiation
			segmentRelations = (segmentRelations==null) ? segmentRelations=new ArrayList<>() : segmentRelations;			
			LandSegmentRelation leftSegment = null;
			LandSegmentRelation rightSegment = null;
			
			if(hights.get(0)<hights.get(1))
			{
				leftSegment = new LandSegmentRelation(SegmentRelation.BORDER,SegmentRelation.UPPER);
				rightSegment = new LandSegmentRelation(SegmentRelation.LOWER,SegmentRelation.BORDER);
			}
			else if(hights.get(0)>hights.get(1))
			{
				leftSegment = new LandSegmentRelation(SegmentRelation.BORDER,SegmentRelation.LOWER);
				rightSegment = new LandSegmentRelation(SegmentRelation.UPPER,SegmentRelation.BORDER);
			}
			else
			{
				leftSegment = new LandSegmentRelation(SegmentRelation.BORDER,SegmentRelation.LEVEL);
				rightSegment = new LandSegmentRelation(SegmentRelation.LEVEL,SegmentRelation.BORDER);
			}
			segmentRelations.add(leftSegment);
			segmentRelations.add(rightSegment);			
		}
		else
		{
			//Lazy instantiation
			segmentRelations = (segmentRelations==null) ? segmentRelations=new ArrayList<>() : segmentRelations;			
			
			//initialized the list
			for(int i=0; i<hights.size(); i++)
			{
				LandSegmentRelation initialSegment = new LandSegmentRelation(SegmentRelation.UNKNOWN,SegmentRelation.UNKNOWN);
				segmentRelations.add(initialSegment);
			}
			
			//updating the first element
			segmentRelations.get(0).leftSide = SegmentRelation.BORDER;
			
			for(int i=1; i<hights.size()-1; i++)
			{
				int before = hights.get(i-1);
				int current = hights.get(i);
				int after = hights.get(i+1);
				
				SegmentRelation beforeRight = SegmentRelation.UNKNOWN;
				SegmentRelation currentLeft = SegmentRelation.UNKNOWN;
				SegmentRelation currentRight = SegmentRelation.UNKNOWN;
				SegmentRelation nextLeft = SegmentRelation.UNKNOWN;
				SegmentRelation nextRight = SegmentRelation.UNKNOWN;
				
				if(before<current && current>after) //i.e. 4-5-3
				{
					beforeRight = SegmentRelation.UPPER;
					currentLeft = SegmentRelation.LOWER;
					currentRight = SegmentRelation.LOWER;
					nextLeft = SegmentRelation.UPPER;
					nextRight = SegmentRelation.UNKNOWN;
				}
				else if(before>current && current<after) //i.e. 6-5-7
				{					
					beforeRight = SegmentRelation.LOWER;
					currentLeft = SegmentRelation.UPPER;
					currentRight = SegmentRelation.UPPER;
					nextLeft = SegmentRelation.LOWER;
					nextRight = SegmentRelation.UNKNOWN;					
				}
				else if(before>current && current>after) //i.e. 6-5-4
				{
					beforeRight = SegmentRelation.LOWER;
					currentLeft = SegmentRelation.UPPER;
					currentRight = SegmentRelation.LOWER;
					nextLeft = SegmentRelation.UPPER;
					nextRight = SegmentRelation.UNKNOWN;	
				}
				else if(before<current && current<after) //i.e. 4-5-6
				{
					beforeRight = SegmentRelation.UPPER;
					currentLeft = SegmentRelation.LOWER;
					currentRight = SegmentRelation.UPPER;
					nextLeft = SegmentRelation.LOWER;
					nextRight = SegmentRelation.UNKNOWN;	
				}
				else if(before==current && current<after) //i.e. 5-5-6
				{
					beforeRight = SegmentRelation.LEVEL;
					currentLeft = SegmentRelation.LEVEL;
					currentRight = SegmentRelation.UPPER;
					nextLeft = SegmentRelation.LOWER;
					nextRight = SegmentRelation.UNKNOWN;	
				}
				else if(before==current && current>after) //i.e. 5-5-4
				{
					beforeRight = SegmentRelation.LEVEL;
					currentLeft = SegmentRelation.LEVEL;
					currentRight = SegmentRelation.LOWER;
					nextLeft = SegmentRelation.UPPER;
					nextRight = SegmentRelation.UNKNOWN;	
				}
				else if(before>current && current==after) // i.e 6-5-5
				{
					beforeRight = SegmentRelation.LOWER;
					currentLeft = SegmentRelation.UPPER;
					currentRight = SegmentRelation.LEVEL;
					nextLeft = SegmentRelation.LEVEL;
					nextRight = SegmentRelation.UNKNOWN;	
				}
				else if(before<current && current==after) //i.e. 4-5-5
				{
					beforeRight = SegmentRelation.UPPER;
					currentLeft = SegmentRelation.LOWER;
					currentRight = SegmentRelation.LEVEL;
					nextLeft = SegmentRelation.LEVEL;
					nextRight = SegmentRelation.UNKNOWN;
				}
				else if(before==current && current==after) //i.e. 5-5-5
				{
					beforeRight = SegmentRelation.LEVEL;
					currentLeft = SegmentRelation.LEVEL;
					currentRight = SegmentRelation.LEVEL;
					nextLeft = SegmentRelation.LEVEL;
					nextRight = SegmentRelation.UNKNOWN;
				}
				
				segmentRelations.get(i-1).rightSide=beforeRight;
				segmentRelations.get(i).leftSide = currentLeft;
				segmentRelations.get(i).rightSide = currentRight;				
				segmentRelations.get(i+1).leftSide = nextLeft;
				segmentRelations.get(i+1).rightSide = nextRight;
				
			}//for
			
			//updating the last element
			segmentRelations.get(segmentRelations.size()-1).rightSide = SegmentRelation.BORDER;
		}//else
		
		//STEP 2: Converting the CodesList to a sequence		
		ArrayList<String> convertCodesListToSequence = convertCodesListToASequence(segmentRelations);
		
		if(convertCodesListToSequence==null) return 0;
		
		//STEP 3: Pattern Matching using the generated sequence		
		int totalNumberOfCastles = 0;
		for(String sequence : convertCodesListToSequence)
		{
			int numberOfCastles =  castleMatching(sequence);
			totalNumberOfCastles+=numberOfCastles;
		}		
		return totalNumberOfCastles;
		
	}
	
	/***
	 * Convert a list of segment relations to a list of string
	 * @param segmentRelations
	 * @return
	 */
	private ArrayList<String> convertCodesListToASequence(List<LandSegmentRelation> segmentRelations)
	{
		
		if(segmentRelations==null) return null;

		
		ArrayList<String> list = new ArrayList<>();
		
		String sequence = "";		
		for(LandSegmentRelation item :segmentRelations)
		{
			if(sequence.length()<Integer.MAX_VALUE)
			{
				sequence = sequence + mapCodeToChar(item.leftSide)+mapCodeToChar(item.rightSide);
			}
			else
			{
				list.add(sequence);	
				sequence = "";
			}
		}
		
		list.add(sequence);
		return list;
	}
	
	/**
	 * A mapping between SegmentRelation and a predefined character
	 * @param code
	 * @return
	 */
	private char mapCodeToChar(SegmentRelation code)
	{
		if(code==SegmentRelation.BORDER)
		{
			return 'B';
		}
		else if(code==SegmentRelation.LOWER)
		{
			return 'L';
		}
		else if(code==SegmentRelation.UPPER)
		{
			return 'U';
		}
		else if(code==SegmentRelation.LEVEL)
		{
			return 'X';
		}
		else //UNKNOWN
		{
			return 'K';
		}
	}
	
	
	//the sequence length is at least 2
	/**
	 * Uses checkPattern method to find all mateches (areas a castle can be built)
	 * @param landSequence
	 * @return number of possible castles
	 */
	private int castleMatching(String landSequence)
	{
		int head1 = 0;
		int head2 = 1;
		
		int count = 0;
		while(head2<landSequence.length())
		{
			char left = landSequence.charAt(head1);
			char right = landSequence.charAt(head2);
			
			while(right=='X')
			{
				head2++;
				right = landSequence.charAt(head2);
			}
			right = landSequence.charAt(head2);
			
			
			boolean res = checkPattern(left, right);
			
			if(res){
				count++;
			}
			
			head1 = head2;
			head2++;
		}
		
		return count;
	}
	
	/**
	 * Implements the CastlePatternPredicate's test method anonymously to find the matches
	 * @param left
	 * @param right
	 * @return true if a castle can be built in an area with specific neighbours
	 */
	private static boolean checkPattern(char left, char right)
	{
		String s = new StringBuilder().append(left).append(right).toString();
		
		//peak patterns
		final String PP1 = "LL";
		final String PP2 = "BL";
		final String PP3 = "LB";
		
		//implementing the CastlePatternPredicate's test method anonymously.
		//test for peak patterns
		CastlePatternPredicate peakPatternPredicate = new CastlePatternPredicate()
		{
			@Override
			public boolean test(String s) {
				
				if(s.equals(PP1) || s.equals(PP2) || s.equals(PP3)) return true;
					
				return false;
			}
		};
		
		//the patterns for vallyes
		final String P_V = "BB";
		final String VP1 = "BU";
		final String VP2 = "UB";
		final String VP3 = "UU";
		
		//implementing the CastlePatternPredicate's test method anonymously.
		//test for valley patterns
		CastlePatternPredicate valleyPatternPredicate = new CastlePatternPredicate()
		{
			@Override
			public boolean test(String s) {
				
				if(s.equals(P_V) || s.equals(VP1) || s.equals(VP2) || s.equals(VP3)) return true;
				
				return false;
			}
		};
		
		return (peakPatternPredicate.test(s) || valleyPatternPredicate.test(s));
		
	}
	
}
