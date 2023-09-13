package exceptions;
public class AgeTooLow extends Exception {
 private static final long serialVersionUID = 1L;
 
 public AgeTooLow()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public AgeTooLow(String s)
  {
    super(s);
  }
}