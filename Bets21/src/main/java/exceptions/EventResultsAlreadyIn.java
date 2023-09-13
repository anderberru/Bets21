package exceptions;
public class EventResultsAlreadyIn extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventResultsAlreadyIn()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventResultsAlreadyIn(String s)
  {
    super(s);
  }
}