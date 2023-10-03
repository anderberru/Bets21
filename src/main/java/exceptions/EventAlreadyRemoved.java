package exceptions;
public class EventAlreadyRemoved extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventAlreadyRemoved()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventAlreadyRemoved(String s)
  {
    super(s);
  }
}