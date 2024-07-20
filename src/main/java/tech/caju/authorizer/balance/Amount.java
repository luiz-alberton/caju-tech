package tech.caju.authorizer.balance;

import java.math.BigDecimal;

public class Amount {

  private static final int SCALE = 2;

  private BigDecimal value;

  public Amount(String newAmount) {
    value = new BigDecimal(newAmount);
  }

  public Amount(Long newAmount) {
    value = BigDecimal.valueOf(newAmount).divide(BigDecimal.valueOf(Math.pow(10, SCALE)));
  }

  public Long toLong() {
    return value.multiply(BigDecimal.valueOf(Math.pow(10, SCALE))).longValue();
  }

  public BigDecimal getValue() {
    return value;
  }

  public void subtract(String value) {
    var newAmount = new Amount(value);
    this.value = this.value.subtract(newAmount.getValue());
  }

  public void sum(String value) {
    var newAmount = new Amount(value);
    this.value = this.value.add(newAmount.getValue());
  }

  public void subtract(Long value) {
    var newAmount = new Amount(value);
    this.value = this.value.subtract(newAmount.getValue());
  }

  public void sum(Long value) {
    var newAmount = new Amount(value);
    this.value = this.value.add(newAmount.getValue());
  }

  public void sum(BigDecimal value) {
    this.value = this.value.add(value);
  }

  public void subtract(BigDecimal value) {
    this.value = this.value.subtract(value);
  }


}
