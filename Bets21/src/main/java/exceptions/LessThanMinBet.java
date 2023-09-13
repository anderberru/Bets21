package exceptions;

public class LessThanMinBet extends Exception {
 private static final long serialVersionUID = 1L;
 
 public LessThanMinBet()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public LessThanMinBet(String s)
  {
    super(s);
  }
}