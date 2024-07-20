package tech.caju.authorizer.balance;

import jakarta.persistence.AttributeConverter;

public class AmountConverter implements AttributeConverter<Amount, Long> {

  @Override
  public Long convertToDatabaseColumn(Amount amount) {
    return amount != null ? amount.toLong() : 0;
  }

  @Override
  public Amount convertToEntityAttribute(Long amount) {
    return amount != null ? new Amount(amount) : new Amount(0L);
  }
}
