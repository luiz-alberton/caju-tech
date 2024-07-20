package tech.caju.authorizer.transaction;

import java.math.BigDecimal;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record TransactionDto (
  String account,
  BigDecimal totalAmount,
  String mcc,
  String merchant
){}
