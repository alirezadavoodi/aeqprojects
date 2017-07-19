package aequilibrium;

@SuppressWarnings("serial")
/**
 * An exception for when a team of transformers has no member
 * @author Alireza
 *
 */
public class TransformerTeamInvalidNumberException extends Exception{

	public TransformerTeamInvalidNumberException(String message)
	  {
	    super(message);
	  }
}
