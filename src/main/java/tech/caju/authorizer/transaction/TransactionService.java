package tech.caju.authorizer.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.caju.authorizer.balance.BalanceService;
import tech.caju.authorizer.exceptions.InsuficientAmountException;
import tech.caju.authorizer.exceptions.NoBalanceFoundException;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final BalanceService balanceService;

  @Transactional
  public void handleTransaction(TransactionDto dto) {
    var accountId = Long.parseLong(dto.account());
    var balance = balanceService.getBalanceFromAccountByMcc(accountId, dto.mcc())
        .or(() -> balanceService.getBalanceCategoryFromMerchant(accountId, dto.merchant()))
        .or(() -> balanceService.getBalanceCashFromAccount(accountId))
        .orElseThrow(NoBalanceFoundException::new);

    balance.getAmount().subtract(dto.totalAmount());

    if (balance.getAmount().toLong() < 0) throw new InsuficientAmountException();

    balanceService.save(balance);
  }
}
