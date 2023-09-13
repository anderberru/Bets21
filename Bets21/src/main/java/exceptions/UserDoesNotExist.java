package exceptions;
public class UserDoesNotExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public UserDoesNotExist()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public UserDoesNotExist(String s)
  {
    super(s);
  }
}
