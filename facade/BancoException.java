package facade;

public class BancoException extends Exception {

  public BancoException(String message) {
    super(message);
  }

  public BancoException(String message, Throwable e) {
	  super(message, e);
  }
  
}