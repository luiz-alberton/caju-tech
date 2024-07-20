package tech.caju.authorizer;


import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.caju.authorizer.balance.Amount;

public class AmountTest {

  @Test
  void givenStringAmountShouldReturnLong() {
    Amount amount = new Amount("100.00");
    Assertions.assertThat(amount.toLong()).isEqualTo(10000L);
  }

  @Test
  void givenStringAmountShouldSubtract() {
    Amount amount = new Amount("15.87");
    amount.subtract("6.34");
    Assertions.assertThat(amount.toLong()).isEqualTo(953L);
  }

  @Test
  void givenStringAmountShouldSum() {
    Amount amount = new Amount("15.87");
    amount.sum("6.34");
    Assertions.assertThat(amount.toLong()).isEqualTo(2221L);
  }

  @Test
  void givenLongAmountShouldReturnDecimal() {
    Amount amount = new Amount(1587L);
    Assertions.assertThat(amount.getValue()).isEqualTo(BigDecimal.valueOf(15.87));
  }

  @Test
  void givenLongAmountShouldSubtract() {
    Amount amount = new Amount(1587L);
    amount.subtract(634L);
    Assertions.assertThat(amount.toLong()).isEqualTo(953L);
    Assertions.assertThat(amount.getValue()).isEqualTo(BigDecimal.valueOf(9.53));
  }

  @Test
  void givenLongAmountShouldSum() {
    Amount amount = new Amount("15.87");
    amount.sum("6.34");
    Assertions.assertThat(amount.toLong()).isEqualTo(2221L);
    Assertions.assertThat(amount.getValue()).isEqualTo(BigDecimal.valueOf(22.21));
  }

  @Test
  void givenMixedAmountShouldSubtract() {
    Amount amount = new Amount("15.87");
    amount.subtract(634L);
    Assertions.assertThat(amount.toLong()).isEqualTo(953L);
    Assertions.assertThat(amount.getValue()).isEqualTo(BigDecimal.valueOf(9.53));
  }

  @Test
  void givenMixedAmountShouldSum() {
    Amount amount = new Amount("15.87");
    amount.sum(634L);
    Assertions.assertThat(amount.toLong()).isEqualTo(2221L);
    Assertions.assertThat(amount.getValue()).isEqualTo(BigDecimal.valueOf(22.21));
  }


}
