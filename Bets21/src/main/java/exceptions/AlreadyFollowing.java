package exceptions;
public class AlreadyFollowing extends Exception {
 private static final long serialVersionUID = 1L;
 
 public AlreadyFollowing()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public AlreadyFollowing(String s)
  {
    super(s);
  }
}
