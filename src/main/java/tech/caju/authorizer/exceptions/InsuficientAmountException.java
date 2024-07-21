package tech.caju.authorizer.exceptions;

public class InsuficientAmountException extends RuntimeException {

  public String getCode() {
    return "51";
  }
}
