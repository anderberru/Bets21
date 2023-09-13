package exceptions;
public class IncorrectData extends Exception {
 private static final long serialVersionUID = 1L;
 
 public IncorrectData()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public IncorrectData(String s)
  {
    super(s);
  }
}