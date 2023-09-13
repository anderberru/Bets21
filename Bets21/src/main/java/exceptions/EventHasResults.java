package exceptions;
public class EventHasResults extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventHasResults()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventHasResults(String s)
  {
    super(s);
  }
}