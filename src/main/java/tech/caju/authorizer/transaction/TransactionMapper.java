package tech.caju.authorizer.transaction;


import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.caju.authorizer.balance.Amount;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

  Transaction toTransaction(TransactionDto dto);

  default Amount map(BigDecimal totalAmount) {
    return new Amount(totalAmount);
  }

}
