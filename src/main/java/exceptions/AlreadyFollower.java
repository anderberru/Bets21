package exceptions;
public class AlreadyFollower extends Exception {
 private static final long serialVersionUID = 1L;
 
 public AlreadyFollower()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public AlreadyFollower(String s)
  {
    super(s);
  }
}
