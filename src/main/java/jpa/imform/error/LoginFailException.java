package jpa.imform.error;

public class LoginFailException extends RuntimeException {
  public LoginFailException(String email) {
    super("Login fail - email : " + email);
  }
}
