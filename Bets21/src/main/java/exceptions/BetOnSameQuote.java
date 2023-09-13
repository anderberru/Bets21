package exceptions;

public class BetOnSameQuote extends Exception {
	 private static final long serialVersionUID = 1L;
	
	public BetOnSameQuote()
	  {
	    super();
	  }
	  /**This exception is triggered if the user tries to bet on an event where he has already made a bet
	  *@param s String of the exception
	  */
	  public BetOnSameQuote(String s)
	  {
	    super(s);
	  }
}
