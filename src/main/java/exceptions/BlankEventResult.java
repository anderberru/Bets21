package exceptions;
public class BlankEventResult extends Exception {
 private static final long serialVersionUID = 1L;
 
 public BlankEventResult()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public BlankEventResult(String s)
  {
    super(s);
  }
}
