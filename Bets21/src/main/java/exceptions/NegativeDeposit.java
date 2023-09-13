package exceptions;

public class NegativeDeposit extends Exception {
 private static final long serialVersionUID = 1L;
 
 public NegativeDeposit()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public NegativeDeposit(String s)
  {
    super(s);
  }
}