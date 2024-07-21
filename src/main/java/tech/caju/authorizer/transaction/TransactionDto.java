package tech.caju.authorizer.transaction;

import java.math.BigDecimal;

public record TransactionDto (
  Long account,
  BigDecimal totalAmount,
  String mcc,
  String merchant
){}
